package finan.heng.com.apps.app.my.activity;
/*
 * Created by hhm on 2017/5/5.
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.model.MyUserInfo;
import finan.heng.com.apps.save.DataCache;
import onekeyshare.OnekeyShare;

public class InviteFriendActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlRight;
    private TextView tvCode, tvGuiZe;
    private ImageView ivErWeiMa;
    private String cusCode;
    private TextView ivOne, ivTwo, ivThree, ivFour, ivFive;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("邀请好友");
        initView();
        init();
    }

    private void initView() {
        rlRight = findViewById(R.id.tool_bar_layout);
        tvCode = findViewById(R.id.activity_invite_friend_code);
        ivErWeiMa = findViewById(R.id.activity_invite_friend_iv);
        ivOne = findViewById(R.id.activity_invite_friend_iv_one);
        ivTwo = findViewById(R.id.activity_invite_friend_iv_two);
        ivThree = findViewById(R.id.activity_invite_friend_iv_three);
        ivFour = findViewById(R.id.activity_invite_friend_iv_four);
        ivFive = findViewById(R.id.activity_invite_friend_iv_five);
        tvGuiZe = findViewById(R.id.activity_invite_friend_end_tv);
    }

    private void initData() {
        MyUserInfo cacheData = DataCache.instance.getCacheData("heng", "MyUserInfo");
        cusCode = cacheData.result.cusCode;
        tvCode.setText(cusCode);
        ivErWeiMa.setImageResource(R.mipmap.icon);
    }

    private void init() {
        rlRight.setOnClickListener(this);
        ivOne.setOnClickListener(this);
        ivTwo.setOnClickListener(this);
        ivThree.setOnClickListener(this);
        ivFour.setOnClickListener(this);
        ivFive.setOnClickListener(this);
        tvGuiZe.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View v) {
        ShareSDK.initSDK(InviteFriendActivity.this);
        switch (v.getId()) {
            case R.id.tool_bar_layout:
                startActivity(new Intent(InviteFriendActivity.this, InviteHistoryActivity.class));
                break;
            case R.id.activity_invite_friend_iv_one:
                Platform plat = ShareSDK.getPlatform(QQ.NAME);
                final OnekeyShare oks = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (plat.getName() != null) {
                    oks.setPlatform(plat.getName());
                }
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("我的推荐码："+cusCode+"，快来恒利来投资吧！");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                oks.setTitleUrl("http://api.hengll.com/hotspot/compony");
                // text是分享文本，所有平台都需要这个字段
                oks.setText(" ");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://admin.hengll.com/upload/image/logo.png");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://api.hengll.com/hotspot/compony");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment(" ");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("恒利来");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://api.hengll.com/hotspot/compony");

                //启动分享
                oks.show(this);
                break;
            case R.id.activity_invite_friend_iv_two:
                if (!TextUtils.isEmpty(cusCode)) {
                    Platform plats = ShareSDK.getPlatform(QZone.NAME);
                    final OnekeyShare ok = new OnekeyShare();
                    //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                    if (plats.getName() != null) {
                        ok.setPlatform(plats.getName());
                    }
                    //关闭sso授权
                    ok.disableSSOWhenAuthorize();
                    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                    ok.setTitle("我的推荐码："+cusCode+"，快来恒利来投资吧！");
                    // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                    ok.setTitleUrl("http://api.hengll.com/hotspot/compony");
                    // text是分享文本，所有平台都需要这个字段
                    ok.setText(" ");
                    //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                    ok.setImageUrl("http://admin.hengll.com/upload/image/logo.png");
                    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                    // url仅在微信（包括好友和朋友圈）中使用
                    ok.setUrl("http://api.hengll.com/hotspot/compony");
                    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                    ok.setComment(" ");
                    // site是分享此内容的网站名称，仅在QQ空间使用
                    ok.setSite("恒利来");
                    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                    ok.setSiteUrl("http://api.hengll.com/hotspot/compony");

                    //启动分享
                    ok.show(this);
                }
                break;
            case R.id.activity_invite_friend_iv_three:
                Platform platt = ShareSDK.getPlatform(Wechat.NAME);
                final OnekeyShare okss = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (platt.getName() != null) {
                    okss.setPlatform(platt.getName());
                }
                //关闭sso授权
                okss.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                okss.setTitle("我的推荐码："+cusCode+"，快来恒利来投资吧！");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                okss.setTitleUrl("http://api.hengll.com/hotspot/compony");
                // text是分享文本，所有平台都需要这个字段
                okss.setText(" ");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                okss.setImageUrl("http://admin.hengll.com/upload/image/logo.png");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                okss.setUrl("http://api.hengll.com/hotspot/compony");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                okss.setComment(" ");
                // site是分享此内容的网站名称，仅在QQ空间使用
                okss.setSite("恒利来");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                okss.setSiteUrl("http://api.hengll.com/hotspot/compony");

                //启动分享
                okss.show(this);
                break;
            case R.id.activity_invite_friend_iv_four://WechatMoments
                Platform platwx = ShareSDK.getPlatform(WechatMoments.NAME);
                final OnekeyShare okwx = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (platwx.getName() != null) {
                    okwx.setPlatform(platwx.getName());
                }
                //关闭sso授权
                okwx.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                okwx.setTitle("我的推荐码："+cusCode+"，快来恒利来投资吧！");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                okwx.setTitleUrl("http://api.hengll.com/hotspot/compony");
                // text是分享文本，所有平台都需要这个字段
                okwx.setText(" ");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                okwx.setImageUrl("http://admin.hengll.com/upload/image/logo.png");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                okwx.setUrl("http://api.hengll.com/hotspot/compony");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                okwx.setComment(" ");
                // site是分享此内容的网站名称，仅在QQ空间使用
                okwx.setSite("恒利来");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                okwx.setSiteUrl("http://api.hengll.com/hotspot/compony");

                //启动分享
                okwx.show(this);

                break;
            case R.id.activity_invite_friend_iv_five:
                sendMessage();
                break;
            case R.id.activity_invite_friend_end_tv:
                Intent intent2 = new Intent(InviteFriendActivity.this, WebActivity.class);
                intent2.putExtra("title", "邀请奖励规则");
                intent2.putExtra("url", URLHelper.getInstance().URL + "hotspot/yqhy");
                startActivity(intent2);
                break;
        }
    }

    private void sendMessage() {
        Platform platwx = ShareSDK.getPlatform(ShortMessage.NAME);
        final OnekeyShare okwx = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platwx.getName() != null) {
            okwx.setPlatform(platwx.getName());
        }
        //关闭sso授权
        okwx.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        okwx.setTitle("我的推荐码："+cusCode+"，快来恒利来投资吧！");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        okwx.setTitleUrl("http://api.hengll.com/hotspot/compony");
        // text是分享文本，所有平台都需要这个字段
        okwx.setText(" ");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        okwx.setImageUrl("http://admin.hengll.com/upload/image/logo.png");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        okwx.setUrl("http://api.hengll.com/hotspot/compony");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        okwx.setComment(" ");
        // site是分享此内容的网站名称，仅在QQ空间使用
        okwx.setSite("恒利来");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        okwx.setSiteUrl("http://api.hengll.com/hotspot/compony");

        //启动分享
        okwx.show(this);

    }
}
