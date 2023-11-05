package com.example.myapplicationteim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private int editingPosition = -1;
    private List<ImageItem> imageItems;
    private ImageListAdapter adapter;
    private ImageView imageView;
    private Uri selectedImageUri; // Để lưu trữ đường dẫn của ảnh được chọn
    private EditText editTextID; // Thêm EditText cho mã
    private EditText editTextTen; // Thêm EditText cho tên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddImage = findViewById(R.id.button_Upload);
        Button btnSaveImage = findViewById(R.id.btnSaveImage);
        Button bt_Xoa = findViewById(R.id.btn_Xoa);
        Button bt_Update = findViewById(R.id.btn_Sua);
        Button bt_Xoatrang = findViewById(R.id.btn_XoaTrang);
        imageView = findViewById(R.id.imageView_Anh);
        ListView listViewImages = findViewById(R.id.listViewImages);

        // Ánh xạ EditText cho mã và tên
        editTextID = findViewById(R.id.editText_ID);
        editTextTen = findViewById(R.id.editTexT_Ten);
        RadioButton rb_nam = findViewById(R.id.radio_nam);
        RadioButton rb_nu = findViewById(R.id.radio_Nu);

        Spinner sp_NV = (Spinner) findViewById(R.id.spinner_Nhanvien);

        String[] listItem = getResources().getStringArray(R.array.listcpu);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listItem);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_NV.setAdapter(adapter1);


        imageItems = new ArrayList<>();
        adapter = new ImageListAdapter(this, imageItems);
        listViewImages.setAdapter(adapter);

        // Sự kiện khi nhấn nút "Upload Ảnh"
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        // Sự kiện khi nhấn nút "Thêm"
        btnSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImageUri != null) {
                    // Lấy mã, tên và đường dẫn ảnh từ EditText và selectedImageUri
                    String maNV = editTextID.getText().toString();
                    String tenNV = editTextTen.getText().toString();
                    String gioitinhNV = rb_nam.isChecked() ? "Nam" : "Nu";
                    String donvi = sp_NV.getSelectedItem().toString();

                    if (!maNV.isEmpty() && !tenNV.isEmpty() && selectedImageUri != null) {
                        int manv = Integer.parseInt(maNV);
                        // Thêm thông tin mã, tên và ảnh vào danh sách và thông báo cho adapter cập nhật
                        imageItems.add(new ImageItem(manv, tenNV, gioitinhNV, donvi, selectedImageUri));
                        adapter.notifyDataSetChanged();
                    } else {
                        // Hiển thị thông báo hoặc thực hiện xử lý khi có lỗi (ví dụ: hiển thị Toast)
                        Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin và chọn ảnh.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        // Sự kiện khi nhấn vào một mục trong danh sách
        listViewImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Lấy ImageItem được chọn
                ImageItem selectedItem = imageItems.get(i);

                // Hiển thị thông tin của ImageItem lên các EditText và RadioButton
                editTextID.setText(String.valueOf(selectedItem.getMaNV()));
                editTextTen.setText(selectedItem.getTenNV());

                // Kiểm tra và thiết lập RadioButton dựa trên giới tính
                RadioButton rbNam = findViewById(R.id.radio_nam);
                RadioButton rbNu = findViewById(R.id.radio_Nu);

                if (selectedItem.getGioiTinh().equals("Nam")) {
                    rbNam.setChecked(true);
                    rbNu.setChecked(false);
                } else {
                    rbNam.setChecked(false);
                    rbNu.setChecked(true);
                }
                String donvi = selectedItem.getDonVi();
                int position = findPositionInSpinner(sp_NV, donvi);
                if (position >= 0) {
                    sp_NV.setSelection(position);
                }

                editingPosition = i;

                // Hiển thị ảnh lên ImageView
                imageView.setImageURI(selectedItem.getImageUri());
            }

            private int findPositionInSpinner(Spinner spinner, String value) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (adapter.getItem(i).equals(value)) {
                        return i;
                    }
                }
                return -1; // Trả về -1 nếu không tìm thấy giá trị trong Spinner
            }
        });
        bt_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editingPosition >= 0) {
                    // Xóa mục đã chọn khỏi danh sách
                    imageItems.remove(editingPosition);
                    adapter.notifyDataSetChanged();

                    // Xóa dữ liệu trong các EditText, RadioButton và ImageView
                    editTextID.setText("");
                    editTextTen.setText("");
                    RadioButton rbNam = findViewById(R.id.radio_nam);
                    RadioButton rbNu = findViewById(R.id.radio_Nu);
                    rbNam.setChecked(false);
                    rbNu.setChecked(false);
                    imageView.setImageURI(null);
                    sp_NV.setSelection(0); // Đặt Spinner về vị trí mặc định

                    editingPosition = -1; // Đặt lại vị trí đang chỉnh sửa
                }
            }
        });
        bt_Xoatrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextID.setText("");
                editTextTen.setText("");
                rb_nam.isChecked();
                sp_NV.setSelection(0);
                //Xóa ảnh đang upload
                imageView.setImageResource(R.drawable.trong);

            }
        });

        bt_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editingPosition >= 0) {
                    // Lấy thông tin từ EditText, RadioButton và Spinner
                    String maNV = editTextID.getText().toString();
                    String tenNV = editTextTen.getText().toString();
                    String gioiTinhNV = rb_nam.isChecked() ? "Nam" : "Nu";
                    String donvi = sp_NV.getSelectedItem().toString();

                    // Cập nhật thông tin cho mục đã chọn trong danh sách
                    ImageItem selectedItem = imageItems.get(editingPosition);
                    selectedItem.setMaNV(Integer.parseInt(maNV));
                    selectedItem.setTenNV(tenNV);
                    selectedItem.setGioiTinh(gioiTinhNV);
                    selectedItem.setDonVi(donvi);

                    // Cập nhật đường dẫn ảnh cho mục đã chọn
                    selectedItem.setImageUri(selectedImageUri);

                    // Thông báo cho adapter cập nhật dữ liệu
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    // Mở cửa sổ chọn ảnh
    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn Ảnh"), PICK_IMAGE_REQUEST);
    }

    // Xử lý kết quả khi chọn ảnh từ thư viện
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();

            // Hiển thị ảnh đã chọn lên ImageView
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}