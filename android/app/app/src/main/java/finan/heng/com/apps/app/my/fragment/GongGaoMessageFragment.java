package finan.heng.com.apps.app.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mcxiaoke.bus.Bus;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.activity.GongGaoDetailActivity;
import finan.heng.com.apps.app.my.adapter.GongGaoMessageAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetGongGaoModel;
import finan.heng.com.apps.model.GetGongGaoResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 Administrator
 * @创建时间 2017/4/29 14:38
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GongGaoMessageFragment extends BaseFragment {

    private ListView fragment_message_listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_jiaoyimessage, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {

        new HttpHelper(getActivity()).getGongGao().subscribe(new Action1<GetGongGaoResponse>() {
            @Override
            public void call(GetGongGaoResponse getGongGaoResponse) {
                final ArrayList<GetGongGaoModel.ArticleListBean> articleList = getGongGaoResponse.result.getArticleList();
                if (articleList == null || articleList.size() == 0) {
                    return;
                }
                GongGaoMessageAdapter allTradeAdapter = new GongGaoMessageAdapter(getActivity(), articleList);
                fragment_message_listview.setAdapter(allTradeAdapter);
                fragment_message_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), GongGaoDetailActivity.class);
                        intent.putExtra("body", articleList.get(position));
                        startActivity(intent);
                    }
                });
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    if (getActivity() != null) {
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }
                }
            }
        });
    }

    private void initView(View view) {
        fragment_message_listview = view.findViewById(R.id.fragment_message_listview);
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public View getScrollableView() {
        return null;
    }
}
