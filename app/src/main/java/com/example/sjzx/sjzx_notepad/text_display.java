package com.example.sjzx.sjzx_notepad;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class text_display  extends AppCompatActivity  implements View.OnClickListener{
    private ListView lv;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    private Button selectall;
    private Button cancel;
    private Button confirmdelete;
    private LinearLayout layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_file_display);

        //actionbar
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //结束
        initView();
    }




    //Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

            case R.id.action_delete:
                adapter = new MyAdapter(this,cursor);
                lv.setAdapter(adapter);

                layout1.setVisibility(View.VISIBLE);
                confirmdelete.setVisibility(View.VISIBLE);
                for (int n = 0; n < adapter.getCount(); n++) {
                    adapter.isVisiblecheck.put(n, View.VISIBLE);
                }
                break;
            case R.id.action_share:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //结束

    public void initView(){
        lv =(ListView)findViewById(R.id.list_text);
        selectall = (Button) findViewById(R.id.bt_selectall);
        cancel = (Button) findViewById(R.id.bt_cancelselectall);
        confirmdelete = (Button) findViewById(R.id.bt_confirmdelete);
        layout1 = (LinearLayout)findViewById(R.id.linear);


        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();

        selectall.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirmdelete.setOnClickListener(this);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);

                Intent i = new Intent(text_display.this, selectAct.class);
                i.putExtra(NotesDB.ID,
                        cursor.getInt(cursor.getColumnIndex(NotesDB.ID)));
                i.putExtra(NotesDB.CONTENT, cursor.getString(cursor
                        .getColumnIndex(NotesDB.CONTENT)));
                i.putExtra(NotesDB.TITLE, cursor.getString(cursor
                        .getColumnIndex(NotesDB.TITLE)));
//                i.putExtra(NotesDB.IMG,
//                        cursor.getString(cursor.getColumnIndex(NotesDB.IMG)));
//                i.putExtra(NotesDB.VIDEO,
//                        cursor.getString(cursor.getColumnIndex(NotesDB.VIDEO)));
                i.putExtra(NotesDB.TIME,
                        cursor.getString(cursor.getColumnIndex(NotesDB.TIME)));
                startActivity(i);

            }
        });
    }


      @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_selectall:

                adapter = new MyAdapter(this,cursor);
                lv.setAdapter(adapter);

                for (int n = 0; n < cursor.getCount(); n++) {
                    adapter.isVisiblecheck.put(n, View.VISIBLE);
                    adapter.isSelected.put(n, true);
                }

            case R.id.bt_cancelselectall:

                adapter = new MyAdapter(this,cursor);
                lv.setAdapter(adapter);

                for (int n = 0; n < cursor.getCount(); n++) {
                    adapter.isVisiblecheck.put(n, View.VISIBLE);
                    adapter.isSelected.put(n, false);
                }

            case R.id.bt_confirmdelete:

                adapter = new MyAdapter(this,cursor);
                lv.setAdapter(adapter);

                for (int n = 0; n < cursor.getCount(); n++) {
                    adapter.isVisiblecheck.put(n, View.VISIBLE);
                    adapter.isSelected.put(n, true);
                }
        }
    }

    public void  selectDB(){
         cursor = dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
          adapter = new MyAdapter(this,cursor);
          lv.setAdapter(adapter);
    }
    @Override
    protected void onResume(){
        super.onResume();
        selectDB();
    }


}
