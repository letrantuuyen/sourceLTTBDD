package com.example.anhki.tradingbook.View;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhki.tradingbook.Controller.UserController;
import com.example.anhki.tradingbook.Model.ProductModel;
import com.example.anhki.tradingbook.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProductCreate extends AppCompatActivity {
    String productType;
    String [] nameImg = new String[4];
    TextView txtName, txtAddress, tvCart;
    EditText txteState, txtePrice,txteDescription, txteProduct;
    Button btnCreate;
    Spinner spinner;
    ImageView img1, img2, img3, img4, btnBack;
    ImageButton btnCart;
    UserController userController;
    ProductModel productModel = new ProductModel();

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private Uri imgUri;
    private Uri[] Array = new Uri[4];
    String pathImage ="";
    int click;
    boolean checked;

    FirebaseStorage storage;
    StorageReference storageReference;
    String mess = "0/4";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_create);

        txtName = (TextView) findViewById(R.id.txtNamePost);
        txtAddress = (TextView) findViewById(R.id.txtAddressPost);
        tvCart = (TextView) findViewById(R.id.tvCart);
        txteState = (EditText) findViewById(R.id.txteState);
        txtePrice = (EditText) findViewById(R.id.txtePrice);
        txteProduct = (EditText) findViewById(R.id.txteNameProduct);
        txteDescription = (EditText) findViewById(R.id.txteDescription);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        img1 = (ImageView) findViewById(R.id.imgProduct1);
        img2 = (ImageView) findViewById(R.id.imgProduct2);
        img3 = (ImageView) findViewById(R.id.imgProduct3);
        img4 = (ImageView) findViewById(R.id.imgProduct4);

        tvCart.setText(String.valueOf(SlashScreenActivity.countCart));

        userController = new UserController(getApplicationContext());
        userController.getUserProController(LoginActivity.Current_IdUser, txtName,txtAddress);

        List<String> list = new ArrayList<>();
        list.add("Truyện tranh");
        list.add("Sách chuyên ngành");
        list.add("Tạp chí");
        list.add("Tiểu thuyết");
        list.add("Sách khoa học");
        list.add("Sách kỹ năng- phương châm sống");
        list.add("Giáo trình");
        list.add("Thể loại khác");
        for (int i = 0;i<4;i++)
            nameImg[i] = UUID.randomUUID().toString();
        spinner = (Spinner) findViewById(R.id.spinProType);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.product_type,
                android.R.layout.simple_dropdown_item_1line);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productType = spinner.getItemAtPosition(position).toString();
                switch (productType){
                    case "Truyện tranh":
                        productType = "IdProductType1";
                        break;
                    case "Sách chuyên ngành":
                        productType = "IdProductType2";
                        break;
                    case "Tạp chí":
                        productType = "IdProductType3";
                        break;
                    case "Tiểu thuyết":
                        productType = "IdProductType4";
                        break;
                    case "Sách khoa học":
                        productType = "IdProductType5";
                        break;
                    case "Sách kỹ năng- phương châm sống":
                        productType = "IdProductType6";
                        break;
                    case "Giáo trình":
                        productType = "IdProductType7";
                        break;
                    default:
                        productType = "IdProductType8";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        final String[] items = new String[] {"From Camera", "From Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(this), android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = 1;
                dialog.show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = 2;
                dialog.show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = 3;
                dialog.show();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = 4;
                dialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 4; i++){
                    uploadImage(Array[i], i);
                }
                if (SlashScreenActivity.ROLE.equals("Admin")){
                    checked = true;
                } else checked = false;
                productModel.createProduct(false,checked, txteDescription.getText().toString(),
                        LoginActivity.Current_IdUser,LoginActivity.Current_IdUser+"_"+checked, txteProduct.getText().toString(),
                        Integer.valueOf(txtePrice.getText().toString()), productType,
                        productType + "_"+checked+"_false", txteState.getText().toString());
                productModel.createProductImage(nameImg[0],nameImg[1],nameImg[2],nameImg[3]);
                Toast.makeText(getApplicationContext(), "Đăng sản phẩm thành công, xin đợi duyệt!",Toast.LENGTH_LONG).show();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        Bitmap bitmap = null;
        String path = "";
        if (requestCode == PICK_FROM_FILE){
            imgUri = data.getData();
            switch (click){
                case 1:
                    Array[0] = imgUri;
                    break;
                case 2:
                    Array[1] = imgUri;
                    break;
                case 3:
                    Array[2] = imgUri;
                    break;
                default:
                    Array[3] = imgUri;
                    break;
            }
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
        switch (click) {
            case 1:
                img1.setImageBitmap(bitmap);
                break;
            case 2:
                img2.setImageBitmap(bitmap);
                break;
            case 3:
                img3.setImageBitmap(bitmap);
                break;
            default:
                img4.setImageBitmap(bitmap);
                break;
        }
    }

    public  String getRealPathFromURI (Uri contentURI) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.managedQuery(contentURI, proj, null, null, null);
        if (cursor == null) return  null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return  cursor.getString(column_index);
    }

    private void uploadImage(Uri imgUri,final int a) {

        if(imgUri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(ProductCreate.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("Product/"+ nameImg[a]);
            ref.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(ProductCreate.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            mess = String.valueOf(a) + "/4";
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ProductCreate.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%" + mess);
                        }
                    });
        }
    }
}
