package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;


import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SimpleVerticalHM;

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
        cvWrap.setBackgroundColor(itemView.getResources().getColor(item.getColor()));
        tvTitle.setText(item.getTittle());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
