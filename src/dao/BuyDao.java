package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import beans.BuyBeans;
import beans.SellListBeans;

public class BuyDao {

	/**購入情報登録
	 *
	 * @param buy
	 * @return
	 */
	public int insertBuyInfo(BuyBeans buy) throws SQLException{
		Connection con = null;
		int buyId = -1;

		try {
			con = DBManager.getConnection();
			String sql = "INSERT INTO buy VALUES(0,?,?,?,?)";
			PreparedStatement pStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, buy.getUserId());
			pStmt.setInt(2, buy.getTotalPrice());
			pStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pStmt.setInt(4, buy.getCheckboxInfo());
			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();
			if (rs.next()) {
				buyId = rs.getInt(1);
			}
			System.out.println("insert buyData");

			return buyId;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**buyIdからBuyBeansを取得
	 *
	 * @param buyId
	 * @return
	 */
	public BuyBeans getBuyBeansByBuyId(int buyId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM buy WHERE buy_id = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, buyId);
			BuyBeans buy = new BuyBeans();

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {

				buy.setBuyId(rs.getInt("buy_id"));
				buy.setBuyDate(rs.getTimestamp("buy_date"));
				buy.setUserId(rs.getInt("buy_user_id"));
				buy.setTotalPrice(rs.getInt("total_price"));
				buy.setCheckboxInfo(rs.getInt("checkbox_info"));
			}

			System.out.println("get buy data");

			return buy;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**ユーザIDから最新の購入情報を取得
	 * @param userId
	 * @return
	 */
	public BuyBeans getBuyBeansByUserId(int userId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM buy WHERE buy_user_id = ? ORDER BY buy_date DESC LIMIT 1";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);
			BuyBeans buy = new BuyBeans();

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {

				buy.setBuyId(rs.getInt("buy_id"));
				buy.setBuyDate(rs.getTimestamp("buy_date"));
				buy.setUserId(rs.getInt("buy_user_id"));
				buy.setTotalPrice(rs.getInt("total_price"));
				buy.setCheckboxInfo(rs.getInt("checkbox_info"));
			}else {
				return null;
			}
			System.out.println("get buy data");

			return buy;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**ユーザIDから最新の購入履歴一覧を取得
	 * @param userId
	 * @return
	 */
	public ArrayList<BuyBeans> getBuyBeansListByUserId(int userId) throws SQLException{
		Connection con = null;
		ArrayList<BuyBeans> buyList = new ArrayList<BuyBeans>();
		boolean getList = false;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM buy WHERE buy_user_id = ? ORDER BY buy_date DESC";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);


			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				BuyBeans buy = new BuyBeans();
				buy.setBuyId(rs.getInt("buy_id"));
				buy.setBuyDate(rs.getTimestamp("buy_date"));
				buy.setUserId(rs.getInt("buy_user_id"));
				buy.setTotalPrice(rs.getInt("total_price"));
				buy.setCheckboxInfo(rs.getInt("checkbox_info"));

				buyList.add(buy);
				getList = true;
			}
			if(getList) {
				System.out.println("get buy data");
				return buyList;
			}else {
				System.out.println("buy data is nothing");
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**受注一覧のための情報を取得
	 *
	 * @param userId
	 * @return
	 */
	public ArrayList<SellListBeans> getSellList(int userId) throws SQLException{
		Connection con = null;
		ArrayList<SellListBeans> sellList = new ArrayList<SellListBeans>();
		boolean isSell = false;

		try {
			con = DBManager.getConnection();

			String sql =
					"SELECT user.user_name,"
					+ " user.home_address,"
					+ " sell.*"
					+ " FROM"
					+ " user JOIN"

					+ " ( SELECT"
					+ " b.buy_user_id,"
					+ " b.buy_date,"
					+ " bd.buy_detail_id,"
					+ " bd.checkbox,"
					+ " bd.item_number,"
					+ " i.user_id,"
					+ " i.item_name,"
					+ " i.item_price"
					+ " FROM"
					+ " buy b"
					+ " JOIN buy_detail bd ON b.buy_id = bd.buy_id"
					+ " JOIN item i ON bd.item_id = i.item_id"
					+ " WHERE"
					+ " i.user_id = ?) sell"

					+ " ON user.user_id = sell.buy_user_id"
					+ " WHERE"
					+ " sell.buy_user_id != ?"
					+ " ORDER BY sell.buy_date DESC";

			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setInt(1, userId);
			pStmt.setInt(2, userId);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				SellListBeans sell = new SellListBeans();
				sell.setBuyDate(rs.getTimestamp("buy_date"));
				sell.setBuyUserId(rs.getInt("buy_user_id"));
				sell.setCheckbox(rs.getInt("checkbox"));
				sell.setItemName(rs.getString("item_name"));
				sell.setItemNumber(rs.getInt("item_number"));
				sell.setSellUserId(rs.getInt("user_id"));
				sell.setBuyUserHomeAddress(rs.getString("home_address"));
				sell.setBuyUserName(rs.getString("user_name"));
				sell.setItemPrice(rs.getInt("item_price"));
				sell.setBuyDetailId(rs.getInt("buy_detail_id"));
				sellList.add(sell);
				isSell = true;
			}

			System.out.println("get sellList");
			if (isSell) {
				return sellList;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public SellListBeans getSellBeans(int buyDetailId) throws SQLException{
		Connection con = null;
		boolean isSell = false;

		try {
			con = DBManager.getConnection();

			String sql =
					"SELECT user.user_name,"
					+ " user.home_address,"
					+ " user.address,"
					+ " sell.*"
					+ " FROM"
					+ " user JOIN"

					+ " (SELECT"
					+ " b.buy_user_id,"
					+ " b.buy_date,"
					+ " bd.buy_detail_id,"
					+ " bd.checkbox,"
					+ " bd.item_number,"
					+ " i.user_id,"
					+ " i.item_name,"
					+ " i.item_price"
					+ " FROM"
					+ " buy b"
					+ " JOIN buy_detail bd ON b.buy_id = bd.buy_id"
					+ " JOIN item i ON bd.item_id = i.item_id"
					+ " WHERE"
					+ " bd.buy_detail_id = ?"
					+ " ORDER BY"
					+ " b.buy_id DESC) sell"

					+ " ON user.user_id = sell.buy_user_id";

			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setInt(1, buyDetailId);
			ResultSet rs = pStmt.executeQuery();
			SellListBeans sell = new SellListBeans();

			while(rs.next()) {

				sell.setBuyDate(rs.getTimestamp("buy_date"));
				sell.setBuyUserId(rs.getInt("buy_user_id"));
				sell.setCheckbox(rs.getInt("checkbox"));
				sell.setItemName(rs.getString("item_name"));
				sell.setItemNumber(rs.getInt("item_number"));
				sell.setSellUserId(rs.getInt("user_id"));
				sell.setBuyUserHomeAddress(rs.getString("home_address"));
				sell.setBuyUserAddress(rs.getString("address"));
				sell.setBuyUserName(rs.getString("user_name"));
				sell.setItemPrice(rs.getInt("item_price"));
				sell.setBuyDetailId(rs.getInt("buy_detail_id"));
				isSell = true;
			}

			System.out.println("get sellList");
			if (isSell) {
				return sell;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}