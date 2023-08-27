package com.ps.cloud.service;

import com.ps.cloud.entity.Dept;

import java.util.List;

public interface DeptService
{
	boolean add(Dept dept);

	Dept get(Long id);

	List<Dept> list();
}
