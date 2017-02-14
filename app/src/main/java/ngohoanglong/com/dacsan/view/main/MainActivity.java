package ngohoanglong.com.dacsan.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vnwarriors.advancedui.appcore.common.viewpager.InkPageIndicator;
import com.vnwarriors.advancedui.appcore.common.viewpager.ModelPagerAdapter;
import com.vnwarriors.advancedui.appcore.common.viewpager.PagerModelManager;
import com.vnwarriors.advancedui.appcore.common.viewpager.ScrollerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.GuideFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.view_pager)
    ScrollerViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();
        InkPageIndicator springIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        // just set viewPager
        springIndicator.setViewPager(viewPager);

        setupToolbar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static Intent getIntentNewTask(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    private List<String> getTitles() {
        ArrayList<String> list = new ArrayList<String>();
        return list;
    }

    private List<String> getBgRes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://a2milk.co.uk/wp-content/uploads/foodtip01-copy205kb.png");
        list.add("https://a2milk.co.uk/wp-content/uploads/foodtip04-copy250kb.png");
        list.add("https://a2milk.co.uk/wp-content/uploads/foodtip03-copy147kb.png");
        list.add("https://a2milk.co.uk/wp-content/uploads/foodtip02-copy203kb.png");
        return list;
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mAppBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            boolean showTitle = (mCollapsingToolbar.getHeight() + verticalOffset) <= (mToolbar.getHeight() * 2);
            if (showTitle) {
                mCollapsingToolbar.setTitle("Vivmall.com");
            } else {
                mCollapsingToolbar.setTitle("");
            }

        });
    }
}
