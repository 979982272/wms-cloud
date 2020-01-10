package cn.czcxy.xj.basicclient.vendor.entity;


import cn.czcxy.xj.core.platform.base.annotation.CreateFlag;
import cn.czcxy.xj.core.platform.base.annotation.ModifyFlag;
import cn.czcxy.xj.core.platform.base.annotation.NotEmpty;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_vendor")
@CreateFlag
@ModifyFlag
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    /**
     * 供应商编码
     */
    @Id
    @NotEmpty
    private String id;

    /**
     * 供应商名称
     */
    @Column(name = "vendor_name")
    @NotEmpty
    private String vendorName;

    /**
     * 负责人编号
     */
    @Column(name = "vendor_emp_id")
    @NotEmpty
    private String vendorEmpId;

    /**
     * 负责人 名称
     */
    @Column(name = "vendor_emp_name")
    @NotEmpty
    private String vendorEmpName;

    /**
     * 应收金额
     */
    @Column(name = "payable_amount")
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
    @Column(name = "post_code")
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    @Column(name = "contact_person")
    private String contactPerson;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 传真
     */
    @Column(name = "contact_fax")
    private String contactFax;

    /**
     * 邮件
     */
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 法人
     */
    @Column(name = "legal_person")
    private String legalPerson;

    /**
     * 营业执照
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 税务登记号
     */
    @Column(name = "taxation_code")
    private String taxationCode;

    /**
     * 开户行
     */
    @Column(name = "opening_bank")
    private String openingBank;

    /**
     * 开户账号
     */
    @Column(name = "opening_bank_account")
    private String openingBankAccount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 创建人编号
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 修改人编号
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    @Version
    private Integer version;
}