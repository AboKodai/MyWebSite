package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ItemTypeBeans;

public class ItemTypeDao {

	/**商品の種類を配列で格納
	 *
	 * @return
	 */
	public ArrayList<ItemTypeBeans> findItemType() {
		Connection con = null;

		try {
			con = DBManager.getConnection();
			ArrayList<ItemTypeBeans> itbList = new ArrayList<ItemTypeBeans>();
			//SERECT文の準備
			String sql = "SELECT * FROM item_type";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemTypeBeans it = new ItemTypeBeans();
				it.setItemTypeId(rs.getInt("item_type_id"));
				it.setItemTypeName(rs.getString("item_type_name"));
				itbList.add(it);
			}
			System.out.println("種類名をすべて取得");
			return itbList;

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
