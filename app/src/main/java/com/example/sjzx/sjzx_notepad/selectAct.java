package com.example.sjzx.sjzx_notepad;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class selectAct extends AppCompatActivity  {

    private TextView s_tv;
    private TextView s_title_tv;
    private TextView s_time_tv;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    String title ="";
    String de_content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);

        //actionbar
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        //over

        s_tv = (TextView) findViewById(R.id.s_tv);
        s_title_tv = (TextView) findViewById(R.id.s_title_tv);
        s_time_tv = (TextView)findViewById(R.id.s_time_tv);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();


        s_title_tv.setText(getIntent().getStringExtra(NotesDB.TITLE));
        s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));
        s_time_tv.setText(getIntent().getStringExtra(NotesDB.TIME));
    }

    //Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
               break;
            case R.id.action_delete:
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
                        break;

            case R.id.action_edit:
                Toast.makeText(this, "编辑", Toast.LENGTH_SHORT)
                        .show();
                Bundle bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("de_content",de_content);

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //over


    public void deleteDate(){
        dbWriter.delete(NotesDB.TABLE_NAME,"_id=" + getIntent().getIntExtra(NotesDB.ID,0),null);
    }

}
