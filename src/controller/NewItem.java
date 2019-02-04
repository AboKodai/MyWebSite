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
import beans.ItemTypeBeans;
import beans.UserBeans;
import dao.DeliveryMethodDao;
import dao.ItemDao;
import dao.ItemTypeDao;
import dao.ItemTypeTableDao;

/**
 * Servlet implementation class NewItem
 */
@WebServlet("/NewItem")
public class NewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//商品の種類を取得
		ItemTypeDao itDao = new ItemTypeDao();
		ArrayList<ItemTypeBeans> itbList = new ArrayList<ItemTypeBeans>();
		itbList = itDao.findItemType();
	//送料を取得
		DeliveryMethodDao dmDao = new DeliveryMethodDao();
		ArrayList<DeliveryMethodBeans> dmbList = new ArrayList<DeliveryMethodBeans>();
		dmbList = dmDao.findDeliveryMethod();
	//リクエストスコープにセット
		request.setAttribute("itbList", itbList);
		request.setAttribute("dmbList", dmbList);
	//商品登録画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newItem.jsp");
		dispatcher.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ItemDao itemDao = new ItemDao();
		ItemTypeTableDao ittDao = new ItemTypeTableDao();


	//入力された内容を取得
		String itemName = request.getParameter("itemName");
		String itemDetail = request.getParameter("itemDetail");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		int  itemNumber= Integer.parseInt(request.getParameter("itemNumber"));
		String[]itemTypeList = request.getParameterValues("type");
		int deliveryMethodId = Integer.parseInt(request.getParameter("deliveryMethod"));
		String fileName = request.getParameter("fileName");

	//セッションスコープからユーザIDを取得
		UserBeans ub = new UserBeans();
		ub = (UserBeans)session.getAttribute("userInfo");
		int userId = ub.getUserId();

	//itemテーブルに商品を登録と同時にitemIdの取得
		int itemId = itemDao.newItem(userId, itemName, itemDetail, itemPrice, itemNumber, deliveryMethodId, fileName);

	//itemIDとitemTypeListをもとにitemTypeTableに登録
		ittDao.entryItem(itemId,itemTypeList );
		System.out.println("種類登録成功");

	//マイページへリダイレクト
		response.sendRedirect("MyPage");
	}

}
