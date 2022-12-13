package service.impl; 
 
import java.util.List;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import model.Admin;
import service.AdminService; 
 
public class AdminServicesImpl implements AdminService { 
	AdminDao adminDao = new AdminDaoImpl(); 
 
	@Override 
	public void insert(Admin admin) { 
		adminDao.insert(admin); 
	} 
 
	@Override
	public void edit(Admin admin) {
		Admin oldAdmin = adminDao.get(admin.getId());		
		oldAdmin.setUsername(admin.getUsername());
		oldAdmin.setPassword(admin.getPassword());
		oldAdmin.setName(admin.getName());
		adminDao.edit(oldAdmin); 

	} 
 
	@Override 
	public void delete(String id) { 
		adminDao.delete(id); 
 
	} 
 
	@Override 
	public Admin get(int id) { 
		return adminDao.get(id); 
	} 
 
	@Override 
	public Admin get(String name) { 
		return adminDao.get(name); 
	} 
 
	@Override 
	public List<Admin> getAll() { 
		return adminDao.getAll(); 
	}	

    @Override
    public int getToltal(String departmentId) {
        return adminDao.getToltal(departmentId);
    }

    @Override
    public List<Admin> paging(int index, String departmentId) {
        return adminDao.paging(index,departmentId);
    }
} 
 
