package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ItemBeans;
import beans.ItemTypeBeans;
import dao.ItemDao;
import dao.ItemTypeDao;

/**
 * Servlet implementation class Top
 */
@WebServlet("/Top")
public class Top extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Top() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//itemType一覧読み取り
		ItemTypeDao itDao = new ItemTypeDao();
		ArrayList<ItemTypeBeans> itbList = new ArrayList<ItemTypeBeans>();
		itbList = itDao.findItemType();
	//全商品情報の取得
		ItemDao itemDao = new ItemDao();
		ArrayList<ItemBeans> ibList = itemDao.getRandomItem();
		request.setAttribute("randomList", ibList);
		request.setAttribute("typeList", itbList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
