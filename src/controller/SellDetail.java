package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SellListBeans;
import dao.BuyDao;

/**
 * Servlet implementation class SellDetail
 */
@WebServlet("/SellDetail")
public class SellDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BuyDao buyDao = new BuyDao();

		if(session.getAttribute("userInfo") == null) {
			//ログインセッションがない場合ログイン画面にリダイレクト
			response.sendRedirect("Login");
			return;
		}
		//リクエストスコープから値を取得
		int buyDetailId = Integer.parseInt(request.getParameter("buyDetailId"));

		SellListBeans sell = buyDao.getSellBeans(buyDetailId);
		request.setAttribute("sell", sell);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sellDetail.jsp");
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
