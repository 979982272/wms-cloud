package cn.czcxy.xj.basicclient.goods.entity;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eidp_goods_type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsType {
    @Version
    private Integer version;
    /**
     * 主键
     */
    @Id
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
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 创建人id
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 修改日期
     */
    private Date modifytime;

    /**
     * 修改人id
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;
}