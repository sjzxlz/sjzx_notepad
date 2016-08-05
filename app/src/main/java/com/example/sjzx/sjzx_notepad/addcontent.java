package com.example.sjzx.sjzx_notepad;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;


public class addcontent extends AppCompatActivity  {

    private Button savebtn,cancelbtn;
    private EditText ettext;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent_text);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
        initView();
    }
    public void initView(){
        savebtn = (Button)findViewById(R.id.save);
        cancelbtn = (Button)findViewById(R.id.cancel);
        ettext = (EditText)findViewById(R.id.ettext) ;


        savebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                addDB();
                finish();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT, ettext.getText().toString());
        cv.put(NotesDB.TIME, getTime());
        dbWriter.insert(NotesDB.TABLE_NAME, null, cv);
    }
    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }








}
