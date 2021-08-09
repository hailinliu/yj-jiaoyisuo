package com.sskj.ident.ui.activity;


import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.ident.R;
import com.sskj.ident.R2;
import com.sskj.ident.component.DaggerUserDataComponent;
import com.sskj.ident.presenter.VerificationSecondActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.ImageUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.IDENT_VERIFICATION_SECOND)
public class VerificationSecondActivity extends BaseActivity<VerificationSecondActivityPresenter> {
    @BindView(R2.id.ivCardHolder)
    ImageView ivCardHolder;
    @BindView(R2.id.ivCardFront)
    ImageView ivCardFront;
    @BindView(R2.id.ivCardBack)
    ImageView ivCardBack;
    @BindView(R2.id.btSubmit)
    Button btSubmit;
    Map<ImageView, String> picMap = new HashMap<>();
    private ImageView ivSelect;//当前选中的
    @Inject
    UserViewModel userViewModel;
    private String filePath;

    @Override
    protected int getLayoutId() {
        return R.layout.ident_activity_verification_second;
    }

    @Override
    public VerificationSecondActivityPresenter getPresenter() {
        return new VerificationSecondActivityPresenter();
    }

    @Override
    protected void initView() {
        setTitle(App.INSTANCE.getString(R.string.identverificationSecondActivity1));
        DaggerUserDataComponent.create().inject(this);
        ClickUtil.click(ivCardHolder, () -> {
            ivSelect = ivCardHolder;
            selectPic();
        });
        ClickUtil.click(ivCardBack, () -> {
            ivSelect = ivCardBack;
            selectPic();
        });
        ClickUtil.click(ivCardFront, () -> {
            ivSelect = ivCardFront;
            selectPic();
        });
        ClickUtil.click(btSubmit, () -> {
            if (picMap.size() < 2) {
                ToastUtil.showShort(App.INSTANCE.getString(R.string.identverificationSecondActivity2));
                return;
            }
            mPresenter.apiAuthenticationAdvancedcertification(picMap.get(ivCardFront), picMap.get(ivCardBack), "");
        });
    }

    private void selectPic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                    mPresenter.submitVerify(new File(filePath));
                    break;
            }

        }
    }


    public void certificationSuccess() {
        userViewModel.update();
        finish();
    }

    public void uploadSuccess(String url) {
        ImageUtil.setImage(filePath, ivSelect);
        picMap.put(ivSelect, url);
    }
}