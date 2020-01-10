package cn.czcxy.xj.dispatchclient.base.common;

import cn.czcxy.xj.basicserver.common.service.ICommonClientService;
import cn.czcxy.xj.core.platform.base.controller.BaseController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.util.CommonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公用控制器
 *
 * @author weihua
 * @create 2017-05-15 18:47
 */
@RestController
@RequestMapping(value = "/base/common")
public class CommonController extends BaseController {

    @Resource
    private ICommonClientService commonService;

    /**
     * 通过包名获取枚举的下拉框数据源
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/getComboModelDataSource")
    @ResponseBody
    public List<ComboModel> getComboModelDataSource(@RequestParam("key") String key) {
        List<ComboModel> comboModels = null;
        try {
            comboModels = commonService.getComboModelDataSource(key);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            comboModels = new ArrayList<>();
        }
        return comboModels;
    }
}
