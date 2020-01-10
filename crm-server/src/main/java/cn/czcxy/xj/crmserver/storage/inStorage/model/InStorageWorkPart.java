package cn.czcxy.xj.crmserver.storage.inStorage.model;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InStorageWorkPart {
    private Integer version;

    private String id;

    /**
     * 入库单号
     */
    private String inStorageWork;

    /**
     * 来源明细编号
     */
    private String fromOrderPart;

    /**
     * 产品编码
     */
    private String goodsId;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 单位编码
     */
    private String goodsUnitId;

    /**
     * 单位名称
     */
    private String goodsUnitName;

    /**
     * 规格型号
     */
    private String goodsModel;

    /**
     * 计划数量
     */
    private BigDecimal planAmount;

    /**
     * 入库数量
     */
    private BigDecimal receivingAmount;

    /**
     * 备注
     */
    private String remark;


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