package cn.czcxy.xj.dispatchclient.base.development.service.impl;

import cn.czcxy.xj.basicserver.development.model.Development;
import cn.czcxy.xj.core.util.CommonUtil;
import cn.czcxy.xj.core.util.FileUtil;
import cn.czcxy.xj.core.util.FreemakerUtil;
import cn.czcxy.xj.dispatchclient.base.development.service.IDevelopmentService;
import cn.czcxy.xj.dispatchclient.properties.DevelopmentProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wwh
 * @Date: 2018/7/1 0001 00:11
 * @Description:
 */
@Service
public class DevelopmentServiceImpl implements IDevelopmentService {

    @Resource
    private DevelopmentProperties developmentProperties;


    @Override
    public void createGridHtml(List<Development> developments, String src, String htmlName) throws Exception {
        createHtml(developments, src, htmlName, developmentProperties.getGridHtmlFtl(), "html");
    }

    @Override
    public void createFormHtml(List<Development> developments, String src, String htmlName) throws Exception {
        createHtml(developments, src, htmlName, developmentProperties.getEditHtmlFtl(), "html");
    }

    /**
     * 创建html页面
     *
     * @param developments 数据
     * @param src          路径
     * @param htmlName     页面名称
     * @param ftl          模板位置
     * @throws Exception
     */
    private void createHtml(List<Development> developments, String src, String htmlName, String ftl, String prefix) throws Exception {
        String folderPath = developmentProperties.getHtmPath() + CommonUtil.transSrc(src);
        String filePath = folderPath + CommonUtil.transHtmlName(htmlName, prefix);
        developments = sortDevelopments(developments);
        Map map = new HashMap();
        map.put("developments", developments);
        map.put("htmlName", htmlName);
        map.put("controllerSrc", CommonUtil.transSrc(src));
        FreemakerUtil.createFileByFreemaker(map, ftl, filePath, folderPath,this.getClass().getClassLoader());
    }

    /**
     * 给数据排序与转换列名
     *
     * @param list
     * @return
     */
    private List<Development> sortDevelopments(List<Development> list) {
        Development[] developments = new Development[list.size()];
        Development development = null;
        List<Development> data = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            developments[i] = list.get(i);
        }
        for (int i = 0; i < developments.length - 1; i++) {
            for (int j = 0; j < developments.length - i - 1; j++) {
                if (developments[j].getSort() > developments[j + 1].getSort()) {
                    development = developments[j];
                    developments[j] = developments[j + 1];
                    developments[j + 1] = development;
                }
            }
        }
        // 将列名字段进行转换
        for (int i = 0; i < developments.length; i++) {
            development = developments[i];
            development.setColumnName(CommonUtil.transSqlFiledToEntityFiled(development.getColumnName()));
            data.add(development);
        }
        return data;
    }

    @Override
    public ResponseEntity<byte[]> createGridVue(List<Development> developments, String src, String vueName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buldeVueFtl(developments,src,vueName,developmentProperties.getGridVueFtl());
    }

    @Override
    public ResponseEntity<byte[]> createFormVue(List<Development> developments, String src, String vueName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buldeVueFtl(developments,src,vueName,developmentProperties.getEditVuelFtl());
    }

    /**
     * 创建下载vue
     * @param developments
     * @param src
     * @param vueName
     * @param ftlPath
     * @return
     * @throws Exception
     */
    private ResponseEntity<byte[]> buldeVueFtl(List<Development> developments, String src, String vueName, String ftlPath)throws Exception{
        String prefix = "vue";
        createHtml(developments, src, vueName, ftlPath, prefix);
        String folderPath = developmentProperties.getHtmPath() + CommonUtil.transSrc(src);
        String filePath = folderPath + CommonUtil.transHtmlName(vueName, prefix);
        File newFile = new File(filePath);
        ResponseEntity<byte[]> responseEntity = FileUtil.buildResponseEntity(newFile, "");
        FileUtil.delFolder(newFile.getParent());
        return responseEntity;
    }
}
