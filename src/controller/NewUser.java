package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		HttpSession session = request.getSession();
		//ログインセッションがある場合はマイページにリダイレクト
		if(session.getAttribute("userInfo") != null) {
			response.sendRedirect("MyPage");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**フォームに入力された値を取得し登録可能か確認
		 * OKなら登録確認画面へ
		 */
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {

			//入力されたデータの取得
			String loginId = request.getParameter("loginId");
			String userName = request.getParameter("userName");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = null;
			try {
				birthDate = sdf.parse(request.getParameter("birthDate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String password = request.getParameter("password");
			String passwordCon = request.getParameter("passwordCon");
			String homeAddress = request.getParameter("homeAddress");
			String address = request.getParameter("address");

			//パスワードとパスワード確認の入力内容確認
			if (!(password.equals(passwordCon))) {
				request.setAttribute("sysMsg", "パスワードの入力内容が異なっています");

				//入力内容を引き継ぐため、リクエストスコープにセット
				UserBeans user = new UserBeans(loginId, userName, birthDate, password, homeAddress, address);
				request.setAttribute("user", user);
				request.setAttribute("birthDate",(String)request.getParameter("birthDate"));

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//ログインIDとメールアドレスが使用済みかどうか確認
			UserDao userDao = new UserDao();
			boolean checkId = userDao.userCheckId("",loginId);
			boolean checkAddress = userDao.userCheckAddress("",address);

			//ログインIDが使用済みだった場合、エラーメッセージをセットして新規登録画面に遷移
			if(checkId) {
				request.setAttribute("sysMsg", "このログインIDは既に使用されています");

				//入力内容を引き継ぐため、リクエストスコープにセット
				UserBeans user = new UserBeans(loginId, userName, birthDate, password, homeAddress, address);
				request.setAttribute("user", user);
				request.setAttribute("birthDate", (String)request.getParameter("birthDate"));

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//メールアドレスが使用済みだった場合、エラーメッセージをセットして新規登録画面に遷移
			if(checkAddress) {
				request.setAttribute("sysMsg", "このメールアドレスは既に使用されています");

				//入力内容を引き継ぐため、リクエストスコープにセット
				UserBeans user = new UserBeans(loginId, userName, birthDate, password, homeAddress, address);
				request.setAttribute("user", user);
				request.setAttribute("birthDate", (String)request.getParameter("birthDate"));

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//入力された内容が問題ない場合、入力内容をセッションスコープにセット
			UserBeans user = new UserBeans(loginId, userName, birthDate, password, homeAddress, address);
			session.setAttribute("user", user);
			session.setAttribute("birthDate", (String)request.getParameter("birthDate"));
			//登録確認画面にリダイレクト
			response.sendRedirect("NewUserCon");
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			response.sendRedirect("Error");
		}

	}

}
