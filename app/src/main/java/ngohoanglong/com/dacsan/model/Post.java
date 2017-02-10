package ngohoanglong.com.dacsan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Long on 2/8/2017.
 */

public class Post implements Serializable{

    @SerializedName("productIndex")
    @Expose
    private Integer productIndex;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("productDes")
    @Expose
    private String productDes;
    @SerializedName("productPrice")
    @Expose
    private Double productPrice;
    @SerializedName("moreinfo")
    @Expose
    private String moreinfo;
    @SerializedName("rownum")
    @Expose
    private Integer rownum;
    @SerializedName("newPrice")
    @Expose
    private Double newPrice;
    @SerializedName("pricePercent")
    @Expose
    private Integer pricePercent;
    @SerializedName("isPromo")
    @Expose
    private Integer isPromo;
    @SerializedName("num_cart")
    @Expose
    private Double numCart;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("Nlike")
    @Expose
    private Integer nlike;
    @SerializedName("Nview")
    @Expose
    private Integer nview;
    @SerializedName("Nbuy")
    @Expose
    private Integer nbuy;

    public Integer getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(Integer productIndex) {
        this.productIndex = productIndex;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getMoreinfo() {
        return moreinfo;
    }

    public void setMoreinfo(String moreinfo) {
        this.moreinfo = moreinfo;
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Integer getPricePercent() {
        return pricePercent;
    }

    public void setPricePercent(Integer pricePercent) {
        this.pricePercent = pricePercent;
    }

    public Integer getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(Integer isPromo) {
        this.isPromo = isPromo;
    }

    public Double getNumCart() {
        return numCart;
    }

    public void setNumCart(Double numCart) {
        this.numCart = numCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNlike() {
        return nlike;
    }

    public void setNlike(Integer nlike) {
        this.nlike = nlike;
    }

    public Integer getNview() {
        return nview;
    }

    public void setNview(Integer nview) {
        this.nview = nview;
    }

    public Integer getNbuy() {
        return nbuy;
    }

    public void setNbuy(Integer nbuy) {
        this.nbuy = nbuy;
    }

}