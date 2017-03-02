package ngohoanglong.com.dacsan.utils.recyclerview.holderfactory;

import android.view.View;

import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductTypeHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SimpleVerticalHM;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.BaseViewHolder;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.LoadMoreHolder;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.ProductItemHolder;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.ProductTypeHolder;
import ngohoanglong.com.dacsan.utils.recyclerview.viewholder.SimpleVerticalHolder;


/**
 * Created by Long on 10/5/2016.
 */

public class HolderFactoryImpl implements HolderFactory {

    private static final int ITEM_VERTICAL = R.layout.layout_item_vertical;
    private static final int ITEM_LOAD_MORE = R.layout.layout_item_load_more;
    private static final int ITEM_PRODUCT = R.layout.layout_product_item_vertical;
    private static final int ITEM_PRODUCT_TYPE = R.layout.layout_product_type_item_horizontal;

    @Override
    public BaseViewHolder createHolder(int type, View view) {
        switch(type) {
            case ITEM_VERTICAL: return new SimpleVerticalHolder(view);
            case ITEM_LOAD_MORE: return new LoadMoreHolder(view);
            case ITEM_PRODUCT: return new ProductItemHolder(view);
            case ITEM_PRODUCT_TYPE: return new ProductTypeHolder(view);
        }
        return null;
    }

    @Override
    public int getType(SimpleVerticalHM simpleVerticalVM) {
        return ITEM_VERTICAL;
    }

    @Override
    public int getType(ProductItemHM productItemHM) {
        return ITEM_PRODUCT;
    }

    @Override
    public int getType(LoadMoreHM loadMoreHM) {
        return ITEM_LOAD_MORE;
    }

    @Override
    public int getType(ProductTypeHM productTypeHM) {
        return ITEM_PRODUCT_TYPE;
    }


}
