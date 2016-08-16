package com.example.sjzx.sjzx_notepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class note_edit extends AppCompatActivity {

    private NotesDB notesDB;
    private EditText ee_text;
    private EditText ee_title_text;
    public int rowid;
    String title,de_content;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit);

        notesDB = new NotesDB(this);
        initView();

        //actionbar
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //结束
    }

    public void initView(){

        ee_text = (EditText) findViewById(R.id.ee_text);
        ee_title_text = (EditText) findViewById(R.id.ee_title_text);

        Bundle bundle = getIntent().getExtras();
            title = bundle.getString("title");
            de_content = bundle.getString("de_content");
             rowid = bundle.getInt("rowid",0);

            ee_title_text.setText(title);
             ee_text.setText(de_content);

    }

    //Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

            case R.id.action_save:
                Toast.makeText(this, "保存",Toast.LENGTH_SHORT)
                        .show();
                       save();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //结束


    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }
    private void save(){
         title=ee_title_text.getText().toString();
        de_content=ee_text.getText().toString();
        notesDB.updateNotes(rowid,title,de_content);


        Bundle bundle1=new Bundle();
        bundle1.putString("title",title);
        bundle1.putString("de_content",de_content);

        Intent intent1 =new Intent();
        intent1.putExtras(bundle1);
        setResult(0,intent1);
        finish();
    }


}
