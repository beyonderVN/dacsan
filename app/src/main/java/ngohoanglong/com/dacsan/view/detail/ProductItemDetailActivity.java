package ngohoanglong.com.dacsan.view.detail;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.DragDismissDelegate;
import ngohoanglong.com.dacsan.utils.LifecycleDelegate;

public class ProductItemDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivCover)
    ImageView ivCover;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @BindView(R.id.tvPersons)
    TextView tvPersons;
    @BindView(R.id.tvDifficult)
    TextView tvDifficult;
    @BindView(R.id.tvCookTime)
    TextView tvCookTime;
    @BindView(R.id.tvPreparationTime)
    TextView tvPreparationTime;

    @BindView(R.id.rvIngredientList)
    RecyclerView rvIngredientList;
    @BindView(R.id.rvPreparationList)
    RecyclerView rvPreparationList;
    @BindView(R.id.rvCommentList)
    RecyclerView rvCommentList;

    @BindView(R.id.nsScrollView)
    NestedScrollView nsScrollView;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.rlProgressLoading)
    RelativeLayout rlProgressLoading;

//    private IngredientAdapter ingredientAdapter;
//    private PrepareAdapter prepareAdapter;
//
//    private List<ItemDetailViewModel> ingredientList;
//    private List<ItemDetailViewModel> prepareList;
//    private List<Comment> commentList;
//
//    CallbackManager callbackManager;
//    ShareDialog shareDialog;

    LinearLayoutManager mLayoutManagerVerticalIngredient;
    LinearLayoutManager mLayoutManagerVerticalPrepare;
//
    List<LifecycleDelegate> lifecycleDelegates = new ArrayList<>();

    { // Initializer block
        lifecycleDelegates.add(new DragDismissDelegate(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_detail_item);
        setContentView(R.layout.activity_product_detail_item);
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onCreate(savedInstanceState);
        }
//        callbackManager = CallbackManager.Factory.create();
//        shareDialog = new ShareDialog(this);
//        // this part is optional
//        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
//            @Override
//            public void onSuccess(Sharer.Result result) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });

        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        setupUI();


    }

    @Override
    protected void onStart() {
        super.onStart();

        nsScrollView.setTranslationY(nsScrollView.getTranslationY()+1000);
        ViewCompat.animate(nsScrollView)
                .translationYBy(-1000)
                .setStartDelay(200)
                .setDuration(500)
                .start();
        View rlWrapCommentInput = findViewById(R.id.rlWrapCommentInput);
        rlWrapCommentInput.setTranslationY(rlWrapCommentInput.getTranslationY()+200);
        ViewCompat.animate(rlWrapCommentInput)
                .translationYBy(-200)
                .setStartDelay(500)
                .setDuration(500)
                .start();

    }

    private void setupUI() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
//        Picasso.with(this)
//                .load(mPost.getTipImage().getUrl())
//                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable())
//                .resize(400, (int) (400 * mPost.getTipImageRatio()))
//                .into(ivCover);
//        tvIngredients.setText(post.getTipIngredients().replace("#i","- "));
//        tvPreparation.setText(post.getTipDescription().replace("#p","- "));

//        tvName.setText(mPost.getTipName());
//        tvPersons.setText(mPost.getTipPersons() + " People");
//        switch (mPost.getTipDifficulty()) {
//            case 1:
//                tvDifficult.setText("Easy");
//                break;
//            case 2:
//                tvDifficult.setText("Medium");
//                break;
//            case 3:
//                tvDifficult.setText("Hard");
//                break;
//        }
//        String[] strings = mPost.getTipTime().split("(#tp)|(#tc)");
//        int[] ints = {0, 0, 0, 0};
//        int i = 0;
//        for (String str : strings) {
//            if (str.length() > 0) {
//                int time = Integer.parseInt(str.trim());
//                ints[i] = time;
//            }
//            i++;
//
//        }
//        tvPreparationTime.setText(ints[1] + " min.");
//        tvCookTime.setText(ints[2] + " min.");
//        createData();
//        setupAdapter();

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


//
//    private void preparePrepareList() {
//        prepareList = new ArrayList<>();
//        String prepareString = mPost.getTipDescription();
//        boolean mode = prepareString.contains("#e");
//        String[] tmp1 = prepareString.split("\n");
//        if (mode) {
//
//            int number = 0;
//            for (int i = 0; i < tmp1.length; i++) {
//                String ingre = tmp1[i];
//                int type = 0;
//                if (ingre.contains("#e")) {
//                    number = 0;
//                    ingre = clearString(ingre);
//                    ingre = "<font color=\"#e1431c\">" + ingre + "</font>";
//                    type = 1;
//                } else if (ingre.contains("#p")) {
//                    number++;
//                    ingre = clearString(ingre);
//                    ingre = "<font color=\"#e1431c\">" + number + "</font>." + "\t" + ingre;
//                    type = 2;
//                }
//                ItemDetailViewModel itemDetailViewModel = new ItemDetailViewModel(ingre, type);
//                prepareList.add(itemDetailViewModel);
//            }
//        } else {
//            for (int i = 0; i < tmp1.length; i++) {
//                String ingre = tmp1[i];
//                if (ingre.contains("#p")) {
//                    ingre = clearString(ingre);
//                    ingre = "<font color=\"#e1431c\">" + (i + 1) + "</font>." + "\t" + ingre;
//                }
//                ItemDetailViewModel itemDetailViewModel = new ItemDetailViewModel(ingre, 2);
//                prepareList.add(itemDetailViewModel);
//            }
//        }
//    }
//
//    private void prepareIngredientList() {
//        ingredientList = new ArrayList<>();
//        String ingredients = mPost.getTipIngredients();
//        boolean mode = ingredients.contains("#e");
//        String[] tmp1 = ingredients.split("\n");
//        if (mode) {
//            for (int i = 0; i < tmp1.length; i++) {
//                String ingre = tmp1[i];
//                int type = 0;
//                if (ingre.contains("#e")) {
//                    ingre = clearString(ingre);
//                    ingre = "<font color=\"#e1431c\">" + ingre + "</font>";
//                    type = 1;
//                } else if (ingre.contains("#i")) {
//                    ingre = clearString(ingre);
//                    type = 2;
//                }
//                ItemDetailViewModel itemDetailViewModel = new ItemDetailViewModel(ingre, type);
//                ingredientList.add(itemDetailViewModel);
//            }
//        } else {
//            for (int i = 0; i < tmp1.length; i++) {
//                String ingre = tmp1[i];
//                if (ingre.contains("#i")) {
//                    ingre = clearString(ingre);
//                }
//                ItemDetailViewModel itemDetailViewModel = new ItemDetailViewModel(ingre, 2);
//                ingredientList.add(itemDetailViewModel);
//            }
//        }
//    }
//
//    private void prepareCommentList() {
//        commentList = new ArrayList<>();
//        if (mPost.getTipComments() != null && mPost.getTipComments().size() > 0)
//            commentList.addAll(new ArrayList<>(mPost.getTipComments().values()));
//    }
//
//    private String clearString(String string) {
//        if (string.contains("#e")) {
//            string = string.replace("#e", "");
//        }
//        if (string.contains("#i")) {
//            string = string.replace("#i", "");
//        }
//        if (string.contains("#p")) {
//            string = string.replace("#p", "");
//        }
//        return string;
//    }
//
//    private void setupAdapter() {
//
//        mLayoutManagerVerticalIngredient =
//                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
////        mLayoutManagerVertical.set
//
//        ingredientAdapter = new IngredientAdapter(ingredientList);
//        rvIngredientList.setLayoutManager(mLayoutManagerVerticalIngredient);
//        rvIngredientList.setAdapter(ingredientAdapter);
//
//
//        mLayoutManagerVerticalPrepare = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        prepareAdapter = new PrepareAdapter(prepareList);
//        rvPreparationList.setLayoutManager(mLayoutManagerVerticalPrepare);
//
//        rvPreparationList.setAdapter(prepareAdapter);
//
//        rvCommentList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvCommentList.setAdapter(new CommentAdapter(commentList));
////      fix bug layout auto scroll up
//        rvIngredientList.setFocusable(false);
//        rvIngredientList.setFocusable(false);
//        rvCommentList.setFocusable(false);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.share:
//                shareRecipeFacebook();
//                break;
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



//    @OnClick(R.id.btnShareRecipe)
//    public void customDialog() {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        // ...Irrelevant code for customizing the buttons and title
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.option_photo_layout, null);
//        dialogBuilder.setView(dialogView);
//
//        TextView tvGallery = (TextView) dialogView.findViewById(R.id.tvGallery);
//
//        TextView tvCamera = (TextView) dialogView.findViewById(R.id.tvCamera);
//
//        AlertDialog alertDialog = dialogBuilder.create();
//
//        tvCamera.setOnClickListener(v -> {
//            photoCameraIntent();
//            alertDialog.hide();
//        });
//        tvGallery.setOnClickListener(v -> {
//            photoGalleryIntent();
//            alertDialog.hide();
//        });
//        alertDialog.show();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onResume();
        }

    }

    @Override
    protected void onPause() {

        for (LifecycleDelegate lifecycleDelegate : lifecycleDelegates) {
            lifecycleDelegate.onPause();
        }
        super.onPause();
    }



//    FirebaseStorage storage = FirebaseStorage.getInstance();
//    Uri selectedImageUri;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
 }

