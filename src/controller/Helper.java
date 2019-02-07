package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import beans.ItemBeans;

public class Helper {

	//セッションスコープの値を返し、同時に削除も行う。
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object o = session.getAttribute(str);
		session.removeAttribute(str);
		return o;
	}

	//ログインセッションがnullだった場合はfalseを入れる
	public static void makeLoginSession(HttpSession session, String str) {
		Object o = session.getAttribute(str);
		if(o==null) {
			session.setAttribute("isLogin", false);
		}
	}

	//カートの合計金額の算出
	public static int getTotalItemPrice(ArrayList<ItemBeans> cart) {
		int total = 0;
		for(ItemBeans item : cart) {
			total += item.getItemPrice();
		}
		return total;
	}

	//送料がかかるかどうか判別
	public static boolean confirmDeliveryMethod(ArrayList<ItemBeans> cart){
		boolean flag = false;
		for(ItemBeans item : cart) {
			if(item.getDelivaryMethod()==2) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
