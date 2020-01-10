package cn.czcxy.xj.basicserver.organization.model;

import lombok.*;
import tk.mybatis.mapper.annotation.Version;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EidpOrganization {
    private Integer version;
    /**
     * 机构编码
     */
    private Integer id;

    /**
     * 机构名称
     */
    private String organizationName;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remaker;

    /**
     * 组织机构编码
     */
    private String organizationCode;

}