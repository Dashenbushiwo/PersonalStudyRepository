package com.ps.cloud.controller;

import com.ps.cloud.entity.Dept;
import com.ps.cloud.service.DeptService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController
{
	private final DeptService service;
	private final DiscoveryClient client;

	public DeptController(DeptService service, DiscoveryClient client) {
		this.service = service;
		this.client = client;
	}


	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept)
	{
		return service.add(dept);
	}

	/**
	 * 不建议CircuitBreaker这么写，耦合性过高
	 */
	@CircuitBreaker(name = "backendA", fallbackMethod = "getProcessFallbackMethod")
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id)
	{
		Dept dept = service.get(id);
		if (dept == null) {
			throw new RuntimeException("该ID：" + id + "在数据库中无法找到对应信息");
		}
		return service.get(id);
	}

	public Dept getProcessFallbackMethod(Throwable t, @PathVariable("id") Long id) {
		System.out.println("服务降级" + t.getMessage());
		Dept dept = new Dept();
		dept.setDeptno(id);
		dept.setDname("该ID：" + id + "在数据库中无法找到对应信息，null--@CircuitBreaker");
		dept.setDb_source("No this database in MySQL");
		return dept;
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		return service.list();
	}

	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery()
	{
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}

}
