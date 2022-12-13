/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.DepartmentDao;
import dao.impl.DepartmentDaoImpl;
import java.util.List;
import model.Department;
import service.DepartmentService;

/**
 *
 * @author Admin
 */
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentDao departmentDao = new DepartmentDaoImpl();
    
    @Override
    public List<Department> getAll() {
        return departmentDao.getAll();
    }
    
}
