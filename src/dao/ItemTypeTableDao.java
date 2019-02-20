package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemTypeTableDao {

	/**商品ID登録
	 *
	 * @param itemId
	 * @param strItemTypeId
	 * @return
	 */
	public boolean entryItem(int itemId, String[] strItemTypeId) throws SQLException{
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
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**ItemIdからItemTypeIdを検索する。
	 * @param itemId
	 * @return
	 */
	public ArrayList<Integer> getItemTypeIdByItemId(int itemId) throws SQLException{
		Connection con = null;
		ArrayList<Integer> itemTypeIdList = new ArrayList<Integer>();

		try {
			con = DBManager.getConnection();
			String sql = "SELECT * FROM item_type_table WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, itemId);

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("item_type_id");
				itemTypeIdList.add(id);
			}

			System.out.println("get itemTypeIdList");
			return itemTypeIdList;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**itemIdに紐づくitemTypeIdを削除
	 *
	 * @param itemId
	 */
	public void itemTypeDeleteByItemId(int itemId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sqlDelete = "DELETE FROM item_type_table WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sqlDelete);
			pStmt.setInt(1, itemId);

			pStmt.executeUpdate();

			System.out.println("delete");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
