package cn.czcxy.xj.crmclient.purchase.purchaseApply.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApplyPart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseApplyPartMapper extends CustomizeMapper<PurchaseApplyPart> {
    List<PurchaseApplyPart> findPartById(@Param("id") String id);
}