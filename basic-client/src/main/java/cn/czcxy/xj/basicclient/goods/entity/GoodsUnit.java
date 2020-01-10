package cn.czcxy.xj.basicclient.goods.entity;


import lombok.*;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eidp_goods_unit")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsUnit {
    @Version
    private Integer version;
    /**
     * 单位编号
     */
    @Id
    private String id;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 创建人编号
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 修改人编号
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;

}