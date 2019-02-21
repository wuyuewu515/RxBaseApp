package com.example.baseapp.modules.home;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Five_伍
 * @create: 2019/2/19
 * @Describe:
 */
public class HomePresenter extends HomeContract.Presenter {

    private List<String> datas = new ArrayList<>();

    @Override
    protected void start() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 50; i++) {
            datas.add(new String("当前位置" + i));
        }
        mView.setAdapterData(datas);
    }
}
