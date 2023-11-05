package com.example.anhki.tradingbook.View.Fragment.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Controller.UserController;
import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.InfoUser;
import com.example.anhki.tradingbook.View.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

public class Account extends Fragment implements View.OnClickListener{
    EditText txtName, txtAddress, txtPhone, txtPoint;
    ImageButton btnEditName, btnEditPhone, btnEditAddress;
    ImageView imgView;
    Button btnUpdate, btnChoose, btnUser;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private Uri imgUri;
    String pathImage ="";

    FirebaseStorage storage;
    StorageReference storageReference;

    UserController userController;

    public static Account newInstance(int i) {
        return new Account();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_user_account, container, false);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        txtName = (EditText) view.findViewById(R.id.txtName);
        txtPhone = (EditText) view.findViewById(R.id.txtPhone);
        txtAddress = (EditText) view.findViewById(R.id.txtAddress);
        txtPoint = (EditText) view.findViewById(R.id.txtPoint);
        btnEditName = (ImageButton) view.findViewById(R.id.btnEditName);
        btnEditPhone = (ImageButton) view.findViewById(R.id.btnEditPhone);
        btnEditAddress = (ImageButton) view.findViewById(R.id.btnEditAddress);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate) ;
        btnChoose = (Button) view.findViewById(R.id.btnChoose);
        imgView = (ImageView) view.findViewById(R.id.imgAva);

        btnUser = (Button) view.findViewById(R.id.btnTempUserInfo);

        userController = new UserController(getContext());
        userController.getUserController(LoginActivity.Current_IdUser, txtName,txtPhone, txtAddress,txtPoint, imgView);

        //Lock txt
        txtName.setFocusable(false);
        txtPhone.setFocusable(false);
        txtAddress.setFocusable(false);
        txtPoint.setFocusable(false);
        btnUpdate.setFocusable(false);

        btnEditName.setOnClickListener(this);
        btnEditPhone.setOnClickListener(this);
        btnEditAddress.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnChoose.setOnClickListener(this);
        btnUser.setOnClickListener(this);

        //Push pic to imageview
        final String[] items = new String[] {"From Camera", "From Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Image...");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which== 0){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File file = new File(Environment.getExternalStorageDirectory(),"tmp_avatar" +String.valueOf(System.currentTimeMillis()) + ".jpg");
                    imgUri = Uri.fromFile(file);
                    try {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
                        intent.putExtra("return data", true);
                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                    dialog.cancel();
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Complete action using"),PICK_FROM_FILE);
                }
            }
        });
        final AlertDialog dialog = builder.create();
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnEditName:
                btnEditName.setVisibility(v.GONE);
                btnUpdate.setFocusable(true);
                txtName.setFocusableInTouchMode(true);
                break;
            case R.id.btnEditPhone:
                btnEditPhone.setVisibility(v.GONE);
                btnUpdate.setFocusable(true);
                txtPhone.setFocusableInTouchMode(true);
                break;
            case R.id.btnEditAddress:
                btnEditAddress.setVisibility(v.GONE);
                btnUpdate.setFocusable(true);
                txtAddress.setFocusableInTouchMode(true);
                break;
            case R.id.btnUpdate:
                btnUpdate.setFocusable(false);
                txtName.setFocusable(false);
                txtPhone.setFocusable(false);
                txtAddress.setFocusable(false);
                btnEditName.setVisibility(v.VISIBLE);
                btnEditAddress.setVisibility(v.VISIBLE);
                break;
            case R.id.btnTempUserInfo:
                Intent iInfoUser = new Intent(getActivity(), InfoUser.class);
                iInfoUser.putExtra("name", txtName.getText().toString());
                iInfoUser.putExtra("address", txtAddress.getText().toString());
                iInfoUser.putExtra("phone", txtPhone.getText().toString());
                iInfoUser.putExtra("activity", "account");

                imgView.buildDrawingCache();
                Bitmap bmp1 = imgView.getDrawingCache();
                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
                byte[] byteArray = stream1.toByteArray();

                iInfoUser.putExtra("image", byteArray);

                startActivity(iInfoUser);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        Bitmap bitmap = null;
        String path = "";
        if (requestCode == PICK_FROM_FILE){
            imgUri = data.getData();
            path = getRealPathFromURI(imgUri);
            if (path == null)
                path = imgUri.getPath();
            if (path != null)
                bitmap = BitmapFactory.decodeFile(path);
        } else {
            path = imgUri.getPath();
            bitmap = BitmapFactory.decodeFile(path);
        }
        pathImage = path;
        imgView.setImageBitmap(bitmap);
        uploadImage();
    }

    public  String getRealPathFromURI (Uri contentURI) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(contentURI, proj, null, null, null);
        if (cursor == null) return  null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return  cursor.getString(column_index);
    }

    private void uploadImage() {

        if(imgUri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("User/"+ UUID.randomUUID().toString());
            ref.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
