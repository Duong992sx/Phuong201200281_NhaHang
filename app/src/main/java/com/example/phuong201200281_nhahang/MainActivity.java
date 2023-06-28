package com.example.phuong201200281_nhahang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

   private ArrayList<NhaHang> ContacList;
    private EditText etSreach;
    private  Adapter ListAdapter;
    private ListView lstContact;
    FloatingActionButton btthem;
    int selectedid = -1;
    private Phuong_Sql db;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.idup:
                Intent intent = new Intent(MainActivity.this,
                        Add.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", ContacList.get(selectedid).getId());
                bundle.putString("Ten", ContacList.get(selectedid).getTennhahang());
                bundle.putString("Diachi", ContacList.get(selectedid).getDiachi());
                bundle.putInt("Sophieu", ContacList.get(selectedid).getSophieu());
                bundle.putFloat("Diem", ContacList.get(selectedid).getdiemtrungbinh());
                intent.putExtras(bundle);
                startActivityForResult(intent, 200);
                break;
            case R.id.iddel:
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Phuong wants to delete?");
                dlgAlert.setTitle("Confirm");
                dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
//                                ContacList.remove(selectedid);
                        db.deleteContact(ContacList.get(selectedid).getId());
                        resetData();
                    }
                });
                dlgAlert.setNegativeButton("Cancel",null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                break;
        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //lấy dữ liệu từ NewContact gửi về
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String ten = bundle.getString("Ten");
        String diachi = bundle.getString("Diachi");
        int sophieumoi = bundle.getInt("Sophieumoi");
        float diemmoi = bundle.getFloat("Diemmoi");
        float diemtbmoi=(ContacList.get(selectedid).getSophieu()*ContacList.get(selectedid).getdiemtrungbinh()+diemmoi)/sophieumoi;
        if(requestCode==100 && resultCode==200 )
        {
            //đặt vào listData
            db.addContact(new NhaHang(ten,diachi,sophieumoi,diemmoi));


        }
        else if(requestCode==200 && resultCode==201)
            db.UpdateContact(id,new NhaHang(id,ten,diachi,sophieumoi,diemtbmoi));


        ListAdapter.notifyDataSetChanged();
        resetData();
    }
    private void resetData(){
        db = new Phuong_Sql(MainActivity.this, "ContactDBb1",null,1);
        ContacList  = db.GetAllContact();
        ListAdapter = new Adapter(ContacList, MainActivity.this);
        lstContact.setAdapter(ListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContacList = new ArrayList<NhaHang>();
        db = new Phuong_Sql(this, "ContactDBb1",null,1);
//        db.addContact(new NhaHang("Sen Tây Hồ ","614 Lạc Long Quân ",123,7));
//        db.addContact(new NhaHang("Nón Lá ","Nguyễn Đình Chiểu ",121,4));


        ContacList = db.GetAllContact();
        lstContact = findViewById(R.id.listview1);
        ListAdapter = new Adapter(ContacList, this);
        etSreach = findViewById(R.id.etSearch1);
        btthem = findViewById(R.id.btnAdd1);

           Collections.sort(ContacList, new Comparator<NhaHang>() {
            @Override
            public int compare(NhaHang o1, NhaHang o2) {
                return (int) (o2.getdiemtrungbinh()-o1.getdiemtrungbinh());
            }
        });

        lstContact.setAdapter(ListAdapter);
        btthem.setOnClickListener(v -> {

            Intent intent = new Intent(this, Add.class);
            startActivityForResult(intent,100);

        });
        registerForContextMenu(lstContact);
        lstContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        selectedid = position;
        int dem = 0;
        float selectedPrice = ContacList.get(selectedid).getdiemtrungbinh();

        for (NhaHang nhahang : ContacList) {
            float price = nhahang.getdiemtrungbinh();
            if (price > selectedPrice) {
                dem++;
            }
        }

        String result = "Có " + dem + " nhà hàng có điểm trung bình lớn hơn phần tử được chọn";
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

        return false;
    }
});

        etSreach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                ListAdapter.getFilter().filter(s.toString());
                ListAdapter.notifyDataSetChanged();
                lstContact.setAdapter(ListAdapter);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}