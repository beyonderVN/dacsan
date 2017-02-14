package ngohoanglong.com.dacsan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Long on 2/14/2017.
 */

public class ProductType {

    @SerializedName("product_type_vmall")
    @Expose
    private String productTypeVmall;
    @SerializedName("product_type_name")
    @Expose
    private String productTypeName;
    @SerializedName("category_img")
    @Expose
    private Object categoryImg;
    @SerializedName("title_img")
    @Expose
    private Object titleImg;
    @SerializedName("isvisible")
    @Expose
    private Integer isvisible;
    @SerializedName("isdelete")
    @Expose
    private Integer isdelete;
    @SerializedName("isstate")
    @Expose
    private Integer isstate;
    @SerializedName("issyn")
    @Expose
    private Integer issyn;
    @SerializedName("list_cate_sub")
    @Expose
    private Object listCateSub;
    @SerializedName("list_slide")
    @Expose
    private Object listSlide;
    @SerializedName("list_p")
    @Expose
    private Object listP;
    @SerializedName("list_top")
    @Expose
    private Object listTop;
    @SerializedName("list_branch")
    @Expose
    private Object listBranch;
    @SerializedName("list_item_big")
    @Expose
    private Object listItemBig;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("isnew")
    @Expose
    private String isnew;
    @SerializedName("item_sub")
    @Expose
    private Object itemSub;
    @SerializedName("item_se_sub")
    @Expose
    private Object itemSeSub;
    @SerializedName("list_brand")
    @Expose
    private Object listBrand;

    public String getProductTypeVmall() {
        return productTypeVmall;
    }

    public void setProductTypeVmall(String productTypeVmall) {
        this.productTypeVmall = productTypeVmall;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Object getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(Object categoryImg) {
        this.categoryImg = categoryImg;
    }

    public Object getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(Object titleImg) {
        this.titleImg = titleImg;
    }

    public Integer getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(Integer isvisible) {
        this.isvisible = isvisible;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getIsstate() {
        return isstate;
    }

    public void setIsstate(Integer isstate) {
        this.isstate = isstate;
    }

    public Integer getIssyn() {
        return issyn;
    }

    public void setIssyn(Integer issyn) {
        this.issyn = issyn;
    }

    public Object getListCateSub() {
        return listCateSub;
    }

    public void setListCateSub(Object listCateSub) {
        this.listCateSub = listCateSub;
    }

    public Object getListSlide() {
        return listSlide;
    }

    public void setListSlide(Object listSlide) {
        this.listSlide = listSlide;
    }

    public Object getListP() {
        return listP;
    }

    public void setListP(Object listP) {
        this.listP = listP;
    }

    public Object getListTop() {
        return listTop;
    }

    public void setListTop(Object listTop) {
        this.listTop = listTop;
    }

    public Object getListBranch() {
        return listBranch;
    }

    public void setListBranch(Object listBranch) {
        this.listBranch = listBranch;
    }

    public Object getListItemBig() {
        return listItemBig;
    }

    public void setListItemBig(Object listItemBig) {
        this.listItemBig = listItemBig;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public Object getItemSub() {
        return itemSub;
    }

    public void setItemSub(Object itemSub) {
        this.itemSub = itemSub;
    }

    public Object getItemSeSub() {
        return itemSeSub;
    }

    public void setItemSeSub(Object itemSeSub) {
        this.itemSeSub = itemSeSub;
    }

    public Object getListBrand() {
        return listBrand;
    }

    public void setListBrand(Object listBrand) {
        this.listBrand = listBrand;
    }

}
