package ngohoanglong.com.dacsan.data.request;

/**
 * Created by nongdenchet on 9/27/16.
 */

public class LatestRequest  {
    private final int page;

    public LatestRequest(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

}
