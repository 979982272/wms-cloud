package cn.czcxy.xj.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: wwh
 * @Date: 2018/5/8 0008 22:05
 * @Description: 路由网关过滤器
 */
@Component
public class PreZuulFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);

    @Override
    public String filterType() {
        // 过滤请求之前的
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 过滤器启动的顺序，值越小越先执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // true 执行下方run中的过滤逻辑
        // false 不执行过滤逻辑
        return true;
    }

    @Override
    public Object run() {
        // 过滤逻辑
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            logger.warn("token为空禁止访问！");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                HttpServletResponse response = requestContext.getResponse();
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("token为空禁止访问！");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            return null;
        }
        // 判断是否已经登陆/使用redis
        return null;
    }
}
