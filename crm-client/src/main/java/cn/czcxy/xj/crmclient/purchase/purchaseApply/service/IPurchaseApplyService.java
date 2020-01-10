package cn.czcxy.xj.crmclient.purchase.purchaseApply.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.purchase.purchaseApply.entity.PurchaseApply;

public interface IPurchaseApplyService extends IBaseService<PurchaseApply, String> {
    /**
     * 保存
      * @param t
     * @return
     * @throws Exception
     */
    PurchaseApply savePurchaseApply(PurchaseApply t) throws Exception;

    /**
     * 审核
     * @param id
     * @throws Exception
     */
    void auditById(String id)throws Exception;

    /**
     * 取消审核
     * @param id
     * @throws Exception
     */
    void cancelAuditById(String id)throws Exception;
}
