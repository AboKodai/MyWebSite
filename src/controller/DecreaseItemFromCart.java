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
import dao.ItemDao;

/**
 * Servlet implementation class DecreaseItemFromCart
 */
@WebServlet("/DecreaseItemFromCart")
public class DecreaseItemFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecreaseItemFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<ItemBeans> cart = new ArrayList<ItemBeans>();
		ArrayList<ItemBeans> newCart = new ArrayList<ItemBeans>();
		ArrayList<ItemBeans> decreaseList = new ArrayList<ItemBeans>();
		ArrayList<String> itemNameList = new ArrayList<String>();
		cart = (ArrayList<ItemBeans>) session.getAttribute("cart");
		decreaseList = (ArrayList<ItemBeans>) Helper.cutSessionAttribute(session, "decreaseList");
		ItemDao itemDao = new ItemDao();
		try {


			//カート内の購入数情報を更新
			for(ItemBeans decreaseItem : decreaseList) {
				for(ItemBeans cartItem : cart) {
					if(decreaseItem.getItemId() == cartItem.getItemId()) {
						int sellNumber = itemDao.getItemNumberByItemId(cartItem.getItemId());
						if(sellNumber <= 0) {
							cart.remove(cartItem);
							itemNameList.add(cartItem.getItemName());
							break;
						}else {
							cartItem.setSellNumber(sellNumber);
							itemNameList.add(cartItem.getItemName());
							break;
						}
					}

				}
			}

			session.setAttribute("cart", cart);
			request.setAttribute("itemNameList", itemNameList);
			request.setAttribute("sysMsg", "購入処理失敗<br>購入数が在庫を上回っています。以下の商品の購入数を変更もしくは削除しました。");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
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
