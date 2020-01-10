package cn.czcxy.xj.basicserver.goods.model;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsType {
    private Integer version;
    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String des;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父级主键
     */
    private String parentId;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 创建人id
     */
    private String createEmpId;

    /**
     * 创建人
     */
    private String createEmp;

    /**
     * 修改日期
     */
    private Date modifytime;

    /**
     * 修改人id
     */
    private String modifyEmpId;

    /**
     * 修改人
     */
    private String modifyEmp;

    /**
     * 是否删除0：正常,1删除
     */
    private int serverFlag;


}