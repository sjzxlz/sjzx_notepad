package com.example.sjzx.sjzx_notepad;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button textbtn, imgbtn, videobtn;
    private Button xxbtn;
    private Intent i,i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    public void initView(){
        textbtn = (Button)findViewById(R.id.text);
        imgbtn = (Button)findViewById(R.id.img);
        videobtn = (Button)findViewById(R.id.video);
        xxbtn = (Button)findViewById(R.id.list_button);
        textbtn.setOnClickListener(this);
        xxbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
         i = new Intent(this,addcontent.class);
        i1 = new Intent(this,text_display.class);
        switch (v.getId()){

            case R.id.text:
            startActivity(i);
                break;

            case R.id.list_button:
                startActivity(i1);
                break;
        }

    }


}
