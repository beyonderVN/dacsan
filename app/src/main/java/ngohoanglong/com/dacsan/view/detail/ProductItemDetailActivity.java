package ngohoanglong.com.dacsan.view.detail;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.PlaceHolderDrawableHelper;
import com.vnwarriors.advancedui.appcore.common.util.AnimUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.CurrencyUtil;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.view.BaseDelegateActivity;
import ngohoanglong.com.dacsan.view.delegate.DragDismissDelegate;

public class ProductItemDetailActivity extends BaseDelegateActivity {
    private static final String TAG = "ProductItemDetailActivi";
    @BindView(R.id.ivCover)
    ImageView ivCover;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tvStore)
    TextView tvStore;
    @BindView(R.id.tvBuyTimes)
    TextView tvBuyTimes;
    @BindView(R.id.tvWatchTimes)
    TextView tvWatchTimes;
    @BindView(R.id.tvFavourite)
    TextView tvFavourite;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvProductDes)
    TextView tvProductDes;
    @BindView(R.id.nsScrollView)
    NestedScrollView nsScrollView;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.rlProgressLoading)
    RelativeLayout rlProgressLoading;
    @BindView(R.id.rlWrapCommentInput)

    View rlWrapCommentInput;
    {
        lifecycleDelegates.add(new DragDismissDelegate(ProductItemDetailActivity.this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_product_detail_item);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupUI();
    }


    @Override
    protected void onStart() {
        super.onStart();
        setupAnimation();
    }

    private void setupAnimation() {
        nsScrollView.setTranslationY(nsScrollView.getTranslationY()+1000);
        rlWrapCommentInput.setTranslationY(rlWrapCommentInput.getTranslationY()+200);
        toolBar.setTranslationY(toolBar.getTranslationY()-100);
        getWindow().getSharedElementEnterTransition().addListener(new AnimUtils.TransitionListenerAdapter(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);

                ViewCompat.animate(nsScrollView)
                        .translationYBy(-1000)
                        .setStartDelay(0)
                        .setDuration(300)
                        .start();

                ViewCompat.animate(rlWrapCommentInput)
                        .translationYBy(-200)
                        .setStartDelay(0)
                        .setDuration(300)
                        .start();

                ViewCompat.animate(toolBar)
                        .translationYBy(+100)
                        .setStartDelay(0)
                        .setDuration(300)
                        .start();
            }
        });
    }

    private void setupUI() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.nsScrollView);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int distance = oldScrollY - scrollY;
                if (distance > 5) {
                    hideToolbar();
                }
                if (distance < -10) {
                    showToolbar();
                }
            }
        });

        ProductItemHM productItemHM = (ProductItemHM) getIntent().getSerializableExtra("POST");
        PostVivmall postVivmall = productItemHM.getPostVivmall();
        tvName.setText(postVivmall.getProductName());

        tvBuyTimes.setText(doubleToString(postVivmall.getNumBuy()));
        tvWatchTimes.setText(doubleToString(postVivmall.getNumView()));
        tvPrice.setText(CurrencyUtil.convertCurrency(postVivmall.getProductPrice(),new Locale("vn", "VN")));
        tvFavourite.setText(doubleToString(postVivmall.getNumView()));
        tvStore.setText("VinhSang Commerce");
        tvProductDes.setText(Html.fromHtml(getResources().getString(R.string.product_dest)));
        Picasso.with(this)
                .load(postVivmall.getProductImage())
                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable())
                .into(ivCover);

    }
    public String doubleToString(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
    private void showToolbar() {
        if (toolBar.getVisibility() != View.GONE) {
            toolBar.setVisibility(View.GONE);
        }
    }

    private void hideToolbar() {
        if (toolBar.getVisibility() != View.VISIBLE) {
            toolBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


 }

