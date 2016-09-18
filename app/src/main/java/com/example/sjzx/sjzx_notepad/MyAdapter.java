package com.example.sjzx.sjzx_notepad;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseAdapter {


    private Context context;
    private Cursor cursor;
    private LinearLayout layout;
    private List<String> selectid = new ArrayList<String>();


    private boolean isMulChoice; //是否多选

    //记录某一项的checkbox是否可见
    private HashMap<Integer,Integer> visiblecheck;
    //记录某一项的checkbox是否被选择
    private HashMap<Integer,Boolean> ischeck;

    public MyAdapter(Context context,Cursor cursor) {
        this.context = context;
        this.cursor = cursor;

        //初始化
        visiblecheck = new HashMap<Integer, Integer>();

        ischeck = new HashMap<Integer, Boolean>();

        if (isMulChoice) {
            for (int i = 0; i < cursor.getCount(); i++) {
                visiblecheck.put(i, View.VISIBLE);
                ischeck.put(i, false);
            }
        } else{
            for (int i = 0; i < cursor.getCount(); i++) {
                visiblecheck.put(i, View.INVISIBLE);
                ischeck.put(i, false);
            }
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
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.cell,null);

        TextView titletv = (TextView)layout.findViewById(R.id.list_title);
        CheckBox ceb=(CheckBox)layout.findViewById(R.id.delete_cb);

//        TextView timetv =(TextView)layout.findViewById(R.id.list_time);
        cursor.moveToPosition(position);
        String title = cursor.getString(cursor.getColumnIndex("title"));
//        String time = cursor.getString(cursor.getColumnIndex("time"));
        titletv.setText(title);

        ceb.setChecked(ischeck.get(position));
        ceb.setVisibility(View.INVISIBLE);

//        timetv.setText(time);
        return layout;
    }


}
