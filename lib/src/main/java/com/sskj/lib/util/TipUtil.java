package com.sskj.lib.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sskj.common.base.App;
import com.sskj.common.base.GlideApp;
import com.sskj.common.box.glidetransform.GlideRoundTransformCenterCrop;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;
import com.sskj.lib.ui.view.RoundCornerImageView;
import com.sskj.lib.widget.RudessMaterialDialog;

import org.raphets.roundimageview.RoundImageView;

import static com.sskj.lib.mvchelper.LoadViewFactory.dip2px;

public class TipUtil {


    private RequestOptions optionsRound;

    /**
     * 公告弹窗
     */
    public static void showGonggao(Context context, String title, String content) {
       // Fresco.initialize(context);
        MaterialDialog dialog = new RudessMaterialDialog.Builder(context)
                .customView(R.layout.lib_dialog_gonggao, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);

        ((TextView) customView.findViewById(R.id.tvContent)).setText(content);
        ((TextView) customView.findViewById(R.id.tvTitle)).setText(title);
       /* RoundCornerImageView imageView = customView.findViewById(R.id.simleview);
       // imageView.setImageResource(R.mipmap.lib_bg_notice);
        ImageUtil.setImage(R.mipmap.lib_bg_notice,imageView);*/
        CornerTransform transformation = new CornerTransform(context, dip2px(context, 30));
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(true, true, false, false);
        RequestOptions  optionsRound = new RequestOptions().transform(transformation);
        //ImageView imageView = customView.findViewById(R.id.image);
       /* RoundImageView imageView = customView.findViewById(R.id.simleview);
        GlideApp.with(context)
                .asBitmap()
                .load(R.mipmap.lib_bg_notice)
                .skipMemoryCache(true)
                .apply(optionsRound)
                .into(imageView);*/
        if (TextUtils.isEmpty(title)) {
            customView.findViewById(R.id.tvTitle).setVisibility(View.GONE);

         /*   RoundCornerImageView imageView = customView.findViewById(R.id.simleview);
            Glide.with(context).asBitmap().load(R.mipmap.lib_bg_notice).into(imageView);*/

           /* SimpleDraweeView draweeView =customView.findViewById(R.id.simleview);
            draweeView.setImageResource(R.mipmap.lib_bg_notice);*/
          /*  CornerTransform transformation = new CornerTransform(context, dip2px(context, 10));
            //只是绘制左上角和右上角圆角
            transformation.setExceptCorner(true, true, false, false);
            RequestOptions  optionsRound = new RequestOptions().transform(transformation);
           ImageView imageView = customView.findViewById(R.id.image);
            GlideApp.with(context)
                    .load(R.mipmap.lib_bg_notice)
                    .skipMemoryCache(true)
                    .apply(optionsRound)
                    .into(imageView);*/
        }
        ClickUtil.click(customView.findViewById(R.id.ivClose), () -> {
            dialog.dismiss();
        });
    }

    /**
     * 各种提示弹窗
     *
     * @param activity
     * @param title
     * @param content
     * @param sure
     * @param onTipSure
     * @return
     */
    public static MaterialDialog getSureTip(Activity activity, String title, String content, String sure, OnTipSure onTipSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.lib_dialog_sure, false)
                .autoDismiss(true)
                .cancelable(true)
                .show();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        TextView tvContent = customView.findViewById(R.id.tvContent);
        TextView tvTitle = customView.findViewById(R.id.tvTitle);
        Button btSure = customView.findViewById(R.id.btSure);
        Button btCancel = customView.findViewById(R.id.btCancel);
        btCancel.setTextColor(ContextCompat.getColor(App.INSTANCE,R.color.libTextWhite));
        ClickUtil.click(btCancel, dialog::dismiss);
        tvContent.setText(Html.fromHtml(content));
        if (TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.GONE);
        }
        tvTitle.setText(title);
        btSure.setText(sure);

        ClickUtil.click(btSure, () -> {
            dialog.dismiss();
            onTipSure.onSure();
        });
        return dialog;
    }
/**
 * 滑块儿弹窗
 */
public static MaterialDialog getSlide(Activity activity) {

    MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
            .customView(R.layout.lib_dialog_slide, false)
            .autoDismiss(false)
            .cancelable(false)
            .show();
   View customView = dialog.getCustomView();
  SeekBar seekBar = customView.findViewById(R.id.sb);
  TextView tv = customView.findViewById(R.id.tv);
  seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          if (seekBar.getProgress() == seekBar.getMax()) {
              tv.setVisibility(View.VISIBLE);
              tv.setTextColor(Color.WHITE);
              tv.setText("完成验证");
          } else {
              tv.setVisibility(View.INVISIBLE);
          }
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
          if (seekBar.getProgress() != seekBar.getMax()) {
              seekBar.setProgress(0);
              tv.setVisibility(View.VISIBLE);
              tv.setTextColor(Color.GRAY);
              tv.setText("向右滑动验证");
          }
      }
  });
    return dialog;
}
    /**
     * 账号失效弹窗，不可手动取消
     */
    public static MaterialDialog getExitTip(Activity activity, String content, OnTipSure onTipSure) {
        MaterialDialog dialog = new RudessMaterialDialog.Builder(activity)
                .customView(R.layout.lib_dialog_exit, false)
                .autoDismiss(false)
                .cancelable(false)
                .show();
        View customView = dialog.getCustomView();
        ((ViewGroup) (customView.getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        ((ViewGroup) (customView.getParent().getParent().getParent().getParent().getParent())).setBackgroundColor(Color.TRANSPARENT);
        TextView tvContent = customView.findViewById(R.id.tvContent);
        TextView tvTitle = customView.findViewById(R.id.tvTitle);
        TextView btSure = customView.findViewById(R.id.btSure);
        ImageView ivClose = customView.findViewById(R.id.ivClose);
        ivClose.setVisibility(View.GONE);
        ClickUtil.click(ivClose, () -> {
            dialog.dismiss();
        });
        tvContent.setText(content);
        tvTitle.setText(App.INSTANCE.getString(R.string.libappCircleCheckUtil1));
        btSure.setText(App.INSTANCE.getString(R.string.libtipUtil2));
        ClickUtil.click(btSure, () -> {
            dialog.dismiss();
            onTipSure.onSure();
        });
        return dialog;
    }

    public static MaterialDialog getSureTip(Activity activity, String title, String content, OnTipSure onTipSure) {
        return getSureTip(activity, title, content, App.INSTANCE.getString(R.string.libtipUtil2), onTipSure);
    }

    public static MaterialDialog getSureTip(Activity activity, String content, OnTipSure onTipSure) {
        return getSureTip(activity, App.INSTANCE.getString(R.string.libappCircleCheckUtil1), content, App.INSTANCE.getString(R.string.libtipUtil2), onTipSure);
    }

    public interface OnTipSure {
        void onSure();
    }
}
