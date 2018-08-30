package pt.greggz.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

import pt.greggz.myapplication.View.MoviesPagerAdapter;
import pt.greggz.myapplication.View.NowPlayingMoviesFragment;
import pt.greggz.myapplication.View.UpcomingMoviesFragment;
import pt.greggz.myapplication.ViewModel.NowPlayingViewModel;
import pt.greggz.myapplication.ViewModel.UpcomingMoviesViewModel;

public class MainActivity extends AppCompatActivity {

    ViewPager moviesCategoriesSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesCategoriesSlider = findViewById(R.id.pager);

        moviesCategoriesSlider.setAdapter(new MoviesPagerAdapter(getSupportFragmentManager(),
                UpcomingMoviesFragment.newInstance(),
                NowPlayingMoviesFragment.newInstance()));

        moviesCategoriesSlider.addOnPageChangeListener(movieFragmentChangeListener);
        // Initialize
        setTitle(Objects.requireNonNull(moviesCategoriesSlider.getAdapter()).getPageTitle(0));
    }

    private ViewPager.OnPageChangeListener movieFragmentChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            setTitle(Objects.requireNonNull(moviesCategoriesSlider.getAdapter()).getPageTitle(i));
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
