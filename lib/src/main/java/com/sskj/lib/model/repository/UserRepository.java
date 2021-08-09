package com.sskj.lib.model.repository;


import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.sskj.common.rxbus2.RxBus;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.common.util.ToastUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.bean.UserData;
import com.sskj.lib.bean.SafeSettingBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.model.room.UserDao;
import com.sskj.lib.model.room.UserDataBase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.schedulers.Schedulers;

import static com.sskj.lib.BaseHttpConfig.BASE_URL;

/**
 * Created by lv on 18-5-7.
 */
//@Singleton
public class UserRepository {


    public UserDao userDao;

    @Inject
    public UserRepository(@Named("UserDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public UserRepository(Context context) {
        RxBus.getDefault().register(this);
        UserDataBase userDataBase = UserDataBase.getInstance(context);
        userDao = userDataBase.getUserDao();
    }

    public LiveData<List<SafeSettingBean>> getAllUser() {
        return userDao.getAll();
    }

    public void updateUser() {
        if (TextUtils.isEmpty(SPUtil.get(SPConfig.ID, ""))) {
            return;
        }
        OkGo.<String>get(BASE_URL + "/uc/approve/security/setting")
               // .params("id",SPUtil.get(SPConfig.ID, ""))
                .execute(/*new NewJsonCallBack<BaseBean<UserData1>>(){
                             @Override
                             public void onSuccess(BaseBean<UserData1> httpData) {
                                 super.onSuccess(httpData);
                             }

                             @Override
                             public void onError(Response<BaseBean<UserData1>> response) {
                                 super.onError(response);
                             }
                         }*/
                        /*new JsonCallBack<BaseBean<UserData1>>() {
                             @Override
                             public void onSuccess(BaseBean<UserData1> httpData) {
                                 super.onSuccess(httpData);
                             }

                             @Override
                             public void onError(Response<BaseBean<UserData1>> response) {
                                 super.onError(response);
                             }
                         }*/
                        new StringCallback() {
                             @Override
                             public void onSuccess(Response<String> response) {

                                   //String jsonBody = response.body().replace("\"data\":\"\"}", "\"data\":null}");
                                /* try {
                                     JSONObject jsonObject = new JSONObject(response.body());
                                   Object data =  jsonObject.get("data");
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }*/

                                 try {
                                     JSONObject jsonObject = new JSONObject(response.body());
                                    String dataString = jsonObject.getString("data");
                                    int code =  jsonObject.getInt("code");
                                    if(code==4000){
                                        clear();
                                        SPUtil.clear();
                                        HttpHeaders httpHeaders = OkGo.getInstance().getCommonHeaders();
                                        httpHeaders.clear();
                                        ARouter.getInstance().build(RConfig.LOGIN_LOGIN).navigation();
                                    }else if(code==0){
                                        SafeSettingBean data =  GSonUtil.GsonToBean(dataString, SafeSettingBean.class);
                                        LiveDataBus.get().with(RxBusCode.ISLOGIN,SafeSettingBean.class).postValue(data);
                                        insert(data);
                                    }else {
                                        ToastUtil.showShort(jsonObject.getString("message"));
                                    }

                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }

                               //  SPUtil.putBean();
                              // data.getAvatar();
                              // insert(data);
                                /*     JSONObject jsonObject = new JSONObject();
                                     int code = jsonObject.getInt("code");
                                     String msg = jsonObject.getString("message");*/


                                 //  SafeSettingBean data =  GSonUtil.GsonToBean(response.body(), SafeSettingBean.class);
                             //  insert();
                              // data.getData();
                                 ;
                             }
                         }
                        /*new JsonCallBack<HttpData<UserData>>() {
                    @Override
                    public void onSuccess(Response<HttpData<UserData>> response) {
                        HttpData<UserData> httpData = response.body();
                        if (httpData.getStatus() == BaseHttpConfig.OK) {
                            httpData.getData().setId(1);
                            insert(httpData.getData());
                        }else if(httpData.getStatus()==401){
                           // RxBus.getDefault().send(1100);
                            SPUtil.clear();
                            clear();
                            ToastUtil.showShort(httpData.getMsg());
                           // httpData.getData().setId(1);
                          //  insert(httpData.getData());
                        }
                    }

                    @Override
                    public void onError(Response<HttpData<UserData>> response) {
                        RxBus.getDefault().send(1100);
                        super.onError(response);
                        System.out.println("1");
                    }
                }*/);
    }


    public void insert(SafeSettingBean user) {
        Schedulers.newThread().scheduleDirect(() -> userDao.insert(user));
    }

    public void clear() {
        Schedulers.newThread().scheduleDirect(() -> userDao.deleteAll());
    }

    public void update(SafeSettingBean user) {
        Schedulers.newThread().scheduleDirect(() -> userDao.update(user));
    }

    public void update() {
        updateUser();
    }
}
