package cn.czcxy.xj.basicclient.role.service.impl;

import cn.czcxy.xj.basicclient.role.entity.EidpRoleMenu;
import cn.czcxy.xj.basicclient.role.mapper.EidpRoleMenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.czcxy.xj.basicclient.role.service.IEidpRoleService;
import cn.czcxy.xj.basicclient.role.mapper.EidpRoleMapper;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.basicclient.role.entity.EidpRole;
import org.springframework.transaction.annotation.Transactional;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Transactional(rollbackFor = Exception.class)
@Service("eidpRoleService")
public class EidpRoleServiceImpl extends BaseServiceImpl<EidpRole, java.lang.Integer> implements IEidpRoleService {

    @Resource
    @BaseResource
    private EidpRoleMapper eidpRoleMapper;

    @Resource
    private EidpRoleMenuMapper eidpRoleMenuMapper;

    @Override
    public List<EidpRoleMenu> findEidpRoleMenusByRoleId(Integer roleId) throws Exception {
        Example example = new Example(EidpRoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        return eidpRoleMenuMapper.selectByExample(example);
    }

    @Override
    public void assignMenu(Integer[] ids, Integer roleId) throws Exception {
        EidpRoleMenu eidpRoleMenu = null;
        Example example = new Example(EidpRoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        eidpRoleMenuMapper.deleteByExample(example);
        // 父级菜单会有重复，用hashSet去重
        Set<Integer> idSet = new HashSet<>();
        for (Integer id : ids) {
            idSet.add(id);
        }
        Iterator<Integer> menuIds = idSet.iterator();
        while (menuIds.hasNext()) {
            eidpRoleMenu = new EidpRoleMenu();
            eidpRoleMenu.setRoleId(roleId);
            eidpRoleMenu.setMenuId(menuIds.next());
            eidpRoleMenuMapper.insertSelective(eidpRoleMenu);
        }
    }
}