package com.example.administrator.bottom.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.bottom.R;

/**
 * Created by Administrator on 2017\12\11 0011.
 */

public class TakeView extends RelativeLayout {

    private ImageView imageView;
    private OrderView orderView;

    public TakeView(Context context) {
        super(context);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_order, this);

        // 获取控件
        imageView =(ImageView)findViewById(R.id.imageViewHead);
        orderView =(OrderView)findViewById(R.id.order_view);

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
        this.orderView = orderView;
    }

}
