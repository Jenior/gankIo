package me.jokey.mvp.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import butterknife.BindView;
import me.jokey.mvp.R;
import me.jokey.mvp.common.BaseActivity;
import me.jokey.mvp.mvp.ui.fragment.TabLayoutFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    // 定义FragmentManager对象管理器
    private FragmentManager mFragmentManager;
    private Fragment mZhihuFragment, mGankFragment, mVideoFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
            case R.id.navigation_dashboard:
            case R.id.navigation_notifications:
                setDefaultFragment(item.getItemId());
                return true;
        }
        return false;
    };

    @Override
    protected int initView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        setDefaultFragment(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * 设置默认的Fragment
     *
     * @param itemId 选项卡的标号：id
     */
    private void setDefaultFragment(int itemId) {
        //开启事务
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (itemId) {
            case R.id.navigation_home:
                if (mZhihuFragment == null) {
                    mZhihuFragment = new TabLayoutFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", 0);
                    mZhihuFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.content, mZhihuFragment);
                } else  // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(mZhihuFragment);
                break;
            case R.id.navigation_dashboard:
                if (mGankFragment == null) {
                    mGankFragment = new TabLayoutFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", 1);
                    mGankFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.content, mGankFragment);
                } else  // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(mGankFragment);
                break;
        }
        fragmentTransaction.commit();   // 事务提交
    }

    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction 事务
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (mZhihuFragment != null) fragmentTransaction.hide(mZhihuFragment);

        if (mGankFragment != null) fragmentTransaction.hide(mGankFragment);

        if (mVideoFragment != null) fragmentTransaction.hide(mVideoFragment);
    }

}
