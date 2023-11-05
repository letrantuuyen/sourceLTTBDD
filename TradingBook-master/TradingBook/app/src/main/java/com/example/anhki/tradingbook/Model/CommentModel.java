package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.anhki.tradingbook.Controller.Interface.CommentInterface;
import com.example.anhki.tradingbook.View.InfoUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentModel {

    String content, date, idComment, idAccountR;
    float ratingstar;

    DatabaseReference nodeRoot;
    Query nodeOne;

    UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public CommentModel(){
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public CommentModel(String content, String date, String idAccountR, float ratingstar){
        this.content = content;
        this.date = date;
        this.idAccountR = idAccountR;
        this.ratingstar = ratingstar;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdAccountR() {
        return idAccountR;
    }

    public void setIdAccountR(String idAccountR) {
        this.idAccountR = idAccountR;
    }

    public float getRatingstar() {
        return ratingstar;
    }

    public void setRatingstar(float ratingstar) {
        this.ratingstar = ratingstar;
    }

//    public void createComment (String content, String date, String idAccountR, String idAccountRed, float ratingstar){
//        CommentModel comment = new CommentModel(content,date,idAccountR, idAccountRed, ratingstar);
//        getIdStringComment();
//        String id = "IdComment" + String.valueOf(count + 1);
//        Log.d("IdComment", id);
//        nodeRoot.child(id).setValue(comment);
//    };

    public void createComment (final String idUser, final String content, final String date, final String idAccountR, final float ratingstar){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                long count = dataSnapshot.child("commentratings").child(idUser).getChildrenCount();
                CommentModel comment = new CommentModel(content,date,idAccountR, ratingstar);
                String id = "IdComment" + String.valueOf(count + 1);
                Log.d("IdComment", id);
                nodeRoot.child("commentratings").child(idUser).child(id).setValue(comment);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    public void getDanhSachCmt(final CommentInterface commentInterface, final String idUser){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotCMT = dataSnapshot.child("commentratings").child(idUser);
                for (DataSnapshot valueProductType : dataSnapshotCMT.getChildren()){
                    CommentModel commentModel = valueProductType.getValue(CommentModel.class);

                    UserModel userModel =  dataSnapshot.child("accounts").child(commentModel.getIdAccountR()).getValue(UserModel.class);
                    commentModel.setUserModel(userModel);

                    commentInterface.getDanhSachCMTModel(commentModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}