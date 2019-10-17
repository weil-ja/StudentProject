package com.taotao.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_item")
public class Item extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String sellPoint;

    private Long price;

    private Integer num;

    private String barcode;

    private String image;

    private Long cid;

    private Integer status;

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public Item setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Item setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public Item setNum(Integer num) {
        this.num = num;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public Item setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Item setImage(String image) {
        this.image = image;
        return this;
    }

    public Long getCid() {
        return cid;
    }

    public Item setCid(Long cid) {
        this.cid = cid;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Item setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price
                + ", num=" + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status="
                + status + "]";
    }

}
