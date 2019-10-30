package com.wedontanything.usedmarket.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedontanything.usedmarket.Data.CommentItem;
import com.wedontanything.usedmarket.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {

    List<CommentItem> commentItem = new ArrayList<>();

    public CommentAdapter() {

    }

    public void setItem(List<CommentItem> item) {
        this.commentItem = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item, viewGroup, false);
        CommentAdapter.Holder holder = new CommentAdapter.Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.Holder holder, int i) {
        holder.commentUserId.setText(commentItem.get(i).userId);
        holder.commentContent.setText(commentItem.get(i).content);
    }

    @Override
    public int getItemCount() {
        return commentItem.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView commentUserId;
        public TextView commentContent;

        public Holder(@NonNull View itemView) {
            super(itemView);

            commentUserId = itemView.findViewById(R.id.commentUserId);
            commentContent = itemView.findViewById(R.id.commentContent);
        }
    }
}
