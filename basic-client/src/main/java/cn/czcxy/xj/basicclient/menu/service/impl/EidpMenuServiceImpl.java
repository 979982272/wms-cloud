package cn.czcxy.xj.basicclient.menu.service.impl;

import cn.czcxy.xj.basicclient.menu.entity.EidpMenu;
import cn.czcxy.xj.basicclient.menu.entity.Items;
import cn.czcxy.xj.basicclient.menu.mapper.EidpMenuMapper;
import cn.czcxy.xj.basicclient.menu.service.IEidpMenuService;
import cn.czcxy.xj.core.extra.system.AuthInfo;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(rollbackFor = Exception.class)
@Service("eidpMenuService")
public class EidpMenuServiceImpl extends BaseServiceImpl<EidpMenu, Integer> implements IEidpMenuService {

    @Resource
    @BaseResource
    private EidpMenuMapper eidpMenuMapper;

    @Override
    public void deleteById(Integer id) throws Exception {
        Example example = new Example(EidpMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", id);
        if (CollectionUtils.isNotEmpty(eidpMenuMapper.selectByExample(example))) {
            throw new Exception("请先删除子菜单!");
        }
        super.deleteById(id);
    }

}