package com.henry.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.henry.hh.R;
import com.henry.hh.adapter.LocationAdapter;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.hh.utils.LocationUtils;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


public class LocationActivity extends CheckPermissionsActivity
        implements PoiSearch.OnPoiSearchListener, View.OnClickListener,
        TextWatcher, OnRecyclerItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    public static final String PLACE = "place";

    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient = null;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private LatLonPoint lp;
    private int currentPage = 0;

    private EditText mEdit;
    private ImageView mSearch;
    private ImageView mClear;
    private RecyclerView mRecyclerView;
    private LocationAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private BGARefreshLayout mRefreshLayout;
    //是否为刷新
    private boolean isRefresh = false;
    //收索结果总页数
    private int pageCount = 0;
    //是否为加载
    private boolean isUpload = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initTitle();
        initRefresh();
        initView();
        initList();

        locationClient = new AMapLocationClient(getApplicationContext());
        AMapLocation location = locationClient.getLastKnownLocation();
        String result = LocationUtils.getLocationStr(location);
        LogUtils.d(TAG, "location:" + result);
        if (location != null) {
            lp = new LatLonPoint(location.getLongitude(), location.getLatitude());
            doSearchQuery("");
        }
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        setTitle("地点");
        showBackwardView("返回", true);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mSearch = getViewById(R.id.iv_search);
        mEdit = getViewById(R.id.et_search);
        mClear = getViewById(R.id.iv_clear);
        mRecyclerView = getViewById(R.id.recycler_location);
        ControlsUtils.setHeight(mEdit, ScreenUtils.getScreenHeight(mContext) / 15);
        mSearch.setOnClickListener(this);
        mClear.setOnClickListener(this);
        mEdit.addTextChangedListener(this);
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mRefreshLayout.setRefreshViewHolder(
                new BGANormalRefreshViewHolder(getApplicationContext(), true));
    }

    /**
     * 初始化列表
     */
    private void initList() {
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        //创建并设置Adapter
        adapter = new LocationAdapter(mContext);
        mRecyclerView.setAdapter(adapter);
        adapter.addOnItemClickListener(this);
    }


    /**
     * 收索兴趣点
     *
     * @param key
     */
    protected void doSearchQuery(String key) {
        currentPage = 0;
        searchQuery(key, currentPage);
    }

    /**
     * 收索兴趣点，指定页
     *
     * @param key
     * @param currentPage
     */
    private void searchQuery(String key, int currentPage) {
        query = new PoiSearch.Query(key, "", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(15);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        if (lp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    /**
     * 返回箭头
     *
     * @param backwardView
     */
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

    /**
     * 收索结果
     *
     * @param poiResult
     * @param i
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                if (poiResult.getQuery().equals(query)) {// 是否是同一条
                    pageCount = poiResult.getPageCount();
                    LogUtils.d(TAG, "poiResult:" + pageCount + "..." + poiResult.getPois().size() + "....." + poiResult.getPois().get(0).toString());
                    if (isUpload) {
                        //结束加载
                        mRefreshLayout.endLoadingMore();
                        isUpload = false;
                        adapter.append(poiResult.getPois());
                        return;
                    }
                    if (isRefresh) {
                        //结束刷新
                        mRefreshLayout.endRefreshing();
                        isRefresh = false;
                    }
                    adapter.refresh(poiResult.getPois());

                }
            } else {
                LogUtils.e(TAG, "i");
            }
        } else if (i == 1802) {
            showToast("socket 连接超时");
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_clear:
                mEdit.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            mClear.setVisibility(View.GONE);
            doSearchQuery("");
        } else {
            mClear.setVisibility(View.VISIBLE);
            doSearchQuery(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideSoftKeyboard();
        }
        return false;
    }

    @Override
    public void onItemClick(View view, List data, int position) {


        //数据是使用Intent返回
        Intent intent = new Intent();
        //把返回数据存入Intent
        String plcae = data.get(position).toString();
        intent.putExtra(PLACE, plcae == null ? "" : plcae);
        //设置返回数据
        setResult(RESULT_OK, intent);
        mEdit.setText(plcae == null ? "" : plcae);
        //关闭Activity
        finish();

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        doSearchQuery(mEdit.getText().toString());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        currentPage++;
        if (currentPage >= pageCount) {
            searchQuery(mEdit.getText().toString(), currentPage);
            isUpload = true;
        } else {
            showToast("没有更多数据了");
        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRefreshLayout.endLoadingMore();
//            }
//        }, 2000);
        return true;
    }
}