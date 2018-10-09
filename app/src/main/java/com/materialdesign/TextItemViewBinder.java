package com.materialdesign;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2018/9/10
 * 邮箱 18780569202@163.com
 */
public class TextItemViewBinder extends ItemViewBinder<TextItem,TextItemViewBinder.TextHolder> {
    @NonNull
    @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item1, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem item) {
        holder.tv.setText(item.text);
    }

    static class TextHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public TextHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
