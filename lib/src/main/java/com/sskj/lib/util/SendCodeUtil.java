package com.sskj.lib.util;

import android.app.Activity;
import android.support.design.widget.BottomSheetDialog;
import android.widget.TextView;

import com.hey.lib.HeySpinner;
import com.sskj.common.base.App;
import com.sskj.common.util.ClickUtil;
import com.sskj.common.util.TimeUtil;
import com.sskj.lib.R;
import com.sskj.lib.bean.enums.AreaCodeEnum;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class SendCodeUtil {

    private static BottomSheetDialog bottomSheet;

    public static Disposable timeCount(LifecycleProvider lifecycleProvider, TextView tvGetCheckCode) {
        return TimeUtil.newTime(lifecycleProvider)
                .subscribe(aLong -> {
                    if (aLong > 0) {
                        tvGetCheckCode.setText(aLong + "s");
                    } else {
                        tvGetCheckCode.setText(App.INSTANCE.getString(R.string.libsendCodeUtil1));
                        tvGetCheckCode.setEnabled(true);
                    }
                });
    }

    public static void areaCode(Activity activity, HeySpinner heySpinner, AreaCodeBack areaCodeBack) {
        Flowable.fromIterable(AreaCodeEnum.list())
                .map(areaCodeEnum -> areaCodeEnum.getAreaName())
                .toList()
                .subscribe((strings, throwable) -> {
                    heySpinner.setText(strings.get(0));
                    areaCodeBack.onCode(AreaCodeEnum.list().get(0));
                    ClickUtil.click(heySpinner, () -> {
                        bottomSheet = BottomSheetUtil.getBottomSheet(activity, null, (recyclerView, i, view) -> {
                            heySpinner.setText(strings.get(i));
                            areaCodeBack.onCode(AreaCodeEnum.list().get(i));
                            bottomSheet.dismiss();
                        }, strings);
                        bottomSheet.show();
                    });
                });
    }

    public interface AreaCodeBack {
        void onCode(AreaCodeEnum areaCodeEnum);
    }
}
