package com.example.administrator.bottom.frag;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyGenCode;
import com.example.administrator.bottom.atys.AtyLogin;
import com.example.administrator.bottom.atys.AtyMainFrame;
import com.example.administrator.bottom.custom.Orderview;
import com.example.administrator.bottom.net.DownloadOrders;
import com.example.administrator.bottom.net.Order;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.bottom.Config.APP_ID;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragOrder extends Fragment {
    private Orderview order;
    private LinearLayout ll;
    private LinearLayout history;
    private String phone;

    private ViewPager pager;
    private List<View> views;
    private List<TextView> tvs = new ArrayList<TextView>();
    TextView tv1;
    TextView tv2;

    public FragOrder() {

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if (Config.loginStatus == 0) {
            view = inflater.inflate(R.layout.aty_unlog, container, false);
            view.findViewById(R.id.to_login).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AtyLogin.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                }
            });

            view.findViewById(R.id.back_to_home).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), AtyMainFrame.class);
                    i.putExtra("page", "home");
                    startActivity(i);
                }
            });
        } else {
            view = inflater.inflate(R.layout.frag_order, container, false);

            ll = (LinearLayout) view.findViewById(R.id.current_order_ll);
            history = (LinearLayout) view.findViewById(R.id.history_order_ll);

            pager = (ViewPager) view.findViewById(R.id.order_pager);
            tv1 = (TextView) view.findViewById(R.id.page_current);
            tv1.setTextColor(Color.BLUE);
            tv2 = (TextView) view.findViewById(R.id.page_history);
            tv1.setOnClickListener(new FragOrder.MyClickListener(0));
            tv2.setOnClickListener(new FragOrder.MyClickListener(1));
            tvs.add(tv1);
            tvs.add(tv2);



            //        初始化ViewPager组件
            initView();
            initViewPager();

            if (Config.loginStatus == 1) {
                // 获得phoneNum
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
                phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
                fresh();
            }
        }
        return view;
    }

    public void fresh(){

        if(ll != null){
            ll.removeAllViews();
        }
        if(history != null){
            history.removeAllViews();
        }
        new DownloadOrders(phone, new DownloadOrders.SuccessCallback() {


            @Override
            public void onSuccess(ArrayList<Order> orders) {

                for (Order o : orders) {
                    String number = o.getOrderNum();
                    String time = o.getTime();
                    String loc = o.getLocation();
                    String note = o.getNote();
                    String status = o.getStatus();
                    String date = o.getDate();
                    final Orderview newov = new Orderview(getActivity());

                    newov.setOrder_intro("小件快递");
                    newov.setOrder_num(number);
                    newov.setOrder_time(time);
                    newov.setOrder_loc(loc);
                    newov.setNum(number);
                    newov.setTime(date);
                    if (note.equals("none")) {
                        note = "无";
                    }
                    newov.setOrder_note(note);
                    if (status.equals("0")) {
                        newov.setOrder_status("已结束");
                        history.addView(newov);
                    } else if (status.equals("1")) {
                        newov.setOrder_status("正在送货");
                        ll.addView(newov);
                    } else if (status.equals("2")) {
                        newov.setOrder_status("订单异常");
                        ll.addView(newov);
                    }

                    newov.setCancelButtonListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                                    Toast.makeText(getActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        }
                    });
                    newov.setContactButtonListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                                    Toast.makeText(getActivity(), "点击了联系按钮", Toast.LENGTH_SHORT).show();
                        }
                    });
                    newov.setCancelButtonListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                                    Toast.makeText(getActivity(), "点击了修改按钮", Toast.LENGTH_SHORT).show();
                        }
                    });
                    newov.setGetCodeButtonListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), AtyGenCode.class);
                            intent.putExtra("code",newov.getNum());
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);

                        }
                    });
                }
            }
        }, new DownloadOrders.FailCallback() {

            @Override
            public void onFail() {
                Toast.makeText(getActivity(), R.string.fail_to_commit, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Config.loginStatus == 1) {
            tv1.setOnClickListener(new FragOrder.MyClickListener(0));
            tv2.setOnClickListener(new FragOrder.MyClickListener(1));
        }

    }

    private class MyClickListener implements View.OnClickListener {

        private int index;

        public MyClickListener(int index) {
            // TODO Auto-generated constructor stub
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //改变ViewPager当前显示页面
            pager.setCurrentItem(index);
        }
    }

    //初始化ViewPager中显示的数据
    public void initView() {
        // TODO Auto-generated method stub
        views = new ArrayList<View>();
        LayoutInflater li = getActivity().getLayoutInflater();
        views.add(li.inflate(R.layout.mod_current_order, null));
        views.add(li.inflate(R.layout.mod_history_order, null));
    }


    public void initViewPager() {
        // TODO Auto-generated method stub

        PagerAdapter adapter = new FragOrder.MyPagerAdapter();
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int index) {
                // TODO Auto-generated method stub
                for (int i = 0; i < tvs.size(); i++) {
                    if (i == index) {
                        tvs.get(i).setTextColor(Color.BLUE);
                    } else {
                        tvs.get(i).setTextColor(Color.rgb(55, 55, 55));
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        //有多少个切换页
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return views.size();
        }

        //对超出范围的资源进行销毁
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            //super.destroyItem(container, position, object);
            container.removeView(views.get(position));
        }

        //对显示的资源进行初始化
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            //return super.instantiateItem(container, position);
            container.addView(views.get(position));
            return views.get(position);
        }
    }

}
