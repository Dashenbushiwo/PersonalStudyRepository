package com.ps.cloud.dao;

import com.ps.cloud.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDao
{
	boolean addDept(Dept dept);

	Dept findById(Long id);

	List<Dept> findAll();
}
