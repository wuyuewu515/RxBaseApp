package com.example.baseapp.modules.home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.baseapp.R;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author: Five_伍
 * @create: 2019/2/20
 * @Describe: 首页recycleview的适配器
 */
public class HomeDataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeDataAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title, item);

    }
}
