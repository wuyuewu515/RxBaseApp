package com.example.baseapp;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseapp.bean.UserLoginInfo;
import com.example.baseapp.exception.ApiException;
import com.example.baseapp.net.ApiObserver;
import com.example.baseapp.net.NetWorkManager;
import com.example.baseapp.net.ResponseTransformer;
import com.example.baseapp.net.SchedulerProvider;
import com.example.baseapp.utils.TelephonyUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    public static final int READ_PHONE_STATE = 10001;
    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.tv_btn2)
    TextView tvBtn2;

    private Activity activity;

    private SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = this;
        requestPermission();
        tvBtn.setText("你好啊");
        tvBtn2.setText("点击我试试");
    }

    private boolean requestPermission() {
        String[] perms = {Manifest.permission.READ_PHONE_STATE};

        //判断有没有权限
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 如果有权限了, 就做你该做的事情
            return true;
        } else {
            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是你要申请的权限
            EasyPermissions.requestPermissions(this, "要这个权限啊", READ_PHONE_STATE, perms);
            return false;
        }
    }

    @OnClick(R.id.tv_btn2)
    public void onViewClicked() {


        NetWorkManager.getInstance().getExampleAPI().login("15601645052", "123456", TelephonyUtils.getDeviceId(this))
                .compose(ResponseTransformer.handleResult())
                .compose(schedulerProvider.applySchedulers())
                .subscribe(new ApiObserver<UserLoginInfo>() {

                    @Override
                    public void onApiSuccess(UserLoginInfo data) {
                        Log.i("MainActivity", "getMessage===：流程成功...");
                        Log.i("MainActivity", "data===" + data.toString());
                    }

                    @Override
                    public void onApiError(ApiException apiException) {
                        Log.i("MainActivity", "getMessage===：" + apiException.getDisplayMessage());
                        Log.i("MainActivity", "getCode===：" + apiException.getCode());
                        Toast.makeText(activity, apiException.getDisplayMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
