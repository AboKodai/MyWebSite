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
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		//リクエストスコープから商品情報を取得
		ItemBeans item = (ItemBeans) request.getAttribute("item");
		//カートに追加されたか判別
		boolean addItem = false;

		//カート情報を取得
		ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>) session.getAttribute("cart");
		if (cart == null) {
			//カート情報がない場合カートを作成
			cart = new ArrayList<ItemBeans>();
			//カートに商品を追加
			cart.add(item);
			addItem = true;
		} else {
			//カートに商品がある場合
			for (int i = 0; i < cart.size(); i++) {
				ItemBeans cartItem = new ItemBeans();
				cartItem = cart.get(i);
				if (cartItem.getItemId() == item.getItemId()) {
					//カート内にすでに同一商品がある場合購入数を加算
					int sellNumber = cartItem.getSellNumber();
					sellNumber += item.getSellNumber();
					cartItem.setSellNumber(sellNumber);
					addItem = true;
					break;
				}
			}
		}
		if (!addItem) {
			//カートに1度も追加されていなかった場合、カートに追加
			cart.add(item);
		}

		//セッションスコープに商品情報と購入情報を更新
		session.setAttribute("cart", cart);

		request.setAttribute("sysMsg", "商品を追加しました");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
