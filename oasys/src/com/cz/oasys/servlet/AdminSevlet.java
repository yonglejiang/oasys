package com.cz.oasys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ca.oasys.model.Admin;
import com.cz.oasys.dao.AdminDao;
import com.cz.oasys.factory.DaoFactory;
import com.cz.oasys.jdbc.DbHelp;
import com.cz.oasys.util.EncryptUtil;

@WebServlet("/AdminSevlet")
public class AdminSevlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		String option = req.getParameter("option");
		switch (option) {
		case "login":
			login(req, resp);
			break;
		case "logout":
			break;

		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DbHelp dp = new DbHelp();
		// 工厂设计模式,来获得adminDao
		AdminDao adminDao = DaoFactory.getAdminDao(dp);
		// 查询数据库,根据用户传入的account获得记录
		// 然后比对用户传入的密码及数据库中已有的密码
		// 从用户发送的request请求中读出来参数
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// 1,判断验证码
		// 2,判断用户名密码是否为空
		if (account == null || password == null) {
			// 如果用户名或密码为空
			request.setAttribute("errmsg", "用户名密码不能为空");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else{
			// 1,根据账号从数据库查询记录
						Admin queryAdmin = adminDao.getOneByAccount(account);
						// System.out.println(queryAdmin);
						// 判断查出来的queryAdmin是否为空
						if (queryAdmin.getPassword() == null || "".equals(queryAdmin.getPassword())) {
							request.setAttribute("errmsg", "该账号不存在");
							request.getRequestDispatcher("error.jsp").forward(request, response);
						} else {
							// 对用户传入的密码进行加密,然后与数据库中的密码进行比较
							String encryptPass = EncryptUtil.encryptMd5(password);
							if (encryptPass.equals(queryAdmin.getPassword())) {//密码匹配成功
								// 1,将登录成功后的用户存入 session
								request.getSession().setAttribute("admin", queryAdmin);
								response.sendRedirect("success.jsp");
							} else {
								request.setAttribute("errmsg", "密码错误");
								request.getRequestDispatcher("error.jsp").forward(request, response);
							}
						}
		}

	}

}
