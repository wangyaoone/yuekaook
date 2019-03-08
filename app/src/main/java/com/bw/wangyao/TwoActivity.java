package com.bw.wangyao;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.wangyao.adapter.ShowAdapterone;
import com.bw.wangyao.bean.ShowBean;
import com.bw.wangyao.presenter.ShowPresenter;
import com.bw.wangyao.view.ShowView;
import com.google.gson.Gson;

import java.util.List;

public class TwoActivity extends AppCompatActivity implements ShowView {
    private MyHandler MyHandler=new MyHandler();
    private RecyclerView krecy1;
    private CheckBox ck1;
    private TextView ktext;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        krecy1 = findViewById(R.id.recy1);
        ck1 = findViewById(R.id.ck1);
        ktext = findViewById(R.id.text22);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TwoActivity.this);
        krecy1.setLayoutManager(linearLayoutManager);
        //实例化P层创建对象
        showPresenter = new ShowPresenter(this);
        showPresenter.show();
        showPresenter.attachview(this);
    }
    @Override
    public void view(String date) {
       /* Log.i("aa",date);*/
        Message message = new Message();
             message.what=1;
             message.obj=date;
        MyHandler.sendMessage(message);
    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                      switch (msg.what){
                          case 1:
                              String  obj = (String) msg.obj;
                              Gson gson = new Gson();
                     ShowBean showBean = gson.fromJson(obj, ShowBean.class);
                     List<ShowBean.DataBean> data = showBean.getData();

                              //写适配器
                final ShowAdapterone showAdapterone = new ShowAdapterone(TwoActivity.this,data);
                        krecy1.setAdapter(showAdapterone);
                              showAdapterone.setZh2(new ShowAdapterone.Zh2() {
                                  @Override
                                  public void getdate(int date) {
                                      ktext.setText("总价为:¥"+date);
                                  }
                              });
                 ck1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                          if (ck1.isChecked()){
                                              showAdapterone.che1(true);
                                          }else
                                          {
                                              showAdapterone.che1(false);
                                          }
                                  }
                              });

                              break;
                      }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.detachview();
          /* Log.i("a","销毁了");*/
    }
}
