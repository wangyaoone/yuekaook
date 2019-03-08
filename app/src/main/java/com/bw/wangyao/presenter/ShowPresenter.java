package com.bw.wangyao.presenter;

import com.bw.wangyao.model.ShowModel;
import com.bw.wangyao.view.ShowView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: 12547
 * @Date: 2019/3/8 14:10:23
 * @Description:
 */
public class ShowPresenter <T> {
    private Reference<T> mviewModel;
    private final ShowModel showModel;
    private final ShowView showView;

    public ShowPresenter(ShowView view) {
        showModel = new ShowModel();
        showView = view;
    }



    //与P层关联 处理内存泄露
    public  void  attachview (T view){
        mviewModel =new WeakReference<T>(view);
    }

    public void show() {
        showModel.show();
             //接收值
        showModel.setShowDate(new ShowModel.ShowDate() {
            @Override
            public void getdate(String date) {
                 showView.view(date);
            }
        });
    }
    // P层解除关联
    public  void  detachview(){
        if (mviewModel.get()!=null){
            mviewModel.clear();
            mviewModel=null;
        }
    }
}
