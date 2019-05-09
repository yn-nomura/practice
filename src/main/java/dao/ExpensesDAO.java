package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Expenses;

/**
 * 経費データを扱うDAO*/
public class ExpensesDAO {
	/**
	 * クエリ文字列
	 */

	private static final String SELECT_PART_QUERY = "select  \n" +
			"EL.APPLICATION_ID \n" +
			",EL.APPLICATION_DATE \n" +
			",EL.UPDATE_DATE \n" +
			",EM.NAME \n" +
			",SL.EXPENSES_NAME \n" +
			",EL.AMOUNTOFMONEY \n" +
			",ST.STATUS_NAME \n" +
			"from \n" +
			"EXPENSES_LIST EL \n" +
			",EMPLOYEE EM \n" +
			",STATUS ST \n" +
			",SPENDING_LIST SL \n" +
			"where \n" +
			"1=1 \n" +
			"and EL.EXPENSES_ID=SL.EXPENSES_ID \n" +
			"and EL.STATUS_ID=ST.STATUS_ID \n" +
			"and EL.APP_NAME_ID=EM.ID \n";

	private static final String SELECT_DETAIL_QUERY = "select \n" +
			"EL.APPLICATION_ID \n" +
			",EL.APPLICATION_DATE \n" +
			",EL.UPDATE_DATE \n" +
			",EM.NAME \n" +
			",SL.EXPENSES_NAME \n" +
			",EL.AMOUNTOFMONEY \n" +
			",ST.STATUS_NAME \n" +
			",EME.NAME \n" +
			"from \n" +
			"EXPENSES_LIST EL \n" +
			",EMPLOYEE EM \n" +
			",STATUS ST \n" +
			",SPENDING_LIST SL \n" +
			",EMPLOYEE EME \n" +
			"where \n" +
			"1=1 \n" +
			"and EL.EXPENSES_ID=SL.EXPENSES_ID \n" +
			"and EL.STATUS_ID=ST.STATUS_ID \n" +
			"and EL.APP_NAME_ID=EM.ID \n" +
			"and EL.UPDATE_ID = EME.ID \n";

	private static final String SELECT_BY_ID_QUERY = "select  \n" +
			"EL.APPLICATION_ID \n" +
			",EL.APPLICATION_DATE \n" +
			",EL.UPDATE_DATE \n" +
			",EM.NAME \n" +
			",SL.EXPENSES_NAME \n" +
			",EL.AMOUNTOFMONEY \n" +
			",ST.STATUS_NAME \n" +
			"from \n" +
			"EXPENSES_LIST EL \n" +
			",EMPLOYEE EM \n" +
			",STATUS ST \n" +
			",SPENDING_LIST SL \n" +
			"where \n" +
			"1=1 \n" +
			"and EL.APPLICATION_ID= ? \n"+
			"and EL.EXPENSES_ID=SL.EXPENSES_ID \n" +
			"and EL.STATUS_ID=ST.STATUS_ID \n" +
			"and EL.APP_NAME_ID=EM.ID \n";

	private static final String INSERT_QUERY ="insert into"
			+"EXPENSES_LIST(APPLICATION_ID,APPLICATION_DATE,UPDATE_DATE,APP_NAME_ID,EXPENSES_ID,PAYMENT,AMOUNTOFMONEY,STATUS_ID,UPDATE_ID)"
			+"values(?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_QUERY = "UPDATE EXPENSES_LIST SET APPLICATION_DATE=? UPDATE_DATE=? APP_NAME_ID=? EXPENSES_ID=? PAYMENT=? AMOUNTOFMONEY=? STATUS_ID=? WHERE APPLICATION_ID=?";

	private static final String DELETE_QUERY="DELETE FROM EXPENSES_LIST WHERE APPLICATION_ID =?";

	/**
	 * 経費一覧の一部を取得する
	 */
	public List<Expenses>findPart(){
		List<Expenses>result = new ArrayList<>();

		Connection connection = ConnectionProvider.getConnection();
		if(connection == null){
			return result;
		}

		try  (Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(SELECT_PART_QUERY);

			//確認
			System.out.println("-----------------");
			System.out.println(rs);

			while (rs.next()) {
				System.out.println(rs.getInt("APPLICATION_ID"));
				result.add(processRow(rs));
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			ConnectionProvider.close(connection);
		}

		return result;

	}

	/**
	 * 経費の詳細を取得する*/
//	public List<Expenses>findDetail(int id){
//		List<Expenses>result = new ArrayList<>();
//		Connection connection = ConnectionProvider.getConnection();
//		if(connection == null){
//			return result;
//		}
//		try (Statement statement = connection.createStatement();){
//			ResultSet rs = statement.executeQuery(SELECT_BY_ID_QUERY);
//			statement.setInt(1, id);
//			while(rs.next()){
//				result.add(processDetailRow(rs));
//			}
//		}catch(SQLException e){
//			e.printStackTrace();
//		}finally{
//			ConnectionProvider.close(connection);
//		}
//		return result;
//	}


	public Expenses findById(int id) {
		Expenses result = null;

		Connection connection = ConnectionProvider.getConnection();
		if (connection == null) {
			return result;
		}

		try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				result = processRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(connection);
		}

		return result;
	}

	/**
	 * 新規登録*
	 */
	 public Expenses create(Expenses expenses){
		 Connection connection = ConnectionProvider.getConnection();
			if (connection == null) {
				return expenses;
			}

			try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, new String[] { "ID" });) {
				// INSERT実行
				setParameter(statement,expenses,false);
				statement.executeUpdate();
				// INSERTできたらKEYを取得
				ResultSet rs = statement.getGeneratedKeys();
				rs.next();
				int id = rs.getInt(1);
				expenses.setApplicationId(id);
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				ConnectionProvider.close(connection);
			}

			return expenses;
		}


	 public boolean update(Expenses expenses){
		 Connection connection = ConnectionProvider.getConnection();
		 if (connection == null) {
				return false;
			}

			int count = 0;
			try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
				statement.setInt(1,expenses.getApplicationId());
				statement.setString(2,expenses.getApplicationDate());
				statement.setString(3,expenses.getUpdateDate());
				statement.setString(4,expenses.getName());
				statement.setString(5,expenses.getExpensesName());
				statement.setString(6,expenses.getAmountOfMoney());
				statement.setString(7,expenses.getStatusName());
				count = statement.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				ConnectionProvider.close(connection);
			}

			return count == 1;
	 }

	 /**指定されてidでデータ削除*/
	 public boolean remove(int id){
		 Connection connection = ConnectionProvider.getConnection();
			if (connection == null) {
				return false;
			}

			int count = 0;
			try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
				// DELETE実行
				statement.setInt(1, id);
				count = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectionProvider.close(connection);
			}
			return count == 1;
	 }

	/**
	 * 検索結果行をオブジェクトとして構成する。
	 * @param rs 検索結果が収められているResultSet
	 * @return 検索結果行の各データを収めたPostインスタンス
	 * @throws SQLException ResultSetの処理中発生した例外
	 */
	private Expenses processRow(ResultSet rs) throws SQLException {
		Expenses result = new Expenses();
//		Expensesの本体の再現
		result.setApplicationId(rs.getInt("APPLICATION_ID"));
		result.setApplicationDate(rs.getString("APPLICATION_DATE"));
		result.setUpdateDate(rs.getString("UPDATE_DATE"));
		result.setName(rs.getString("NAME"));
		result.setExpensesName(rs.getString("EXPENSES_NAME"));
		result.setAmountOfMoney(rs.getString("AMOUNTOFMONEY"));
		result.setStatusName(rs.getString("STATUS_NAME"));
		return result;
	}

	private Expenses processDetailRow(ResultSet rs) throws SQLException {
		Expenses result = new Expenses();
//		Expensesの本体の再現
		result.setApplicationId(rs.getInt("APPLICATION_ID"));
		result.setApplicationDate(rs.getString("APPLICATION_DATE"));
		result.setUpdateDate(rs.getString("UPDATE_DATE"));
		result.setName(rs.getString("NAME"));
		result.setExpensesName(rs.getString("EXPENSES_NAME"));
		result.setAmountOfMoney(rs.getString("AMOUNTOFMONEY"));
		result.setStatusName(rs.getString("STATUS_NAME"));
		result.setName(rs.getString("NAME"));
		return result;
	}

	/**
	 * オブジェクトからSQLにパラメータを展開する。
	 *
	 * @param statement パラメータ展開対象のSQL
	 * @param employee パラメータに対して実際の値を供給するオブジェクト
	 * @param forUpdate 更新に使われるならtrueを、新規追加に使われるならfalseを指定する。
	 * @throws SQLException パラメータ展開時に何らかの問題が発生した場合に送出される。
	 */
	private void setParameter(PreparedStatement statement, Expenses expenses, boolean forUpdate) throws SQLException {
		int count = 1;

		statement.setInt(count++, expenses.getApplicationId());
		statement.setString(count++, expenses.getApplicationDate());
		statement.setString(count++, expenses.getUpdateDate());
		statement.setString(count++, expenses.getName());
		statement.setString(count++, expenses.getExpensesName());
		statement.setString(count++, expenses.getAmountOfMoney());
		statement.setString(count++, expenses.getStatusName());

		if (forUpdate) {
			statement.setInt(count++, expenses.getApplicationId());
		}
	}

}
