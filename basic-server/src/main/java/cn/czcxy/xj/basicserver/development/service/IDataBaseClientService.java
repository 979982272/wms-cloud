package cn.czcxy.xj.basicserver.development.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/dataBase")
public interface IDataBaseClientService {
    @RequestMapping(value = "loadData", method = RequestMethod.POST)
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest) ;
}
