package com.ps.cloud.cfgbeans;

import com.ps.cloud.loadbalance.CustomLoadBalancerConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Cloud版本如果Hoxton.M2 RELEASED版本之前的，
 * Nacos Discovery默认是集成了Ribbon的，
 * 但是最新Alibaba-Nacos-Discovery在Hoxton.M2 RELEASED版本之后弃用了Ribbon，
 * 使用Spring Cloud Loadbalancer作为客户端的负载均衡组件。
 * 从Spring Cloud 2020版本开始，Spring Cloud移除了 Ribbon，
 * 使用Spring Cloud Loadbalancer作为客户端的负载均衡组件.
 * 这里使用的是Spring Cloud Loadbalancer方式
 */
@Configuration
@LoadBalancerClient(name = "MICROSERVICECLOUD-DEPT", configuration = {CustomLoadBalancerConfiguration.class})
public class CustomRestTemplateConfig //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{
    /**
     * Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
     * 但Spring Cloud Ribbon 已被默认使用Spring Cloud Loadbalancer作为客户端的负载均衡组件
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}

//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">