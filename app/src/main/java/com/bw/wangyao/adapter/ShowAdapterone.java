package com.bw.wangyao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bw.wangyao.R;
import com.bw.wangyao.bean.ShowBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.internal.Internal;

/**
 * @Auther: 12547
 * @Date: 2019/3/8 14:43:11
 * @Description:
 */
public class ShowAdapterone extends RecyclerView.Adapter<ShowAdapterone.myviewholder> {

   private Context context;
   private List<ShowBean.DataBean> list;
   private Map<String,Boolean>map=new HashMap<>();
    public ShowAdapterone(Context context, List<ShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
        che2(false);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_1,null,false);
        myviewholder myviewholder = new myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final myviewholder myviewholder, int i) {
        myviewholder.kch1.setChecked(list.get(i).isCheck1());
        myviewholder.kch1.setText(list.get(i).getSellerName());
        //设置bool
        String sellerid = list.get(i).getSellerid();
        myviewholder.kch1.setChecked(map.get(sellerid));
        //布局管理者
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        myviewholder.krecy2.setLayoutManager(linearLayoutManager);
          //数据
        List<ShowBean.DataBean.ListBean> list = this.list.get(i).getList();
           //适配器
        final ShowAdaptertwo showAdaptertwo = new ShowAdaptertwo(context,list);
        myviewholder.krecy2.setAdapter(showAdaptertwo);


        showAdaptertwo.setZhi(new ShowAdaptertwo.Zhi() {
            @Override
            public void getdate(int date) {
                 zh2.getdate(date);
            }
        });

        myviewholder.kch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断父类型是否为true
                if (myviewholder.kch1.isChecked()){
                    showAdaptertwo.che3(true);
                }else
                {
                    showAdaptertwo.che3(false);
                }

                //处理价格

            }
        });


        //判断父类型是否为true
            if (myviewholder.kch1.isChecked()){
                showAdaptertwo.che3(true);
            }else
            {
                showAdaptertwo.che3(false);
            }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends  RecyclerView.ViewHolder{

        private final CheckBox kch1;
        private final RecyclerView krecy2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            kch1 = itemView.findViewById(R.id.mych1);
            krecy2 = itemView.findViewById(R.id.recy2);
        }
    }

     public  void  che1(boolean a){
             che2(a);
             notifyDataSetChanged();
     }

    private void che2(boolean a) {
        map.clear();
           for (int i=0;i<list.size();i++){
                 map.put(list.get(i).getSellerid()+"",a);
           }
    }

       public interface Zh2{
           void  getdate(int date);
       }
       private Zh2 zh2;

    public void setZh2(Zh2 zh2) {
        this.zh2 = zh2;
    }
}
