package com.example.phuong201200281_nhahang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add extends AppCompatActivity {
     EditText  editText_diem;
     TextView tvten,tvdiachi,tvsophieu;
    Button button_add,btn_back;
     private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        button_add=findViewById(R.id.btnthem);
        btn_back=findViewById(R.id.btnquayve);
        editText_diem =findViewById(R.id.editdiemDG);
        tvten =findViewById(R.id.tvTenNH);
        tvdiachi =findViewById(R.id.tvDiaChiNH);
        tvsophieu =findViewById(R.id.tvSPNH);


         Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            id = bundle.getInt("Id");
            String ten = bundle.getString("Ten");
            String diachi = bundle.getString("Diachi");
            int phieucu = bundle.getInt("Sophieu");
            int phieumoi =phieucu+1;
            float diemcu = bundle.getFloat("Diem");

           // etId.setText(String.valueOf(id));
            tvten.setText(ten);
            tvsophieu.setText(String.valueOf(phieumoi));
             tvdiachi.setText(String.valueOf(diachi));
             editText_diem.setText(String.valueOf(diemcu));

            button_add.setText("Edit");
        }
        btn_back.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivityForResult(intent1,0);
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){

                    //Tạo intent để trở về MainActivity
                    Intent intent = new Intent();
                    //Tạo bundle là đối tượng để chứa dữ liệu
                    Bundle bundle = new Bundle();
                    //bundle hoạt động như một Java Map các phần tử phân biệt theo key
                    //bundle có các hàm put... trong đó ... là kiểu dữ liệu tương ứng
                   bundle.putInt("Id",id);
                    bundle.putString("Ten", tvten.getText().toString());
                    bundle.putString("Diachi", tvdiachi.getText().toString());
                    bundle.putInt("Sophieumoi", Integer.parseInt(tvsophieu.getText().toString()));
                  int diem = Integer.parseInt(editText_diem.getText().toString());
                  float diemMoi = (float) diem;
                   bundle.putFloat("Diemmoi", diemMoi);
                    //có thể đặt cả đối tượng lên bundle bằng hàm putSerilizable
                    //đặt bundle lên intent

                    intent.putExtras(bundle);


                    //trả về bằng hàm setResult
                    //tham số thứ nhất là resultCode để quản lý phiên
                    //tham số thứ hai  là intent chứa dữ liệu gửi về
                    setResult(200, intent);
                    if(button_add.getText()=="Edit")
                        setResult(201, intent);
                    //Kết thúc: đóng activity hiện thời.
                    finish();
                }

            }
        });
    }
    boolean check(){

          if(editText_diem.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập so ngay !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    }