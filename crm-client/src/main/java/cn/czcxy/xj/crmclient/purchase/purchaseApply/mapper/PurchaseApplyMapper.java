package cn.czcxy.xj.crmclient.purchase.purchaseApply.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApply;
import org.apache.ibatis.annotations.Param;

public interface PurchaseApplyMapper extends CustomizeMapper<PurchaseApply> {
    /**
     * 查询采购申请单，一对多
     *
     * @param id
     * @return
     */
    PurchaseApply findPurchaseApplyById(@Param("id") String id);

    /**
     * 修改采购申请状态
     *
     * @param id
     * @param status
     */
    void updatePurchaseApplyStatusById(@Param("id") String id, @Param("status") Integer status);
}