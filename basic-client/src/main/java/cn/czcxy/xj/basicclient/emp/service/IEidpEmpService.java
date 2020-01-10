package cn.czcxy.xj.basicclient.emp.service;

import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.basicclient.emp.entity.EidpEmp;

/**
 * @Auther: wwh
 * @Date: 2018/5/27 0027 23:20
 * @Description:
 */
public interface IEidpEmpService extends IBaseService<EidpEmp,String> {
    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    EidpEmp findEidpEmpByName(String userName);

    String getCreateEidpEmpId();

    /**
     * 重置密码
     * @param ids
     * @throws Exception
     */
    void reSetPassword(String[] ids)throws Exception;
}
