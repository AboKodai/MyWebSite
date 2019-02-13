package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemBeans;
import dao.ItemDao;

/**
 * Servlet implementation class ItemDelete
 */
@WebServlet("/ItemDelete")
public class ItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//ログインセッションがない場合はログイン画面へ
		if(session.getAttribute("userInfo") == null) {
			response.sendRedirect("Login");
		}
		//リクエストスコープからitemIdを取得
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		//itemIdをもとに商品情報を取得
		ItemDao itemDao = new ItemDao();
		ItemBeans item = itemDao.itemDetail(itemId);

		request.setAttribute("item", item);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		ItemDao itemDao = new ItemDao();
		itemDao.itemDelete(itemId);

		response.sendRedirect("MyPage");


	}

}
