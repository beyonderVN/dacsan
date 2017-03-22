package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SectionHM;

/**
 * Created by Long on 3/2/2017.
 */
public class TypeHolder extends BaseViewHolder<SectionHM> {

    @BindView(R.id.cvWrap)
    CardView cvWrap;
    @BindView(R.id.tvCatalogue)
    TextView tvCatalogue;

    public TypeHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(SectionHM item) {
        tvCatalogue.setText(item.getProductType().getProductTypeName());
        int color = item.getColor();
        if(item.isCheck()){
            cvWrap.setCardElevation(10);
            cvWrap.setCardBackgroundColor(itemView.getResources().getColor(color));
            tvCatalogue.setTextColor(itemView.getContext().getResources().getColor(R.color.white));
        }else {
            cvWrap.setCardElevation(0);
            cvWrap.setCardBackgroundColor(itemView.getContext().getResources().getColor(R.color.white));
            tvCatalogue.setTextColor(itemView.getResources().getColor(color));
        }
    }
}
