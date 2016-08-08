package com.example.sjzx.sjzx_notepad;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class selectAct extends AppCompatActivity implements View.OnClickListener{
    private Button s_delete,s_back;
    private TextView s_tv;
    private TextView s_title_tv;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        s_delete = (Button)findViewById(R.id.s_delete);
        s_back = (Button)findViewById(R.id.s_back);
        s_tv = (TextView)findViewById(R.id.s_tv);
        s_title_tv = (TextView)findViewById(R.id.s_title_tv);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
        s_back.setOnClickListener(this);
        s_delete.setOnClickListener(this);

        s_title_tv.setText(getIntent().getStringExtra(NotesDB.TITLE));
        s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s_delete:
            deleteDate();
                finish();
                break;
            case R.id.s_back:
                finish();
                break;

        }
    }
    public void deleteDate(){

        dbWriter.delete(NotesDB.TABLE_NAME,"_id=" + getIntent().getIntExtra(NotesDB.ID,0),null);

    }
}
