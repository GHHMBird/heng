package finan.heng.com.apps.app.home.fragment;
/*
 * Created by hhm on 2017/4/25.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcxiaoke.bus.Bus;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseFragment;

public class JiaoYiJiLuFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        init();
        return view;
    }

    private void init() {

    }

    private void initView(View view) {

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
