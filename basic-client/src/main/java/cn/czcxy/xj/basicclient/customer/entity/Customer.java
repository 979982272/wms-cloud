package cn.czcxy.xj.basicclient.customer.entity;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_customer")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    /**
     * 客户编码
     */
    @Id
    private String id;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 客户类型 10售后20商场30零售
     */
    @Column(name = "customer_type")
    private String customerType;

    /**
     * 客户负责人编号
     */
    @Column(name = "customer_emp_id")
    private String customerEmpId;

    /**
     * 客户负责人 名称
     */
    @Column(name = "customer_emp_name")
    private String customerEmpName;

    /**
     * 销售方式10直销20委托代销30零售
     */
    @Column(name = "sales_type")
    private String salesType;

    /**
     * 客户级别10VIP客户20重要客户30一般客户
     */
    @Column(name = "customer_level")
    private String customerLevel;

    /**
     * 应收金额
     */
    @Column(name = "receivable_amount")
    private BigDecimal receivableAmount;

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