package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DeliveryMethodBeans;
import beans.ItemBeans;
import dao.DeliveryMethodDao;
import dao.ItemDao;

/**
 * Servlet implementation class ItemDetail
 */
@WebServlet("/ItemDetail")
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao itemDao = new ItemDao();
		ItemBeans item = new ItemBeans();
		DeliveryMethodBeans dmb = new DeliveryMethodBeans();
		DeliveryMethodDao dmDao = new DeliveryMethodDao();
		item = itemDao.itemDetail( Integer.parseInt(request.getParameter("item_id")));
		request.setAttribute("item", item);
		dmb = dmDao.getDeliveryMethod(item.getDelivaryMethod());
		request.setAttribute("dmb", dmb);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemDetail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao itemDao = new ItemDao();

	//商品IDを取得
		int itemId = Integer.parseInt(request.getParameter("itemId"));
	//IDをもとに商品情報を取得
		ItemBeans item = itemDao.itemDetail(itemId);
	//購入数情報を取得
		int sellNumber = Integer.parseInt(request.getParameter("sellNumber"));
	//商品に購入数情報を追加
		item.setSellNumber(sellNumber);
	//リクエストスコープに商品をセット
		request.setAttribute("item", item);
		String selected = request.getParameter("select");
		switch(selected) {
	//カートに入れるが押された場合
		case "addItem":
			RequestDispatcher dispatcher = request.getRequestDispatcher("AddItem");
			dispatcher.forward(request, response);
			break;
	//購入手続きが押された場合
		case "buy":
			break;




		}


	}

}
