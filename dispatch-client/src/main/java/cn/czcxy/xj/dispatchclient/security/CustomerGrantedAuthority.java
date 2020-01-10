package cn.czcxy.xj.dispatchclient.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: wwh
 * @Date: 2018/5/26 0026 22:09
 * @Description:
 */
public class CustomerGrantedAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "user";
    }
}
