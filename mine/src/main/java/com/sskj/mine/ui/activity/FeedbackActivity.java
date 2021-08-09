package com.sskj.mine.ui.activity;

import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.presenter.FeedbackPresenter;

import butterknife.BindView;

/**
 * 意见反馈
 */
@Route(path = RConfig.MINE_FEEDBACK)
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> {
    @BindView(R2.id.et_content)
    EditText et_content;
    @BindView(R2.id.et_contact)
    EditText et_contact;
    @BindView(R2.id.btn_commit)
    Button btn_commit;
    private ToastNextInputs inputs;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_feedback;
    }

    @Override
    public FeedbackPresenter getPresenter() {
        return new FeedbackPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.minefeedbackActivity1));
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs
                .add(et_content, SchemeUtil.notEmpty(et_content))
                .add(et_contact, SchemeUtil.notEmpty(et_contact))
        ;
    }

    @Override
    protected void initEvent() {
        ClickUtil.click(btn_commit, () -> {
            if (inputs.test()) {
                mPresenter.sendRequest(et_content.getText().toString(), et_contact.getText().toString());
            }
        });
    }
    public void success() {
        onBackPressed();
    }
}
