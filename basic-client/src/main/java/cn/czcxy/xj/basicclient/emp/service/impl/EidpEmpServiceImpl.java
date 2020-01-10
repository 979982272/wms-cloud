package cn.czcxy.xj.basicclient.emp.service.impl;

import cn.czcxy.xj.core.extra.system.AuthInfo;
import cn.czcxy.xj.core.extra.system.UserInfoUtil;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.basicclient.emp.entity.EidpEmp;
import cn.czcxy.xj.basicclient.emp.mapper.EidpEmpMapper;
import cn.czcxy.xj.basicclient.emp.service.IEidpEmpService;
import cn.czcxy.xj.core.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wwh
 * @Date: 2018/5/27 0027 23:21
 * @Description:
 */
@Transactional(rollbackFor = Exception.class)
@Service("eidpEmpService")
public class EidpEmpServiceImpl extends BaseServiceImpl<EidpEmp, String> implements IEidpEmpService {
    @Resource
    @BaseResource
    private EidpEmpMapper eidpEmpMapper;

    @Override
    public void reSetPassword(String[] ids) throws Exception {
        EidpEmp eidpEmp = null;
        for (String id : ids) {
            eidpEmp = eidpEmpMapper.selectByPrimaryKey(id);
            if (null == eidpEmp) {
                throw new Exception("查询不到对应的用户！【" + id + "】");
            }
            eidpEmp.setPassword(new BCryptPasswordEncoder().encode("123456"));
            eidpEmpMapper.updateByPrimaryKey(eidpEmp);
        }
    }

    @Override
    public EidpEmp findEidpEmpByName(String userName) {
        List<EidpEmp> eidpEmps = eidpEmpMapper.findEidpEmpByName(userName);
        EidpEmp eidpEmp = null;
        if (CollectionUtils.isNotEmpty(eidpEmps)) {
            eidpEmp = eidpEmps.get(0);
        }
        return eidpEmp;
    }

    @Override
    public String getCreateEidpEmpId() {
        Integer count = eidpEmpMapper.getEidpMaxCount();
        if (null == count) {
            count = 0;
        }
        return CommonUtil.transIdByCount(count);
    }

    @Override
    public EidpEmp save(EidpEmp eidpEmp) throws Exception {
        valide(eidpEmp);
        List<EidpEmp> eidpEmps = eidpEmpMapper.findEidpEmpByName(eidpEmp.getUsername());
        if (CollectionUtils.isNotEmpty(eidpEmps)) {
            throw new Exception("存在相同的姓名!【" + eidpEmp.getUsername() + "】");
        }
        eidpEmp.setPassword(new BCryptPasswordEncoder().encode("123456"));
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        eidpEmp.setCreatetime(new Date());
        eidpEmp.setCreateEmpId(authInfo.getEmpId());
        eidpEmp.setCreateEmp(authInfo.getEmpName());
        super.save(eidpEmp);
        return eidpEmp;
    }

    @Override
    public EidpEmp update(EidpEmp eidpEmp) throws Exception {
        valide(eidpEmp);
        List<EidpEmp> eidpEmps = eidpEmpMapper.findEidpEmpByNameAndNotId(eidpEmp.getUsername(), eidpEmp.getId());
        if (CollectionUtils.isNotEmpty(eidpEmps)) {
            throw new Exception("存在相同的姓名!【" + eidpEmp.getUsername() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        eidpEmp.setModifytime(new Date());
        eidpEmp.setModifyEmpId(authInfo.getEmpId());
        eidpEmp.setModifyEmp(authInfo.getEmpName());
        eidpEmpMapper.updateByPrimaryKeyWithVersion(eidpEmp);
        return eidpEmp;
    }

    /**
     * 验证
     *
     * @param entity
     * @throws Exception
     */
    private void valide(EidpEmp entity) throws Exception {
        if (null == entity) {
            throw new Exception("需要保存的用户信息为空！");
        }
        if (StringUtils.isEmpty(entity.getId())) {
            throw new Exception("用户编号不能为空！");
        }
        if (StringUtils.isEmpty(entity.getUsername())) {
            throw new Exception("用户姓名不能为空！");
        }
    }
}
