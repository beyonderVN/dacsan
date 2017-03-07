package ngohoanglong.com.killsometime.recyclerview.viewholder;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.killsometime.R;
import ngohoanglong.com.killsometime.recyclerview.holdermodel.SimpleVerticalHM;


/**
 * Created by Long on 11/10/2016.
 */
public class SimpleVerticalHolder extends BaseViewHolder<SimpleVerticalHM> {
    @BindView(R.id.cvWrap)
    CardView cvWrap;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    public SimpleVerticalHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bind(SimpleVerticalHM item) {
        tvTitle.setText(item.getTittle());
    }
}
