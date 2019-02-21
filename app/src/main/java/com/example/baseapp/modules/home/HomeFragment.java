package com.example.baseapp.modules.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.baseapp.R;
import com.example.baseapp.base.BaseFragment;
import com.example.baseapp.utils.LogUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author: Five_伍
 * @create: 2019/2/19
 * @Describe:
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private float totalHeight;      //总高度
    private float toolBarHeight;    //toolBar高度
    private float offSetHeight;     //总高度 -  toolBar高度  布局位移值


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appBarlayout)
    AppBarLayout appBarlayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.view_content)
    ConstraintLayout ivDog;


    private HomeDataAdapter adapter;

    @Override
    protected void bindVP() {
        mPresenter = new HomePresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected Integer getContentId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        ((AppCompatActivity) mActivity).setSupportActionBar(toolbar);

        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle("");//设置标题的名字

        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.GREEN);//设置展开状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        toolbar.setAlpha(0f);
        mCollapsingToolbarLayout.setAlpha(1f);
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
        mCollapsingToolbarLayout.setExpandedTitleGravity(Gravity.BOTTOM | Gravity.CENTER);//设置展开后标题的位置
        mCollapsingToolbarLayout.setExpandedTitleMarginBottom((int) mActivity.getResources().getDimension(R.dimen.qb_px_10));
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.post(new Runnable() {
            @Override
            public void run() {
                totalHeight = appBarlayout.getMeasuredHeight();
                toolBarHeight = toolbar.getMeasuredHeight();

                offSetHeight = totalHeight - toolBarHeight;
                LogUtils.LOG_D(HomeFragment.class, "可以移动距离的高度是" + offSetHeight);

            }
        });

    }

    @Override
    protected void initListener() {
        appBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

                LogUtils.LOG_D(HomeFragment.class, "移动的距离是" + i);

                if (0 == offSetHeight)
                    return;
                float dogAl = (-i) / offSetHeight;
                LogUtils.LOG_D(HomeFragment.class, "dogAl==" + dogAl);

                ivDog.setAlpha(1 - dogAl);
                toolbar.setAlpha(dogAl);

//                //第一次进入获取高度，以及差值 高度差比值
//                if (llHeight == 0){
//                    llHeight = ll.getMeasuredHeight();
//                    params = (FrameLayout.LayoutParams) ll.getLayoutParams();
//
//                    //算出高度偏移量比值  相对与llHeight
//                    llHeightOffScale = 1.0f - (toolBarHeight / llHeight);
//
//                    //得到滑动差值 就是布局marginTop
//                    llOffDistance = params.topMargin;
//                    //得到滑动比值
//                    llOffDistanceScale = llOffDistance / offSetHeight;
//                }
//
//                //滑动一次 得到渐变缩放值
//                float alphaScale = (-verticalOffset) / offSetHeight;
//
//                //获取高度缩放值
//                float llHeightScale = 1.0f-(llHeightOffScale*((-verticalOffset)/offSetHeight));
//                //计算maigintop值
//                float distance = llOffDistance - (-verticalOffset)*llOffDistanceScale;
//
//                image.setAlpha(1.0f-alphaScale);
//                bac.setAlpha(alphaScale);
//                params.height = (int)(llHeight* llHeightScale);
//                params.setMargins(0,(int)distance,0,0);
//
//                fl.requestLayout();


            }
        });
    }

    @Override
    public void setAdapterData(List<String> datas) {
        if (null == adapter) {
            adapter = new HomeDataAdapter(datas);
            adapter.setEmptyView(getEmptyView());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(datas);
        }
    }
}
