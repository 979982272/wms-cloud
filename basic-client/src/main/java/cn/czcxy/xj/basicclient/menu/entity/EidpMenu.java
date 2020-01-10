package cn.czcxy.xj.basicclient.menu.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eidp_menu")
public class EidpMenu {

    @Id
    private Integer id;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 父级编码
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 地址
     */
    private String url;

    /**
     * 文本
     */
    private String text;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    @Transient
    private List<EidpMenu> childMenus;

    @Transient
    private EidpMenu parentMenu;
}