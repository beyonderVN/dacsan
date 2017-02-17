package ngohoanglong.com.dacsan.utils.recyclerview.holdermodel;


import ngohoanglong.com.dacsan.R;
import ngohoanglong.com.dacsan.utils.recyclerview.holderfactory.ViewTypeFactory;

/**
 * Created by Long on 11/10/2016.
 */
public class SimpleVerticalHM extends BaseHM {
    int color = R.color.white;
    public String getTittle() {
        return tittle;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public String toString() {
        return "SimpleVerticalHM{" +
                "color=" + color +
                ", tittle='" + tittle + '\'' +
                '}';
    }

    String tittle;
    public SimpleVerticalHM(String tittle) {
        this.tittle = tittle;
    }
    public SimpleVerticalHM(String tittle, int color) {
        this(tittle);
        this.color = color;
    }
    @Override
    public int getVMType(ViewTypeFactory vmTypeFactory) {
        return vmTypeFactory.getType(this);
    }
}
