package com.ps.cloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Spring Cloud LoadBalancer替代Ribbon实现 随机/轮训 方式负载均衡策略配置
 */
public class CustomLoadBalancerConfiguration {

    /**
     * 自定义负载均衡策略（随机/轮训）
     *
     * @return ReactorLoadBalancer
     */
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment, LoadBalancerClientFactory factory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new CustomRandomLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);           //随机
        //return new RoundRobinLoadBalancer(factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);     // 轮训
    }
}
