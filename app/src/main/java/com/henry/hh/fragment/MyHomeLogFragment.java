package com.henry.hh.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.henry.hh.R;
import com.henry.hh.constants.Constants;
import com.henry.hh.dialog.ButtonMenuFragment;
import com.henry.hh.interfaces.OnPhotoGetListener;
import com.henry.hh.utils.FileUtils;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.ScreenUtils;

import java.io.File;

import static android.app.Activity.RESULT_CANCELED;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyHomeLogFragment extends BaseFragment
        implements View.OnClickListener, OnPhotoGetListener {

    private ImageView mAvatar;
    private static final int IMAGE_REQUEST_CODE = 0; // 请求码 本地图片
    private static final int CAMERA_REQUEST_CODE = 1; // 拍照
    private static final int RESULT_REQUEST_CODE = 2; // 裁剪
    private static final String SAVE_AVATORNAME = "head.png";// 保存的图片名

    public MyHomeLogFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_my_home);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mAvatar = getViewById(R.id.image_avatar);
        mAvatar.setOnClickListener(this);
    }

    private void takePhoto() {
        Intent intentFromCapture = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                .fromFile(new File(Environment
                        .getExternalStorageDirectory(),
                        SAVE_AVATORNAME)));
        startActivityForResult(intentFromCapture,
                CAMERA_REQUEST_CODE);
    }

    private void selectImg() {
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery,
                IMAGE_REQUEST_CODE);
    }

    @Override
    public void onClick(View v) {
        ButtonMenuFragment buttonMenuFragment = new ButtonMenuFragment();
        buttonMenuFragment.show(getActivity().getFragmentManager(), TAG);
        buttonMenuFragment.setOnPhotoGetListener(this);
    }

    @Override
    public void onPhotoTaken() {
        takePhoto();
    }

    @Override
    public void onPicSelected() {
        selectImg();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    startPhotoZoom(Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), SAVE_AVATORNAME)));
                    break;
                case RESULT_REQUEST_CODE:
                    if (data != null) {
                        getImageToView(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        int width = ScreenUtils.getScreenWidth(getActivity());
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", width);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    private void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            FileUtils.createDir(Constants.PROGREM_FOLDER);
            FileUtils.createDir(Constants.CACHE_FOLDER);
            com.henry.library.utils.FileUtils.bitmapToFile(photo, Constants.PATH_AVATAR);
            mAvatar.setImageBitmap(photo);
        }

    }
}
