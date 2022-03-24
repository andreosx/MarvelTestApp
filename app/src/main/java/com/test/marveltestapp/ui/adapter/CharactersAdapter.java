package com.test.marveltestapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.marveltestapp.R;
import com.test.marveltestapp.data.dto.Character;
import com.test.marveltestapp.util.ImageUtil;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder>{

    private Context ctx;
    private List<Character> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public CharactersAdapter(Context context, List<Character> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.ctx = context;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(mData.get(position).getName());

        Glide.with(ctx)
                .load(ImageUtil.getUrlImg(mData.get(position),"portrait_xlarge"))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView txtName;
        ImageView ivThumbnail;

        private ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Character getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

