package com.sskj.mine.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bulong.rudeness.RudenessScreenHelper;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.common.base.App;
import com.sskj.common.box.decoration.DividerLineItemDecoration;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.base.BaseFragment;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.util.EditHintUtils;
import com.sskj.lib.util.ImageUtil;
import com.sskj.mine.R;
import com.sskj.mine.R2;
import com.sskj.mine.bean.BIBIBean;
import com.sskj.mine.http.HttpConfig;
import com.sskj.mine.presenter.OneFragmentPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RConfig.ONEFRAGMENT)
public class OneFragment extends BaseFragment<OneFragmentPresenter> {
    /* @BindView(R2.id.checkbox)
     CheckBox box;
     @BindView(R2.id.et_sousuo)
     EditText editText;*/
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.checkbox)
    CheckBox checkbox;
    @BindView(R2.id.et_sousuo)
    EditText etSousuo;
    @BindView(R2.id.image)
    ImageView image;
    boolean isflag =true;
    private SlimAdapter slimAdapter;
    List<BIBIBean.DataBean> data;
    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment_detail;
    }

    @Override
    protected OneFragmentPresenter getPresenter() {
        return new OneFragmentPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getBiBi("0", "");
        LiveDataBus.get().with(RxBusCode.REFRESH).observe(this,this::refresh);
        RxTextView.textChanges(etSousuo).subscribe(data->{
            if(data==null){
                mPresenter.getBiBi("0", "");
            }else {
                mPresenter.getBiBi("0", data.toString());
            }

        });
    }

    private void refresh(Object o) {
        if(checkbox.isChecked()){
            mPresenter.getBiBi("1", "");
        }else {
            mPresenter.getBiBi("0", "");
        }

    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerLineItemDecoration(getActivity())
                .setPaintWidth((int) RudenessScreenHelper.pt2px(App.INSTANCE, 1))
                .setDividerColor(ContextCompat.getColor(App.INSTANCE, R.color.libTextGray)));

       etSousuo.setHint(EditHintUtils.setHintSizeAndContent(App.INSTANCE.getString(R.string.mine_solo1),14,true));
    }

    @Override
    protected void initEvent() {
        //super.initEvent();
        slimAdapter = SlimAdapter.<BIBIBean.DataBean>create().register(R.layout.mine_recy_item_bibi, new SlimInjector<BIBIBean.DataBean>() {
            @Override
            public void onInject(BIBIBean.DataBean bean, IViewInjector iViewInjector, List list) {
                ImageView imageView = (ImageView) iViewInjector.findViewById(R.id.image);
                ImageUtil.setImage(HttpConfig.BASE_URL + bean.getCoin().getImgUrl(), imageView);
                iViewInjector.text(R.id.tv_name, bean.getCoin().getUnit())
                        .text(R.id.tv_num, new BigDecimal(bean.getBalance()).setScale(8,BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                // iViewInjector.image(R.id.image,H)
            }
        }).attachTo(rv).updateData(new ArrayList());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(data);
                slimAdapter.updateData(data);
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mPresenter.getBiBi("1", "");
                }else {
                    mPresenter.getBiBi("0", "");
                }
            }
        });
    }

    public void setBIBI(List<BIBIBean.DataBean> data) {
        this.data =data;
        String s = GSonUtil.GsonString(data);
        SPUtil.put("bibi",s);
        slimAdapter.updateData(data);
    }


}
