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
import dao.BuyDetailDao;

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

		try {
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			BuyDetailDao bdDao = new  BuyDetailDao();
			int buyDetailId = Integer.parseInt(request.getParameter("buyDetailId"));
			if (request.getParameter("checkbox") == null) {
				bdDao.checkboxUpdate(buyDetailId, 0);
			}else if(Integer.parseInt(request.getParameter("checkbox")) == 1) {
				bdDao.checkboxUpdate(buyDetailId, 1);
			}
			response.sendRedirect("SellDetail?buyDetailId="+buyDetailId);
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			response.sendRedirect("Error");
		}
	}

}
