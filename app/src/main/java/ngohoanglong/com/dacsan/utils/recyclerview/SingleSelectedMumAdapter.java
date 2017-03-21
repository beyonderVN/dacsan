package ngohoanglong.com.dacsan.utils.recyclerview;

import android.animation.LayoutTransition;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.vnwarriors.advancedui.appcore.common.recyclerviewhelper.InfiniteScrollListener;

import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactory;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactoryImpl;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductTypeHM;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.BaseViewHolder;
import ngohoanglong.com.dacsan.view.main.ProductTypeViewModel;

import static ngohoanglong.com.dacsan.R.id.rvProductTypeList;


/**
 * Created by Long on 10/5/2016.
 */

public class SingleSelectedMumAdapter extends MumAdapter {


    private int selectedPosition = -1;
    public SingleSelectedMumAdapter(Context context,
                                    HolderFactory holderFactory,
                                    ObservableArrayList<BaseHM> observableArrayList,
                                    OnSelectItemClickEvent onSelectItemClickEvent) {
        super(context, holderFactory,observableArrayList,onSelectItemClickEvent);
    }
    @Override
    public void onBindViewHolder(BaseViewHolder<BaseHM> holder, int position) {
        if(holder!=null){
            BaseHM baseHM = items.get(position);
            baseHM.setCheck(position == selectedPosition);
            holder.bind(baseHM);
            holder.itemView.setOnClickListener(v -> {
                selectedPosition = position;
                onSelectItemClickEvent.onItemClick(position, baseHM);
                notifyDataSetChanged();
            });
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }



}
