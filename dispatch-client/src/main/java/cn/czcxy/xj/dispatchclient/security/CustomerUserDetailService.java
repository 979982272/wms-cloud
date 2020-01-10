package cn.czcxy.xj.dispatchclient.security;

import cn.czcxy.xj.basicserver.emp.model.EidpEmp;
import cn.czcxy.xj.basicserver.emp.service.IEidpEmpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wwh
 * @Date: 2018/5/26 0026 22:01
 * @Description:
 */
@Component
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private IEidpEmpClientService eidpEmpService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        EidpEmp eidpEmp= eidpEmpService.findEidpEmpByName(s);
        if (null == eidpEmp) {
            throw new UsernameNotFoundException("账户:" + s + "没有找到");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new CustomerGrantedAuthority());
        return new User(eidpEmp.getUsername(), eidpEmp.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
