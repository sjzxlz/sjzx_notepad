package com.example.sjzx.sjzx_notepad;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;


public class MyAdapter extends BaseAdapter {


    private Context context;
    private Cursor cursor;
    private   CheckBox ceb;
    private TextView titletv;
    private LayoutInflater inflater=null;

    //记录某一项的checkbox是否可见
    private static HashMap<Integer,Integer> isVisiblecheck;
    //记录某一项的checkbox是否被选择
    public static HashMap<Integer,Boolean> isSelected;

    public MyAdapter(Context context,Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        inflater = LayoutInflater.from(context);

        //初始化
        isVisiblecheck =  new HashMap<Integer, Integer>();

             isSelected = new HashMap<Integer, Boolean>();

            for (int n = 0; n < cursor.getCount(); n++) {

//             isVisiblecheck.put(i, View.INVISIBLE);
                isSelected.put(n, false);
            }
    }
    @Override
    public int getCount() {
        return  cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
                if (convertView == null){
                    holder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.cell,null);
                     holder.titletv = (TextView)convertView.findViewById(R.id.list_title);
                    holder.ceb = (CheckBox)convertView.findViewById(R.id.delete_cb);
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder)convertView.getTag();
                }

        cursor.moveToPosition(position);
        String title = cursor.getString(cursor.getColumnIndex("title"));
//        String time = cursor.getString(cursor.getColumnIndex("time"));
        holder.titletv.setText(title);
        holder.ceb.setChecked(isSelected.get(position));
//        holder.ceb.setVisibility(View.INVISIBLE);
//        timetv.setText(time);
        return convertView;
    }
    private final class ViewHolder {
        TextView titletv;
        CheckBox ceb;
    }



}
