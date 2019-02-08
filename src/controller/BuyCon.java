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
import beans.UserBeans;

/**
 * Servlet implementation class BuyCon
 */
@WebServlet("/BuyCon")
public class BuyCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo") == null) {
			//ログインセッションがない場合ログイン画面にリダイレクト
			response.sendRedirect("Login");
		}

		//カートの情報を取得
		ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>)session.getAttribute("cart");

		//ユーザ情報を取得
		UserBeans user = (UserBeans)session.getAttribute("userInfo");

		//合計金額算出
		int total = Helper.getTotalItemPrice(cart);

		//送料がかかるか確認
		boolean flag = Helper.confirmDeliveryMethod(cart);

		BuyBeans buy = new BuyBeans();
		buy.setUserId(user.getUserId());
		buy.setTotalPrice(total);
		buy.setCheckboxInfo(0);

		//購入情報をセッションスコープに格納
		session.setAttribute("buy", buy);

		request.setAttribute("flag", flag);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyCon.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
