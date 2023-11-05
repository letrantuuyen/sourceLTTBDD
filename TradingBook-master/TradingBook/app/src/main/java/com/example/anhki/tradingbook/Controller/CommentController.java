package com.example.anhki.tradingbook.Controller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anhki.tradingbook.Adapter.AdapterRecyclerComment;
import com.example.anhki.tradingbook.Controller.Interface.CommentInterface;
import com.example.anhki.tradingbook.Model.CommentModel;

import java.util.ArrayList;
import java.util.List;

public class CommentController {
    CommentModel commentModel;
    Context context;
    AdapterRecyclerComment adapterRecyclerComment;

    public CommentController(Context context){
        this.context = context;
        commentModel = new CommentModel();
    }

    public void getDanhSachCommentController(RecyclerView recycler, String idUser){
        final List<CommentModel> commentModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true);
        recycler.setLayoutManager(layoutManager);
        recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true));
        adapterRecyclerComment = new AdapterRecyclerComment(context, commentModelList);
        recycler.setAdapter(adapterRecyclerComment);

        CommentInterface commentInterface = new CommentInterface() {
            @Override
            public void getDanhSachCMTModel(CommentModel commentModel) {
                commentModelList.add(commentModel);
                adapterRecyclerComment.notifyDataSetChanged();
            }
        };
        commentModel.getDanhSachCmt(commentInterface, idUser);
    }
}
