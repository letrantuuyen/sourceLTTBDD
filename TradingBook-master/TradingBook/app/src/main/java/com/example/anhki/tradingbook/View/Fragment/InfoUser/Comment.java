package com.example.anhki.tradingbook.View.Fragment.InfoUser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Controller.CommentController;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.InfoUser;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Comment extends Fragment {

    RecyclerView recyclerComment;
    CommentController commentController;

    public static Comment newInstance() {
        return new Comment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_info_user_comment, container, false);
        recyclerComment = (RecyclerView) view.findViewById(R.id.recyclerComment);

        InfoUser activity = (InfoUser) getActivity();
        String idUser = activity.getMyData();

        commentController = new CommentController(getApplicationContext());
        commentController.getDanhSachCommentController(recyclerComment, idUser);
        Log.d("IDUSER",idUser);

        return view;
    }
}
