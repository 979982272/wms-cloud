package cn.czcxy.xj.basicclient.organization.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;

@Table(name = "eidp_organization_role")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpOrganizationRole {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 机构编码
     */
    @Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 角色编码
     */
    @Column(name = "role_id")
    private Integer roleId;

    @Version
    private Integer version;

}