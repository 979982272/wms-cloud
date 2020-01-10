package cn.czcxy.xj.basicserver.role.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpRoleMenu {
    private Integer id;

    /**
     * 角色编码
     */
    private Integer roleId;

    /**
     * 菜单编码
     */
    private Integer menuId;

}