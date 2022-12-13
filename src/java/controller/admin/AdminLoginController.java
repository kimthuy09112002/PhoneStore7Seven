package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminLoginDao;
import model.Admin;
import model.Department;
import service.AdminService;
import service.DepartmentService;
import service.impl.AdminServicesImpl;
import service.impl.DepartmentServiceImpl;

@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    AdminService adminService = new AdminServicesImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();

    public AdminLoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/admin/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("admin-username");
        String password = request.getParameter("admin-password");
        Admin admin = new Admin();
        admin.setName(request.getParameter("name"));

        if (AdminLoginDao.checkAdminLogin(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin-username", username);
            session.setAttribute("admin-password", password);
            session.setAttribute("department_id", adminService.get(username).getDepartment_id());
            session.setAttribute("department", departmentService.getAll());
            response.sendRedirect(request.getContextPath() + "/admin/homepage");
        } else {
            request.setAttribute("errorMessage", "Tài khoản đăng nhập hoặc mật khẩu sai !!!");
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/login.jsp");
            rd.forward(request, response);
        }

    }

}
