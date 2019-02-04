package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemTypeTableDao {

	public boolean entryItem(int itemId, String[] strItemTypeId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
			String sql = "INSERT INTO item_type_table values(0,?,?)";

			for(int i = 0; i < strItemTypeId.length ; i++) {
				if(strItemTypeId[i] != null) {
					PreparedStatement pStmt = con.prepareStatement(sql);
					pStmt.setInt(1, itemId);
					pStmt.setInt(2, Integer.parseInt(strItemTypeId[i]));
					int result = pStmt.executeUpdate();
					if(result != 1) {
						System.out.println("登録失敗");
						break;
					}
				}
			}
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

}
