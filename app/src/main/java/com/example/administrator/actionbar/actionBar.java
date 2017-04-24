package com.example.administrator.actionbar;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;


public class actionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try{
            ViewConfiguration mconfig=ViewConfiguration.get(this);
            Field mkfield;
            mkfield=ViewConfiguration.class.getDeclaredField("a");
            if(mkfield!=null){
                mkfield.setAccessible(true);
                mkfield.setBoolean(mconfig,false);
            }
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.calendar:
                SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                Date nowdate=new Date(System.currentTimeMillis());
                String str=format.format(nowdate);
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                break;
            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + Uri.parse("10010")));
                startActivity(intent);
                break;
            case R.id.msm:
                SmsManager smsManager=SmsManager.getDefault();
                //第一个是地址，第二个是服务中心，第三个是内容，第四个是返回结果，第o五个是.....
                smsManager.sendTextMessage("10086",null,"10086",null,null);
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.remain,menu);
        return true;
    }
}
