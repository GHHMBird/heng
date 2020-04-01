package finan.heng.com.apps.app.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.adapter.BankListAdapter;
import finan.heng.com.apps.base.BaseActivity;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.BankInfo;
import finan.heng.com.apps.model.BankListResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/15.
 */
public class BankListActivity extends BaseActivity {

    private ListView mBank_listview;
    private ArrayList<BankInfo> bankList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banklist);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("选择银行卡");
        initView();
        init();
    }

    private void init() {

        new HttpHelper(this).getBankInfo().subscribe(new Action1<BankListResponse>() {
            @Override
            public void call(BankListResponse bankInfo) {
                Log.e("hhm", bankInfo.errorMsg);
                 bankList = bankInfo.result.bankList;
                BankListAdapter bankListAdapter=new BankListAdapter(getBaseContext(), bankList);
                mBank_listview.setAdapter(bankListAdapter);


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(BankListActivity.this, requestErrorThrowable.getMessage());
                }
            }
        });


    }

    private void initView() {
        mBank_listview = findViewById(R.id.bank_listview);

        mBank_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("bankId",bankList.get(position).bankId);
                intent.putExtra("bankName",bankList.get(position).bankName);
                setResult(101,intent);
                finish();
            }
        });



    }
}
