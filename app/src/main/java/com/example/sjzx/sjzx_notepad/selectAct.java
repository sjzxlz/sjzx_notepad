package com.example.sjzx.sjzx_notepad;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class selectAct extends AppCompatActivity implements View.OnClickListener {
    private Button s_delete, s_back;
    private TextView s_tv;
    private TextView s_title_tv;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        s_delete = (Button) findViewById(R.id.s_delete);
        s_back = (Button) findViewById(R.id.s_back);
        s_tv = (TextView) findViewById(R.id.s_tv);
        s_title_tv = (TextView) findViewById(R.id.s_title_tv);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
        s_back.setOnClickListener(this);
        s_delete.setOnClickListener(this);

        s_title_tv.setText(getIntent().getStringExtra(NotesDB.TITLE));
        s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.s_delete) {
            Dialog dialog = new AlertDialog.Builder(selectAct.this)
                    .setTitle("删除提示框")
                    .setIcon(R.mipmap.ic_launcher1)
                    .setMessage("确认删除吗？")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            deleteDate();
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        }
        else if (v.getId() == R.id.s_back) {
                finish();
        }
    }
    public void deleteDate(){
        dbWriter.delete(NotesDB.TABLE_NAME,"_id=" + getIntent().getIntExtra(NotesDB.ID,0),null);
    }
}
