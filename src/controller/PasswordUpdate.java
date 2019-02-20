package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBeans;
import dao.UserDao;

/**
 * Servlet implementation class PasswordUpdate
 */
@WebServlet("/PasswordUpdate")
public class PasswordUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//ログインセッションがない場合、ログイン画面へリダイレクト
		if (session.getAttribute("userInfo") == null) {
			response.sendRedirect("Login");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/passwordUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			//現在のパスワード及び入力内容を取得
			UserDao userDao = new UserDao();
			UserBeans userInfo = new UserBeans();
			userInfo = (UserBeans) session.getAttribute("userInfo");
			String password = userInfo.getPassword();
			String inputPassword = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			String newPasswordCon = request.getParameter("newPasswordCon");
			String result = userDao.getMD5Password(inputPassword);
			String resultNewPassword = userDao.getMD5Password(newPassword);


			//エラーが発生する条件を複数設定
			if (!password.equals(result) || !newPassword.equals(newPasswordCon)) {
				request.setAttribute("sysMsg", "パスワードが間違っています");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/passwordUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//更新可能だった場合更新
			if (userDao.passwordUpdate(userInfo.getUserId(), resultNewPassword)) {
				System.out.println("更新成功");

				//ログインセッションを更新
				UserBeans user = userDao.findUser(userInfo.getLoginId(), resultNewPassword);
				session.setAttribute("userInfo", user);
				//マイページへリダイレクト
				response.sendRedirect("MyPage");
			}
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			response.sendRedirect("Error");
		}

	}

}
