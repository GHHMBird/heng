package finan.heng.com.apps.base;

import android.app.Fragment;
import android.os.Bundle;

import com.mcxiaoke.bus.Bus;

import finan.heng.com.apps.utils.ScrollableHelper;



/*
 * Created by hhm on 2017/1/7.
 */

public abstract class BaseFragment extends Fragment implements ScrollableHelper.ScrollableContainer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bus.getDefault().register(this);
        initBaseFragment();
    }

    private void initBaseFragment() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Bus.getDefault().unregister(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void addLoadingFragment(int viewId, String event) {
        LoadingFragment mLoadingFragment = LoadingFragment.newInstance(event);
        getFragmentManager()
                .beginTransaction()
                .replace(viewId, mLoadingFragment, LoadingFragment.TAG)
                .commitAllowingStateLoss();
    }

    public void removeLoadingFragment() {
        LoadingFragment mLoadingFragment = findLoadingFragment();
        if (mLoadingFragment != null) {
            mLoadingFragment.removeSelf(getFragmentManager());
        }
    }


    public LoadingFragment findLoadingFragment() {
        Fragment fragment = getFragmentManager().findFragmentByTag(LoadingFragment.TAG);
        if (fragment != null) {
            return (LoadingFragment) fragment;
        }
        return null;
    }

    public void showLoadingFailLayout() {
        LoadingFragment loadingFragment = findLoadingFragment();
        if (loadingFragment != null) {
            loadingFragment.showLoadingFailView();
        }
    }

    public abstract void refreshData();

    public abstract void refreshFinish();

}
