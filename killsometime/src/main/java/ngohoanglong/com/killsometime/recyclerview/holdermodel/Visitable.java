package ngohoanglong.com.killsometime.recyclerview.holdermodel;


import ngohoanglong.com.killsometime.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 10/5/2016.
 */

public interface Visitable {
    int getVMType(ViewTypeFactory vmTypeFactory);
}
