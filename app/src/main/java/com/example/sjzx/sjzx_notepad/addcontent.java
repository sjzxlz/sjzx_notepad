package com.example.sjzx.sjzx_notepad;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class addcontent extends AppCompatActivity  {

    private EditText ettext;
    private EditText title_text;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent_text);

        //actionbar
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //结束

        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
        initView();
    }

    public void initView() {
        ettext = (EditText) findViewById(R.id.ettext);
        title_text = (EditText) findViewById(R.id.title_text);

    }

    //Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                    finish();
                   break;

            case R.id.action_save:
                Toast.makeText(this, "保存", Toast.LENGTH_SHORT)
                        .show();
                        addDB();
                        finish();
                        break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //结束


    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.TITLE, title_text.getText().toString());
        cv.put(NotesDB.CONTENT, ettext.getText().toString());
        cv.put(NotesDB.TIME, getTime());
        dbWriter.insert(NotesDB.TABLE_NAME, null, cv);
    }
    private   String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }
}
