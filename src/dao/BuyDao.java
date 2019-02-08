package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.Statement;

import beans.BuyBeans;

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
}
