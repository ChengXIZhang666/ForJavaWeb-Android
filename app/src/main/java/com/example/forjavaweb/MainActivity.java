package com.example.forjavaweb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forjavaweb.adapter.NewsAdapter;
import com.example.forjavaweb.bean.NewsBean;
import com.example.forjavaweb.bean.User;
import com.example.forjavaweb.tool.JsonUtil;
import com.example.forjavaweb.tool.LogUtil;
import com.example.forjavaweb.tool.ObjectUtil;
import com.example.forjavaweb.tool.RotateUtil;
import com.example.forjavaweb.tool.ServerUtil;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Banner banner;
    private de.hdodenhof.circleimageview.CircleImageView me;
    private FloatingActionButton fab;
    private TextView nav_name;
    private NavigationView navigationView;
    private Button exit;
    private User user = null;
    public static final int UPDATE_TEXT = 1;//异步同步信号
    public static final int UPDATE_News = 2;//新闻信号
    public static final int UPDATE_Refresh = 3;//新闻信号
    public boolean isApply = false;
    private List<NewsBean> newsBeans = new LinkedList<>();
    private String responseData;
    private SwipeRefreshLayout swipeRefresh;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置进入、退出动画
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.LEFT));

        //获取来自服务器的user对象
        final Intent intent = getIntent();
//        String userFromServer = intent.getStringExtra("user");
//        User user = JsonUtil.JsonToObject(userFromServer, User.class);
        user = ObjectUtil.getUser(intent);

        //下拉刷新
        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.ThemeColor);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });
        //获取来自服务器的新闻
        getNews();


        LogUtil.ShowText("服务器", user.getuName());
        navigationView = findViewById(R.id.nav_view);
        nav_name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);
        nav_name.setText(user.getuName());
        if (user.getuCondition() == 1)
            isApply = true;

        //如果已经报名则隐去悬浮按钮
        fab = findViewById(R.id.fab);
        if (user.getuCondition() == 1) {
            fab.hide();
        }

        //悬浮按钮点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ApplyActivity.class);
//                startActivity(intent);
                //旋转
                fab.setAnimation(RotateUtil.rotateZeroToHalf());
                //Snackbar的点击事件
                Snackbar.make(view, "是否要前往报名报名？", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                toApply();
//                                ToastUtil.sendToast(MainActivity.this, "报名成功~");
                                Intent applyIntent = new Intent(MainActivity.this, ApplyActivity.class);
                                applyIntent.putExtra("user", user.getuId());
                                startActivityForResult(applyIntent, 1);
                            }
                        })
                        .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                            //消失
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                fab.setAnimation(RotateUtil.backAnimations());
                            }
                        }).show();
            }
        });

        //出现抽屉
        me = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.me);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //左部抽屉
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //默认选择主页
        navView.setCheckedItem(R.id.item_home);
        //抽屉的menu点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (isApply) {
                    switch (menuItem.getItemId()) {
//                    case R.id.item_home:
//                        Intent homeintent = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(homeintent);
//                        break;
                        case R.id.item_paper:
                            Intent paperintent = new Intent(MainActivity.this, PaperManageActivity.class);
                            paperintent.putExtra("uId", user.getuId());
                            startActivity(paperintent);
                            break;
                        case R.id.item_order:
//                            Intent orderintent = new Intent(MainActivity.this, OrderOnlineActivity.class);
//                            orderintent.putExtra("uId", user.getuId());
//                            startActivity(orderintent);
                            Intent orderintent = new Intent(MainActivity.this, BookOnlineActivity.class);
                            orderintent.putExtra("uId", user.getuId());
                            startActivity(orderintent);
                            break;
                        case R.id.item_intend:
                            Intent indentintent = new Intent(MainActivity.this, ChooseIndentManageActivity.class);
//                            indentintent.putExtra("uId", user.getuId());
                            startActivity(indentintent);
                            break;
                        default:
                            break;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "您还未报名大会，请先点击首页右下角的绿色悬浮按钮进行报名！", Toast.LENGTH_LONG).show();
                }
                //每次点击完都收回抽屉
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        //退出
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //轮播图设置
        setBanner();
    }


    //每次返回选中的项都是主页
    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.item_home);
    }

    //更新fab
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_News) {
                setNews();
            }
            if (msg.what == UPDATE_Refresh) {
                swipeRefresh.setRefreshing(false);
            }
        }
    };

    //fab旋转动画
//    private RotateAnimation rotateFab() {
//        RotateAnimation mFlipAnimation;
//        mFlipAnimation = new RotateAnimation(0, 45, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mFlipAnimation.setInterpolator(new LinearInterpolator());
//        mFlipAnimation.setDuration(300);//如果参数是0，就是无限旋转
//        mFlipAnimation.setFillAfter(true);
//        return mFlipAnimation;
//    }
//
//    private RotateAnimation backAnimations() {
//        RotateAnimation mFlipAnimation;
//        mFlipAnimation = new RotateAnimation(45, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mFlipAnimation.setInterpolator(new LinearInterpolator());
//        mFlipAnimation.setDuration(300);//如果参数是0，就是无限旋转
//        mFlipAnimation.setFillAfter(true);
//        return mFlipAnimation;
//    }

    //轮播图
    private void setBanner() {
        banner = (Banner) findViewById(R.id.banner);
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.b);
        images.add(R.drawable.c);
        images.add(R.drawable.d);
        images.add(R.drawable.e);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播时间
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //轮播图图片
    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource((Integer) path);
        }

        //createImageView 方法
        @Override
        public ImageView createImageView(Context context) {
            //fresco，需要创建提供的ImageView
            return new ImageView(context);
        }
    }

    //获取新闻的方法
    private void getNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
//                    172.18.179.70
//                            .url("http://172.18.179.70:8080/NewsServlet")
                            .url(ServerUtil.getNewsServlet())
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response != null) {
                        responseData = response.body().string();
                        LogUtil.ShowText(getClass().getName(), responseData);
                        if (!responseData.isEmpty()) {
                            Message message = new Message();
                            message.what = UPDATE_News;
                            Message message1 = new Message();
                            message1.what = UPDATE_Refresh;
                            handler.sendMessage(message);
                            handler.sendMessage(message1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setNews() {
        newsBeans = JsonUtil.JsonToObject(responseData, new TypeToken<List<NewsBean>>() {
        }.getType());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        while (newsBeans.isEmpty()) {
        NewsAdapter adapter = new NewsAdapter(newsBeans);
        recyclerView.setAdapter(adapter);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            fab.hide();
            isApply = true;
        }
    }
}