package com.sskj.hangqing.ui.fragment;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.BankFragmentPresenter;
import com.sskj.hangqing.ui.activity.AccountSafetyActivity;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.SchemeUtil;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.BANKFRAGMENT)
public class BankFragment extends BaseFragment<BankFragmentPresenter> {
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.et_bankname)
    EditText et_bankname;
    @BindView(R2.id.et_bankcode)
    EditText et_bankcode;
    @BindView(R2.id.et_kaihuhang)
    EditText et_kaihuhang;
    @BindView(R2.id.button)
    Button button;
    @Autowired
    String realname;
    @Autowired
    int status;
    private AndroidNextInputs inputs;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_yinhang;
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(inputs.test()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    BottomSheetUtil.showYanZhengMa1(getActivity(),"请输入你的交易密码","交易密码", new BottomSheetUtil.NewOnSure() {
                        @Override
                        public void onSure(String code) {
                            if(code!=null){
                                if(status==0){
                                    mPresenter.bindBank(et_bankname.getText().toString(),
                                            et_kaihuhang.getText().toString(),et_bankcode.getText().toString(),code,tv_name.getText().toString());
                                }else if(status==1){
                                    mPresenter.updteBank(et_bankname.getText().toString(),
                                            et_kaihuhang.getText().toString(),et_bankcode.getText().toString(),code,tv_name.getText().toString());
                                }

                            }
                        }
                    }, new BottomSheetUtil.GetTime() {
                        @Override
                        public void getTime() {
                        }
                    });
                }

            }
            }
        });
    }

    @Override
    protected void initData() {
       // super.initData();
        ARouter.getInstance().inject(this);
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_bankcode, SchemeUtil.notEmpty(et_bankcode))
                .add(et_bankname,SchemeUtil.notEmpty(et_bankname))
                .add(et_kaihuhang,SchemeUtil.notEmpty(et_kaihuhang));
        tv_name.setText(realname);
    }

    @Override
    protected BankFragmentPresenter getPresenter() {
        return new BankFragmentPresenter();
    }

    public void setUI(String message) {
        ToastUtil.showShort(message);
    }
}
