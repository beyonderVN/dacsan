package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnwarriors.advancedui.appcore.common.DynamicHeightImageView;
import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.PlaceHolderDrawableHelper;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.CurrencyUtil;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.view.detail.ProductItemDetailActivity;

/**
 * Created by Long on 11/10/2016.
 */
public class ProductItemHolder extends BaseViewHolder<ProductItemHM> {


    @BindView(R.id.ivProductImage)
    DynamicHeightImageView ivProductImage;
    @BindView(R.id.tvProductName)
    TextView tvProductName;
    @BindView(R.id.tvProductPrice)
    TextView tvProductPrice;
    @BindView(R.id.tvProductStore)
    TextView tvProductStore;
    public ProductItemHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }

    @Override
    public void bind(ProductItemHM item) {
        itemView.setId(item.id);
        PostVivmall postVivmall = item.getPostVivmall();
        Picasso.with(itemView.getContext())
                .load(postVivmall.getProductImage())
                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable())
//                .resize(400, (int) (400 * model.getTipImageRatio()))
                .into(ivProductImage);
        tvProductName.setText(postVivmall.getProductName());
        tvProductPrice.setText(CurrencyUtil.convertCurrency(postVivmall.getProductPrice(),new Locale("vn", "VN")));
        tvProductStore.setText("VinhSangCommerce");

        Intent intent = new Intent(itemView.getContext(), ProductItemDetailActivity.class);
        intent.putExtra("POST", item);
        itemView.setOnClickListener(view -> {
            ActivityOptions ops = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(),
                    Pair.create(ivProductImage, view.getContext().getString(R.string.detail_image))
            );
            view.getContext().startActivity(intent, ops.toBundle());
        });


    }
}
