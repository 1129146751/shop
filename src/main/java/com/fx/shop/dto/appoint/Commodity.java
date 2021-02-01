package com.fx.shop.dto.appoint;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 商品信息API请求参数
 *
 * @author zhangyi
 * @date 2020-8-24
 */
@Data
@ApiModel(value = "商品信息API请求参数", description = "商品信息API请求参数")
public class Commodity {

    private static final long serialVersionUID = 1L;
    // 商品名称
    @ApiModelProperty(value = "商品名称")
    private String GoodsName;
    // 商品编码
    @ApiModelProperty(value = "商品编码")
    private String GoodsCode;
    // 商品数量
    @ApiModelProperty(value = "商品数量")
    private Integer Goodsquantity;
    // 商品价格
    @ApiModelProperty(value = "商品价格")
    private BigDecimal GoodsPrice;
    // 商品重量
    @ApiModelProperty(value = "商品重量")
    private BigDecimal GoodsWeight;
    // 商品描述
    @ApiModelProperty(value = "商品描述")
    private String GoodsDesc;
    // 商品体积
    @ApiModelProperty(value = "商品体积")
    private Double GoodsVol;

    @JSONField(name = "GoodsName")
    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }
    @JSONField(name = "GoodsCode")
    public String getGoodsCode() {
        return GoodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        GoodsCode = goodsCode;
    }
    @JSONField(name = "Goodsquantity")
    public Integer getGoodsquantity() {
        return Goodsquantity;
    }

    public void setGoodsquantity(Integer goodsquantity) {
        Goodsquantity = goodsquantity;
    }
    @JSONField(name = "GoodsPrice")
    public BigDecimal getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        GoodsPrice = goodsPrice;
    }
    @JSONField(name = "GoodsWeight")
    public BigDecimal getGoodsWeight() {
        return GoodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        GoodsWeight = goodsWeight;
    }
    @JSONField(name = "GoodsDesc")
    public String getGoodsDesc() {
        return GoodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        GoodsDesc = goodsDesc;
    }

    @JSONField(name = "GoodsVol")
    public Double getGoodsVol() {
        return GoodsVol;
    }

    public void setGoodsVol(Double goodsVol) {
        GoodsVol = goodsVol;
    }
}
