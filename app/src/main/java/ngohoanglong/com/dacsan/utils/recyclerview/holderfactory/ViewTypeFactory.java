package ngohoanglong.com.dacsan.utils.recyclerview.holderfactory;


import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductItemHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.ProductTypeHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SectionHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SimpleVerticalHM;

/**
 * Created by Long on 11/10/2016.
 */
public interface ViewTypeFactory {

    int getType(SimpleVerticalHM simpleVerticalVM);
    int getType(ProductItemHM productItemHM);
    int getType(LoadMoreHM loadMoreHM);

    int getType(ProductTypeHM productTypeHM);

    int getType(SectionHM sectionHM);
}
