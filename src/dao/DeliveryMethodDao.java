package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.DeliveryMethodBeans;

public class DeliveryMethodDao {

	public ArrayList<DeliveryMethodBeans> findDeliveryMethod() {
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM delivary_method";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList<DeliveryMethodBeans> dmbList = new ArrayList<DeliveryMethodBeans>();
			while(rs.next()) {
				DeliveryMethodBeans dmb = new DeliveryMethodBeans();
				dmb.setDelivaryMethodId(rs.getInt("delivary_method_id"));
				dmb.setDelivaryMethod(rs.getString("delivary_method"));
				dmbList.add(dmb);
			}
			System.out.println("配送方法の取得");
			return dmbList;
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
