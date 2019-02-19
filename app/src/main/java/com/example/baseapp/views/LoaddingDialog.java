package com.example.baseapp.views;

/**
 * Created by zfgx on 2016/2/28.
 */

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baseapp.R;
import com.example.baseapp.utils.LoadingDialogUtils;
import com.example.baseapp.utils.LogUtils;

import androidx.annotation.Nullable;


/**
 * 网络加载框
 *
 * @author zfgx
 * @time 2014年11月29日 下午11:08:03
 */
public class LoaddingDialog extends Dialog {

    private Context mContext;

    TextView txt_msg;
    private final ImageView imageView_loadIcon;

    public LoaddingDialog(Context context, String msg) {
        super(context, R.style.LoddingDialogStyle);
        this.mContext = context;
        setContentView(R.layout.dialog_progressbar);

        txt_msg = (TextView) findViewById(R.id.dialog_message_text);
        txt_msg.setText(msg);

        imageView_loadIcon = (ImageView) findViewById(R.id.imageView_LoadIcon);
        getWindow().setDimAmount(0);

        setCanceledOnTouchOutside(false);
    }

    /**
     * 设置文本提示信息
     *
     * @param msg
     */
    public void setMessageText(String msg) {
        txt_msg.setText(msg);
    }

    @Override
    public void show() {
        try {
            startAnim();
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAnim() {
        try {
            ObjectAnimator anim = ObjectAnimator
                    .ofFloat(imageView_loadIcon, "rotation", 0, 360);
            anim.setDuration(1100);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.setRepeatMode(ValueAnimator.RESTART);
            //设置差值器
            LinearInterpolator lin = new LinearInterpolator();
            anim.setInterpolator(lin);
            anim.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        imageView_loadIcon.clearAnimation();
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
