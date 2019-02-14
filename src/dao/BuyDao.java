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
	public int insertBuyInfo(BuyBeans buy) {
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
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}

	/**buyIdからBuyBeansを取得
	 *
	 * @param buyId
	 * @return
	 */
	public BuyBeans getBuyBeansByBuyId(int buyId) {
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
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**ユーザIDから最新の購入情報を取得
	 * @param userId
	 * @return
	 */
	public BuyBeans getBuyBeansByUserId(int userId) {
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
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**ユーザIDから最新の購入履歴一覧を取得
	 * @param userId
	 * @return
	 */
	public ArrayList<BuyBeans> getBuyBeansListByUserId(int userId) {
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
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**受注一覧のための情報を取得
	 *
	 * @param userId
	 * @return
	 */
	public ArrayList<SellListBeans> getSellList(int userId){
		Connection con = null;
		ArrayList<SellListBeans> buyList = new ArrayList<SellListBeans>();

		try {
			con = DBManager.getConnection();

			String sql =
					"SELECT"
					+ " buy.buy_user_id,"
					+ " buy.buy_date,"
					+ " buy_detail.checkbox,"
					+ " buy_detail.item_number,"
					+ " item.user_id,"
					+ " item.item_name"
					+ " FROM"
					+ " buy"
					+ " JOIN buy_detail ON buy.buy_id = buy_detail.buy_id"
					+ " JOIN item ON buy_detail.item_id = item.item_id"
					+ " WHERE"
					+ " item.user_id = ?"
					+ " ORDER BY"
					+ " buy.buy_id DESC";

			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setInt(1, userId);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				SellListBeans sell = new SellListBeans();

			}


		}
	}


}
