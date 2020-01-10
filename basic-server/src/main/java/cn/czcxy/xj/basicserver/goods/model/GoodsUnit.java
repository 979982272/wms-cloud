package cn.czcxy.xj.basicserver.goods.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsUnit {
    private Integer version;
    /**
     * 单位编号
     */
    private String id;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createEmp;

    /**
     * 创建人编号
     */
    private String createEmpId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyEmp;

    /**
     * 修改人编号
     */
    private String modifyEmpId;

    /**
     * 是否删除0：正常,1删除
     */
    private int serverFlag;

}