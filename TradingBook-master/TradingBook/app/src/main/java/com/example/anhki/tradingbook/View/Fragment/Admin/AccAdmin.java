package com.example.anhki.tradingbook.View.Fragment.Admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.Controller.ProductTypeController;
import com.example.anhki.tradingbook.Controller.UserController;
import com.example.anhki.tradingbook.R;

public class AccAdmin extends Fragment {
    RecyclerView recyclerAccount;
    UserController userController;

    public static AccAdmin newInstance(int i) {
        return new AccAdmin();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_admin_accadmin, container, false);

        recyclerAccount = (RecyclerView) view.findViewById(R.id.recyclerAccount);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        userController = new UserController(getContext());
        userController.getDanhSachAccountController(recyclerAccount);
    }
}
