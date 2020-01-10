package cn.czcxy.xj.dispatchclient.interceptor;

import cn.czcxy.xj.basicserver.emp.model.EidpEmp;
import cn.czcxy.xj.core.platform.base.controller.BaseController;
import cn.czcxy.xj.core.platform.redis.RedisUtil;
import cn.czcxy.xj.core.util.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 前端服务拦截器
 */
@Component
public class DispatchInterceptor implements HandlerInterceptor {

    protected static final Logger logger = LoggerFactory.getLogger(DispatchInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            logger.error("该请求没有token参数:" + request.getServletPath() + ";参数:" + request.getQueryString());
            response.sendRedirect("/login");
        } else {
            RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
            EidpEmp eidpEmp = (EidpEmp) redisUtil.get(token);
            if (eidpEmp == null) {
                logger.error("登陆过期！");
                response.sendRedirect("/login");
            } else {
                redisUtil.set(token, eidpEmp, RedisUtil.tokenTimeOut);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
