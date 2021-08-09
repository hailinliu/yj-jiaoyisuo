package com.sskj.hangqing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.bean.NoticeBean;
import com.sskj.hangqing.presenter.GonggaoActiivityPresenter;
import com.sskj.lib.Constans;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.GONGGAOACTIIVITY)
public class GonggaoActiivity extends BaseActivity<GonggaoActiivityPresenter> {
    @BindView(R2.id.rv)
    RecyclerView rv;
    private SlimAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_gonggao_new_list;
    }

    @Override
    public GonggaoActiivityPresenter getPresenter() {
        return new GonggaoActiivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.hang_announ));
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = SlimAdapter.create().register(R.layout.hang_activity_gonggao_list_item, new SlimInjector<NoticeBean.ContentBean>() {
            @Override
            public void onInject(NoticeBean.ContentBean bean, IViewInjector iViewInjector, List list) {
                iViewInjector.text(R.id.tv_title,bean.getTitle())
                            .text(R.id.tv_content,bean.getContent())
                            .text(R.id.tv_time,bean.getCreateTime())
                            .clicked(R.id.tv_chakan, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(GonggaoActiivity.this, GuideWebActivity.class);
                                    intent.putExtra(Constans.IS_HANGQING_NOTICE, true);
                                    intent.putExtra(Constans.HEAD, App.INSTANCE.getString(R.string.hangqinggonggaoListActivity2));
                                    intent.putExtra(Constans.TITLE, bean.getTitle());
                                    intent.putExtra(Constans.CONTENT, bean.getContent());
                                    intent.putExtra(Constans.TIME, bean.getCreateTime());
                                    startActivity(intent);
                                }
                            });
            }
        }).attachTo(rv).updateData(new ArrayList());
        mPresenter.getNotice();
    }

    public void updateNotice(List<NoticeBean.ContentBean> content) {
        adapter.updateData(content);
    }


}
