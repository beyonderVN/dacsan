package ngohoanglong.com.dacsan.utils.recyclerview.holderfactory;


import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.LoadMoreHM;
import ngohoanglong.com.dacsan.utils.recyclerview.holdermodel.SimpleVerticalHM;

/**
 * Created by Long on 11/10/2016.
 */
public interface ViewTypeFactory {

    int getType(SimpleVerticalHM simpleVerticalVM);
    int getType(LoadMoreHM loadMoreHM);

}
