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
 * Servlet implementation class ItemDeleteFromCart
 */
@WebServlet("/ItemDeleteFromCart")
public class ItemDeleteFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDeleteFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//削除する商品IDを取得
		String[] deleteItemList = request.getParameterValues("itemDelete");
		//カートを取得
		ArrayList<ItemBeans> cart = (ArrayList<ItemBeans>)session.getAttribute("cart");
		String sysMsg = "";
		if(deleteItemList != null) {
			
		//対象商品の削除
			for(String deleteItemId: deleteItemList) {
				for(ItemBeans cartItem: cart) {
					if(cartItem.getItemId() == Integer.parseInt(deleteItemId)) {
						cart.remove(cartItem);
						break;
					}
				}
			}
			sysMsg = "削除しました";
		}else {
			sysMsg = "削除する商品が選択されていません";
		}
		request.setAttribute("sysMsg", sysMsg);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
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
