package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBeans;
import dao.UserDao;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**フォームに入力された値を取得し、userテーブルに追加。
		 * 追加に成功したらマイページへ
		 */
		request.setCharacterEncoding("UTF-8");
		//入力されたデータの取得
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String birthDate = request.getParameter("birthDate");
		String password = request.getParameter("password");
		String passwordCon = request.getParameter("passwordCon");
		String homeAddress = request.getParameter("homeAddress");
		String address = request.getParameter("address");

		//パスワードとパスワード確認の入力内容が異なっていた場合はエラーメッセージをセットして新規登録画面へ
		if (!(password.equals(passwordCon))) {
			request.setAttribute("sysMsg", "パスワードの入力内容が異なっています");

			//入力内容を引き継ぐため、リクエストスコープにセット
			UserBeans user = new UserBeans(loginId, userName, birthDate, homeAddress, address);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//ユーザIDとメールアドレスが使用済みかどうか確認する
		UserDao userDao = new UserDao();
		boolean checkId = userDao.userCheckId(loginId);
		boolean checkAddress = userDao.userCheckAddress(address);

		//ユーザIDが使用済みだった場合、エラーメッセージをセットして新規登録画面に遷移
		if(checkId) {
			request.setAttribute("sysMsg", "このユーザIDは既に使用されています");

			//入力内容を引き継ぐため、リクエストスコープにセット
			UserBeans user = new UserBeans(loginId, userName, birthDate, homeAddress, address);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//メールアドレスが使用済みだった場合、エラーメッセージをセットして新規登録画面に遷移
		if(checkAddress) {
			request.setAttribute("sysMsg", "このメールアドレスは既に使用されています");

			//入力内容を引き継ぐため、リクエストスコープにセット
			UserBeans user = new UserBeans(loginId, userName, birthDate, homeAddress, address);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;
		}


		//入力された内容が問題ない場合、入力内容をセッションスコープにセット

		//新規登録に成功した場合マイページにリダイレクト
		response.sendRedirect("MyPage");

	}

}
