package com.henry.hh.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.henry.hh.R;
import com.henry.hh.utils.LocationUtils;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.LogUtils;


public class LocationActivity extends TitleActivity implements PoiSearch.OnPoiSearchListener {

    public static final String PLACE = "place";

    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient = null;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private LatLonPoint lp ;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initTitle();

//        ((Button) getViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //数据是使用Intent返回
//                Intent intent = new Intent();
//                //把返回数据存入Intent
//                intent.putExtra(PLACE, "My name is linjiqin");
//                //设置返回数据
//                ((Activity) mContext).setResult(RESULT_OK, intent);
//                //关闭Activity
//                ((Activity) mContext).finish();
//            }
//        });

        locationClient = new AMapLocationClient(this.getApplicationContext());
        AMapLocation location = locationClient.getLastKnownLocation();
        String result = LocationUtils.getLocationStr(location);
        LogUtils.d(TAG, "location:" + result);
        lp = new LatLonPoint(location.getLongitude(),location.getLatitude());
        doSearchQuery("WC");
    }

    /**
     * 初始化标题
     */
    private void initTitle(){
        setTitle("地点");
        showBackwardView("返回",true);
    }

    /**
     * 收索兴趣点
     * @param key
     */
    protected void doSearchQuery(String key) {
//        keyWord = mSearchText.getText().toString().trim();
        currentPage = 0;
        query = new PoiSearch.Query(key, "", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);// 设置查第一页

        if (lp != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true));//
            // 设置搜索区域为以lp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }
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

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i==1000){
            if(poiResult!=null&& poiResult.getQuery() != null){
                if (poiResult.getQuery().equals(query)) {// 是否是同一条
                    LogUtils.d(TAG, "poiResult:" + poiResult.getPageCount() + "..."+poiResult.getPois().size()+"....." + poiResult.getPois().get(0).toString());
                }
             } else {
            LogUtils.e(TAG,"i");
        }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}