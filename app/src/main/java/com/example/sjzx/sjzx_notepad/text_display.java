package com.example.sjzx.sjzx_notepad;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class text_display  extends AppCompatActivity{
    private ListView lv;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_file_display);
        initView();
    }

    public void initView(){
        lv =(ListView)findViewById(R.id.list_text);
        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();
    }

    public void  selectDB(){
        Cursor cursor = dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
        adapter = new MyAdapter(this,cursor);
        lv.setAdapter(adapter);
    }
    @Override
    protected void onResume(){
        super.onResume();
        selectDB();
    }
}
