package com.hencoder.hencoderpracticelayout1;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  TabLayout tabLayout;
  ViewPager pager;
  List<PageModel> pageModels = new ArrayList<>();

  {
    pageModels.add(
        new PageModel(R.layout.sample_square_image_view, R.string.title_square_image_view,
            R.layout.practice_square_image_view));
    pageModels.add(new PageModel(R.layout.sample_circle_view, R.string.title_circle_view,
        R.layout.sample_circle_view));
    pageModels.add(new PageModel(R.layout.sample_tab_layout, R.string.title_tab_layout,
        R.layout.sample_tab_layout));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    pager = findViewById(R.id.pager);
    pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

      @Override
      public Fragment getItem(int position) {
        PageModel pageModel = pageModels.get(position);
        return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes);
      }

      @Override
      public int getCount() {
        return pageModels.size();
      }

      @Override
      public CharSequence getPageTitle(int position) {
        return getString(pageModels.get(position).titleRes);
      }
    });

    tabLayout = findViewById(R.id.tabLayout);
    tabLayout.setupWithViewPager(pager);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  private class PageModel {
    @LayoutRes int sampleLayoutRes;
    @StringRes int titleRes;
    @LayoutRes int practiceLayoutRes;

    PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes,
        @LayoutRes int practiceLayoutRes) {
      this.sampleLayoutRes = sampleLayoutRes;
      this.titleRes = titleRes;
      this.practiceLayoutRes = practiceLayoutRes;
    }
  }
}
