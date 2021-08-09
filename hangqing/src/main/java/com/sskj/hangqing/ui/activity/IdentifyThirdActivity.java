package com.sskj.hangqing.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.IdentifyActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.SchemeUtil;

import butterknife.BindView;

@Route(path = RConfig.IDENTIFYTHIRDACTIVITY)
public class IdentifyThirdActivity extends BaseActivity<IdentifyActivityPresenter> {
    @BindView(R2.id.et_name)
    EditText et_name;
    @BindView(R2.id.et_zhengjianhao)
    EditText et_zhengjianhao;
    @BindView(R2.id.logout_btn)
    Button button;
    @Autowired
    int type;
    @Autowired
    String zcountry;
    private AndroidNextInputs inputs;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_identifythird;
    }

    @Override
    public IdentifyActivityPresenter getPresenter() {
        return new IdentifyActivityPresenter();
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        //super.initView();
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_name, SchemeUtil.notEmpty(et_name));
        inputs.add(et_zhengjianhao,SchemeUtil.notEmpty(et_zhengjianhao));
    }

    @Override
    protected void initData() {
        //super.initData();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(inputs.test()){
                 if(et_zhengjianhao.getText().toString().length()>=10){
                     ARouter.getInstance().build(RConfig.IDENTIFYFOURACTIVITY).withString("zcountry",zcountry).withString("name",et_name.getText().toString()).withString("Idcard",et_zhengjianhao.getText().toString()).withInt("newtype",type).navigation();
                     finish();
                 }else {
                     ToastUtil.showShort(App.INSTANCE.getString(R.string.hang_zhengjianhao));
                 }
             }
            }
        });
    }
}
