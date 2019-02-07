package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.Statement;

import beans.BuyBeans;

public class BuyDao {

	public int insertBuyInfo(BuyBeans buy) {
		Connection con = null;
		int buyId = -1;

		try {
			con = DBManager.getConnection();
			String sql = "INSERT INTO buy VALUES(0,?,?,?,?)";
			PreparedStatement pStmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, buy.getUserId());
			pStmt.setInt(2, buy.getTotalPrice());
			pStmt.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			pStmt.setInt(4, buy.getCheckboxInfo());
			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				buyId = rs.getInt(1);
			}
			System.out.println("insert buyData");

			return buyId;
		}catch(SQLException e) {
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

}
