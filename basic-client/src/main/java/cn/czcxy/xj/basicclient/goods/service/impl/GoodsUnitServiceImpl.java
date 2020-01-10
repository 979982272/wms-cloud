package cn.czcxy.xj.basicclient.goods.service.impl;

import cn.czcxy.xj.basicclient.goods.entity.GoodsUnit;
import cn.czcxy.xj.basicclient.goods.mapper.GoodsUnitMapper;
import cn.czcxy.xj.basicclient.goods.service.IGoodsUnitService;
import cn.czcxy.xj.core.extra.system.AuthInfo;
import cn.czcxy.xj.core.extra.system.UserInfoUtil;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Transactional(rollbackFor = Exception.class)
@Service("goodsUnitService")
public class GoodsUnitServiceImpl extends BaseServiceImpl<GoodsUnit, String> implements IGoodsUnitService {

    @Resource
    @BaseResource
    private GoodsUnitMapper goodsUnitMapper;

    @Override
    public GoodsUnit save(GoodsUnit entity) throws Exception {
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        valid(entity);
        if (null != goodsUnitMapper.selectByPrimaryKey(entity.getId())) {
            throw new Exception("已经存在相同单位编号!【" + entity.getId() + "】");
        }
        if (CollectionUtils.isNotEmpty(goodsUnitMapper.findGoodsUnitByUnitName(entity.getUnitName()))) {
            throw new Exception("已经存在相同单位名称!【" + entity.getUnitName() + "】");
        }
        entity.setCreateTime(new Date());
        entity.setCreateEmpId(authInfo.getEmpId());
        entity.setCreateEmp(authInfo.getEmpName());
        return super.save(entity);
    }

    @Override
    public GoodsUnit update(GoodsUnit t) throws Exception {
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        valid(t);
        if (CollectionUtils.isNotEmpty(goodsUnitMapper.findGoodsUnitByUnitNameAndNotId(t.getUnitName(), t.getId()))) {
            throw new Exception("已经存在相同单位名称!【" + t.getUnitName() + "】");
        }
        t.setModifyTime(new Date());
        t.setModifyEmpId(authInfo.getEmpId());
        t.setModifyEmp(authInfo.getEmpName());
        goodsUnitMapper.updateByPrimaryKeyWithVersion(t);
        return t;
    }

    private void valid(GoodsUnit goodsUnit) throws Exception {
        if (StringUtils.isEmpty(goodsUnit.getId())) {
            throw new Exception("单位编号不能为空!");
        }
        if (StringUtils.isEmpty(goodsUnit.getUnitName())) {
            throw new Exception("单位名称不能为空!");
        }
    }
}