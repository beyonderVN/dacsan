package ngohoanglong.com.killsometime.recyclerview.holderfactory;

import android.view.View;

import ngohoanglong.com.killsometime.recyclerview.viewholder.BaseViewHolder;


/**
 * Created by Long on 10/5/2016.
 */

public interface HolderFactory extends ViewTypeFactory {
    BaseViewHolder createHolder(int type, View view);
}
