package com.example.administrator.openrecyclerview.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.openrecyclerview.R;

/**
 * Created by Administrator on 2017/6/4 0004.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    // 为列表提供数据的数据集合
    final String[] contacts = new String[]{
            "当你点击我",
            "我就会展开",
            "我的子类，哈哈",
            "看效果快来",
            "点击我吧有",
            "不一样的视觉效果",
            "Elisa Asmara",
            "Emile Barrientos",
            "Ermes Toscano"
    };

    // 列表展开标识
    int opened = -1;

    /**
     * 绑定item布局
     * @param parent
     * @param pos
     * @return
     */
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int pos) {
        return new MainViewHolder((ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    /**
     * 绑定数据到控件
     * @param holder
     * @param pos
     */
    @Override
    public void onBindViewHolder(MainViewHolder holder, int pos) {
        final String contact = contacts[pos];
        holder.bind(pos, contact);
    }

    /**
     * 返回列表条数
     * @return
     */
    @Override
    public int getItemCount() {
        return contacts.length;
    }

    /**
     * 实例化控件等操作
     */
    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // 标题
        public final TextView contactNameTV;
        // 隐藏的内容
        public final TextView infos;

        // 实例化
        public MainViewHolder(ViewGroup itemView) {
            super(itemView);
            contactNameTV = ((TextView) itemView.findViewById(R.id.contactName));
            infos = ((TextView) itemView.findViewById(R.id.infos));

            itemView.setOnClickListener(this);
        }

        // 此方法实现列表的展开和关闭
        public void bind(int pos, String name) {
            contactNameTV.setText(name);
            infos.setText("点击测试看效果:"+name);
            //设置隐藏 或 点击显示
            if (pos == opened)
                infos.setVisibility(View.VISIBLE);
            else
                infos.setVisibility(View.GONE);
        }

        /**
         * 为item添加点击效果
         * (recyclerView是不提供onItemClickListener的。所以列表的点击事件需要我们自己来实现)
         * @param
         */
        @Override
        public void onClick(View v) {
            if (opened == getPosition()) {
                opened = -1;
                notifyItemChanged(getPosition());
            }
            else {
                int oldOpened = opened;
                opened = getPosition();
                notifyItemChanged(oldOpened);
                notifyItemChanged(opened);
            }
        }
    }
}
