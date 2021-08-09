package com.sskj.invite.http;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;
import com.sskj.common.base.HttpData;
import com.sskj.common.util.SPUtil;
import com.sskj.invite.bean.InviteInfo;
import com.sskj.invite.bean.MineInviteBean;
import com.sskj.invite.bean.RewardBean;
import com.sskj.invite.bean.TeamBean;
import com.sskj.lib.SPConfig;
import com.sskj.lib.bean.PageBean;

/**
 * 作者 :吕志豪
 * 简书：https://www.jianshu.com/u/6e525b929aac
 * github：https://github.com/lvzhihao100
 * 描述：
 * 创建时间：2018-10-15 14:08
 */
public class HttpService {


    public GetRequest<HttpData<PageBean<TeamBean>>> getTeamList(String page, String pageSize) {
        return OkGo.<HttpData<PageBean<TeamBean>>>get(HttpConfig.BASE_URL + HttpConfig.INVITECUSTOM)
                .params("id", SPUtil.get(SPConfig.ID, ""))
                .params("pageNumber", page)
                .params("pageSize", pageSize);
    }

    public PostRequest<HttpData<PageBean<RewardBean>>> getRewardList(String page, String pageSize) {
        return OkGo.<HttpData<PageBean<RewardBean>>>post(HttpConfig.BASE_URL + HttpConfig.INVITE_REWARD)
                .params("stockUserId", SPUtil.get(SPConfig.ID, ""))
                .params("type", "15")
                .params("stockCode", "USDT")
                .params("pageNumber", page)
                .params("pageSize", pageSize);
    }

    public PostRequest<HttpData<MineInviteBean>> getShare() {
        return OkGo.<HttpData<MineInviteBean>>post(HttpConfig.BASE_URL + HttpConfig.INVITEMENU)
                .params("userId", SPUtil.get(SPConfig.ID, ""))
                .params("stockCode", "dig");
    }

    public GetRequest<HttpData<InviteInfo>> getShareInfo() {
        return OkGo.<HttpData<InviteInfo>>get(HttpConfig.BASE_URL + HttpConfig.INVITE)
                .params("id", SPUtil.get(SPConfig.ID,""));
    }
}
