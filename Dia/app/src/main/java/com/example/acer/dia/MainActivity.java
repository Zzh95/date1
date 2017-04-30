package com.example.acer.dia;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.widget.Button;
import android.widget.DatePicker;
import android.view.View.OnClickListener;
import android.app.DatePickerDialog;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView showdate;
    private Button setdate;
    private int year;
    private int month;
    private int day;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showdate=(TextView) this.findViewById(R.id.showtime);
        setdate=(Button) this.findViewById(R.id.setdate);
        //初始化Calendar日历对象
        Calendar c=Calendar.getInstance(Locale.CHINA);
        Date mydate=new Date(); //获取当前日期Date对象
        c.setTime(mydate);////为Calendar对象设置时间为当前日期

        year=c.get(Calendar.YEAR); //获取Calendar对象中的年
        month=c.get(Calendar.MONTH);//获取Calendar对象中的月
        day=c.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
        showdate.setText("当前日期:"+year+"-"+(month+1)+"-"+day); //显示当前的年月日

        //添加单击事件--设置日期
        setdate.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v)
            {

                //创建DatePickerDialog对象
                DatePickerDialog my_datePickerDialog=new DatePickerDialog(MainActivity.this,Datelistener,year,month,day);
                my_datePickerDialog.show();//显示DatePickerDialog组件
            }
        });

    }
    private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
    {


            @Override
            public void onDateSet(DatePicker v, int y, int m,int d) {
                Calendar c=Calendar.getInstance(Locale.CANADA);
                year=c.get(Calendar.YEAR); //获取Calendar对象中的年
                month=c.get(Calendar.MONTH);//获取Calendar对象中的月
                day=c.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
                SimpleDateFormat Format=new SimpleDateFormat("yyy-MM-dd");
                String time = y+"-"+m+"-"+d;
                String current_time =year+"-"+month+"-"+day;
                Date date=null;
                Date current_date=null;
                try{
                    date = Format.parse(time);
                    current_date=Format.parse(current_time);
                }catch (ParseException e){
                   e.printStackTrace();
                }
                if(date.getTime() <= current_date.getTime()){
                    year=y;
                    month=m;
                    day=d;
                    updateDate();
                }else{
                    Toast.makeText(getApplicationContext(), "设置日期无效", Toast.LENGTH_SHORT).show();
                }

            }
        //当DatePickerDialog关闭时，更新日期显示
        private void updateDate()
        {
            //在TextView上显示日期
            showdate.setText("当前日期："+year+"-"+(month+1)+"-"+day);
        }
    };


}