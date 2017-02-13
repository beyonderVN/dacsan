package ngohoanglong.com.dacsan.utils.recyclerview.viewholder;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;


/**
 * Created by Long on 10/5/2016.
 *
 */

public class LoadMoreHolder extends BaseViewHolder<LoadMoreHM> {
    private static final String TAG = "MovieViewHolder";
    ProgressBar progress;
    public LoadMoreHolder(View itemView) {
        super(itemView);
        progress = (ProgressBar) itemView;
    }

    @Override
    public  void bind(LoadMoreHM item) {

        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
        progress.setVisibility(View.VISIBLE );

    }
}