package com.henry.hh.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.poisearch.PoiSearch;
import com.henry.hh.R;
import com.henry.hh.utils.LocationUtils;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.LogUtils;


public class LocationActivity extends TitleActivity {

    public static final String PLACE = "place";

    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private PoiSearch poiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initTitle();

        ((Button) getViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra(PLACE, "My name is linjiqin");
                //设置返回数据
                ((Activity) mContext).setResult(RESULT_OK, intent);
                //关闭Activity
                ((Activity) mContext).finish();
            }
        });

        locationClient = new AMapLocationClient(this.getApplicationContext());
        AMapLocation location = locationClient.getLastKnownLocation();
        String result = LocationUtils.getLocationStr(location);
        LogUtils.d(TAG, "location:" + result);

    }

    /**
     * 初始化标题
     */
    private void initTitle(){
        setTitle("地点");
        showBackwardView("返回",true);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
        }
    }
}