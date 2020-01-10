package cn.czcxy.xj.basicclient.goods.entity;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "eidp_goods_brand")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsBrand {
    @Version
    private Integer version;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 中文名称
     */
    @Column(name = "chn_name")
    private String chnName;

    /**
     * 英文名称
     */
    @Column(name = "eng_name")
    private String engName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;

}