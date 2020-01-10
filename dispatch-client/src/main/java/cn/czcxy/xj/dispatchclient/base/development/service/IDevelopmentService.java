package cn.czcxy.xj.dispatchclient.base.development.service;

import cn.czcxy.xj.basicserver.development.model.Development;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: wwh
 * @Date: 2018/7/1 0001 00:11
 * @Description: 程序制作
 */
public interface IDevelopmentService {
    /**
     * 创建主页页面
     * @param developments
     * @param src
     * @param htmlName
     * @throws Exception
     */
    void createGridHtml(List<Development> developments, String src, String htmlName)throws Exception;

    /**
     * 创建表格页面
     * @param developments
     * @param src
     * @param htmlName
     * @throws Exception
     */
    void createFormHtml(List<Development> developments, String src, String htmlName)throws Exception;

    /**
     * 创建vue表格页
     * @param developments
     * @param src
     * @param htmlName
     * @param request
     * @param response
     * @throws Exception
     */
    ResponseEntity<byte[]> createGridVue(List<Development> developments, String src, String htmlName, HttpServletRequest request, HttpServletResponse response)throws Exception;

    /**
     * 创建vue表单
     * @param developments
     * @param src
     * @param vueName
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    ResponseEntity<byte[]> createFormVue(List<Development> developments, String src, String vueName, HttpServletRequest request, HttpServletResponse response)throws Exception;
}
