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
import beans.ItemTypeBeans;
import dao.ItemDao;
import dao.ItemTypeDao;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/SearchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResult() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ArrayList<ItemBeans> ibList = new ArrayList<ItemBeans>();
		ItemDao itemDao = new ItemDao();

		try {
			//itemType一覧をセット
			ItemTypeDao itDao = new ItemTypeDao();
			ArrayList<ItemTypeBeans> itbList = new ArrayList<ItemTypeBeans>();
			itbList = itDao.findItemType();
			request.setAttribute("typeList", itbList);

			//入力された値を取得
			String searchWord = request.getParameter("searchWord");
			String[] strItemTypeList =request.getParameterValues("type");
			ArrayList<Integer> itemTypeList = new ArrayList<Integer>();

			if(strItemTypeList != null) {
			//String型配列をint型に変換
				for(int i = 0 ; i < strItemTypeList.length;i++) {
					itemTypeList.add(Integer.parseInt(strItemTypeList[i]));
				}
			}else {
				itemTypeList = null;
			}

			//検索されたキーワード及び種類をセッションスコープに格納
			session.setAttribute("searchWord", searchWord);
			session.setAttribute("itemTypeList", itemTypeList);

			//検索結果をページ表示分のみ取得
			ibList = itemDao.findItem(searchWord, itemTypeList);

			//表示ページの配列をセット
			request.setAttribute("itemList", ibList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/searchResult.jsp");
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

	}

}
