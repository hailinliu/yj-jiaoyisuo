package com.sskj.hangqing.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sskj.common.util.ToastUtil;
import com.sskj.hangqing.R;
import com.sskj.hangqing.R2;
import com.sskj.hangqing.component.DaggerUserDataComponent;
import com.sskj.hangqing.http.HttpConfig;
import com.sskj.hangqing.presenter.IdentifyActivityPresenter;
import com.sskj.hangqing.presenter.IdentifyFourActivityPresenter;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseActivity;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserViewModel;
import com.sskj.lib.util.ImageUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = RConfig.IDENTIFYFOURACTIVITY)
public class IdentifyFourActivity extends BaseActivity<IdentifyFourActivityPresenter> {
    @BindView(R2.id.tv1)
    TextView tv1;
    @BindView(R2.id.tv2)
    TextView tv2;
    @BindView(R2.id.tv3)
    TextView tv3;
    @BindView(R2.id.image1)
    ImageView imageView1;
    @BindView(R2.id.image2)
    ImageView imageView2;
    @BindView(R2.id.image3)
    ImageView imageView3;
    @BindView(R2.id.image11)
    ImageView imageView11;
    @BindView(R2.id.image22)
    ImageView imageView22;
    @BindView(R2.id.image33)
    ImageView imageView33;
    @BindView(R2.id.ll_image1)
    LinearLayout ll_image1;
    @BindView(R2.id.ll_image2)
    LinearLayout ll_image2;
    @BindView(R2.id.ll_image3)
    LinearLayout ll_image3;
    @BindView(R2.id.logout_btn)
    Button button;
    @Autowired
    String name;
    @Autowired
    String Idcard;
    @Autowired
    int newtype;
    Map<ImageView, String> picMap = new HashMap<>();
    private ImageView ivSelect;//当前选中的
    private String filePath;
    private boolean isSelect;
    private int type;
    @Inject
    UserViewModel userViewModel;
    @Autowired
    String zcountry;
    private SafeSettingBean userData;
    @Override
    protected int getLayoutId() {
        return R.layout.hang_activity_identifyfour;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        DaggerUserDataComponent.create().inject(this);
       // super.initView();
    }

    @Override
    protected void initData() {
        //super.initData();
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        ll_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=1;
                selectPic(type);
            }
        });
        ll_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=2;
                selectPic(type);
            }
        });
        ll_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=3;
                selectPic(type);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.submit(zcountry,picMap.get(imageView33),Idcard,picMap.get(imageView22),picMap.get(imageView11),name,newtype);
            }
        });
    }

    @Override
    public IdentifyFourActivityPresenter getPresenter() {
        return new IdentifyFourActivityPresenter();
    }
    private void selectPic(int type) {
     /*   PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);*/
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
                    mPresenter.onload(new File(filePath));
                    break;
            }

        }
    }

    public void putList(String data) {
        data = HttpConfig.BASE_URL+data;
        if(type==1){
            picMap.put(imageView11,data);
            imageView1.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
            imageView11.setVisibility(View.VISIBLE);
            ImageUtil.setImage(data,imageView11);

        }else if(type==2){
            picMap.put(imageView22,data);
            ImageUtil.setImage(data,imageView22);
            imageView22.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
        }else if(type==3){
            picMap.put(imageView33,data);
            ImageUtil.setImage(data,imageView33);
            imageView3.setVisibility(View.GONE);
            imageView33.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.GONE);
        }

    }

    public void uploadFail() {

    }

    public void setUI(String message) {
        userViewModel.update();
        ToastUtil.showShort(message);
        LiveDataBus.get().with(RxBusCode.REFRESHSAFETY,Integer.class).postValue(1);
        ARouter.getInstance().build(RConfig.ACCOUNTSAFETYACTIVITY).navigation();
       // finish();
    }
}
