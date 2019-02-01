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
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

	//更新とキャンセルどちらが押されたかで分岐
		String confirm = request.getParameter("confirm");
		switch(confirm) {
	//更新が押された場合
		case "permit":

		//入力された値を取得
			String newLoginId = request.getParameter("loginId");
			String newUserName = request.getParameter("userName");
			String newHomeAddress = request.getParameter("homeAddress");
			String newAddress = request.getParameter("address");

		//入力された値が使用可能かどうか確認
			UserDao userDao = new UserDao();
			UserBeans user = new UserBeans();
			user = (UserBeans) session.getAttribute("userInfo");
			boolean checkId = userDao.userCheckId(user.getLoginId(), newLoginId);
			boolean checkAddress = userDao.userCheckAddress(user.getAddress(), newAddress);

		//ログインIDが使用済みだった場合、エラーメッセージをセットしてユーザ情報更新画面に遷移
			if(checkId) {
				request.setAttribute("sysMsg", "このログインIDは既に使用されています");

			//入力内容を引き継ぐため、セッションスコープにセット
				UserBeans userUpdate = new UserBeans();
				userUpdate.setLoginId(newLoginId);
				userUpdate.setUserName(newUserName);
				userUpdate.setHomeAddress(newHomeAddress);
				userUpdate.setAddress(newAddress);
				request.setAttribute("userUpdate", userUpdate);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}

		//メールアドレスが使用済みだった場合、エラーメッセージをセットしてユーザ情報更新画面に遷移
			if(checkAddress) {
				request.setAttribute("sysMsg", "このメールアドレスは既に使用されています");
			//入力内容を引き継ぐため、リクエストスコープにセット
				UserBeans userUpdate = new UserBeans();
				userUpdate.setLoginId(newLoginId);
				userUpdate.setUserName(newUserName);
				userUpdate.setHomeAddress(newHomeAddress);
				userUpdate.setAddress(newAddress);
				request.setAttribute("userUpdate", userUpdate);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}

		//入力された内容が問題ない場合、情報を更新
			if(userDao.userUpdate(user.getUserId(), newLoginId, newUserName, newHomeAddress, newAddress)) {
				System.out.println("更新成功");
			//ログインセッションを上書き
				session.setAttribute("userInfo", userDao.findUser(newLoginId, user.getPassword()));
			//マイページにリダイレクト
				response.sendRedirect("MyPage");
			}
			break;

	//キャンセルが押された場合
		case "cancel":
			response.sendRedirect("MyPage");
			break;
		}
	}

}
