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

import beans.DeliveryMethodBeans;
import beans.ItemBeans;
import beans.ItemTypeBeans;
import dao.DeliveryMethodDao;
import dao.ItemDao;
import dao.ItemTypeDao;
import dao.ItemTypeTableDao;

/**
 * Servlet implementation class MyItemDetail
 */
@WebServlet("/MyItemDetail")
public class MyItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ItemDao itemDao = new ItemDao();

		try {
			//ログインセッションがない場合ログイン画面にリダイレクト
			if(session.getAttribute("userInfo")==null) {
				response.sendRedirect("Login");
			}

			//リクエストコープからitemIdを取得
			int itemId =Integer.parseInt(request.getParameter("itemId"));
			//商品情報を取得
			ItemBeans item = itemDao.itemDetail(itemId);
			//商品のdeliveryMethod情報を取得
			DeliveryMethodDao  dmDao = new DeliveryMethodDao();
			DeliveryMethodBeans dmb = dmDao.getDeliveryMethod(item.getDelivaryMethod());
			//商品のitemType情報を取得
			ItemTypeTableDao ittDao = new ItemTypeTableDao();
			ArrayList<Integer> itemTypeList = new ArrayList<Integer>();
			itemTypeList = ittDao.getItemTypeIdByItemId(itemId);
			//itemTypeListを取得
			ItemTypeDao itDao = new ItemTypeDao();
			ArrayList<ItemTypeBeans> typeList = new ArrayList<ItemTypeBeans>();
			typeList = itDao.findItemType();

			//リクエストスコープにセット
			request.setAttribute("item", item);
			request.setAttribute("dmb", dmb);
			request.setAttribute("itemTypeList", itemTypeList);
			request.setAttribute("typeList", typeList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myItemDetail.jsp");
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
