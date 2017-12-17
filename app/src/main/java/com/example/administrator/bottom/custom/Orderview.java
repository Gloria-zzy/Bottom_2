package com.example.administrator.bottom.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.bottom.R;

/**
 * Created by Administrator on 2017\11\25 0025.
 */

public class Orderview extends RelativeLayout {

    private TextView order_intro;
    private TextView time;
    private TextView order_time;
    private TextView order_loc;
    private TextView order_note;
    private TextView order_status;
    private Button order_cancel;
    private Button order_contact;
    private Button order_change;
    private Button order_code;
    private TextView order_num;
    private String num;

    public Orderview(Context context){
        super(context);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_record, this);

        // 获取控件
        order_intro = (TextView) findViewById(R.id.introduction);
        time = (TextView) findViewById(R.id.time);
        order_time = (TextView) findViewById(R.id.order_time);
        order_loc = (TextView) findViewById(R.id.order_loc);
        order_note = (TextView) findViewById(R.id.order_note);
        order_status = (TextView) findViewById(R.id.order_status);
        order_cancel = (Button) findViewById(R.id.order_cancel);
        order_contact = (Button) findViewById(R.id.order_contact);
        order_change = (Button) findViewById(R.id.order_change);
        order_code = (Button) findViewById(R.id.order_code);
        order_num = (TextView) findViewById(R.id.order_num);
    }

    public Orderview(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_record, this);

        // 获取控件
        order_intro = (TextView) findViewById(R.id.introduction);
        time = (TextView) findViewById(R.id.time);
        order_time = (TextView) findViewById(R.id.order_time);
        order_loc = (TextView) findViewById(R.id.order_loc);
        order_cancel = (Button) findViewById(R.id.order_cancel);
        order_contact = (Button) findViewById(R.id.order_contact);
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setOrder_intro(String intro) {
        this.order_intro.setText(intro);
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setOrder_time(String order_time) {
        this.order_time.setText(order_time);
    }

    public void setOrder_loc(String order_loc) {
        this.order_loc.setText(order_loc);
    }

    public void setOrder_num(String order_num){
        this.order_num.setText(order_num);
    }

    public void setOrder_note(String order_note){
        this.order_note.setText(order_note);
    }

    public void setOrder_status(String order_status){
        this.order_status.setText(order_status);
    }

    public void setCancelButtonListener(OnClickListener listener) {
        order_cancel.setOnClickListener(listener);
    }

    public void setContactButtonListener(OnClickListener listener) {
        order_contact.setOnClickListener(listener);
    }

    public void setChangeButtonListener(OnClickListener listener){
        order_change.setOnClickListener(listener);
    }

    public void setGetCodeButtonListener(OnClickListener listener){
        order_code.setOnClickListener(listener);
    }
}
