package com.sskj.mine.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hey.lib.HeySpinner;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sskj.common.base.App;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.DisposUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.BaseHttpConfig;
import com.sskj.lib.SPConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.enums.AreaCodeEnum;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.http.JsonCallBack;
import com.sskj.lib.util.SchemeUtil;
import com.sskj.lib.util.SendCodeUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.presenter.BindMobilePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * @author Hey
 * Create at  2019/05/01
 */
public class BindMobileActivity extends BaseActivity<BindMobilePresenter> {


    @BindView(R2.id.mobile_edt)
    EditText mobileEdt;
    @BindView(R2.id.code_edt)
    EditText codeEdt;
    @BindView(R2.id.pwd_edt)
    EditText pwdEdt;
    @BindView(R2.id.get_code_tv)
    TextView getCodeTv;
    @BindView(R2.id.submit_btn)
    Button submitBtn;
    @BindView(R2.id.tv_type)
    TextView tv_type;
    @BindView(R2.id.heySpinner)
    HeySpinner heySpinner;
    @BindView(R2.id.ll_getCode)
    LinearLayout llGetCOde;
    @BindView(R2.id.view_line_code)
    View viewLine;

    private Boolean isBindMobile;
    private boolean isUpdate;
    private Disposable disposableSubscriber;
    private ToastNextInputs inputCodes;
    private ToastNextInputs input;
    private ToastNextInputs inputUpdate;
    private AreaCodeEnum areaCodeEnumChoose;
    @Override
    public int getLayoutId() {
        return R.layout.mine_activity_bind_mobile;
    }

    @Override
    public BindMobilePresenter getPresenter() {
        return new BindMobilePresenter();
    }

    public static void start(Context context, boolean isBindMobile, boolean isUpdate) {
        Intent intent = new Intent(context, BindMobileActivity.class);
        intent.putExtra("isBindMobile", isBindMobile);
        intent.putExtra("isUpdate", isUpdate);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
       // super.initData();
        OkGo.<HttpData<List<AreaCodeEnum>>>post(BaseHttpConfig.BASE_URL + "/app/user/getAreaCode")
                .execute(new JsonCallBack<HttpData<List<AreaCodeEnum>>>() {
                    @Override
                    public void onSuccess(Response<HttpData<List<AreaCodeEnum>>> response) {
                        HttpData<List<AreaCodeEnum>> httpData = response.body();

                        SPUtil.putBean(SPConfig.AREA_CODE_LIST, (ArrayList) httpData.getData());
                        SendCodeUtil.areaCode(BindMobileActivity.this, heySpinner, areaCodeEnum -> areaCodeEnumChoose = areaCodeEnum);
                    }
                });
    }

    @Override
    public void initView() {
        isBindMobile = getIntent().getBooleanExtra("isBindMobile", true);
        isUpdate = getIntent().getBooleanExtra("isUpdate", false);
        if (isUpdate) {
            setTitle(isBindMobile ? App.INSTANCE.getString(R.string.minebindMobileActivity1) : App.INSTANCE.getString(R.string.minebindMobileActivity2));
            llGetCOde.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            setTitle(isBindMobile ? App.INSTANCE.getString(R.string.minebindMobileActivity3) : App.INSTANCE.getString(R.string.minebindMobileActivity4));
        }

        tv_type.setText(isBindMobile ? App.INSTANCE.getString(R.string.minebindMobileActivity5) : App.INSTANCE.getString(R.string.minebindMobileActivity6));
        mobileEdt.setHint(isBindMobile ? App.INSTANCE.getString(R.string.minebindMobileActivity7) : App.INSTANCE.getString(R.string.minebindMobileActivity8));
        heySpinner.setVisibility(isBindMobile ? View.VISIBLE : View.GONE);
       /* SendCodeUtil.areaCode(this, heySpinner, areaCodeEnum -> areaCodeEnumChoose = areaCodeEnum);
        SendCodeUtil.areaCode(this, heySpinner, new SendCodeUtil.AreaCodeBack() {
            @Override
            public void onCode(AreaCodeEnum areaCodeEnum) {

            }
        });*/
        inputCodes = new ToastNextInputs();
        inputCodes.add(mobileEdt, SchemeUtil.notEmpty(mobileEdt), isBindMobile ? SchemeUtil.phone() : SchemeUtil.email());

        input = new ToastNextInputs();

        input.add(mobileEdt, SchemeUtil.notEmpty(mobileEdt), isBindMobile ? SchemeUtil.phone() : SchemeUtil.email())
                .add(codeEdt, SchemeUtil.notEmpty(codeEdt), SchemeUtil.smsCode())
                .add(pwdEdt, SchemeUtil.notEmpty(pwdEdt), SchemeUtil.pwd());

        inputUpdate = new ToastNextInputs();
        inputUpdate.add(mobileEdt, SchemeUtil.notEmpty(mobileEdt), isBindMobile ? SchemeUtil.phone() : SchemeUtil.email())
                .add(pwdEdt, SchemeUtil.notEmpty(pwdEdt), SchemeUtil.pwd());

    }

    public static void start(Context context, boolean isBindMobile) {
        Intent intent = new Intent(context, BindMobileActivity.class);
        intent.putExtra("isBindMobile", isBindMobile);
        context.startActivity(intent);
    }

    @Override
    protected void initEvent() {

        ClickUtil.click(getCodeTv, () -> {
            if (inputCodes.test()) {
                mPresenter.sendCode(getText(mobileEdt), heySpinner.getText().toString());
            }
        });

        ClickUtil.click(submitBtn, () -> {
            if (!isUpdate) {
                if (input.test()) {
                    mPresenter.bindMobile(getText(mobileEdt), getText(codeEdt), getText(pwdEdt), heySpinner.getText().toString());
                }
            } else {
                if (inputUpdate.test()) {
                    mPresenter.updateMobile(getText(mobileEdt), SPUtil.get(SPConfig.ID, ""), getText(pwdEdt));
                }
            }

        });
    }

    public void onAuthCodeSuccess() {
        startCount();
    }


    public void startCount() {
        getCodeTv.setEnabled(false);
        DisposUtil.close(disposableSubscriber);
        disposableSubscriber = SendCodeUtil.timeCount(this, getCodeTv);

    }


}
