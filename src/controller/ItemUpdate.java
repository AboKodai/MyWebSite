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
 * Servlet implementation class ItemUpdate
 */
@WebServlet("/ItemUpdate")
public class ItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			if(session.getAttribute("userInfo") == null) {
				//ログインセッションがない場合ログイン画面にリダイレクト
				response.sendRedirect("Login");
				return;
			}

			//itemIDの取得
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			//formの初期値を取得
			ItemDao itemDao = new ItemDao();
			ItemBeans item = itemDao.itemDetail(itemId);

			DeliveryMethodDao dmDao = new DeliveryMethodDao();
			ArrayList<DeliveryMethodBeans> dmbList = new ArrayList<DeliveryMethodBeans>();
			dmbList = dmDao.findDeliveryMethod();

			ItemTypeTableDao ittDao = new ItemTypeTableDao();
			ArrayList<Integer> itiList = new ArrayList<Integer>();
			itiList = ittDao.getItemTypeIdByItemId(itemId);

			//itemTypeListの取得
			ItemTypeDao itDao = new ItemTypeDao();
			ArrayList<ItemTypeBeans> itbList = new ArrayList<ItemTypeBeans>();
			itbList = itDao.findItemType();

			//リクエストスコープにセット
			request.setAttribute("item", item);
			request.setAttribute("itbList", itbList);
			request.setAttribute("dmbList", dmbList);
			request.setAttribute("itiList", itiList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemUpdate.jsp");
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
		ItemDao itemDao = new ItemDao();
		ItemTypeTableDao ittDao = new ItemTypeTableDao();


		try {
			//入力された内容を取得
			String itemName = request.getParameter("itemName");
			String itemDetail = request.getParameter("itemDetail");
			int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
			int  itemNumber= Integer.parseInt(request.getParameter("itemNumber"));
			String[]itemTypeList = request.getParameterValues("type");
			int deliveryMethodId = Integer.parseInt(request.getParameter("deliveryMethod"));
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			//itemTypeTableを更新
			ittDao.itemTypeDeleteByItemId(itemId);
			ittDao.entryItem(itemId, itemTypeList);

			//itemTableを更新
			itemDao.itemUpdate(itemName, itemDetail, itemPrice, itemNumber, deliveryMethodId, itemId);

			System.out.println("更新成功");


			//マイページへリダイレクト
			response.sendRedirect("MyPage");
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", e.toString());
			response.sendRedirect("Error");
		}

	}

}
