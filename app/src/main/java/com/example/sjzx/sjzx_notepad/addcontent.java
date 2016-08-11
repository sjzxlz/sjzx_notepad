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

    private Button savebtn, cancelbtn;
    private EditText ettext;
    private EditText title_text;
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

    public void initView() {
        savebtn = (Button)findViewById(R.id.save);
        cancelbtn = (Button)findViewById(R.id.cancel);
        ettext = (EditText) findViewById(R.id.ettext);
        title_text = (EditText) findViewById(R.id.title_text);


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

//    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu);
//        inflater.inflate(R.menu.note_edit, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        super.onOptionsItemSelected(item);
//        switch (item.getItemId()) {
//            case R.id.action_save:
//                addDB();
//                finish();
//                break;
//
//            case R.id.action_share:
//                finish();
//                break;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return true;
//    }




    public void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.TITLE, title_text.getText().toString());
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
