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
import beans.BuyDetailBeans;
import beans.ItemBeans;
import dao.BuyDao;
import dao.BuyDetailDao;
import dao.ItemDao;

/**
 * Servlet implementation class BuyResult
 */
@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BuyDao buyDao = new BuyDao();
		BuyDetailDao bdDao = new BuyDetailDao();
		ItemDao itemDao = new ItemDao();
		ArrayList<ItemBeans> decreaseList = new ArrayList<ItemBeans>();

		try {
			//カート情報、送料情報、購入情報の取得
			ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>) Helper.cutSessionAttribute(session, "cart");
			BuyBeans buy = (BuyBeans)Helper.cutSessionAttribute(session, "buy");

			//商品購入数とDB内の商品在庫の比較
			for(ItemBeans items : cart) {
				if(items.getSellNumber() > itemDao.getItemNumberByItemId(items.getItemId())) {
					//購入数が在庫数を上回ってしまった場合
					decreaseList.add(items);
				}
			}

			if(decreaseList.size() > 0) {
				//購入数が在庫を上回った商品があった場合
				session.setAttribute("decreaseList", decreaseList);
				session.setAttribute("cart", cart);
				response.sendRedirect("DecreaseItemFromCart");
				return;
			}

			//購入情報登録
			int buyId = buyDao.insertBuyInfo(buy);

			//カート内の商品すべてを購入詳細に登録
			for(ItemBeans cartItem : cart) {
				BuyDetailBeans bdb = new BuyDetailBeans();
				bdb.setBuyId(buyId);
				bdb.setItemId(cartItem.getItemId());
				bdb.setItemNumber(cartItem.getSellNumber());
				bdDao.insertBuyDetail(bdb);
			}

			//購入商品情報
			BuyBeans resultBuy = buyDao.getBuyBeansByBuyId(buyId);

			//購入詳細情報
			ArrayList<ItemBeans> itemList = bdDao.getItemListForBuyDetail(buyId);

			//商品数量の更新
			for(ItemBeans item : itemList) {
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


			request.setAttribute("resultBuy", resultBuy);
			request.setAttribute("resultBuyDetail", itemList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyResult.jsp");
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
