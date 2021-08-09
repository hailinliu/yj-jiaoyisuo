package com.sskj.lib.util;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sskj.common.base.App;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.R;
import com.sskj.lib.RConfig;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;

public class AppCircleCheckUtil {


    /**
     * 检测是否登录，没有登录直接跳转登录界面
     *
     * @param activity
     * @param userData
     * @return
     */
    public static boolean checkLogin(Activity activity, SafeSettingBean userData) {
        if (userData == null) {
//            TipUtil.getSureTip(activity, ""/*App.INSTANCE.getString(R.string.libappCircleCheckUtil1)*/, App.INSTANCE.getString(R.string.libappCircleCheckUtil2), App.INSTANCE.getString(R.string.libappCircleCheckUtil3), () -> {
//
//            });
            ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
            activity.overridePendingTransition(R.anim.lib_anim_in, R.anim.lib_anim_out);
            return false;
        }
        return true;
    }

    /**
     * 验证实名认证
     *
     * @param userData
     * @return
     */
    public static boolean checkAuth(Activity activity, SafeSettingBean userData) {
        if(userData.getRealVerified()!=0){
            return true;
        }else {
            ToastUtil.showShort("请先实名");
        }
      /*  if(userData.getRealVerified()){

        }*/
        /*if (userData.getBasicAuthenticationStatus() == 0) {
            TipUtil.getSureTip(activity, App.INSTANCE.getString(R.string.libappCircleCheckUtil1), App.INSTANCE.getString(R.string.libappCircleCheckUtil4), App.INSTANCE.getString(R.string.libappCircleCheckUtil5), () -> {
                ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_MENU).navigation();
            });
            return false;
        }*/
        return true;
    }

    /**
     * 验证高级实名认证
     * 高级认证状态 1:未认证 2:待审核 3:已认证 4:审核未通过
     *
     * @param userData
     * @return
     */
    public static boolean checkHighAuth(Activity activity, SafeSettingBean userData) {
       /* if (userData == null)
            return false;
        if (!checkAuth(activity, userData)) {

            return false;
        }
        if (userData.getHighAuthenticationStatus() == 1 || userData.getHighAuthenticationStatus() == 4) {
            TipUtil.getSureTip(activity, App.INSTANCE.getString(R.string.libappCircleCheckUtil1), App.INSTANCE.getString(R.string.libappCircleCheckUtil6), App.INSTANCE.getString(R.string.libappCircleCheckUtil5), () -> {
                ARouter.getInstance().build(RConfig.IDENT_VERIFICATION_MENU).navigation();
            });
            return false;
        }
        if (userData.getHighAuthenticationStatus() == 2) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.libappCircleCheckUtil7));
            return false;
        }*/
        return true;
    }

    /**
     * 验证资金密码
     *
     * @param userData
     * @return
     */
    public static boolean checkTradePwd(Activity activity, SafeSettingBean userData) {
        if(userData.getFundsVerified()==1){
            return true;
        }else {
            ToastUtil.showShort("请到安全设置中心设置交易密码");
        }
      /*  if (userData.getTradePswdStatus() == 1) {
            return true;
        } else {
            TipUtil.getSureTip(activity, App.INSTANCE.getString(R.string.libappCircleCheckUtil8), App.INSTANCE.getString(R.string.libappCircleCheckUtil9), App.INSTANCE.getString(R.string.libappCircleCheckUtil10), () -> {
                ARouter.getInstance().build(RConfig.MINE_SET_FUND_PWD)
                        .withString("mobile", userData.getTel())
                        .withInt("type", userData.getTradePswdStatus()).navigation();

            });
            return false;
        }*/
        return false;
    }


    /**
     * 验证商家
     * 商家认证状态 1.未申请 2.已申请(待审核）3.同意 4.拒绝 5.已解绑
     *
     * @param userData
     * @return
     */
    public static boolean checkShop(Activity activity, SafeSettingBean userData) {
      /*  if (userData.getShopAuthenticationStatus() == 1 || userData.getShopAuthenticationStatus() == 5 || userData.getShopAuthenticationStatus() == 4) {  // 不是商家
            TipUtil.getSureTip(activity, "", App.INSTANCE.getString(R.string.libappCircleCheckUtil11), App.INSTANCE.getString(R.string.libappCircleCheckUtil5), () -> {
                ARouter.getInstance().build(RConfig.MINE_SELLER_APPLY).navigation();
            });
            return false;
        }
        if (userData.getShopAuthenticationStatus() == 2) {
            ToastUtil.showShort(App.INSTANCE.getString(R.string.libappCircleCheckUtil12));
            return false;
        }

        return true;
    }*/
        return false;
    }
}