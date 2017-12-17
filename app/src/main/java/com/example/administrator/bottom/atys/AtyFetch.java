package com.example.administrator.bottom.atys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.net.UploadOrder;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

import static com.example.administrator.bottom.Config.APP_ID;

public class AtyFetch extends AppCompatActivity {

    private Spinner time_spinner;
    private Spinner loc_spinner;
    private EditText note_edittext;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private String time;
    private String loc;
    private String note;

    //UI组件初始化
    private void bindView() {
        time_spinner = (Spinner) findViewById(R.id.time_spinner);
        loc_spinner = (Spinner) findViewById(R.id.loc_spinner);
        note_edittext = (EditText) findViewById(R.id.fetch_note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aty_fetch2);
        getSupportActionBar().hide();

        bindView();
        findViewById(R.id.Fetch_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
        }
        });

        //数据
        data_list = new ArrayList<String>();
        data_list.add("18：30~19：30");
        data_list.add("19：30~20：30");
        data_list.add("20：30~21：30");
        data_list.add("21：30~22：30");

        //适配器 android.R.layout.simple_spinner_item
        arr_adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, data_list);
        //设置样式 android.R.layout.simple_spinner_dropdown_item
        arr_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        //加载适配器
        time_spinner.setAdapter(arr_adapter);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("默认地址");
        data_list.add("明德楼");
        data_list.add("文德楼");
        data_list.add("信息中心");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        //加载适配器
        loc_spinner.setAdapter(arr_adapter);

        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time = (String) time_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loc = (String) loc_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        findViewById(R.id.fetch_summit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = sDateFormat.format(new java.util.Date());

                // 获得phoneNum
                note = note_edittext.getText().toString();
                if(note.equals("")){
                    note = "none";
                }
                SharedPreferences sharedPreferences = getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
                String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");

                new UploadOrder(phone, time, loc, note,date, new UploadOrder.SuccessCallback() {

                    @Override
                    public void onSuccess() {

                        Toast.makeText(AtyFetch.this,"提交成功！", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AtyFetch.this, AtyMainFrame.class);
                        i.putExtra("page","order");
                        startActivity(i);
                        finish();

                    }
                }, new UploadOrder.FailCallback() {

                    @Override
                    public void onFail() {
                        Toast.makeText(AtyFetch.this, R.string.fail_to_commit, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
