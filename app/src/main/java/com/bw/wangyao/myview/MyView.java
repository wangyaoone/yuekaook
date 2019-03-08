package com.bw.wangyao.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bw.wangyao.R;

/**
 * @Auther: 12547
 * @Date: 2019/3/8 15:50:26
 * @Description:
 */
public class MyView extends LinearLayout {
    private  int num=1;
    private Button kjia;
    private Button kjian;
    private EditText ked;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
            initdate(context,attrs);
    }



    public MyView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initdate(Context context, AttributeSet attrs) {
        View view=LayoutInflater.from(context).inflate(R.layout.myview,null,false);
               addView(view);
            kjia = view.findViewById(R.id.jia);
            kjian = view.findViewById(R.id.jian);
            ked = view.findViewById(R.id.myed);
                  ked.setText(num+"");
        kjian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (num>1){
                       num--;
                       ked.setText(num+"");
                       zclick.getdate(num);
                   }
            }
        });
        kjia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                    num++;
                    ked.setText(num+"");
                    zclick.getdate(num);
            }
        });
    }
    //接口回调值
       public  interface  Zclick{
            void  getdate(int  date);
        }
        private Zclick zclick;

       public void setZclick(Zclick zclick) {
        this.zclick = zclick;
       }
}
