package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;


import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vnwarriors.advancedui.appcore.common.DynamicHeightImageView;
import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.PlaceHolderDrawableHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.model.PostVivmall;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;

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
        PostVivmall postVivmall = item.getPostVivmall();
        Picasso.with(itemView.getContext())
                .load(postVivmall.getProductImage())
                .placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable())
//                .resize(400, (int) (400 * model.getTipImageRatio()))
                .into(ivProductImage);
        tvProductName.setText(postVivmall.getProductName());
        tvProductPrice.setText(postVivmall.getProductPrice().toString());
        tvProductStore.setText("VinhSangCommerce");
    }
}
