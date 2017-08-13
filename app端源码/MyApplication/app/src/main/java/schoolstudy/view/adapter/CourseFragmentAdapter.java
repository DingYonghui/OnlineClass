package schoolstudy.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 这个类是课程类的适配器
 */
public class CourseFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mData;
    public CourseFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mData = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
