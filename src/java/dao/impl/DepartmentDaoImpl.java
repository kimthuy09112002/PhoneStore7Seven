/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.DepartmentDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.connectDB;
import model.Department;

/**
 *
 * @author Admin
 */
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList();
        String sql = "SELECT * FROM department";
        Connection conn = connectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setDescribe(rs.getString("describe"));
                departments.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }
    
}
