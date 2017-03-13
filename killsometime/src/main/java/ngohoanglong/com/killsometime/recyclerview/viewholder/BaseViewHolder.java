package ngohoanglong.com.killsometime.recyclerview.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Long on 10/5/2016.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T item);

    public class VIewsada extends View{
        public VIewsada(Context context) {
            super(context);
        }

    }
}
