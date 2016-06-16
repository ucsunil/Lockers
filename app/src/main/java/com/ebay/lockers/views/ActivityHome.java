package com.ebay.lockers.views;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ebay.lockers.R;
import com.ebay.lockers.views.fragments.HomeFragment;
import com.ebay.lockers.views.fragments.ItemsFragment;
import com.ebay.lockers.views.fragments.LockersFragment;
import com.ebay.lockers.views.fragments.NotificationsFragment;
import com.ebay.lockers.views.fragments.SearchFragment;

public class ActivityHome extends AppCompatActivity {

    private static int NUMBER_OF_SCREENS = 5;

    private ViewPager pager;
    private TabLayout tabLayout;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new HomeScreenViewPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class HomeScreenViewPagerAdapter extends FragmentStatePagerAdapter {

        public HomeScreenViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new SearchFragment();
                case 2:
                    return new ItemsFragment();
                case 3:
                    return new NotificationsFragment();
                case 4:
                    return new LockersFragment();
            }
            // Should never be hit
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_SCREENS;
        }
    }
}
