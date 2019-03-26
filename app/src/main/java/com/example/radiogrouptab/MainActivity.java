package com.example.radiogrouptab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.radiogrouptab.fragment.FindFragment;
import com.example.radiogrouptab.fragment.HomeFragment;
import com.example.radiogrouptab.fragment.ProfileFragment;
import com.example.radiogrouptab.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mHomeContent;
    private RadioGroup mHomeRadioGroup;
    private RadioButton mHomeHomeRb;
    private RadioButton mHomeFindRb;
    private RadioButton mHomeSearchRb;
    private RadioButton mHomeProfileRb;

    static final int NUM_ITEMS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        mHomeContent = (FrameLayout) findViewById(R.id.mHomeContent);
        mHomeRadioGroup = (RadioGroup) findViewById(R.id.mHomeRadioGroup);
        mHomeHomeRb = (RadioButton) findViewById(R.id.mHomeHomeRb);
        mHomeFindRb = (RadioButton) findViewById(R.id.mHomeFindRb);
        mHomeSearchRb = (RadioButton) findViewById(R.id.mHomeSearchRb);
        mHomeProfileRb = (RadioButton) findViewById(R.id.mHomeProfileRb);

        mHomeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                switch (checkedId) {
                    case R.id.mHomeHomeRb:
                        index = 0;
                        break;
                    case R.id.mHomeFindRb:
                        index = 1;
                        break;
                    case R.id.mHomeSearchRb:
                        index = 2;
                        break;
                    case R.id.mHomeProfileRb:
                        index = 3;
                        break;
                }
                //通过fragments这个adapter还有index来替换布局中的内容
                Fragment fragment = (Fragment) fragments.instantiateItem(mHomeContent, index);
                //开始将不居中的内容设置为第一个
                fragments.setPrimaryItem(mHomeContent, 0, fragment);
                fragments.finishUpdate(mHomeContent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHomeRadioGroup.check(R.id.mHomeHomeRb);
    }

    FragmentStatePagerAdapter fragments = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new FindFragment();
                    break;
                case 2:
                    fragment = new SearchFragment();
                    break;
                case 3:
                    fragment = new ProfileFragment();
                    break;
                default:
                    fragment = new HomeFragment();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    };
}
