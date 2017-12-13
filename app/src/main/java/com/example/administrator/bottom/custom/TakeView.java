package com.example.administrator.bottom.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.bottom.R;

/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class TakeView extends RelativeLayout {

    private ImageView imageView;
    private Button btn_ask_to_take;
    private OrderView orderView;

    public TakeView(Context context) {
        super(context);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_order, this);

        // 获取控件
        imageView =(ImageView)findViewById(R.id.imageViewHead);
        orderView =(OrderView)findViewById(R.id.order_view);
        btn_ask_to_take = (Button)findViewById(R.id.ask_to_take);

        orderView.getTv_order_ordernum().setVisibility(View.GONE);
        orderView.getTvshow_order_ordernum().setVisibility(View.GONE);
        orderView.getLl_order_picknum_status().setVisibility(View.GONE);
        orderView.getOrder_cancel().setVisibility(View.GONE);
        orderView.getOrder_change().setVisibility(View.GONE);
        orderView.getOrder_code().setVisibility(View.GONE);
    }


    public TakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_record, this);

        // 获取控件
        imageView =(ImageView)findViewById(R.id.imageViewHead);
        orderView =(OrderView)findViewById(R.id.order_view);
    }

    public Button getBtn_ask_to_take() {
        return btn_ask_to_take;
    }

    public void setBtn_ask_to_take(Button btn_ask_to_take) {
        this.btn_ask_to_take = btn_ask_to_take;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public void setOrderView(OrderView orderView) {
        this.orderView.setOrder_intro(orderView.getOrder_intro().getText().toString());
//        this.orderView.setOrder_num(orderView.getNum());
        this.orderView.setOrder_point(orderView.getOrder_point().getText().toString());
//        this.orderView.setOrder_takenum(orderView.getOrder_takenum().getText().toString());
        this.orderView.setOrder_loc(orderView.getOrder_loc().getText().toString());
//        this.orderView.setNum(orderView.getNum());
        this.orderView.setTime(orderView.getTime().getText().toString());
        this.orderView.setOrder_note(orderView.getOrder_note().getText().toString());
    }

}
