package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyBeans;
import beans.UserBeans;
import dao.BuyDao;

/**
 * Servlet implementation class BuyList
 */
@WebServlet("/BuyList")
public class BuyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BuyDao buyDao = new BuyDao();
		ArrayList<BuyBeans> buyList = new ArrayList<BuyBeans>();
		
		//ログインセッションがない場合はログイン画面
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect("Login");
		}
		UserBeans user = (UserBeans)session.getAttribute("userInfo");
		
		//購入一覧を取得
		buyList = buyDao.getBuyBeansListByUserId(user.getUserId());
		request.setAttribute("buyList", buyList);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
