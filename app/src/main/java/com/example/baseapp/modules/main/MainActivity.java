package com.example.baseapp.modules.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseActivity;
import com.example.baseapp.modules.home.HomeFragment;
import com.example.baseapp.modules.mycar.MyCarFragment;
import com.example.baseapp.modules.personal.PersonalFragment;
import com.example.baseapp.utils.AppManager;

import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View {

    public static final int READ_PHONE_STATE = 10001;

    int mIndex = 0;
    private Fragment[] mFragments;

    @Override
    protected Integer getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }


    @Override
    protected void initData() {
        requestPermission();
        //首页
        HomeFragment homeFragment = new HomeFragment();
        //我的车辆
        MyCarFragment myCarFragment = new MyCarFragment();
        //个人中心
        PersonalFragment personalFragment = new PersonalFragment();
        //添加到数组
        mFragments = new Fragment[]{homeFragment, myCarFragment, personalFragment};
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.fragment_home, homeFragment).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }

    /**
     * fragment切换
     *
     * @param index
     */
    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fragment_home, mFragments[index]).show(mFragments[index]);
        } else {
            ft.hide(mFragments[mIndex]);
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;

    }


    @Override
    protected void bindVP() {
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    private boolean requestPermission() {
        String[] perms = {Manifest.permission.READ_PHONE_STATE};

        //判断有没有权限
        if (EasyPermissions.hasPermissions(mActivity, perms)) {
            // 如果有权限了, 就做你该做的事情
            return true;
        } else {
            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是你要申请的权限
            EasyPermissions.requestPermissions(mActivity, "要这个权限啊", READ_PHONE_STATE, perms);
            return false;
        }
    }

    public static void inTo(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    private boolean isExit = false;

    @Override
    public void onBackPressed() {

        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            showToast("再按一次退出应用");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            //退出app
            AppManager.AppExit(mActivity);
        }
    }


    @OnClick({R.id.home, R.id.myCar, R.id.personal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home: //首页
                setIndexSelected(0);
                break;
            case R.id.myCar: //我的车辆
                setIndexSelected(1);
                break;
            case R.id.personal: //个人中心
                setIndexSelected(2);
                break;
        }
    }
}
