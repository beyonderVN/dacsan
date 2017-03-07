package ngohoanglong.com.killsometime.recyclerview.holderfactory;


import ngohoanglong.com.killsometime.recyclerview.holdermodel.LoadMoreHM;
import ngohoanglong.com.killsometime.recyclerview.holdermodel.SimpleVerticalHM;

/**
 * Created by Long on 11/10/2016.
 */
public interface ViewTypeFactory {

    int getType(SimpleVerticalHM simpleVerticalVM);
    int getType(LoadMoreHM loadMoreHM);
}
