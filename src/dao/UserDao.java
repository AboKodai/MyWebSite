package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import beans.UserBeans;

public class UserDao {

	/**
	 * 単体ユーザ情報の取得
	 * @param loginId
	 * @param password
	 * @return
	 */
	public UserBeans findUser(String loginId, String password) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE login_id = ? AND password = ? AND delete_flag=1";

			PreparedStatement pStmt = con.prepareStatement(sql);



			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			System.out.println("login is conpleted");
			int userIdInfo = rs.getInt("user_id");
			String loginIdInfo = rs.getString("login_id");
			String userNameInfo = rs.getString("user_name");
			String passwordInfo = rs.getString("password");
			Date birthDateInfo = rs.getTimestamp("birth_date");
			String homeAddressInfo = rs.getString("home_address");
			String address = rs.getString("address");

			return new UserBeans(userIdInfo, loginIdInfo, userNameInfo, passwordInfo, birthDateInfo,homeAddressInfo,address);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			} 
		}
	}

	/**
	 * 新規会員登録
	 * @param user
	 * @return
	 */
	public boolean newUser(UserBeans user) throws SQLException{
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
			String sql = "INSERT INTO user VALUES(0, ?, ? ,? ,? ,? ,?,1)";

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
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			} 
		}
	}


	/**ログインIDが使用済みかどうかを確認する
	 *
	 * @param loginId
	 * @return boolean
	 * ログインIDが使用済みだった場合はtrueを使用されていなければfalseを返す
	 */
	public boolean userCheckId(String loginId ,String newLoginId) throws SQLException{
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
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			} 
		}
	}



	/**メールアドレスが使用済みかどうかを確認する
	*
	* @param address
	* @return boolean
	* メールアドレスが使用済みだった場合はtrueを使用されていなければfalse
	*/
	public boolean userCheckAddress(String address ,String newAddress) throws SQLException{
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
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			} 
		}
	}



	/**ユーザ退会処理
	 *
	 * @param userId
	 * @return
	 */
	public void userDelete(int userId) throws SQLException{
		Connection con = null;

		try {
			con = DBManager.getConnection();
			//userテーブルのUPDATE文を準備
			String sqlUser = "UPDATE user SET delete_flag=0 WHERE user_id = ?";
			//実行
			PreparedStatement pStmtUser = con.prepareStatement(sqlUser);
			pStmtUser.setInt(1, userId);
			pStmtUser.executeUpdate();

			//itemテーブルのUPDATE文を準備
			String sqlItem = "UPDATE item SET delete_flag=0 WHERE user_id = ?";
			//実行
			PreparedStatement pStmtItem = con.prepareStatement(sqlItem);
			pStmtItem.setInt(1, userId);
			pStmtItem.executeUpdate();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
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
	public boolean userUpdate(int userId, String loginId, String userName, String homeAddress, String address) throws SQLException{
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
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			} 
		}
	}



	/**パスワードの更新
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean passwordUpdate(int userId, String password) throws SQLException{
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
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			} 
		}
	}

	/**パスワードを暗号化
	 *
	 * @param password
	 * @return
	 */
	public String getMD5Password(String password) {
		try {
			String source = password;
			Charset charset = StandardCharsets.UTF_8;
			String algorithm = "MD5";
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);
			return result;
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;

		}
	}
}