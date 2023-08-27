package com.ps.cloud.service;

import com.ps.cloud.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable throwable)
	{
		log.error("服务方异常", throwable);
		return new DeptClientService() {
			@Override
			public Dept get(long id)
			{
				System.out.println("服务降级");
				Dept dept = new Dept();
				dept.setDeptno(id);
				dept.setDname("该ID：" + id + "没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
				dept.setDb_source("no this database in MySQL");
				return dept;
			}

			@Override
			public List<Dept> list()
			{
				return null;
			}

			@Override
			public boolean add(Dept dept)
			{
				return false;
			}
		};
	}
}
