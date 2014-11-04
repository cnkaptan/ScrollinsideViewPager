package com.solidict.scrollinsideviewpager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.devsmart.android.ui.HorizontalListView;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity {

    protected static String[] aylar;
    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;
    @InjectView(R.id.pager)
    ViewPager mPager;
    @InjectView(R.id.denemeTextView)
    TextView mDenemeTextView;
    @InjectView(R.id.denemeButton)
    Button mDenemeButton;
    @InjectView(R.id.vertical_listview)
    ListView mVerticalListview;
    @InjectView(R.id.mevsimlerList)
    HorizontalListView mMevsimlerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        aylar = getResources().getStringArray(R.array.mevsimler);
        mVerticalListview.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, aylar));


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance((position + 1), getPageTitle(position).toString());
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        String page, titlepage;

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_TITLE = "section_title";
        @InjectView(R.id.section_label)
        TextView mSectionLabel;
        @InjectView(R.id.mevsimlerList)
        HorizontalListView mMevsimlerList;


        public static PlaceholderFragment newInstance(int sectionNumber, String titlepage) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_SECTION_TITLE, titlepage);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, rootView);


            page = getArguments().getInt(ARG_SECTION_NUMBER) + "";
            titlepage = getArguments().getString(ARG_SECTION_TITLE);

            mSectionLabel = (TextView) rootView.findViewById(R.id.section_label);
            mSectionLabel.setText(titlepage + "\t" + page);
            HorizontalListViewAdapter horizontalListAdapter =new  HorizontalListViewAdapter(aylar);

            mMevsimlerList.setAdapter(horizontalListAdapter);

            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.reset(this);
        }
    }

}


// HORİZONTAL LIST VIEW AFAPTER
class HorizontalListViewAdapter extends BaseAdapter {
    String[] aylar;
    public HorizontalListViewAdapter(String[] aylar){

        this.aylar = aylar;

    }

    @Override
    public int getCount() {
        return aylar.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_infilate_layout, null);
        TextView title = (TextView) retval.findViewById(R.id.horizontalText);
        title.setText(aylar[position]);

        return retval;
    }

}
