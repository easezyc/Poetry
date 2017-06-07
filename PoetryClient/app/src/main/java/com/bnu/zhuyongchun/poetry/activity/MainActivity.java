package com.bnu.zhuyongchun.poetry.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bnu.zhuyongchun.poetry.R;
import com.bnu.zhuyongchun.poetry.entity.Config;
import com.bnu.zhuyongchun.poetry.fragment.HomeFragment;
import com.bnu.zhuyongchun.poetry.fragment.RememberedFragment;
import com.bnu.zhuyongchun.poetry.fragment.SearchFragment;
import com.bnu.zhuyongchun.poetry.fragment.AddnewfriendFragment;
import com.bnu.zhuyongchun.poetry.fragment.MyfriendFragment;
import com.bnu.zhuyongchun.poetry.fragment.UserFragment;
import com.bnu.zhuyongchun.poetry.view.CustomFontTextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private HomeFragment homeFragment;
    private RememberedFragment rememberFragment;
    private SearchFragment searchFragment;
    private UserFragment userFragment;
    private FragmentManager fragmentManager;
    private ImageView homeImage,searchImage,rememberedImage,userImage;
    private TextView homeText,searchText,rememberedText,userText;
    private AddnewfriendFragment addnewfriendFragment;
    private MyfriendFragment myfriendFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomFontTextView.setDefaultFont(this,"MONOSPACE","fonts/kaiti.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        initView();
        setTabSelection(0);
    }

    private void initView()
    {
        View home_layout=findViewById(R.id.home_layout);
        View user_layout=findViewById(R.id.user_layout);
        View search_layout=findViewById(R.id.search_layout);
        View remembered_layout=findViewById(R.id.remembered_layout);
        userText=(TextView)findViewById(R.id.user_text);
        userImage=(ImageView)findViewById(R.id.user_image);
        homeText=(TextView)findViewById(R.id.home_text);
        homeImage=(ImageView)findViewById(R.id.home_image);
        searchText=(TextView)findViewById(R.id.search_text);
        searchImage=(ImageView)findViewById(R.id.search_image);
        rememberedText=(TextView)findViewById(R.id.remembered_text);
        rememberedImage=(ImageView)findViewById(R.id.remembered_image);
        try{
            home_layout.setOnClickListener(this);
            user_layout.setOnClickListener(this);
            search_layout.setOnClickListener(this);
            remembered_layout.setOnClickListener(this);
        }
        catch (NullPointerException e)
        {
            Log.i("error",e.toString());
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_showmyfriend) {
            setTabSelection(4);
        } else if (id == R.id.nav_addfriend) {
            setTabSelection(5);

        } else if (id == R.id.nav_exchange) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_exit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.search_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.remembered_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.user_layout:
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        //清除所有的文字和图片选中状态
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        //hideFragments(transaction);
        switch (index) {
            case 0://选择主页
                homeImage.setImageResource(R.drawable.home_selected);
                homeText.setTextColor(Color.parseColor(Config.getConfig().getSelected_color()));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();

                }
                transaction.replace(R.id.content, homeFragment);
                break;
            case 1://点击搜索
                searchImage.setImageResource(R.drawable.search_selected);
                searchText.setTextColor(Color.parseColor(Config.getConfig().getSelected_color()));
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();

                }
                transaction.replace(R.id.content, searchFragment);

                break;
            case 2://点击已背
                rememberedImage.setImageResource(R.drawable.remember_selected);
                rememberedText.setTextColor(Color.parseColor(Config.getConfig().getSelected_color()));
                if (rememberFragment == null) {
                    rememberFragment = new RememberedFragment();

                }
                transaction.replace(R.id.content, rememberFragment);

                break;
            case 3://点击user
                userImage.setImageResource(R.drawable.user_selected);
                userText.setTextColor(Color.parseColor(Config.getConfig().getSelected_color()));
                if (userFragment == null) {
                    userFragment = new UserFragment();

                }
                transaction.replace(R.id.content, userFragment);

                break;
            case 4:
                if (myfriendFragment == null) {
                    myfriendFragment = new MyfriendFragment();

                }
                transaction.replace(R.id.content, myfriendFragment);
                break;
            case 5:
                if (addnewfriendFragment == null) {
                    addnewfriendFragment = new AddnewfriendFragment();

                }
                transaction.replace(R.id.content, addnewfriendFragment);
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        userImage.setImageResource(R.drawable.user_unselected);
        userText.setTextColor(Color.parseColor(Config.getConfig().getUnselected_color()));
        homeImage.setImageResource(R.drawable.home_unselected);
        homeText.setTextColor(Color.parseColor(Config.getConfig().getUnselected_color()));
        searchImage.setImageResource(R.drawable.search_unselected);
        searchText.setTextColor(Color.parseColor(Config.getConfig().getUnselected_color()));
        rememberedImage.setImageResource(R.drawable.remember_unselected);
        rememberedText.setTextColor(Color.parseColor(Config.getConfig().getUnselected_color()));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (searchFragment != null) {
            transaction.hide(searchFragment);
        }
        if (rememberFragment != null) {
            transaction.hide(rememberFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
        if(myfriendFragment!=null){
            transaction.hide(myfriendFragment);
        }
        if(addnewfriendFragment!=null){
            transaction.hide(addnewfriendFragment);
        }
    }
}
