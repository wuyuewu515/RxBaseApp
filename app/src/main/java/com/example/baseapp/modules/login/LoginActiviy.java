package com.example.baseapp.modules.login;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseActivity;
import com.example.baseapp.modules.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: Five_伍
 * @create: 2019/2/15
 * @Describe: 登录页面
 */
public class LoginActiviy extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected void bindVP() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected Integer getContentId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mPresenter.login();
    }

    @Override
    public String getNumber() {
        return etNumber.getText().toString();
    }

    @Override
    public String getPwd() {
        return etPwd.getText().toString();
    }

    @Override
    public boolean checkInput() {

        boolean chekNumber = TextUtils.isEmpty(etNumber.getText().toString().trim());
        if (chekNumber) {
            showToast("请输入账号");
            return false;
        }
        boolean checkPwd = TextUtils.isEmpty(etPwd.getText().toString().trim());
        if (checkPwd) {
            showToast("请输入密码");
            return false;
        }
        return true;
    }

    @Override
    public void intoActivity() {
        MainActivity.inTo(mActivity);
    }

    public static void inTo(Activity activity) {
        Intent intent = new Intent(activity, LoginActiviy.class);
        activity.startActivity(intent);
    //    activity.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
