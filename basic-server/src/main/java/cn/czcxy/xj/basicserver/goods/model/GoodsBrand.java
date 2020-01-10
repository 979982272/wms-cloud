package cn.czcxy.xj.basicserver.goods.model;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsBrand {
    private Integer version;
    /**
     * 主键
     */
    private String id;

    /**
     * 中文名称
     */
    private String chnName;

    /**
     * 英文名称
     */
    private String engName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    private int serverFlag;


}