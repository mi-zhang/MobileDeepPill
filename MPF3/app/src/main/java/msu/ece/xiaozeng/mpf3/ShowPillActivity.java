package msu.ece.xiaozeng.mpf3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import msu.ece.xiaozeng.mpf3.entity.Pill;

public class ShowPillActivity extends AppCompatActivity {

    private TextView tv_pill_name;
    private TextView tv_pill_color;
    private TextView tv_pill_imprint;
    private ImageView iv_pill_front_pic;
    private ImageView iv_pill_back_pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViews();

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        Pill pill = (Pill) intent.getExtras().getSerializable("pill");

        tv_pill_name.setText(pill.name);
        tv_pill_color.setText(pill.color);
        tv_pill_imprint.setText(pill.imprint);
        iv_pill_front_pic.setImageResource(pill.frontPicID);
        iv_pill_back_pic.setImageResource(pill.backPicID);

    }

    private void findViews(){
        tv_pill_name = (TextView)findViewById(R.id.tv_pill_name);
        tv_pill_color= (TextView)findViewById(R.id.tv_pill_color);
        tv_pill_imprint= (TextView)findViewById(R.id.tv_pill_imprint);
        iv_pill_front_pic = (ImageView) findViewById(R.id.iv_pill_front_pic);
        iv_pill_back_pic = (ImageView) findViewById(R.id.iv_pill_back_pic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
