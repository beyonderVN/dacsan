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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnwarriors.advancedui.appcore.common.viewpager.InkPageIndicator;
import com.vnwarriors.advancedui.appcore.common.viewpager.ModelPagerAdapter;
import com.vnwarriors.advancedui.appcore.common.viewpager.PagerModelManager;
import com.vnwarriors.advancedui.appcore.common.viewpager.ScrollerViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.DacsanApplication;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.GuideFragment;
import ngohoanglong.com.dacsan.view.BaseDelegateActivity;
import ngohoanglong.com.dacsan.view.delegate.RxDelegate;
import ngohoanglong.com.dacsan.view.login.LoginActivity;

public class MainActivity extends BaseDelegateActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private static final String SHOW_TITLE = "SHOW_TITLE";
    @BindView(R.id.view_pager)
    ScrollerViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar)
    AppBarLayout mAppBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private RxDelegate rxDelegate = new RxDelegate();
    {
        lifecycleDelegates.add(rxDelegate);
    }

    @Inject
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DacsanApplication.getAppComponent()
                .inject(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            showTitle = savedInstanceState.getBoolean(SHOW_TITLE);
        }
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
        setupNavigationMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindViewModel();
    }

    protected void bindViewModel() {
        viewModel.loginIsSuccess()
                .takeUntil(rxDelegate.stopEvent())
                .subscribe(this::handleResponse);
    }
    private void handleResponse(Boolean aBoolean){
        if(!aBoolean){
            startActivity(LoginActivity.getIntentNewTask(this));
        }
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
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
        if (id == R.id.sign_out) {
            ((DacsanApplication)getApplication()).getAppComponent().getAuthManager().signout();
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
    boolean showTitle = false;
    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mAppBar.setExpanded(!showTitle);
        mAppBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            showTitle = (mCollapsingToolbar.getHeight() + verticalOffset) <= (mToolbar.getHeight() * 2);
            if (showTitle) {
                mCollapsingToolbar.setTitle("Vivmall.com");
            } else {
                mCollapsingToolbar.setTitle("");
            }

        });

    }

    private void setupNavigationMenu() {
        ImageView ivAvatar;
        TextView tvNavigationHeader;
        TextView tvName;
        TextView tvEmail;
        TextView tvDes;

        View headerLayout = mNavigationView.getHeaderView(0);
        ivAvatar = (ImageView) headerLayout.findViewById(R.id.ivAvatar);
        tvNavigationHeader = (TextView) headerLayout.findViewById(R.id.tvNavigationHeader);
        tvName = (TextView) headerLayout.findViewById(R.id.tvName);
        tvName.setText("vivmall.vn");
        tvEmail = (TextView) headerLayout.findViewById(R.id.tvEmail);
        tvEmail.setText("admin@vivmall.vn");
        tvDes = (TextView) headerLayout.findViewById(R.id.tvDes);
        tvDes.setText("Today is good for shopping!");
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(SHOW_TITLE,showTitle);
    }
}
