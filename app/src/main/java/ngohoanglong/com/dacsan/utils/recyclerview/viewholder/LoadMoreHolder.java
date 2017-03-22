package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;


/**
 * Created by Long on 10/5/2016.
 *
 */

public class LoadMoreHolder extends BaseViewHolder<LoadMoreHM> {
    private static final String TAG = "MovieViewHolder";

    public LoadMoreHolder(View itemView) {
        super(itemView);

    }

    @Override
    public  void bind(LoadMoreHM item) {
        if(itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }
}
