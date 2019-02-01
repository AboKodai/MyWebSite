package controller;

import javax.servlet.http.HttpSession;

public class Helper {

	//セッションスコープの値を返し、同時に削除も行う。
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object o = session.getAttribute(str);
		session.removeAttribute(str);
		return o;
	}
}
