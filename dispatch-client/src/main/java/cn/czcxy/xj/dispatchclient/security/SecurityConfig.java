package cn.czcxy.xj.dispatchclient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

/**
 * @Auther: wwh
 * @Date: 2018/5/24 0024 21:11
 * @Description:
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("0000").password("123456").roles("user");
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        // 生成密码A
        // new BCryptPasswordEncoder().encode("");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                // 设置静态的资源允许所有访问
                .antMatchers("/static/base/**").permitAll()
//                // 程序制作下载功能不过滤
//                .antMatchers("/base/development/createGridVue").permitAll()
//                // 其他所有资源都需要登陆后才能访问
                .anyRequest().authenticated()
                // 设置默认登陆页面/login
                .and().formLogin().loginPage("/login")
                // 强制指定登陆成功后跳转的路劲
                .successHandler(new CustomerAuthenticationSuccessHandler("/loginStatus?status=true"))
                .failureUrl("/loginStatus?status=false")
                .permitAll()
                // 设置缓存，默认2周有效
                .and().rememberMe().tokenValiditySeconds(1209600).key("mykey")
                // 设置登出的路径和登出成功后访问的路径
                .and().logout().logoutUrl("/loginOut").logoutSuccessUrl("/login").permitAll()
                // 禁用crsf
                .and().csrf().disable()
                // 使ifream可以访问资源
                .headers().frameOptions().sameOrigin()
        ;
    }
}
