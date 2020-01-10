package cn.czcxy.xj.basicclient.role.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "eidp_role_menu")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpRoleMenu {
    @Id
    private Integer id;

    /**
     * 角色编码
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单编码
     */
    @Column(name = "menu_id")
    private Integer menuId;

}