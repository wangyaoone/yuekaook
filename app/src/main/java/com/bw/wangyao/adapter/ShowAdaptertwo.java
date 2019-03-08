package com.bw.wangyao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.wangyao.R;
import com.bw.wangyao.bean.ShowBean;
import com.bw.wangyao.myview.MyView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 12547
 * @Date: 2019/3/8 15:09:35
 * @Description:
 */
public class ShowAdaptertwo extends RecyclerView.Adapter<ShowAdaptertwo.myviewholder> {
    private Map<Integer,Integer>mm=new HashMap<>();
     private Map<String,Boolean> map2=new HashMap<>();
     private Context context;
     private List<ShowBean.DataBean.ListBean>list;

        private int date1=1;
    public ShowAdaptertwo(Context context, List<ShowBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        che4(false);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          View view=LayoutInflater.from(context).inflate(R.layout.item_2,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, final int i) {

        myviewholder.kch2.setChecked(list.get(i).isCheck2());
        Glide.with(context).load(list.get(i).getImages()).into(myviewholder.kim2);
        myviewholder.kmy1.setText(list.get(i).getTitle());
        myviewholder.kt2.setText(list.get(i).getPrice()+"");

          //接收接口回调的值
        myviewholder.kmyview.setZclick(new MyView.Zclick() {
            @Override
            public void getdate(int date) {
                   //得到输入框的值
                date1 = date;
            }
        });
             //设置值
        String pid = list.get(i).getPid()+"";
        myviewholder.kch2.setChecked(map2.get(pid));

        myviewholder.kch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i1 = list.get(i).getPrice();
                mm.put(i1,date1);
                int a=0;
                for (Integer key:mm.keySet()){
                        a+=key;
                     //接口回调将值传回去
                    zhi.getdate(a);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        private final CheckBox kch2;
        private final ImageView kim2;
        private final TextView kmy1;
        private final TextView kt2;
        private final MyView kmyview;

        public myviewholder(@NonNull View itemView) {
              super(itemView);
            kch2 = itemView.findViewById(R.id.myck2);
            kim2 = itemView.findViewById(R.id.myimage2);
            kmy1 = itemView.findViewById(R.id.mytexttwo);
            kt2 = itemView.findViewById(R.id.mytextwtwo2);
            kmyview = itemView.findViewById(R.id.myview);
          }
      }

    public  void  che3(boolean a){
          che4(a);
        notifyDataSetChanged();
    }

    private void che4(boolean a) {
        map2.clear();
        for (int i=0;i<list.size();i++){
            map2.put(list.get(i).getPid()+"",a);
        }
    }

    public interface  Zhi{
           void getdate(int date);
    }
    private Zhi zhi;

    public void setZhi(Zhi zhi) {
        this.zhi = zhi;
    }
}
