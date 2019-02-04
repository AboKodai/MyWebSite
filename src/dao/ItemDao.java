package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.ItemBeans;

public class ItemDao {

	/**商品新規登録
	 *
	 * @param userId
	 * @param userId
	 * @param itemName
	 * @param itemDetail
	 * @param itemPrice
	 * @param itemNumber
	 * @param deliveryMethodId
	 * @param fileName
	 * @return itemId
	 */
	public int newItem(int userId, String itemName, String itemDetail,int itemPrice, int itemNumber, int deliveryMethodId, String fileName) {
		Connection con = null;
		int itemId = -1;
		try {
			con = DBManager.getConnection();

			String sql = "INSERT INTO item values(0,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, userId);
			pStmt.setString(2, itemName);
			pStmt.setString(3, itemDetail);
			pStmt.setInt(4, itemPrice);
			pStmt.setInt(5, itemNumber);
			pStmt.setInt(6, deliveryMethodId);
			pStmt.setString(7, fileName);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			if(rs.next()) {
				itemId = rs.getInt(1);
			}

			System.out.println("商品登録成功");
			return itemId;
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

	/**ユーザIDと商品名から商品を検索
	 *
	 * @param userId
	 * @param itemName
	 * @return
	 */
	public ItemBeans findItem(int userId, String itemName) {
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM item WHERE user_id = ?, item_name = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setString(2, itemName);
			ResultSet rs = pStmt.executeQuery();
			ItemBeans ib = new ItemBeans();
		//結果をセット
			ib.setItemId(rs.getInt("item_id"));
			ib.setUserId(rs.getInt("user_id"));
			ib.setItemName(rs.getString("item_name"));
			ib.setItemDetail(rs.getString("item_detail"));
			ib.setItemPrice(rs.getInt("item_price"));
			ib.setItemNumber(rs.getInt("item_number"));
			ib.setDelivaryMethod(rs.getInt("delivery_method_id"));
			ib.setFailName(rs.getString("file_name"));
			System.out.println("商品情報を検索");
			return ib;

		}catch (SQLException e) {
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


