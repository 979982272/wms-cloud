package cn.czcxy.xj.basicserver.vendor.model;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VendorGoods {
    private Integer version;
    /**
     * 主键
     */
    private String id;

    /**
     * 产品编码
     */
    private String goodsId;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 产品类型编码
     */
    private String goodsTypeId;

    /**
     * 产品类型名称
     */
    private String goodsTypeName;

    /**
     * 产品单位编码
     */
    private String goodsUnitId;

    /**
     * 产品单位名称
     */
    private String goodsUnitName;

    /**
     * 产品品牌编码
     */
    private String goodsBrandId;

    /**
     * 产品品牌名称
     */
    private String goodsBrandName;

    /**
     * 规格型号
     */
    private String goodsModel;

    /**
     * 供应商编码
     */
    private String vendorId;

    /**
     * 供应商名称
     */
    private String vendorName;

    /**
     * 供应价格
     */
    private BigDecimal normalPrice;

    /**
     * 是否删除0：正常,1删除
     */
    private Integer serverFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createEmp;


    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyEmp;

}