package com.example.forjavaweb.tool;


import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 定位工具类
 */
public class LocationUtil extends AppCompatActivity {
    private LocationClient mLocationClient;
    private LocationClientOption option;

    private EditText provinceEdit, cityEdit;

    public LocationUtil(LocationClient mLocationClient, EditText province, EditText city) {
        this.mLocationClient = mLocationClient;
        this.provinceEdit = province;
        this.cityEdit = city;
        //注册位置监听器
        this.mLocationClient.registerLocationListener(new MyLocationListener());
    }

    //初始化
    private void initLocation() {
        this.option = new LocationClientOption();
        this.option.setOpenGps(true);
        this.option.setIsNeedAddress(true);
        this.mLocationClient.setLocOption(option);
    }

    //获取位置
    public void getLocation() {
        //初始化
        this.initLocation();
        this.mLocationClient.start();
    }

    //位置监听器
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bdLocation == null) {
                        return;
                    }
                    String province = bdLocation.getProvince();    //获取省份
                    String city = bdLocation.getCity();    //获取城市
                    if (province == null || city == null) {
//                        textView.setText(SyncStateContract.Constants.GET_LOCATION_ERROE);
                    } else {
//                        textView.setText(province + " - " + city);
                        provinceEdit.setText(province);
                        cityEdit.setText(city);
                    }

                    LocationUtil.this.mLocationClient.stop();
                }
            });
        }
    }
}
