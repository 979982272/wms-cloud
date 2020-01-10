package ${classPack}.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import ${classPack}.service.I${className}Service;
import ${classPack}.mapper.${className}Mapper;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import ${classPack}.entity.${className};
import org.springframework.transaction.annotation.Transactional;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;

@Transactional(rollbackFor = Exception.class)
@Service("${className?uncap_first}Service")
public class ${className}ServiceImpl extends BaseServiceImpl<${className},${idType}> implements I${className}Service{

    @Resource
    @BaseResource
    private ${className}Mapper ${className?uncap_first}Mapper;

}