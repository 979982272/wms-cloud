package cn.czcxy.xj.core.platform.init;

import cn.czcxy.xj.core.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 系统初始化
 *
 * @author weihua
 * @create 2017-05-07下午 10:24
 */
@Component
public class SystemInit implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            ApplicationContext context = event.getApplicationContext();
            SpringUtil.setApplicationContext(context);
        }
        if (event.getApplicationContext().getParent().getParent() == null){
            ApplicationContext context = event.getApplicationContext();
            SpringUtil.setApplicationContext(context);
        }
    }
}
