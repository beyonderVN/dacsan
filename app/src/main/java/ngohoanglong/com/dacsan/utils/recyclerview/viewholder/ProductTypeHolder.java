package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductTypeHM;

/**
 * Created by Long on 3/2/2017.
 */
public class ProductTypeHolder extends BaseViewHolder<ProductTypeHM> {

    @BindView(R.id.cvWrap)
    CardView cvWrap;
    @BindView(R.id.tvCatalogue)
    TextView tvCatalogue;
    public ProductTypeHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(ProductTypeHM item) {
        tvCatalogue.setText(item.getProductType().getProductTypeName());
    }
}
