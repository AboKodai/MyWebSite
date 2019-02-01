package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyBeans {

	private int buyId;
	private int userId;
	private int totalPrice;
	private Date buyDate;
	private int checkboxInfo;


	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public int getCheckboxInfo() {
		return checkboxInfo;
	}
	public void setCheckboxInfo(int checkboxInfo) {
		this.checkboxInfo = checkboxInfo;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}

}
