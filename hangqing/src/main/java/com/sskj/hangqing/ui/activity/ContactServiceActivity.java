package com.sskj.hangqing.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.presenter.ContactServiceActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RConfig.CONTACTSERVICEACTIVITY)
public class ContactServiceActivity extends BaseActivity<ContactServiceActivityPresenter> {

    @BindView(R2.id.tv_leixing)
    TextView tvLeixing;
    @BindView(R2.id.image)
    ImageView image;
    @BindView(R2.id.image1)
    ImageView image1;
    @BindView(R2.id.image2)
    ImageView image2;
    @BindView(R2.id.image3)
    ImageView image3;
    @BindView(R2.id.bt_submit)
    Button btSubmit;
    @BindView(R2.id.et_content)
    EditText etContent;
    private int type;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_conact_service;
    }

    @Override
    protected void initView() {
        // super.initView();
        setTitle(App.INSTANCE.getString(R.string.hang_lianxi));
        tvLeixing.setVisibility(View.INVISIBLE);
        image.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initEvent() {
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=1;
                selectPic(type);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=2;
                selectPic(type);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=3;
                selectPic(type);
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getFeedback(etContent.getText().toString());
            }
        });
    }
    private void selectPic(int type) {

        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        PictureSelector.create(this)
                                .openGallery(PictureMimeType.ofImage())
                                .previewImage(true)
                                .isCamera(true)
                                .imageFormat(PictureMimeType.JPEG)
                                .maxSelectNum(1)
                                .compress(true)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }
                });
    }
    String filePath;
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
                    if(type==1){
                        image1.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    }else if(type==2){
                        image2.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    }else if(type==3){
                        image3.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    }
                    break;
            }

        }
    }
    @Override
    public ContactServiceActivityPresenter getPresenter() {
        return new ContactServiceActivityPresenter();
    }


    public void setData(String msg) {
        ToastUtil.showShort(msg);
    }
}
