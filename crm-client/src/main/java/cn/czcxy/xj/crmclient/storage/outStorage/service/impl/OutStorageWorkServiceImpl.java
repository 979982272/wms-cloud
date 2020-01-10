package cn.czcxy.xj.crmclient.storage.outStorage.service.impl;

import cn.czcxy.xj.core.extra.enums.OrderTypeEnum;
import cn.czcxy.xj.core.extra.enums.storage.OutStorageStatusEnum;
import cn.czcxy.xj.core.extra.enums.storage.StorageStatusEnum;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.crmclient.sales.entity.SalesOrder;
import cn.czcxy.xj.crmclient.sales.service.ISalesOrderService;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWork;
import cn.czcxy.xj.crmclient.storage.outStorage.entity.OutStorageWorkPart;
import cn.czcxy.xj.crmclient.storage.outStorage.mapper.OutStorageWorkMapper;
import cn.czcxy.xj.crmclient.storage.outStorage.service.IOutStorageWorkPartService;
import cn.czcxy.xj.crmclient.storage.outStorage.service.IOutStorageWorkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("outStorageWorkService")
public class OutStorageWorkServiceImpl extends BaseServiceImpl<OutStorageWork, String> implements IOutStorageWorkService {

    @Resource
    @BaseResource
    private OutStorageWorkMapper outStorageWorkMapper;

    @Resource
    private IOutStorageWorkPartService outStorageWorkPartService;

    @Resource
    private ISalesOrderService salesOrderService;

    @Override
    public void outStorageByStorageId(String id) throws Exception {
        OutStorageWork outStorageWork = outStorageWorkMapper.findOutStorageWorkById(id);
        if (null == outStorageWork) {
            throw new Exception("查询不到对应的出库单！【" + id + "】");
        }
        if (outStorageWork.getStatus() > OutStorageStatusEnum.SUCCESSDELIVERY.getCode()) {
            throw new Exception("该出库单已完全出库！【" + id + "】");
        }
        /**
         * 1.出库，修改出库单/销售单出库数量;将出库库存减少，总库存减少
         * 2.修改出库单状态
         * 3.修改销售单状态
         */
        outStorageWorkPartService.outStorage(outStorageWork.getOutStorageWorkParts());
        updateOutStorageWorkStatus(outStorageWork);
        salesOrderService.updateSalesOrderStatus(outStorageWork.getFromOrder());
    }

    /**
     * 修改出库单状态
     *
     * @param storageWork
     * @throws Exception
     */
    @Override
    public void updateOutStorageWorkStatus(OutStorageWork storageWork) throws Exception {
        Integer successDeliveryCount = 0;
        Integer partDeliveryCount = 0;
        Integer deliveryCode = null;
        List<OutStorageWorkPart> outStorageWorkParts = storageWork.getOutStorageWorkParts();
        for (OutStorageWorkPart part : outStorageWorkParts) {
            if (part.getDeliveryAmount().compareTo(part.getPlanAmount()) >= 0) {
                // 收货 大于采购数量的记录数
                // 如果该记录数等于明细数量则表名完全下推，否则部分下推
                successDeliveryCount = successDeliveryCount + 1;
            }

            if (part.getDeliveryAmount().compareTo(BigDecimal.ZERO) > 0) {
                partDeliveryCount = partDeliveryCount + 1;
            }
        }
        if (successDeliveryCount == outStorageWorkParts.size()) {
            // 完全下推的数量等于明细数量：完全下推
            deliveryCode = OutStorageStatusEnum.SUCCESSDELIVERY.getCode();
        } else if (partDeliveryCount > 0) {
            // 部分下推的数量大于明细数量：部分下推
            deliveryCode = OutStorageStatusEnum.PARTDELIVERY.getCode();
        } else {
            deliveryCode = 0;
        }
        // 如果下推状态等于零则表示错误调用不做处理
        if (deliveryCode != 0) {
            outStorageWorkMapper.updateOutStorageWorkStatusById(storageWork.getId(), deliveryCode);
        }
    }

    @Override
    public void buidOutStorageWorkBySalesOrder(SalesOrder salesOrder) throws Exception {
        OutStorageWork outStorageWork = new OutStorageWork();
        String id = CommonUtil.getIdByCode(OrderTypeEnum.OUTSTORAGEWORK.getCode());
        outStorageWork.setId(id);
        outStorageWork.setStatus(StorageStatusEnum.AUDIT.getCode());
        outStorageWork.setFromOrder(salesOrder.getId());
        outStorageWork.setOrderType(OrderTypeEnum.SALESORDER.getCode());
        outStorageWork.setOrderDate(new Date());
        outStorageWork.setCustomerId(salesOrder.getCustomerId());
        outStorageWork.setCustomerName(salesOrder.getCustomerName());
        outStorageWork.setWarehouseId(salesOrder.getWarehouseId());
        outStorageWork.setWarehouseName(salesOrder.getWarehouseName());
        outStorageWork.setRemark(salesOrder.getRemark());
        super.save(outStorageWork);
        // 构建明细信息
        outStorageWorkPartService.buidOutStoragePartWorkBySalesOrder(salesOrder.getSalesOrderParts(), id);
    }

    @Override
    public OutStorageWork selectById(String id) {
        return outStorageWorkMapper.findOutStorageWorkById(id);
    }
}