package com.example.anhki.tradingbook.View.Fragment.Image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anhki.tradingbook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FragmentImage1 extends Fragment {
    ImageView imageView;
    Bundle bundle;
    public static FragmentImage1 newInstance() {
        return new FragmentImage1();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_image_1, container, false);

        imageView = (ImageView) view.findViewById(R.id.imgProduct);

        if (getActivity().getIntent().hasExtra("bundle")) {
            bundle = getActivity().getIntent().getBundleExtra("bundle");
//            byte[] byteArray = bundle.getByteArray("picture1");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            imageView.setImageBitmap(bmp);

            StorageReference storageImgProductType = FirebaseStorage.getInstance().getReference().child("Product").child(bundle.getString("picture1"));
            long ONE_MEGABYTE = 1024 * 1024;
            storageImgProductType.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
        return view;
    }
}
