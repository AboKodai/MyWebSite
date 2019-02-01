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
 * Servlet implementation class UserDelete
 */
@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン情報がない場合はログイン画面にリダイレクト
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect("Login");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

	//分岐準備
		String confirm = request.getParameter("confirm");
		switch(confirm) {
	//キャンセルが押された場合
		case "cancel":
		//マイページにリダイレクト
			response.sendRedirect("MyPage");
			break;
	//退会するが押された場合
		case "permit":
		//ユーザに紐づく情報を削除
			UserDao userDao = new UserDao();
			UserBeans user = new UserBeans();
			user = (UserBeans) session.getAttribute("userInfo");
			if(userDao.userDelete(user.getUserId())) {
				System.out.println("削除成功");
			}

		//ログアウト処理へ
			response.sendRedirect("Logout");

		}
	}

}
