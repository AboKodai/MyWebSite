package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SellListBeans {

	private int buyUserId;
	private Date buyDate;
	private int checkbox;
	private int itemNumber;
	private int sellUserId;
	private String itemName;
	private String buyUserHomeAddress;
	private String buyUserName;
	private String buyUserAddress;
	private int itemPrice;
	private int buyDetailId;

	public int getBuyUserId() {
		return buyUserId;
	}
	public void setBuyUserId(int buyUserId) {
		this.buyUserId = buyUserId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public int getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(int checkbox) {
		this.checkbox = checkbox;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getSellUserId() {
		return sellUserId;
	}
	public void setSellUserId(int sellUserId) {
		this.sellUserId = sellUserId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBuyUserHomeAddress() {
		return buyUserHomeAddress;
	}
	public void setBuyUserHomeAddress(String buyUserHomeAddress) {
		this.buyUserHomeAddress = buyUserHomeAddress;
	}
	public String getBuyUserName() {
		return buyUserName;
	}
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getBuyDetailId() {
		return buyDetailId;
	}
	public void setBuyDetailId(int buyDetailId) {
		this.buyDetailId = buyDetailId;
	}
	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}
	public String getBuyUserAddress() {
		return buyUserAddress;
	}
	public void setBuyUserAddress(String buyUserAddress) {
		this.buyUserAddress = buyUserAddress;
	}


}
