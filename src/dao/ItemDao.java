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
	public int newItem(int userId, String itemName, String itemDetail,int itemPrice, int itemNumber, int deliveryMethodId, String fileName) throws SQLException{
		Connection con = null;
		int itemId = -1;
		try {
			con = DBManager.getConnection();

			String sql = "INSERT INTO item values(0,?,?,?,?,?,?,?,1)";
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
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**ユーザIDと商品名から商品を検索
	 *
	 * @param userId
	 * @param itemName
	 * @return
	 */
	public ItemBeans findItem(int userId, String itemName) throws SQLException{
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
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**ランダムに商品を取得
	 *
	 * @return
	 */
	public ArrayList<ItemBeans> getRandomItem() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		String sql = null;

		try {
			con = DBManager.getConnection();

			sql = "SELECT * FROM item WHERE delete_flag = 1 ORDER BY RAND() LIMIT 8";
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
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**商品検索
	 *
	 * @return
	 */
	public ArrayList<ItemBeans> findItem(String searchWord, ArrayList<Integer> itemTypeIdList) throws SQLException{
		Connection con = null;
		Statement stmt = null;
		String sql = null;

		try {
			con = DBManager.getConnection();

			if(searchWord.length() == 0 && itemTypeIdList == null) {
				//全検索
				sql = "SELECT * FROM item WHERE delete_flag = 1 ORDER BY RAND()";
			}
			else if(searchWord.length() > 0 && itemTypeIdList == null) {
				//商品名検索
				sql =
						"SELECT *"
						+ " FROM item"
						+ " WHERE item_name LIKE '%" +searchWord+ "%'"
						+ " AND delete_flag = 1"
						+ " ORDER BY item_id ASC";
			}
			else if (searchWord.length() == 0 && itemTypeIdList.size() > 0 ) {
				//種類検索
				sql = "SELECT i.*,"
						+ " ittable.*,"
						+ " it.*"
						+ " FROM item i"
						+ " INNER JOIN item_type_table ittable ON i.item_id = ittable.item_id"
						+ " INNER JOIN item_type it ON ittable.item_type_id = it.item_type_id"
						+ " WHERE";
				for(int i = 0 ; i < itemTypeIdList.size(); i++) {
					sql += " EXISTS (SELECT 1"
							+ " FROM item_type_table"
							+ " WHERE item_type_table.item_id = i.item_id"
							+ " AND item_type_table.item_type_id ="+itemTypeIdList.get(i);
					if(i<itemTypeIdList.size()-1) {
						sql += ") AND ";
					}
				}
				sql +=
						") AND delete_flag = 1"
						+ " GROUP BY i.item_id"
						+ " ORDER BY i.item_id ASC";

			}
			else {
				//商品名検索＋種類検索
				sql = "SELECT i.*,"
						+ " ittable.*,"
						+ " it.*"
						+ " FROM item i"
						+ " INNER JOIN item_type_table ittable ON i.item_id = ittable.item_id"
						+ " INNER JOIN item_type it ON ittable.item_type_id = it.item_type_id"
						+ " WHERE ";
				for(int i = 0 ; i < itemTypeIdList.size(); i++) {
					sql += " EXISTS (SELECT 1"
							+ " FROM item_type_table"
							+ " WHERE item_type_table.item_id = i.item_id"
							+ " AND item_type_table.item_type_id ="+itemTypeIdList.get(i);

					if(i<itemTypeIdList.size()-1) {
						sql += ") AND ";
					}
				}
				sql += ") AND item_name LIKE '%" +searchWord+"%'"
						+ " AND delete_flag = 1"
						+ " GROUP BY i.item_id"
						+ " ORDER BY i.item_id ASC";

			}

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
			System.out.println("get all item");
			return ibList;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**itemIdをもとに商品情報を取得。商品詳細
	 *
	 * @param itemId
	 * @return
	 */
	public ItemBeans itemDetail(int itemId) throws SQLException{
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
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**商品数量から購入数を減算
	 *
	 * @param buyId
	 * @param userId
	 */
	public void decreaseItemNumber(int itemNumber, int itemId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "UPDATE item SET item_number = ? WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, itemNumber);
			pStmt.setInt(2, itemId);
			pStmt.executeUpdate();

			System.out.println("update itemNumber");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}



	/**商品更新
	 * @param itemName
	 * @param itemDetail
	 * @param itemPrice
	 * @param itemNumber
	 * @param deliveryMethodId
	 * @param fileName
	 * @param itemId
	 */
	public void itemUpdate(String itemName ,String itemDetail, int itemPrice, int itemNumber, int deliveryMethodId, int itemId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "UPDATE item SET item_name = ?, item_detail = ?, item_price = ?, item_number = ?, delivery_Method_id = ? WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, itemName);
			pStmt.setString(2, itemDetail);
			pStmt.setInt(3, itemPrice);
			pStmt.setInt(4, itemNumber);
			pStmt.setInt(5, deliveryMethodId);
			pStmt.setInt(6, itemId);
			pStmt.executeUpdate();

			System.out.println("item info update");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**ユーザIDをもとに商品情報を取得。出品商品一覧。
	 *
	 * @param userId
	 * @return
	 */
	public ArrayList<ItemBeans> getItemByuserId(int userId) throws SQLException{
		Connection con = null;
		ArrayList<ItemBeans> itemList = new ArrayList<ItemBeans>();
		boolean isAdd = false;
		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM item WHERE user_id = ?";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("delete_flag") == 1) {
					ItemBeans ib = new ItemBeans();
					ib.setItemId(rs.getInt("item_id"));
					ib.setUserId(rs.getInt("user_id"));
					ib.setItemName(rs.getString("item_name"));
					ib.setItemDetail(rs.getString("item_detail"));
					ib.setItemPrice(rs.getInt("item_price"));
					ib.setItemNumber(rs.getInt("item_number"));
					ib.setDelivaryMethod(rs.getInt("delivery_method_id"));
					ib.setFailName(rs.getString("file_name"));
					itemList.add(ib);
					isAdd = true;
				}
			}

			if(isAdd) {

				System.out.println("get itemList info");
				return itemList;
			}else {
				return null;
			}

		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public void itemDelete(int itemId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "UPDATE item SET delete_flag=0 WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, itemId);

			pStmt.executeUpdate();

			System.out.println("item delete from item table");

		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**商品在庫の確認
	 *
	 * @param itemId
	 * @return
	 */
	public int getItemNumberByItemId(int itemId) throws SQLException{
		Connection con = null;
		int number = -1;

		try {
			con=DBManager.getConnection();

			String sql = "SELECT item_number FROM item WHERE item_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, itemId);

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				number = rs.getInt(1);
			}

			return number;
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


