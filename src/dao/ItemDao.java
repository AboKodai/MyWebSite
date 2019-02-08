package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

			System.out.println("entry item");
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
			System.out.println("get item info");
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

	/**ランダムに商品を取得
	 *
	 * @return
	 */
	public ArrayList<ItemBeans> getRandomItem() {
		Connection con = null;
		Statement stmt = null;
		String sql = null;

		try {
			con = DBManager.getConnection();

			sql = "SELECT * FROM item ORDER BY RAND() LIMIT 8";
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<ItemBeans> ibList = new ArrayList<ItemBeans>();

			while (rs.next()) {
				ItemBeans ib = new ItemBeans();
				ib.setItemId(rs.getInt(1));
				ib.setUserId(rs.getInt(2));
				ib.setItemName(rs.getString(3));
				ib.setItemDetail(rs.getString(4));
				ib.setItemPrice(rs.getInt(5));
				ib.setItemNumber(rs.getInt(6));
				ib.setDelivaryMethod(rs.getInt(7));
				ib.setFailName(rs.getString(8));
				ibList.add(ib);
			}
			System.out.println("get randon Item");
			return ibList;
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

	/**商品検索
	 *
	 * @return
	 */
	public ArrayList<ItemBeans> findItem(String searchWord, int[] itemTypeIdList, int pageNum, int pageMaxItemCount ) {
		Connection con = null;
		PreparedStatement pStmt = null;
		String sql = null;

		try {
			int startItemNum = (pageNum - 1) * pageMaxItemCount;
			con = DBManager.getConnection();

			if(searchWord.length() == 0 && itemTypeIdList == null) {
			//全検索
				sql = "SELECT * FROM item ORDER BY RAND() ASC LIMIT ?,?;";
				pStmt = con.prepareStatement(sql);
				pStmt.setInt(1, startItemNum);
				pStmt.setInt(2, pageMaxItemCount);
			}
			else if(searchWord.length() > 0 && itemTypeIdList == null) {
			//商品名検索
				sql = "SELECT * FROM item WHERE item_name LIKE '%" +searchWord+ "%' ORDER BY item_id ASC LIMIT ?,?";
				pStmt = con.prepareStatement(sql);
				pStmt.setInt(1, startItemNum);
				pStmt.setInt(2, pageMaxItemCount);
			}
			else if (searchWord.length() == 0 && itemTypeIdList.length > 0 ) {
			//種類検索
				sql = "SELECT item.*, item_type_table.*, item_type.* "
						+ "FROM item INNER JOIN item_type_table ON item.item_id = item_type_table.item_id "
						+ "INNER JOIN item_type ON item_type_table.item_type_id = item_type.item_type_id "
						+ "WHERE ";
				for(int i = 0 ; i < itemTypeIdList.length; i++) {
					sql += "item_type.item_type_id ="+itemTypeIdList[i];
					if(i<itemTypeIdList.length-1) {
						sql += " OR ";
					}
				}
				sql += " GROUP BY item.item_id ORDER BY item.item_id ASC LIMIT ?,?";

				pStmt = con.prepareStatement(sql);
				pStmt.setInt(1, startItemNum);
				pStmt.setInt(2, pageMaxItemCount);
			}
			else {
			//商品名検索＋種類検索
				sql = "SELECT item.*, item_type_table.*, item_type.* "
						+ "FROM item INNER JOIN item_type_table ON item.item_id = item_type_table.item_id "
						+ "INNER JOIN item_type  ON item_type_table.item_type_id = item_type.item_type_id "
						+ "WHERE (";
				for(int i = 0 ; i < itemTypeIdList.length; i++) {
					sql += "item_type.item_type_id ="+itemTypeIdList[i];
					if(i<itemTypeIdList.length-1) {
						sql += " OR ";
					}
				}
				sql += ") AND item_name LIKE '%" +searchWord+"%' GROUP BY item.item_id ORDER BY item.item_id ASC LIMIT ?,?";

				pStmt = con.prepareStatement(sql);
				pStmt.setInt(1, startItemNum);
				pStmt.setInt(2, pageMaxItemCount);
			}

			ResultSet rs = pStmt.executeQuery();
			ArrayList<ItemBeans> ibList = new ArrayList<ItemBeans>();
			while (rs.next()) {
				ItemBeans ib = new ItemBeans();
				ib.setItemId(rs.getInt(1));
				ib.setUserId(rs.getInt(2));
				ib.setItemName(rs.getString(3));
				ib.setItemDetail(rs.getString(4));
				ib.setItemPrice(rs.getInt(5));
				ib.setItemNumber(rs.getInt(6));
				ib.setDelivaryMethod(rs.getInt(7));
				ib.setFailName(rs.getString(8));
				ibList.add(ib);
			}
			System.out.println("get all item");
			return ibList;
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

	/**itemIdをもとに商品情報を取得。商品詳細
	 *
	 * @param itemId
	 * @return
	 */
	public ItemBeans itemDetail(int itemId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM item WHERE item_id = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, itemId);
			ResultSet rs = pStmt.executeQuery();
			ItemBeans item = new ItemBeans();
			while(rs.next()) {
				item.setItemId(rs.getInt("item_id"));
				item.setUserId(rs.getInt("user_id"));
				item.setItemName(rs.getString("item_name"));
				item.setItemDetail(rs.getString("item_detail"));
				item.setItemPrice(rs.getInt("item_price"));
				item.setItemNumber(rs.getInt("item_number"));
				item.setDelivaryMethod(rs.getInt("delivery_method_id"));
				item.setFailName(rs.getString("file_name"));
			}

			System.out.println("get item info");
			return item;

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

	/**商品数量から購入数を減算
	 *
	 * @param buyId
	 * @param userId
	 */
	public void decreaseItemNumber(int itemNumber, int itemId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "UPDATE item SET item_number = ? WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, itemNumber);
			pStmt.setInt(2, itemId);
			pStmt.executeUpdate();

			System.out.println("update itemNumber");

	}catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
}


