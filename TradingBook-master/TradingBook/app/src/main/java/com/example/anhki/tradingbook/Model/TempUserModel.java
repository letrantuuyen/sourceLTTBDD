package com.example.anhki.tradingbook.Model;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.Log;


public class TempUserModel {
    String UserName, UserPhone, UserAddress;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

}
