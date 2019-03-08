package com.bw.wangyao.model;

import android.util.Log;

import com.bw.wangyao.utils.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: 12547
 * @Date: 2019/3/8 14:10:56
 * @Description:
 */
public class ShowModel {

      //接口回调
       public  interface  ShowDate{
              void getdate(String date);
       }
       private ShowDate showDate;

    public void setShowDate(ShowDate showDate) {
        this.showDate = showDate;
    }

    private String url="http://172.17.8.100/ks/product/getCarts?uid=51";
    public void show() {
        Utils.doget(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
              /*  Log.i("aa",string);*/
                    showDate.getdate(string);
            }
        });
    }
}
