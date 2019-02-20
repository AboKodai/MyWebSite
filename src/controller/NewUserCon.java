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
 * Servlet implementation class NewUserCon
 */
@WebServlet("/NewUserCon")
public class NewUserCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//新規登録画面を経ずにアクセスした場合、新規登録画面にリダイレクト
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			response.sendRedirect("NewUser");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUserCon.jsp");
		dispatcher.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			//登録が確定されたか判定
			String confirm = request.getParameter("confirm");
			switch(confirm) {
			case "cancel":
				response.sendRedirect("NewUser");
				break;

			//登録が確定された場合
			case "submit":
				UserDao userDao = new  UserDao();
				UserBeans user = new UserBeans();
				//新規登録と同時にセッションスコープのインスタンスを削除
				user = (UserBeans)Helper.cutSessionAttribute(session, "user");
				String resultPassword = userDao.getMD5Password(user.getPassword());
				user.setPassword(resultPassword);
				boolean result = userDao.newUser(user);
				session.removeAttribute("birthDate");
				if(result) {
					//ログイン処理
					session.setAttribute("userInfo", userDao.findUser(user.getLoginId(), user.getPassword()));
					System.out.println("登録成功");
				}else {
					System.out.println("登録失敗");
				}
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
