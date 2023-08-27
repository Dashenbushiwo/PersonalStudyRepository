package com.ps.cloud.service.impl;

import com.ps.cloud.dao.DeptDao;
import com.ps.cloud.service.DeptService;
import com.ps.cloud.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService
{
	private final DeptDao dao;

	public DeptServiceImpl(DeptDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Dept dept)
	{
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id)
	{
		return dao.findById(id);
	}

	@Override
	public List<Dept> list()
	{
		return dao.findAll();
	}

}
