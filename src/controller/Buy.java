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

import beans.ItemBeans;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>) session.getAttribute("cart");
		Helper.makeLoginSession(session,"isLogin");

		if(!(boolean)session.getAttribute("isLogin")) {
			//ログインセッションがない場合ログイン画面にリダイレクト
			response.sendRedirect("Login");
			return;
		}else if(cart.size() == 0 ) {
			//カートに商品がない場合カートにフォワード
			request.setAttribute("sysMsg", "購入する商品がありません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
			dispatcher.forward(request, response);
			return;

		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buy.jsp");
			dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buy.jsp");
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
