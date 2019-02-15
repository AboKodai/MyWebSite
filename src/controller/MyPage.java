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
import beans.ItemBeans;
import beans.SellListBeans;
import beans.UserBeans;
import dao.BuyDao;
import dao.ItemDao;

/**
 * Servlet implementation class MyPage
 */
@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BuyDao buyDao = new BuyDao();
		ItemDao itemDao = new ItemDao();
		if(session.getAttribute("userInfo") == null) {
			//ログインセッションがない場合ログイン画面にリダイレクト
			response.sendRedirect("Login");
			return;
		}
		UserBeans user = (UserBeans) session.getAttribute("userInfo");
		int userId = user.getUserId();
		//購入履歴表示のため
		BuyBeans buy = buyDao.getBuyBeansByUserId(userId);
		request.setAttribute("buy", buy);

		//受注一覧表示のため
		ArrayList<SellListBeans> sellList = new ArrayList<SellListBeans>();
		sellList = buyDao.getSellList(userId);
		request.setAttribute("sellList", sellList);


		//出品商品一覧のため
		ArrayList<ItemBeans> itemList = new ArrayList<ItemBeans>();
		itemList = itemDao.getItemByuserId(userId);
		request.setAttribute("myItemList", itemList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
