package com.sskj.lib.ui.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.lib.R;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.box.LiveDataBus;

import sskj.lee.appupdatelibrary.BaseUpdateDialogFragment;
import sskj.lee.appupdatelibrary.BaseVersion;
import sskj.lee.appupdatelibrary.NumberProgressBar;


@Route(path = RConfig.LIB_UPDATE)//版本更新
public class AppUpDateFragment extends BaseUpdateDialogFragment implements View.OnClickListener {

    private NumberProgressBar mUpdateProgressbar;
    private View mDialogConfirm;
    private View mDialogViewCancel;


    @Override
    public int getLayoutId() {
        return R.layout.lib_fragment_app_update;
    }

    @Override
    protected void initView(View view, BaseVersion versionData) {
        mDialogConfirm = view.findViewById(R.id.app_update_confirm);
        mDialogViewCancel = view.findViewById(R.id.view_cancel);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(versionData.getTitle());
        mDialogViewCancel.setOnClickListener(this);
        mDialogConfirm.setOnClickListener(this);
        if (mVersionData.isMustUp()) {
            mDialogViewCancel.setVisibility(View.GONE);
        } else {
            mDialogViewCancel.setVisibility(View.VISIBLE);
        }
        TextView content = view.findViewById(R.id.tvContent);
        mUpdateProgressbar = view.findViewById(R.id.progress);
        content.setText(versionData.getContent());
    }

    @Override
    protected void onDownLoadStart() {
        mDialogConfirm.setEnabled(false);
        mUpdateProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDownLoadUpdate(int progress) {
        mUpdateProgressbar.setProgress(progress);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.view_cancel) {
            if (mVersionData.isMustUp()) {
                showMustUpDialog();
            } else {
                LiveDataBus.get().with(RxBusCode.SHOW_GONGGAO,Integer.class)
                        .postValue(1);
                dismiss();
            }
        } else if (v.getId() == R.id.app_update_confirm) {
            startDownload();

        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        // 这里吧原来的commit()方法换成了commitAllowingStateLoss()
        ft.commitAllowingStateLoss();
    }
}
