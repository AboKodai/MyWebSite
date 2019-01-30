package beans;

public class UserBeans {

	private int userId;
	private String loginId;
	private String userName;
	private String birthDate;
	private String password;
	private String homeAddress;
	private String address;

	//ログイン処理
	public UserBeans(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	//新規登録時の入力内容引継ぎ
	public UserBeans(String loginId, String userName, String birthDate, String homeAddress, String address) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.birthDate = birthDate;
		this.homeAddress = homeAddress;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
