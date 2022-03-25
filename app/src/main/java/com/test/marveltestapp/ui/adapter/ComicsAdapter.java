package com.test.marveltestapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.marveltestapp.R;
import com.test.marveltestapp.data.dto.comics.Result;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder>{

    private Context ctx;
    private List<Result> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public ComicsAdapter(Context context, List<Result> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.ctx = context;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item_comics, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(mData.get(position).getTitle());
        holder.txtDate.setText(mData.get(position).getModified());
        holder.txtDescription.setText(mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AppCompatTextView txtTitle;
        AppCompatTextView txtDate;
        AppCompatTextView txtDescription;

        private ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtDescription = itemView.findViewById(R.id.txt_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Result getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

