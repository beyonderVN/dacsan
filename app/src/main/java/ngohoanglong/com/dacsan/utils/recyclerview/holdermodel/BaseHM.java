package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;



import java.io.Serializable;

/**
 * Created by Long on 10/7/2016.
 */

public abstract class BaseHM implements Serializable, Visitable {
    private boolean fullSpan = false;

    public boolean isFullSpan() {
        return fullSpan;
    }

    public void setFullSpan(boolean fullSpan) {
        this.fullSpan = fullSpan;
    }
}
