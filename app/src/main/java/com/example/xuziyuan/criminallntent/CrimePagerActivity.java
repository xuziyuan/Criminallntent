package com.example.xuziyuan.criminallntent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID ="com.example.xuziyuan.criminallntent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,uuid);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager)findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new CrimePagerAdapter(fragmentManager));
        for(int i = 0;i<mCrimes.size();i++){
            if (mCrimes.get(i).getId().equals(uuid)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    private class CrimePagerAdapter extends FragmentPagerAdapter {

        public CrimePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            return CrimeFragment.newInstance(mCrimes.get(i).getId());
        }

        @Override
        public int getCount() {
            return mCrimes.size();
        }
    }
}
