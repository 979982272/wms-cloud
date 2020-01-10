package cn.czcxy.xj.basicclient.organization.entity;

import cn.czcxy.xj.core.platform.base.annotation.NotDuplicate;
import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eidp_organization")
public class EidpOrganization {
    /**
     * 机构编码
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 机构名称
     */
    @Column(name = "organization_name")
    @NotDuplicate
    private String organizationName;

    /**
     * 联系人
     */
    @Column(name = "link_man")
    private String linkMan;

    /**
     * 联系电话
     */
    @Column(name = "link_phone")
    private String linkPhone;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remaker;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    /**
     * 组织机构编码
     */
    @Column(name = "organization_code")
    @NotDuplicate
    private String organizationCode;

    @Version
    private Integer version;
}