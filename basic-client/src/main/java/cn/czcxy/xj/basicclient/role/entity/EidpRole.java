package cn.czcxy.xj.basicclient.role.entity;

import cn.czcxy.xj.core.platform.base.annotation.NotDuplicate;
import lombok.*;

import javax.persistence.*;

@Table(name = "eidp_role")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpRole {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    @NotDuplicate
    private String roleName;

    /**
     * 备注
     */
    private String remaker;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    @Version
    private Integer version;
}