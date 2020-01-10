package cn.czcxy.xj.basicserver.role.model;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpRole {
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remaker;

    private Integer version;
}