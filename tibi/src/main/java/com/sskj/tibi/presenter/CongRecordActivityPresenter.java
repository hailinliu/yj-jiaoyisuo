package com.sskj.tibi.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.common.LogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.FlowableBody;
import com.sskj.common.util.GSonUtil;
import com.sskj.common.util.SPUtil;
import com.sskj.lib.RConfig;
import com.sskj.lib.RxBusCode;
import com.sskj.lib.bean.CoinListBean;
import com.sskj.lib.box.LiveDataBus;
import com.sskj.lib.http.NewJsonConverter;
import com.sskj.lib.router.provider.LogoutProvider;
import com.sskj.mine.bean.TransferRecordBean;
import com.sskj.tibi.bean.CongRecordBean;
import com.sskj.tibi.bean.NewJsonConverter2;
import com.sskj.tibi.bean.NewJsonConverter4;
import com.sskj.tibi.bean.OtherBean;
import com.sskj.tibi.bean.TibiRecordBean;
import com.sskj.tibi.http.HttpConfig;
import com.sskj.tibi.ui.activity.CongRecordActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class CongRecordActivityPresenter extends BasePresenter<CongRecordActivity> {
  /*  public Flowable<List<CongRecordBean.DataBean.ContentBean>> getRecord(String pageNo, String pageSize, String symbol) {
          return OkGo.<CongRecordBean>get(HttpConfig.BASE_URL+"/uc/asset/depositByPage")
                .params("symbol",symbol)
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .converter(new NewJsonConverter2<>())
                 .adapt(new FlowableBody<>())
                .map(data->
                 data.getData().getContent()
                ).onErrorReturnItem(new ArrayList<>());
                *//*.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                CongRecordBean bean =  GSonUtil.GsonToBean(response.body(),CongRecordBean.class);
                //flow =  Flowable.fromIterable(bean.getData().getContent()).toList().toFlowable();


            }
        })*//*

       *//* return httpService.getRecordHistory(p, size, code, type)
                .converter(new JsonConverter<HttpData<PageBean<RecordHistoryBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList());*//*
    }*/
  public void getCoin(String charge){
      OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/rechargeCoin")
              .params("charge",charge)
              .execute(new StringCallback() {
                  @Override
                  public void onSuccess(Response<String> response) {
                      if(response.body()!=null){
                          CoinListBean bean = GSonUtil.GsonToBean(response.body(),CoinListBean.class);
                          if(bean.getCode()==4000){
                              LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                              logoutProvider.logout();
                              LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                          }else if(bean.getCode()==0){
                              mView.setData(bean.getData());
                          }

                      }

                  }
              });
  }
    public void getRecord(String pageNo, String pageSize, String symbol) {
         OkGo.<String>get(HttpConfig.BASE_URL+"/uc/asset/depositByPage")
                .params("symbol",symbol)
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .execute(new StringCallback(){

                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            CongRecordBean bean =  GSonUtil.GsonToBean(response.body(),CongRecordBean.class);
                            if(bean.getCode()==4000){
                                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                                logoutProvider.logout();
                                LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                            }else if(bean.getCode()==0){
                                mView.setdata(bean.getData().getContent(),bean.getData().getTotalPages(),bean.getData().isLast());
                            }

                        }

                    }
                });
              ;
                /*.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                CongRecordBean bean =  GSonUtil.GsonToBean(response.body(),CongRecordBean.class);
                //flow =  Flowable.fromIterable(bean.getData().getContent()).toList().toFlowable();


            }
        })*/

       /* return httpService.getRecordHistory(p, size, code, type)
                .converter(new JsonConverter<HttpData<PageBean<RecordHistoryBean>>>() {
                })
                .adapt(new FlowableBody<>())
                .map(pageBeanHttpData -> pageBeanHttpData.getData().getList());*/
    }
   /* public Flowable<List<TibiRecordBean.DataBean.ContentBean>> getTibiRecord(String pageNo, String pageSize, String unit){
        return OkGo.<TibiRecordBean>post(HttpConfig.BASE_URL+"/uc/withdraw/record")
                .params("pageNo",pageNo)
                .params("pageSize",pageSize)
                .params("unit",unit)
                .converter(new NewJsonConverter4<TibiRecordBean>(){
                    @Override
                    public TibiRecordBean convertResponse(Response response) throws Throwable {
                        return super.convertResponse(response);
                    }
                })
                .adapt(new FlowableBody<>())
                .map(data->data.getData().getContent())
                .onErrorReturnItem(new ArrayList<>());
    }*/
   public void getTibiRecord(String pageNo, String pageSize, String unit){
        OkGo.<String>post(HttpConfig.BASE_URL+"/uc/withdraw/record")
               .params("pageNo",pageNo)
               .params("pageSize",pageSize)
               .params("unit",unit)
                .execute(new StringCallback(){

                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            TibiRecordBean bean =  GSonUtil.GsonToBean(response.body(),TibiRecordBean.class);
                            if(bean.getCode()==4000){
                                LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                                logoutProvider.logout();
                                LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                            }else if(bean.getCode()==0){
                                mView.setNewData(bean.getData().getContent(),bean.getData().getTotalPages(),bean.getData().isLast());
                            }
                        }


                    }
                });
   }
   public void getTransferRecord(String pageNo, String pageSize,String symbol){
       OkGo.<String>get(HttpConfig.BASE_URL+"/uc/asset/transferRecord")
               .params("pageNo",pageNo)
               .params("pageSize",pageSize)
               .params("symbol",symbol)
               .execute(new StringCallback(){

                   @Override
                   public void onSuccess(Response<String> response) {
                       if(response.body()!=null){
                           TransferRecordBean bean =  GSonUtil.GsonToBean(response.body(), TransferRecordBean.class);
                           if(bean.getCode()==4000){
                               LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                               logoutProvider.logout();
                               LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                           }else if(bean.getCode()==0){
                               mView.setNewData1(bean.getData().getContent(),bean.getData().getTotalPages(),bean.getData().isLast());
                           }
                       }


                   }
               });
   }
   public void checkRecord(String pageNo, String pageSize){
       OkGo.<String>post(HttpConfig.BASE_URL+"/uc/asset/transaction")
               .params("pageNo",pageNo)
               .params("pageSize",pageSize)
               .execute(new StringCallback(){

                   @Override
                   public void onSuccess(Response<String> response) {
                       if(response.body()!=null){
                           OtherBean bean =  GSonUtil.GsonToBean(response.body(), OtherBean.class);
                           if(bean.getCode()==4000){
                               LogoutProvider logoutProvider = ARouter.getInstance().navigation(LogoutProvider.class);
                               logoutProvider.logout();
                               LiveDataBus.get().with(RxBusCode.LOGOUT).postValue(1);
                           }else if(bean.getCode()==0){
                               mView.setOtherData(bean.getData().getContent(),bean.getData().getTotalPages(),bean.getData().isLast());
                           }
                       }


                      // mView.setNewData(bean.getData().getContent());
                   }
               });
   }
}
