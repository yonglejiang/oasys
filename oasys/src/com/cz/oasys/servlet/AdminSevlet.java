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
		// �������ģʽ,�����adminDao
		AdminDao adminDao = DaoFactory.getAdminDao(dp);
		// ��ѯ���ݿ�,�����û������account��ü�¼
		// Ȼ��ȶ��û���������뼰���ݿ������е�����
		// ���û����͵�request�����ж���������
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// 1,�ж���֤��
		// 2,�ж��û��������Ƿ�Ϊ��
		if (account == null || password == null) {
			// ����û���������Ϊ��
			request.setAttribute("errmsg", "�û������벻��Ϊ��");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else{
			// 1,�����˺Ŵ����ݿ��ѯ��¼
						Admin queryAdmin = adminDao.getOneByAccount(account);
						// System.out.println(queryAdmin);
						// �жϲ������queryAdmin�Ƿ�Ϊ��
						if (queryAdmin.getPassword() == null || "".equals(queryAdmin.getPassword())) {
							request.setAttribute("errmsg", "���˺Ų�����");
							request.getRequestDispatcher("error.jsp").forward(request, response);
						} else {
							// ���û������������м���,Ȼ�������ݿ��е�������бȽ�
							String encryptPass = EncryptUtil.encryptMd5(password);
							if (encryptPass.equals(queryAdmin.getPassword())) {//����ƥ��ɹ�
								// 1,����¼�ɹ�����û����� session
								request.getSession().setAttribute("admin", queryAdmin);
								response.sendRedirect("success.jsp");
							} else {
								request.setAttribute("errmsg", "�������");
								request.getRequestDispatcher("error.jsp").forward(request, response);
							}
						}
		}

	}

}
