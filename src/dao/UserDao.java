package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBeans;

public class UserDao {

	/**ログイン情報の取得
	 * 入力されたログインIDとパスワードから
	 * ユーザID、ユーザ名、ログインIDを返す
	 * 見つからなければnull*/
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
			int userId = rs.getInt("user_id");
			String userName = rs.getString("user_name");
			return new UserBeans(userId, userName);

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

	/**新規会員登録
	 *
	 * @param loginId
	 * @param userName
	 * @param birthDate
	 * @param password
	 * @param passwordCon
	 * @param homeAddress
	 * @param address
	 * @return UserBeans
	 */
	public boolean newUser(String loginId, String userName, String birthDate, String password, String homeAddress,
			String address) {
		Connection con = null;

		try {

			//データベースに接続
			con = DBManager.getConnection();
			//INSERT文の準備
			String sql = "INSERT INTO user VALUES(0, ?, ? ,? ,? ,? ,?)";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, userName);
			pStmt.setString(3, birthDate);
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
	 */
	public boolean userCheckId(String loginId) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
			//SELECT文の準備
			String sql = "SELECT * FROM user WHERE login_id = ?";
			//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, loginId);
			ResultSet rs = pStmt.executeQuery();

			//ログインIDが使用済みだった場合はtrueを使用されていなければfalseを返す
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
	*/
	public boolean userCheckAddress(String address) {
		Connection con = null;

		try {
			con = DBManager.getConnection();
			//SELECT文の準備
			String sql = "SELECT * FROM user WHERE address = ?";
			//結果の取得
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, address);
			ResultSet rs = pStmt.executeQuery();

			//メールアドレスが使用済みだった場合はtrueを使用されていなければfalseを返す
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
}
