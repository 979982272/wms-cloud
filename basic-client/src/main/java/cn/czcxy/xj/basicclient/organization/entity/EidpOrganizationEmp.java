package cn.czcxy.xj.basicclient.organization.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;

@Table(name = "eidp_organization_emp")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpOrganizationEmp {
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
    @Column(name = "emp_id")
    private String empId;

    @Version
    private Integer version;
}