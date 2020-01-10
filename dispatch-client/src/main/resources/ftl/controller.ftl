package ${classPack}.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import ${classPack}.service.I${className}Service;
import ${classPack}.entity.${className};
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("${controllerPath}")
public class ${className}Controller extends BaseCurdController<${className},${idType}>{

    @Resource
    @BaseResource
    private I${className}Service ${className?uncap_first}Service;
}
