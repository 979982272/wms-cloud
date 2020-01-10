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
public class Vendor {
    private Integer version;
    /**
     * 供应商编码
     */
    private String id;

    /**
     * 供应商名称
     */
    private String vendorName;

    /**
     * 负责人编号
     */
    private String vendorEmpId;

    /**
     * 负责人 名称
     */
    private String vendorEmpName;

    /**
     * 应收金额
     */
    private BigDecimal payableAmount;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 传真
     */
    private String contactFax;

    /**
     * 邮件
     */
    private String contactEmail;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 税务登记号
     */
    private String taxationCode;

    /**
     * 开户行
     */
    private String openingBank;

    /**
     * 开户账号
     */
    private String openingBankAccount;

    /**
     * 备注
     */
    private String remark;

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