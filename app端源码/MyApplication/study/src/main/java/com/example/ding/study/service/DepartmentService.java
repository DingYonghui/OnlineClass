package com.example.ding.study.service;


import com.example.ding.study.entity.Department;

import java.util.List;

/**
 * Created by ding on 2015/10/21.
 */
public interface DepartmentService {

    //得到院系
    public List<Department> getDepartment(String url) throws Exception ;
}
