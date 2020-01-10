package cn.czcxy.xj.basicserver.menu.model;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpMenu {

    private Integer id;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 父级编码
     */
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
    private Integer serverFlag;

    @Transient
    private List<EidpMenu> childMenus;

    @Transient
    private EidpMenu parentMenu;

}