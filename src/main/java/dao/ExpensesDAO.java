package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Employee;

public class ExpensesDAO {
	/**
	 * クエリ文字列
	 */

String ExpensesLists = "select \n" +
		"EL.APPLICATION_ID \n" +
		",EL.APPLICATION_DATE \n" +
		",EM.NAME \n" +
		",SL.EXPENSES_NAME \n" +
		",EL.PAYMENT \n" +
		",EL.AOMOUNT_OF_MONEY \n" +
		",SS.STATUS_NAME \n" +
		",EL.UPDATE_ID \n" +
		"from \n" +
		"SPENDING_LIST SL, \n" +
		"STATUS SS,  \n" +
		"EXPENSES_LIST EL, \n" +
		"EMPLOYEE EM \n" +
		" \n" +
		"where \n" +
		"SL.EXPENSES_ID=EL.EXPENSES_ID \n" +
		"and EL.STATUS_ID=SS.STATUS_ID \n" +
		"and EM.ID=EL.APP_NAME_ID \n" ;



	/**
	 *id指定の検索の実施
	 */

	public Employee findById(int id) {
		Employee result = null;

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
	 * パラメータ指定の検索を実施する
	 */
	public List<Employee> findByParam(Param param) {
		List<Employee> result = new ArrayList<>();

		Connection connection = ConnectionProvider.getConnection();
		if (connection == null) {
			return result;
		}

		String queryString = SELECT_ALL_QUERY + param.getWhereClause();
		try (PreparedStatement statement = connection.prepareStatement(queryString)) {
			param.setParameter(statement);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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
	 * オブジェクトからSQLにパラメータを展開する。
	 *
	 * @param statement パラメータ展開対象のSQL
	 * @param employee パラメータに対して実際の値を供給するオブジェクト
	 * @param forUpdate 更新に使われるならtrueを、新規追加に使われるならfalseを指定する。
	 * @throws SQLException パラメータ展開時に何らかの問題が発生した場合に送出される。
	 */
	private void setParameter(PreparedStatement statement, Employee employee, boolean forUpdate) throws SQLException {
		int count = 1;

		statement.setString(count++, employee.getEmpId());
		statement.setString(count++, employee.getName());
		statement.setInt(count++, employee.getAge());
		statement.setInt(count++, employee.getGender().ordinal());
		statement.setInt(count++, employee.getPhotoId());
		statement.setString(count++, employee.getZip());
		statement.setString(count++, employee.getPref());
		statement.setString(count++, employee.getAddress());
		statement.setInt(count++, employee.getPost().getId());
		if (employee.getEnterDate() != null) {
			statement.setDate(count++, Date.valueOf(employee.getEnterDate()));
		} else {
			statement.setDate(count++, null);
		}
		if (employee.getRetireDate() != null) {
			statement.setDate(count++, Date.valueOf(employee.getRetireDate()));
		} else {
			statement.setDate(count++, null);
		}

		if (forUpdate) {
			statement.setInt(count++, employee.getId());
		}
	}
}
