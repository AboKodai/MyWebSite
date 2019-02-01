package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import beans.UserBeans;

public class UserDao {

	/**
	 * 単体ユーザ情報の取得
	 * @param loginId
	 * @param password
	 * @return
	 */
	public UserBeans findUser(String loginId, String password) {
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE login_id = ? AND password = ?";

			PreparedStatement pStmt = con.prepareStatement(sql);

			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			System.out.println("ログイン成功");
			int userIdInfo = rs.getInt("user_id");
			String loginIdInfo = rs.getString("login_id");
			String userNameInfo = rs.getString("user_name");
			String passwordInfo = rs.getString("password");
			Date birthDateInfo = rs.getTimestamp("birth_date");
			String homeAddressInfo = rs.getString("home_address");
			String address = rs.getString("address");

			return new UserBeans(userIdInfo, loginIdInfo, userNameInfo, passwordInfo, birthDateInfo,homeAddressInfo,address);

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

	/**
	 * 新規会員登録
	 * @param user
	 * @return
	 */
	public boolean newUser(UserBeans user) {
		Connection con = null;

		try {

		//データベースに接続
			con = DBManager.getConnection();
		//値の取得
			String loginId =user.getLoginId();
			String userName = user.getUserName();
			Date birthDate = user.getBirthDate();
			String password = user.getPassword();
			String homeAddress = user.getHomeAddress();
			String address = user.getAddress();
		//INSERT文の準備
			String sql = "INSERT INTO user VALUES(0, ?, ? ,? ,? ,? ,?)";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, userName);
			pStmt.setTimestamp(3, new Timestamp(birthDate.getTime()));
			pStmt.setString(4, password);
			pStmt.setString(5, homeAddress);
			pStmt.setString(6, address);
			//結果の取得
			int result = pStmt.executeUpdate();
			//新規登録に成功したらtrue失敗したらfalse
			if (result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
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

	/**ログインIDが使用済みかどうかを確認する
	 *
	 * @param loginId
	 * @return boolean
	 * ログインIDが使用済みだった場合はtrueを使用されていなければfalseを返す
	 */
	public boolean userCheckId(String loginId ,String newLoginId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
			//SELECT文の準備
			String sql = "SELECT * FROM user WHERE login_id != ? AND login_id = ?";
			//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, newLoginId);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
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

	/**メールアドレスが使用済みかどうかを確認する
	*
	* @param address
	* @return boolean
	* メールアドレスが使用済みだった場合はtrueを使用されていなければfalse
	*/
	public boolean userCheckAddress(String address ,String newAddress) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
			//SELECT文の準備
			String sql = "SELECT * FROM user WHERE address != ? AND address = ?";

			//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, address);
			pStmt.setString(2, newAddress);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
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

	/**ユーザ情報の削除
	 *
	 * @param userId
	 * @return
	 */
	public boolean userDelete(int userId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
		//DELETE文の準備
			String sql = "DELETE FROM user WHERE user_id = ?";
		//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);
			int rs = pStmt.executeUpdate();

			if (rs == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
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

	public boolean itemDelete(int userId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
		//DELETE文の準備
			String sql = "DELETE FROM item WHERE user_id = ?";
		//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, userId);
			int rs = pStmt.executeUpdate();

			if (rs == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
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

	/**ユーザ情報更新
	 *
	 * @param userId
	 * @param loginId
	 * @param homeAddress
	 * @param address
	 * @return
	 */
	public boolean userUpdate(int userId, String loginId, String userName, String homeAddress, String address) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
		//UPDATE文の準備
			String sql = "UPDATE user SET login_id = ?,user_name=?,home_address=?,address=? WHERE user_id=?";
		//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, userName);
			pStmt.setString(3, homeAddress);
			pStmt.setString(4, address);
			pStmt.setInt(5, userId);
			int rs = pStmt.executeUpdate();

			if (rs == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
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
	/**パスワードの更新
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean passwordUpdate(int userId, String password) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
		//UPDATE文の準備
			String sql = "UPDATE user SET password = ? WHERE user_id=?";
		//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, password);
			pStmt.setInt(2, userId);
			int rs = pStmt.executeUpdate();

			if (rs == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
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