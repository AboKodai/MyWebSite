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
import dao.BuyDao;
import dao.BuyDetailDao;

/**
 * Servlet implementation class BuyDetail
 */
@WebServlet("/BuyDetail")
public class BuyDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BuyDao buyDao = new BuyDao();
		ArrayList<ItemBeans> itemList = new ArrayList<ItemBeans>();
		BuyDetailDao bdDao = new BuyDetailDao();

		try {

			//ログインセッションがない場合はログイン画面
			if(session.getAttribute("userInfo") == null) {
				response.sendRedirect("Login");
			}

			//リクエストスコープからbuyIdを取得
			int buyId = Integer.parseInt(request.getParameter("buyId"));

			//購入商品情報
			BuyBeans resultBuy = buyDao.getBuyBeansByBuyId(buyId);

			//購入詳細情報
			itemList = bdDao.getItemListForBuyDetail(buyId);

			request.setAttribute("resultBuy", resultBuy);
			request.setAttribute("itemList", itemList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyDetail.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			response.sendRedirect("Error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
