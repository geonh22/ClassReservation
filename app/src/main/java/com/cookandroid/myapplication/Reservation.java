package com.cookandroid.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservation extends Activity implements View.OnClickListener {
    private android.support.v7.widget.Toolbar toolbar;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText ed_name, ed_age;
    private AlertDialog.Builder dialog;
    private String year="", month="", day="", minute="", hour="";
    private String name="",age="",gender="";
    private String room_num;
    private String result;
    private Button next, S1201,S1202,S1203;
    private TextView room;
    private LinearLayout button_layout, button_layout2;
    private ViewFlipper viewFlipper;
    private String[] limit;
    private ArrayAdapter<String> adapter;
    private Spinner limit_spinner;
    private String limit_time;
    private String id;
    private String peo_num;
    private EditText peo_ed;
    private int p_num;
    private String now="";
    String people_num;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        next = (Button) findViewById(R.id.next);
        peo_ed = (EditText)findViewById(R.id.people);
        button_layout = (LinearLayout) findViewById(R.id.btn_layout);
        button_layout2 = (LinearLayout)findViewById(R.id.btn_layout2);
        timePicker = (TimePicker) findViewById(R.id.time);
        datePicker = (DatePicker)findViewById(R.id.date);
        viewFlipper = (ViewFlipper)findViewById(R.id.vf);
        room = (TextView)findViewById(R.id.room_text);
        S1201 = (Button)findViewById(R.id.bt_S1201);
        S1202 = (Button)findViewById(R.id.bt_S1202);
        S1203 = (Button)findViewById(R.id.bt_S1203);
        S1201.setOnClickListener(this);
        S1202.setOnClickListener(this);
        S1203.setOnClickListener(this);
        limit = getResources().getStringArray(R.array.limit_time);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,limit);
        limit_spinner = (Spinner)findViewById(R.id.limit);
        limit_spinner.setPrompt("몇 시간을 이용하겠습니까?");
        limit_spinner.setAdapter(adapter);
        limit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                limit_time = (String) parent.getItemAtPosition(position);
                Log.d("asd",limit_time);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_S1201:
                room_num="S1201";
                room.setText(room_num);
                break;
            case R.id.bt_S1202:
                room_num="S1202";
                room.setText(room_num);
                break;
            case R.id.bt_S1203:
                room_num="S1203";
                room.setText(room_num);
                break;

        }
    }


    // 맨 처음 다음 버튼
    public void fnext(View v){
        peo_num = peo_ed.getText().toString();
        p_num = Integer.parseInt(peo_num);
        next.setVisibility(View.GONE);
        button_layout.setVisibility(View.VISIBLE);
        button_layout2.setVisibility(View.INVISIBLE);
        viewFlipper.showNext();
    }
    // 그 다음 버튼
    public void btn_next(View v){
        year = String.valueOf(datePicker.getYear());
        month = String.valueOf(1+datePicker.getMonth());
        day = String.valueOf(datePicker.getDayOfMonth());

        next.setVisibility(View.GONE);
        button_layout.setVisibility(View.INVISIBLE);
        button_layout2.setVisibility(View.VISIBLE);
        viewFlipper.showNext();
    }

    //이름 설정 뒤로가기
    public void btn_back_n(View v){
        next.setVisibility(View.VISIBLE);
        button_layout.setVisibility(View.INVISIBLE);
        button_layout2.setVisibility(View.INVISIBLE);
        viewFlipper.showPrevious();
    }
    //데이트 설정 뒤로가기
    public void btn_back_d(View v){
        year ="";
        month ="";
        day="";
        next.setVisibility(View.VISIBLE);
        button_layout.setVisibility(View.VISIBLE);
        button_layout2.setVisibility(View.INVISIBLE);
        viewFlipper.showPrevious();

    }
    // Ok
    @TargetApi(Build.VERSION_CODES.M)
    public void btn_okay(View v) {
        next.setVisibility(View.GONE);
        button_layout.setVisibility(View.INVISIBLE);
        button_layout2.setVisibility(View.VISIBLE);
        minute = String.valueOf(timePicker.getMinute());
        hour = String.valueOf(timePicker.getHour());
        people_num = String.valueOf(p_num);
        people_num +="명";
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String N_year = (new SimpleDateFormat("yyyy").format(date));
        N_year +="년";
        String N_month = (new SimpleDateFormat("MM").format(date));
        N_month +="월";
        String N_day = (new SimpleDateFormat("dd").format(date));
        N_day +="일";
        String N_hour = (new SimpleDateFormat("HH").format(date));
        N_hour +="시";
        String N_minute = (new SimpleDateFormat("mm").format(date));
        N_minute +="분";

        now = N_year + N_month + N_day + N_hour + N_minute;
        Log.d("asd",now);

        result = year + "년" + month + "월" + day + "일" + hour + "시" + minute + "분";

        dialog = new AlertDialog.Builder(Reservation.this);

        dialog.setTitle("예약 확인");
        dialog.setMessage(name+ "님 " +result + " | "+room_num + ", "+p_num+"명"+" 예약. 관리자 승인 대기 중");
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
           public void onClick(DialogInterface dialog, int which) {
                mydatabase();
                startActivity(new Intent(Reservation.this,MainActivity.class));
                finish();
            }
        });
        dialog.setNegativeButton("취소", null);
        dialog.show();
    }
    public void mydatabase(){
        DBHelper db_reservation = new DBHelper(getApplicationContext(),"myongji.db",null,1 );
        db_reservation.insert("insert into reservation values (null,'"+result+"','"+room_num+"',"+p_num+",'"+limit_time+"','"+now+"','"+id+"');");
        Reservation insert = new Reservation();
    }
    public void file(){
        //파일 처리
        try {
            DBHelper db_reservation = new DBHelper(getApplicationContext(),"myongji.db",null,1 );
            String temp = db_reservation.select_join();
            File file3 = new File(getFilesDir().getAbsolutePath() + "/reservation_list.txt"); // X
            File file2 = new File ("/mnt/sdcard/reservation_list.txt"); // X
            File file = new File("/storage/emulated/0/reservation_list.txt"); // X
//            FileOutputStream outfile = new FileOutputStream(file);
            FileOutputStream outfile = openFileOutput("reservation_list.txt",MODE_PRIVATE); // 쓰기모드
            outfile.write(temp.getBytes());
            outfile.close();
            Toast.makeText(getApplicationContext(),"file Ok",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
