package me.jokey.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import me.jokey.mvp.R;

/**
 * Created by wz on 2017/6/16 11:38.
 * tablayout fragment
 */

public class TabLayoutFragment extends BaseFragment {

    private static final String[] TAB_ZHIHU = new String[]{"最新消息", "主题日报"};
    private static final String[] TAB_GANK = new String[]{"Android", "iOS", "福利", "休息视频"};

    private final ArrayList<Fragment> mFragments = new ArrayList<>();
    private final ArrayList<String> mTabs = new ArrayList<>();

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_tablayout, container, false);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int index = bundle.getInt("index");

        switch (index) {
            case 0:
                mFragments.add(new ZhihuDayFragment());
                mFragments.add(new ZhihuThemeFragment());
                mTabs.addAll(Arrays.asList(TAB_ZHIHU));
                break;
            case 1:
                for (int i = 0; i < TAB_GANK.length; i++) {
                    GankFragment fragment = new GankFragment();
                    Bundle b = new Bundle();
                    b.putString("type", TAB_GANK[i]);
                    fragment.setArguments(b);
                    mFragments.add(fragment);
                }
                mTabs.addAll(Arrays.asList(TAB_GANK));
                break;
        }
        mViewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(mFragments.size() - 1);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position);
        }
    }

}
