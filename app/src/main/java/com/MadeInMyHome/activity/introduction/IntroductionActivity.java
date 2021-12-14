package com.MadeInMyHome.activity.introduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.MainActivity;

public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private Button btnNext, btnSkip;
    private IntroAdapter introAdapter;
    private TextView[] mDots;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        getSupportActionBar().hide();
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        setupViewPager();// setup the viewpager, set adapter and page listener

        addDotsIndicator(0);// called for the first launch, after this handled in page listener
    }

    private void moveMainActivityFromIntroduction() {
        // prefManager.setIsFirstLaunch(false);
        startActivity(new Intent(IntroductionActivity.this, MainActivity.class));
        finish();
    }


    private void setupViewPager() {
        introAdapter = new IntroAdapter(this);
        viewPager.setAdapter(introAdapter);
        viewPager.addOnPageChangeListener(pageChangeListener);
    }

    public void addDotsIndicator(int position) {
        // Adding TetView dynamically
        mDots = new TextView[introAdapter.getCount()];

        /* Remove aprvious views when called next time
         if not called then views will keep on adding*/
        dotsLayout.removeAllViews();

        // Set bullets in each dot text view
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("•"));// Html code for bullet
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.dot_inactive_color));

            dotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            // change color of the current selected dot
            mDots[position].setTextColor(getResources().getColor(R.color.dot_active_color));
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            currentItem = position; // currentItem used to get current position and then increase position on click on next button

            addDotsIndicator(position);

            // change the next button text to "next" / "finish"
            if (position == introAdapter.getCount() - 1) {
                // last page, make it "finish" and make the skip button invisible
                btnNext.setText(getString(R.string.finish));
                btnSkip.setVisibility(View.INVISIBLE);
            } else {
                // not last page, set "next" text and make skip button visible
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                if (currentItem < introAdapter.getCount() - 1) {
                    ++currentItem; // increase the value by 1
                    viewPager.setCurrentItem(currentItem); // set the layout at next position
                } else
                    moveMainActivityFromIntroduction(); // launch main screen on last page
                break;

            case R.id.btn_skip:
                moveMainActivityFromIntroduction();
                break;


        }

    }
}