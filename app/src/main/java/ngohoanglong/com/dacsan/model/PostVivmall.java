package ngohoanglong.com.dacsan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Long on 2/8/2017.
 */

public class PostVivmall implements Serializable{

    @SerializedName("productIndex")
    @Expose
    private Integer productIndex;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("producTypeId")
    @Expose
    private Object producTypeId;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("productDes")
    @Expose
    private Object productDes;
    @SerializedName("productPrice")
    @Expose
    private Double productPrice;
    @SerializedName("productProviderId")
    @Expose
    private Object productProviderId;
    @SerializedName("productInputDate")
    @Expose
    private Object productInputDate;
    @SerializedName("productguide")
    @Expose
    private Object productguide;
    @SerializedName("productDescShort")
    @Expose
    private Object productDescShort;
    @SerializedName("productquantity")
    @Expose
    private Object productquantity;
    @SerializedName("moreinfo")
    @Expose
    private Object moreinfo;
    @SerializedName("producttype")
    @Expose
    private Object producttype;
    @SerializedName("orderproduct")
    @Expose
    private Object orderproduct;
    @SerializedName("rownum")
    @Expose
    private Integer rownum;
    @SerializedName("productimglarg")
    @Expose
    private Object productimglarg;
    @SerializedName("typeimglarg")
    @Expose
    private Object typeimglarg;
    @SerializedName("newPrice")
    @Expose
    private Object newPrice;
    @SerializedName("pricePercent")
    @Expose
    private Integer pricePercent;
    @SerializedName("catePromotionId")
    @Expose
    private Object catePromotionId;
    @SerializedName("isPromo")
    @Expose
    private Integer isPromo;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("shop_name")
    @Expose
    private Object shopName;
    @SerializedName("url_shop")
    @Expose
    private Object urlShop;
    @SerializedName("percent_discount")
    @Expose
    private Object percentDiscount;
    @SerializedName("price_promo")
    @Expose
    private Object pricePromo;
    @SerializedName("price_old")
    @Expose
    private Object priceOld;
    @SerializedName("property")
    @Expose
    private Object property;
    @SerializedName("color")
    @Expose
    private Object color;
    @SerializedName("property_value")
    @Expose
    private Object propertyValue;
    @SerializedName("color_value")
    @Expose
    private Object colorValue;
    @SerializedName("branch")
    @Expose
    private Object branch;
    @SerializedName("num_view")
    @Expose
    private Double numView;
    @SerializedName("num_buy")
    @Expose
    private Double numBuy;
    @SerializedName("email")
    @Expose
    private Object email;

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

    public Object getProducTypeId() {
        return producTypeId;
    }

    public void setProducTypeId(Object producTypeId) {
        this.producTypeId = producTypeId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Object getProductDes() {
        return productDes;
    }

    public void setProductDes(Object productDes) {
        this.productDes = productDes;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Object getProductProviderId() {
        return productProviderId;
    }

    public void setProductProviderId(Object productProviderId) {
        this.productProviderId = productProviderId;
    }

    public Object getProductInputDate() {
        return productInputDate;
    }

    public void setProductInputDate(Object productInputDate) {
        this.productInputDate = productInputDate;
    }

    public Object getProductguide() {
        return productguide;
    }

    public void setProductguide(Object productguide) {
        this.productguide = productguide;
    }

    public Object getProductDescShort() {
        return productDescShort;
    }

    public void setProductDescShort(Object productDescShort) {
        this.productDescShort = productDescShort;
    }

    public Object getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(Object productquantity) {
        this.productquantity = productquantity;
    }

    public Object getMoreinfo() {
        return moreinfo;
    }

    public void setMoreinfo(Object moreinfo) {
        this.moreinfo = moreinfo;
    }

    public Object getProducttype() {
        return producttype;
    }

    public void setProducttype(Object producttype) {
        this.producttype = producttype;
    }

    public Object getOrderproduct() {
        return orderproduct;
    }

    public void setOrderproduct(Object orderproduct) {
        this.orderproduct = orderproduct;
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    public Object getProductimglarg() {
        return productimglarg;
    }

    public void setProductimglarg(Object productimglarg) {
        this.productimglarg = productimglarg;
    }

    public Object getTypeimglarg() {
        return typeimglarg;
    }

    public void setTypeimglarg(Object typeimglarg) {
        this.typeimglarg = typeimglarg;
    }

    public Object getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Object newPrice) {
        this.newPrice = newPrice;
    }

    public Integer getPricePercent() {
        return pricePercent;
    }

    public void setPricePercent(Integer pricePercent) {
        this.pricePercent = pricePercent;
    }

    public Object getCatePromotionId() {
        return catePromotionId;
    }

    public void setCatePromotionId(Object catePromotionId) {
        this.catePromotionId = catePromotionId;
    }

    public Integer getIsPromo() {
        return isPromo;
    }

    public void setIsPromo(Integer isPromo) {
        this.isPromo = isPromo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Object getShopName() {
        return shopName;
    }

    public void setShopName(Object shopName) {
        this.shopName = shopName;
    }

    public Object getUrlShop() {
        return urlShop;
    }

    public void setUrlShop(Object urlShop) {
        this.urlShop = urlShop;
    }

    public Object getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(Object percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public Object getPricePromo() {
        return pricePromo;
    }

    public void setPricePromo(Object pricePromo) {
        this.pricePromo = pricePromo;
    }

    public Object getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Object priceOld) {
        this.priceOld = priceOld;
    }

    public Object getProperty() {
        return property;
    }

    public void setProperty(Object property) {
        this.property = property;
    }

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Object getColorValue() {
        return colorValue;
    }

    public void setColorValue(Object colorValue) {
        this.colorValue = colorValue;
    }

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
        this.branch = branch;
    }

    public Double getNumView() {
        return numView;
    }

    public void setNumView(Double numView) {
        this.numView = numView;
    }

    public Double getNumBuy() {
        return numBuy;
    }

    public void setNumBuy(Double numBuy) {
        this.numBuy = numBuy;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PostVivmall{" +
                "productIndex=" + productIndex +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", producTypeId=" + producTypeId +
                ", productImage='" + productImage + '\'' +
                ", productDes=" + productDes +
                ", productPrice=" + productPrice +
                ", productProviderId=" + productProviderId +
                ", productInputDate=" + productInputDate +
                ", productguide=" + productguide +
                ", productDescShort=" + productDescShort +
                ", productquantity=" + productquantity +
                ", moreinfo=" + moreinfo +
                ", producttype=" + producttype +
                ", orderproduct=" + orderproduct +
                ", rownum=" + rownum +
                ", productimglarg=" + productimglarg +
                ", typeimglarg=" + typeimglarg +
                ", newPrice=" + newPrice +
                ", pricePercent=" + pricePercent +
                ", catePromotionId=" + catePromotionId +
                ", isPromo=" + isPromo +
                ", customerId='" + customerId + '\'' +
                ", shopName=" + shopName +
                ", urlShop=" + urlShop +
                ", percentDiscount=" + percentDiscount +
                ", pricePromo=" + pricePromo +
                ", priceOld=" + priceOld +
                ", property=" + property +
                ", color=" + color +
                ", propertyValue=" + propertyValue +
                ", colorValue=" + colorValue +
                ", branch=" + branch +
                ", numView=" + numView +
                ", numBuy=" + numBuy +
                ", email=" + email +
                '}';
    }
}