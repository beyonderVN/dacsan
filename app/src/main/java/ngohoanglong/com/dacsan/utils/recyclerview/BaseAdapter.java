package ngohoanglong.com.dacsan.utils.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.HolderFactory;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.BaseHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.BaseViewHolder;

/**
 * Created by Long on 10/5/2016.
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseHM>> {

    private Context context;
    public HolderFactory holderFactory ;
    private List<BaseHM> list;


    public BaseAdapter(Context context) {
        this.context = context;
    }
    public BaseAdapter(Context context, List<BaseHM> list,HolderFactory holderFactory) {
        this(context);
        this.list = list;
        this.holderFactory = holderFactory;
    }


    public void addList(List<BaseHM> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void addItem(BaseHM baseHM){
        this.list.add(baseHM);
        notifyDataSetChanged();
    }
    public void removeItem(int pos){
        this.list.remove(pos);
        notifyDataSetChanged();
    }
    public void showLoadingMore(){
        this.list.add(new LoadMoreHM());
        notifyDataSetChanged();
    }
    public void hideLoadingMore(){
        if(list.get(list.size()-1) instanceof LoadMoreHM){
            this.list.remove(list.size()-1);
            notifyDataSetChanged();
        }
    }
    @Override
    public BaseViewHolder<BaseHM> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent != null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return holderFactory.createHolder(viewType, view);
        }
        throw new RuntimeException("Parent is null");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseHM> holder, int position) {
        if(holder!=null){
            holder.bind(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getVMType(holderFactory);
    }
}
