package com.example.administrator.bottom.frag;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyFetch;
import com.example.administrator.bottom.atys.AtyMail;
import com.example.administrator.bottom.atys.AtyUnlog;
import com.example.administrator.bottom.custom.OrderView;
import com.example.administrator.bottom.custom.TakeView;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragHome extends Fragment {
    private String context;
    private TextView mTextView;
    private Button get_btn, mail_btn;
    private LinearLayout linearLayout;

    public FragHome() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        get_btn = (Button) view.findViewById(R.id.get_btn);
        linearLayout = (LinearLayout)view.findViewById(R.id.take_orders);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        get_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Config.loginStatus == 0) {
                    Intent intent = new Intent(getActivity(), AtyUnlog.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                } else {
                    startActivityForResult(new Intent(getActivity(), AtyFetch.class), Activity.RESULT_FIRST_USER);
                    getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                }
            }
        });

        OrderView orderView = new OrderView(getActivity());
        TakeView takeView = new TakeView(getActivity());
        takeView.setOrderView(orderView);
        linearLayout.addView(takeView);
        OrderView orderView2 = new OrderView(getActivity());
        TakeView takeView2 = new TakeView(getActivity());
        takeView2.setOrderView(orderView2);
        linearLayout.addView(takeView2);
        OrderView orderView3 = new OrderView(getActivity());
        TakeView takeView3 = new TakeView(getActivity());
        takeView3.setOrderView(orderView3);
        linearLayout.addView(takeView3);
    }
}

