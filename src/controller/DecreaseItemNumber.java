package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyBeans;
import beans.ItemBeans;
import dao.ItemDao;

/**
 * Servlet implementation class DecreaseItemNumber
 */
@WebServlet("/DecreaseItemNumber")
public class DecreaseItemNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecreaseItemNumber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao itemDao = new ItemDao();

		//リクエストスコープ情報の取得
		BuyBeans resultBuy = (BuyBeans) request.getAttribute("resultBuy");
		ArrayList<ItemBeans> resultBuyDetail = (ArrayList<ItemBeans>) request.getAttribute("resultBuyDetail");

		//商品数量の更新
		for(ItemBeans item : resultBuyDetail) {
			//商品の現在数量を取得
			ItemBeans itemTable = new ItemBeans();
			itemTable = itemDao.itemDetail(item.getItemId());
			int ItemNumber = itemTable.getItemNumber();
			//購入された商品数量を取得
			int decreaseItemNumber = item.getSellNumber();

			int newItemNumber = ItemNumber - decreaseItemNumber;

			//商品数量を更新
			itemDao.decreaseItemNumber(newItemNumber, item.getItemId());

				}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyResult.jsp");
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
