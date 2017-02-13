package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 10/5/2016.
 */

public interface Visitable {
    int getVMType(ViewTypeFactory vmTypeFactory);
}
