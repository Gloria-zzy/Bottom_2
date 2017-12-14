package com.example.administrator.bottom.atys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.custom.OrderView;
import com.example.administrator.bottom.net.DownloadOrders;
import com.example.administrator.bottom.net.DownloadTakenOrders;
import com.example.administrator.bottom.net.Order;

import java.util.ArrayList;

import static com.example.administrator.bottom.Config.APP_ID;

public class AtyTakenOrders extends AppCompatActivity {

    private String phone;
    private LinearLayout sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aty_taken_orders);
        getSupportActionBar().hide();

        findViewById(R.id.iv_taken_orders_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.transition.switch_still, R.transition.switch_slide_out_right);
            }
        });

        sv = (LinearLayout) findViewById(R.id.sv_taken_orders);
        SharedPreferences sharedPreferences = AtyTakenOrders.this.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
        if(sv != null){
            sv.removeAllViews();
        }
        new DownloadTakenOrders(phone, new DownloadTakenOrders.SuccessCallback() {

            @Override
            public void onSuccess(ArrayList<Order> orders) {

                for (Order o : orders) {
                    String number = o.getOrderNum();
                    String point = o.getPoint();
                    String takenum = o.getTakenum();
                    String loc = o.getLocation();
                    String note = o.getNote();
                    String status = o.getStatus();
                    String date = o.getDate();
                    final OrderView newov = new OrderView(AtyTakenOrders.this);

                    newov.setOrder_intro("小件快递");
                    newov.setOrder_num(number);
                    newov.setOrder_point(point);
                    newov.setOrder_takenum(takenum);
                    newov.setOrder_loc(loc);
                    newov.setNum(number);
                    newov.setTime(date);
                    if (note.equals("none")) {
                        note = "无";
                    }
                    newov.setOrder_note(note);
                    if (status.equals("0")) {
                        newov.setOrder_status("已结束");
                        sv.addView(newov);
                    } else if (status.equals("1")) {
                        newov.setOrder_status("正在送货");
                        sv.addView(newov);
                    } else if (status.equals("2")) {
                        newov.setOrder_status("待接单");
                        sv.addView(newov);
                    } else if (status.equals("3")) {
                        newov.setOrder_status("订单异常");
                        sv.addView(newov);
                    }
                }
            }
        }, new DownloadTakenOrders.FailCallback() {

            @Override
            public void onFail() {
                Toast.makeText(AtyTakenOrders.this, R.string.fail_to_commit, Toast.LENGTH_LONG).show();
            }
        });

    }
}
