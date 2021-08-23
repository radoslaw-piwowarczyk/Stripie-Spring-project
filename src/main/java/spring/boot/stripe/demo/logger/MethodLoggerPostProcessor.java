package spring.boot.stripe.demo.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MethodLoggerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractLogger) {
            ProxyFactory factory = new ProxyFactory(bean);
            factory.addInterface(AbstractLogger.class);
            factory.addAdvice((MethodBeforeAdvice) (method, methodArgs, target) ->
                    log.debug("Invoked method: {}#{} with args: {}", bean.getClass().getSimpleName(), method.getName(), methodArgs));
            factory.setExposeProxy(true);
            return factory.getProxy();
        } return bean;
    }
}
