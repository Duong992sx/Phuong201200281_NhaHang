package com.example.phuong201200281_nhahang;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
     private ArrayList<NhaHang> data;
    private Activity context;
    private LayoutInflater inflater;
    private ArrayList<NhaHang> databackup;

    public Adapter(){}
     public Adapter(ArrayList<NhaHang> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
       return data.size();
    }

    @Override
    public Object getItem(int position) {
       return data.get(position);
    }

    @Override
    public long getItemId(int position) {
         return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            v = inflater.inflate(R.layout.itemlistview, null);
            //gán giá trị cho tex float
            TextView ten = v.findViewById(R.id.tvTen);
            TextView phong = v.findViewById(R.id.tvPhong);
            TextView gia = v.findViewById(R.id.tvGia);
            ten.setText(data.get(position).getTennhahang());
            phong.setText(String.valueOf(data.get(position).getDiachi()));
            gia.setText(String.valueOf(data.get(position).getdiemtrungbinh()));


        }
        return v;
    }
    //tìm kiếm
      public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                //Backup dữ liệu: lưu tạm data vào databackup
                if(databackup==null)
                    databackup = new ArrayList<>(data);
                //Nếu chuỗi để filter là rỗng thì khôi phục dữ liệu
                if(charSequence==null || charSequence.length()==0)
                {
                    fr.count = databackup.size();
                    fr.values = databackup;
                }
                //Còn nếu không rỗng thì thực hiện filter
                else{
                    //Tìm theo tên
//                    ArrayList<HoaDon_08> newdata = new ArrayList<>();
//                    for(HoaDon_08 u:databackup)
//                        if(u.getName().toLowerCase().contains(
//                                charSequence.toString().toLowerCase()))
//                            newdata.add(u);
//                    fr.count=newdata.size();
//                    fr.values=newdata;
                    //Tìm theo id lớn hơn 1 số nhập vào
//                    ArrayList<HoaDon_08> newdata = new ArrayList<>();
//                    for(HoaDon_08 u:databackup)
//                        if(u.getId() >
//                                Integer.parseInt(charSequence.toString()))
//                            newdata.add(u);
//                    fr.count=newdata.size();
//                    fr.values=newdata;
                    //Tìm theo hoa đơn lớn hơn số nhập vào
                    ArrayList<NhaHang> newdata = new ArrayList<>();
                    for(NhaHang u:databackup)
                        if(u.getdiemtrungbinh() >
                                Integer.parseInt(charSequence.toString()))
                            newdata.add(u);
                    fr.count=newdata.size();
                    fr.values=newdata;

                }
                return fr;
            }
            @Override
            protected void publishResults(CharSequence charSequence,
                                          FilterResults filterResults) {
                data = new ArrayList<NhaHang>();
                ArrayList<NhaHang> tmp =(ArrayList<NhaHang>)filterResults.values;
                for(NhaHang u: tmp)
                    data.add(u);
                notifyDataSetChanged();
            }
        };
        return f;
    }
}
