package com.ps.cloud.cfgbeans;

import com.ps.cloud.loadbalance.CustomLoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Configuration;

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
public class CustomRestTemplateConfig
{

}