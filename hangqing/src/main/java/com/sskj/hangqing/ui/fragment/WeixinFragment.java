package com.sskj.hangqing.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.WeixinFragmentPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.box.verifier.ToastNextInputs;
import com.sskj.lib.util.BottomSheetUtil;
import com.sskj.lib.util.SchemeUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

@Route(path = RConfig.WEIXINFRAGMENT)
public class WeixinFragment extends BaseFragment<WeixinFragmentPresenter> {
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.et_wechat)
    EditText et_wechat;
    @BindView(R2.id.tv_upload)
    TextView tv_upload;
    @BindView(R2.id.bt_save)
    Button button;
    @Autowired
    String realname;
    @Autowired
    int status;
    private AndroidNextInputs inputs;
    private String filePath;

    @Override
    protected void initData() {
        //super.initData();
        ARouter.getInstance().inject(this);
        tv_name.setText(realname);
        inputs = new ToastNextInputs();
        inputs.clear();
        inputs.add(et_wechat, SchemeUtil.notEmpty(et_wechat));
    }
    @Override
    protected void initEvent() {
        //super.initEvent();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputs.test()){
                    BottomSheetUtil.showYanZhengMa1(getActivity(),"请输入你的交易密码","交易密码", new BottomSheetUtil.NewOnSure() {
                        @Override
                        public void onSure(String code) {
                            if(code!=null){
                                if(status==0){
                                    mPresenter.bindWeixin(et_wechat.getText().toString(),filePath,code,tv_name.getText().toString());
                                }else {
                                    mPresenter.updataWeixin(et_wechat.getText().toString(),filePath,code,tv_name.getText().toString());
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
        });
        tv_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPic();
            }
        });
    }
    private void selectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = selectList.get(0);
                    if (media.isCompressed()) {
                        filePath = media.getCompressPath();
                    } else {
                        filePath = media.getPath();
                    }
                    mPresenter.onload(new File(filePath));
                    break;
            }

        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.hang_fragment_weixin;
    }

    @Override
    protected WeixinFragmentPresenter getPresenter() {
        return new WeixinFragmentPresenter();
    }

    public void setUI(String message) {
        ToastUtil.showShort(message);
    }

    public void putList(String toString) {
        filePath = toString;
    }
}
