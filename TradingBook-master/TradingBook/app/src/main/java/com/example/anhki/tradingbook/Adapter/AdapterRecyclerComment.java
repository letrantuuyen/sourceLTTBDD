package com.example.anhki.tradingbook.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.anhki.tradingbook.Model.CommentModel;
import com.example.anhki.tradingbook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecyclerComment extends RecyclerView.Adapter<AdapterRecyclerComment.ViewHolder> {

    List<CommentModel> commentModelList;
    Context context;

    public AdapterRecyclerComment(Context context, List<CommentModel> commentModelList){
        this.commentModelList = commentModelList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameAccount, tvDate, txtContent;
        CircleImageView imProduct;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameAccount = (TextView) itemView.findViewById(R.id.txtNameAcoountComment);
            tvDate = (TextView) itemView.findViewById(R.id.txtDateComment);
            txtContent = (TextView) itemView.findViewById(R.id.txtContentComment);
            imProduct = (CircleImageView) itemView.findViewById(R.id.imAccountComment);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerComment.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_comment, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerComment.ViewHolder viewHolder, int i) {
        CommentModel commentModel = commentModelList.get(i);

        viewHolder.tvNameAccount.setText(commentModel.getUserModel().getNameUser());
        viewHolder.tvDate.setText(commentModel.getDate());
        viewHolder.txtContent.setText(commentModel.getContent());
        viewHolder.ratingBar.setRating(commentModel.getRatingstar());

        StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("User").child(commentModel.getUserModel().getAvatar());
        long ONE_MEGABYTE = 1024 * 1024;
        storageImgProductType.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                viewHolder.imProduct.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }
}
