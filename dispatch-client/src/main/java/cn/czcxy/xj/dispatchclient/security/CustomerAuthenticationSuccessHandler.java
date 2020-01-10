package cn.czcxy.xj.dispatchclient.security;

import cn.czcxy.xj.basicserver.emp.model.EidpEmp;
import cn.czcxy.xj.basicserver.emp.service.IEidpEmpClientService;
import cn.czcxy.xj.basicserver.menu.model.EidpMenu;
import cn.czcxy.xj.basicserver.menu.model.Node;
import cn.czcxy.xj.basicserver.menu.service.IEidpMenuClientService;
import cn.czcxy.xj.core.platform.redis.RedisUtil;
import cn.czcxy.xj.core.util.SpringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * spring Security登陆成功的处理
 */
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String forwardUrl;

    public CustomerAuthenticationSuccessHandler(String forwardUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(forwardUrl), "'" + forwardUrl + "' is not a valid forward URL");
        this.forwardUrl = forwardUrl;
    }

    /**
     * 登陆成功后spring security 会回调这个方案
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        String userName = user.getUsername();
        IEidpMenuClientService eidpMenuClientService = SpringUtil.getBean(IEidpMenuClientService.class);
        IEidpEmpClientService eidpEmpClientService = SpringUtil.getBean(IEidpEmpClientService.class);
        RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
        EidpEmp eidpEmp = eidpEmpClientService.findEidpEmpByName(userName);
        eidpEmp.setPassword(null);
        // 获取token
        String token = redisUtil.createToken(userName);
        List<Node<Integer, String>> nodes = eidpMenuClientService.getMenusByEmpOrOrganization(eidpEmp.getId(), null, token);
        // 缓存到redis中
        redisUtil.set(token, eidpEmp, RedisUtil.tokenTimeOut);
        redisUtil.set(token + "menu", nodes, RedisUtil.tokenTimeOut);
        request.getRequestDispatcher(this.forwardUrl + "&token=" + token + "&userName=" + userName).forward(request, response);
    }
}
