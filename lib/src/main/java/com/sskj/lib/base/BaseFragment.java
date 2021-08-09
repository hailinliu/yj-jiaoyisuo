package com.sskj.lib.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bulong.rudeness.RudenessScreenHelper;
import com.sskj.lib.http.IBaseViewLife;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Lee on 2018/2/28 0028.
 */

public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IBaseViewLife {

    protected P mPresenter;
    private Unbinder mUnbinder;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RudenessScreenHelper.resetDensity(getContext(), 750);

        if (inflate == null) {
            inflate = inflater.inflate(getLayoutId(), null);
            mPresenter = getPresenter();
            mPresenter.attachView(this, getActivity());
            mUnbinder = ButterKnife.bind(this, inflate);
            initView();
            initData();
            initEvent();
        }
        if (inflate.getParent() != null) {
            ((ViewGroup) inflate.getParent()).removeView(inflate);
        }
        return inflate;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.cancelRequest();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

    }


    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {

    }

    @Override
    public void showLoading() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) (getActivity())).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) (getActivity())).hideLoading();
        }
    }

    @Override
    public String getText(TextView textView) {
        return textView.getText().toString();
    }


    @Override
    public int color(int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    @Override
    public Drawable drawable(int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    @Override
    public void setText(TextView textView, String text) {
        if (textView != null) {
            textView.setText(text);
        }
        if (textView instanceof EditText) {
            ((EditText) textView).setSelection(textView.getText().length());
        }
    }
}
