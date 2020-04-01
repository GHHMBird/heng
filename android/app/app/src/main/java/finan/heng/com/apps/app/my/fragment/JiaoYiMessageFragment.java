package finan.heng.com.apps.app.my.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mcxiaoke.bus.Bus;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.my.adapter.JiaoYiMessageAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.GetJiaoYi;
import finan.heng.com.apps.model.GetJiaoYiListResponse;
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
public class JiaoYiMessageFragment extends BaseFragment {

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
        new HttpHelper(getActivity()).getJiaoYiList().subscribe(new Action1<GetJiaoYiListResponse>() {
            @Override
            public void call(GetJiaoYiListResponse getJiaoYiListResponse) {
                ArrayList<GetJiaoYi> smgList = getJiaoYiListResponse.result.smgList;
                if (smgList == null || smgList.size() == 0){
                    return;
                }
                JiaoYiMessageAdapter allTradeAdapter = new JiaoYiMessageAdapter(getActivity(), smgList);
                fragment_message_listview.setAdapter(allTradeAdapter);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
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
