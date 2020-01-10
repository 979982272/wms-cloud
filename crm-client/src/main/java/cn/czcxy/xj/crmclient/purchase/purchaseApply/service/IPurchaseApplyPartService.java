package cn.czcxy.xj.crmclient.purchase.purchaseApply.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApplyPart;

import java.util.List;

public interface IPurchaseApplyPartService extends IBaseService<PurchaseApplyPart,String> {

    /**
     * 保存明细信息
     * @param purchaseApplyParts
     * @param applyId
     * @throws Exception
     */
    void savePurchaseApplyPart(List<PurchaseApplyPart> purchaseApplyParts, String applyId)throws Exception;
}
