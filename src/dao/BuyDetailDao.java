package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BuyDetailBeans;
import beans.ItemBeans;

public class BuyDetailDao {

	/**購入詳細情報登録
	 *
	 * @param bdb
	 */
	public void insertBuyDetail(BuyDetailBeans bdb) throws SQLException{
		Connection con = null;
		try {
			con= DBManager.getConnection();

			String sql = "INSERT INTO buy_detail VALUES(0, ?, ?, ?,0)";
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, bdb.getBuyId());
			pStmt.setInt(2, bdb.getItemId());
			pStmt.setInt(3, bdb.getItemNumber());

			pStmt.executeUpdate();

			System.out.println("insert buyDetail");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**購入詳細情報検索
	 *
	 * @param buyId
	 * @return
	 */
	public ArrayList<BuyDetailBeans> getBuyDetailBeansByBuyId(int buyId) throws SQLException{
		Connection con = null;
		try {
			con= DBManager.getConnection();

			String sql = "SELECT * FROM buy_detail WHERE buy_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, buyId);

			ResultSet rs = pStmt.executeQuery();

			ArrayList<BuyDetailBeans> bdbList = new ArrayList<BuyDetailBeans>();
			while(rs.next()) {

				BuyDetailBeans bdb = new BuyDetailBeans();
				bdb.setItemId(rs.getInt("item_id"));
				bdb.setBuyId(rs.getInt("buy_id"));
				bdb.setItemNumber(rs.getInt("item_number"));
				bdb.setBuyDetailId(rs.getInt("buy_detail_id"));
				bdbList.add(bdb);
			}
			System.out.println("get BuyDetailBeansList");
			return bdbList;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**購入IDから購入詳細を取得
	 *
	 * @param buyId
	 * @return
	 */
	public ArrayList<ItemBeans> getItemListForBuyDetail(int buyId) throws SQLException{
		Connection con = null;
		try {
			con= DBManager.getConnection();

			String sql =
					"SELECT buy_detail.*,"
					+ " item.item_price,"
					+ " item.item_name FROM buy_detail"
					+ " JOIN item"
					+ " ON item.item_id = buy_detail.item_id"
					+ " WHERE buy_detail.buy_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, buyId);

			ResultSet rs = pStmt.executeQuery();
			ArrayList<ItemBeans> itemList = new ArrayList<ItemBeans>();
			while(rs.next()) {
				ItemBeans item = new ItemBeans();
				item.setItemName(rs.getString("item_name"));
				item.setSellNumber(rs.getInt("item_number"));
				item.setItemPrice(rs.getInt("item_price"));
				item.setItemId(rs.getInt("item_id"));

				itemList.add(item);
			}
			System.out.println("get itemBeansList");
			return itemList;
	}catch (SQLException e) {
		System.out.println(e.getMessage());
		throw new SQLException(e);
	} finally {
		if (con != null) {
			con.close();
		}
	}
}

	/**チェックボックスの更新
	 *
	 * @param buyDetailId
	 */
	public void checkboxUpdate(int buyDetailId, int checkbox) throws SQLException{
		Connection con = null;
		try {
			con= DBManager.getConnection();

			String sql =
					"UPDATE buy_detail"
					+ " SET checkbox = ?"
					+ " WHERE buy_detail_id = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, checkbox);
			pStmt.setInt(2, buyDetailId);

			pStmt.executeUpdate();
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
