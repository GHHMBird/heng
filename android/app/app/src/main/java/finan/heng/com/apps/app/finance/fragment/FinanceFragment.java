package finan.heng.com.apps.app.finance.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcxiaoke.bus.annotation.BusReceiver;

import java.util.ArrayList;

import finan.heng.com.apps.HttpHelper;
import finan.heng.com.apps.R;
import finan.heng.com.apps.app.finance.adapter.FinanceAdapter;
import finan.heng.com.apps.base.BaseFragment;
import finan.heng.com.apps.base.LoadingFragment;
import finan.heng.com.apps.helper.ViewHelper;
import finan.heng.com.apps.model.FinanceTitleInfo;
import finan.heng.com.apps.model.FinanceTitleResponse;
import finan.heng.com.apps.rx.RequestErrorThrowable;
import rx.functions.Action1;

/**
 * @创建者 hhm
 * @创建时间 2017/4/23 14:33
 * @描述 ${TODO}
 * @创建者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class FinanceFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager mViewPager;
    private LinearLayout llTitle;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_finance, container, false);
        addLoadingFragment(R.id.fragment_finance_fl, "HENG_FinanceFragment");
        return view;
    }

    @BusReceiver
    public void StringEvent(String event) {
        if (event.equals("HENG_FinanceFragment")) {
            initView();
        }
    }

    private void initView() {
        new HttpHelper(getActivity()).getFinanceTitle().subscribe(new Action1<FinanceTitleResponse>() {
            @Override
            public void call(FinanceTitleResponse financeTitleResponse) {
                ArrayList<FinanceTitleInfo> productsGenreVoList = financeTitleResponse.result.productsGenreVoList;
                init(view, productsGenreVoList);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (throwable instanceof RequestErrorThrowable) {
                    LoadingFragment loadingFragment = findLoadingFragment();
                    if (loadingFragment!=null) {
                        loadingFragment.removeSelf(getFragmentManager());
                    }

                    RequestErrorThrowable requestErrorThrowable = (RequestErrorThrowable) throwable;
                    if (getActivity() != null) {
                        ViewHelper.showToast(getActivity(), requestErrorThrowable.getMessage());
                    }
                }
            }
        });
    }

    private void init(View view, ArrayList<FinanceTitleInfo> list) {
        llTitle = view.findViewById(R.id.fragment_finance_ll_title);
        for (int pos = 0; pos < list.size(); pos++) {
            TextView textView = new TextView(getActivity());
            textView.setTextSize(16);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20,0,20,0);
            textView.setLayoutParams(layoutParams);
            textView.setClickable(true);
            if (pos == 0) {
                textView.setTextColor(getResources().getColor(R.color.text_Fc291d));
            } else {
                textView.setTextColor(getResources().getColor(R.color.color_bbbbbb));
            }
            textView.setText(list.get(pos).genreTitle);
            textView.setOnClickListener(this);
            textView.setId(pos);
            llTitle.addView(textView);
        }

        mViewPager = view.findViewById(R.id.fragment_finance_viewpager);
        mViewPager.setOffscreenPageLimit(list.size());
        ArrayList<Fragment> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new AssetFragment(i,list.get(i).id));
        }
        FinanceAdapter financeAdapter = new FinanceAdapter(getFragmentManager(), arrayList);
        mViewPager.setAdapter(financeAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setSelect(int i) {
        clearText();
        ((TextView) llTitle.getChildAt(i)).setTextColor(getResources().getColor(R.color.text_Fc291d));
    }

    private void clearText() {
        int childCount = llTitle.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((TextView) llTitle.getChildAt(i)).setTextColor(getResources().getColor(R.color.color_bbbbbb));
        }
    }

    @Override
    public void onClick(View v) {
        setSelect(v.getId());
        mViewPager.setCurrentItem(v.getId());
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
