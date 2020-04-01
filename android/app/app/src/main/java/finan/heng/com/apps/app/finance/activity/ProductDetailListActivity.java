package finan.heng.com.apps.app.finance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.WebActivity;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.URLHelper;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetInvestHistoryDetailModel;
import finan.heng.com.apps.model.GetInvestHistoryDetailResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 16:52
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ProductDetailListActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvMoney, mTvTime, mTvThree, mTvFour, mTvFive, mTvSix, mTvSeven, mTvEight, mTvNine;
    private String id;
    private String orderId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_detail);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("");
        orderId = getIntent().getStringExtra("id");
        initView();
        init();
    }

    private void init() {
        new HttpHelper(this).getInvestHistoryDetail(Integer.parseInt(orderId)).subscribe(new Action1<GetInvestHistoryDetailResponse>() {
            @Override
            public void call(GetInvestHistoryDetailResponse getInvestHistoryDetailResponse) {
                initData(getInvestHistoryDetailResponse);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(ProductDetailListActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });
    }

    private void initData(GetInvestHistoryDetailResponse response) {
        GetInvestHistoryDetailModel.InvestDetailInfoBean info = response.result.getInvestDetailInfo();
        setBarTitle(info.getTitle());
        mTvMoney.setText(info.getAmount() + "元");
        mTvTime.setText(info.getAddTime().length() > 10 ? info.getAddTime().substring(0, 10) : info.getAddTime());
        mTvThree.setText(info.getMinProfit());
        mTvFour.setText(info.getTzqx());
        mTvFive.setText(info.getJxqsy());
        mTvSix.setText(info.getHbsy());
        mTvSeven.setText(info.getYgsy());
        mTvEight.setText(info.getEndTime());
        mTvNine.setText(info.getShowStatus());
        id = info.getProductId();
    }

    private void initView() {
        mTvMoney = findViewById(R.id.activity_product_list_one);//投资金额
        mTvTime = findViewById(R.id.activity_product_list_two);//投资日期
        mTvThree = findViewById(R.id.activity_product_list_three);//预期年化
        mTvFour = findViewById(R.id.activity_product_list_four);//投资期限
        mTvFive = findViewById(R.id.activity_product_list_five);//使用加息券
        mTvSix = findViewById(R.id.activity_product_list_six);//使用红包
        mTvSeven = findViewById(R.id.activity_product_list_seven);//总收益
        mTvEight = findViewById(R.id.activity_product_list_eight);//回款日期
        mTvNine = findViewById(R.id.activity_product_list_nine);//目前状态

        findViewById(R.id.activity_product_list_detail_see).setOnClickListener(this);
        findViewById(R.id.activity_product_list_detail_detail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_product_list_detail_see:
                Intent i = new Intent(ProductDetailListActivity.this, WebActivity.class);
                i.putExtra("url", URLHelper.getInstance().URL + "investmentProtocol?id=" + id);
                i.putExtra("title", "投资协议");
                startActivity(i);
                break;
            case R.id.activity_product_list_detail_detail:
                Intent intent = new Intent(ProductDetailListActivity.this, ProductDetailActivity.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);
                break;
        }
    }
}
