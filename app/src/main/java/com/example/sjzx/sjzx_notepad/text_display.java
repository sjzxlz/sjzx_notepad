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
import android.widget.ListView;
import android.widget.Toast;

public class text_display  extends AppCompatActivity{
    private ListView lv;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    private Intent i;
    private boolean isMulChoice; //是否多选

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
                Toast.makeText(this, "删除", Toast.LENGTH_SHORT)
                        .show();
                finish();
                break;
            case R.id.action_share:
                Toast.makeText(this, "上传", Toast.LENGTH_SHORT)
                        .show();
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //结束

    public void initView(){
        lv =(ListView)findViewById(R.id.list_text);
        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();

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
