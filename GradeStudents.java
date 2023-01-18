import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;

class GradeStudents {
	private String subject;
	private int point;
	private ResultSet resultSet;
	private Scanner scanner = new Scanner(System.in);
	private StudentGradeApp sgApp = new StudentGradeApp();

	public GradeStudents(String subject) {
		this.subject = subject;
	}

	public void insertGrade() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff", "root", "");
		Connection connection = dbmsConnection.getConnection();
		Statement statement = connection.createStatement();
		String sql = "select point from " + subject;
		String sql2 = "select count(*) from " + subject;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery(sql2);
		resultSet.next();
		int count = resultSet.getInt(1);
		System.out.println("Number of records in the " + subject + " table: " + count);
		String[] gradeArray = new String[count];
		resultSet = preparedStatement.executeQuery();
		int i = 0;
		// String query = "ALTER TABLE Employee ADD grade VARCHAR(1) ";
		// Executing the query
		// statement.executeUpdate(query);
		// System.out.println("Column added......");
		while (resultSet.next()) {
			point = resultSet.getInt(1);
			gradeArray[i] = sgApp.grade(point);
			preparedStatement = connection.prepareStatement("update " + subject + " set grade = ? where point = ?;");
			preparedStatement.setString(1, sgApp.grade(point));
			preparedStatement.setInt(2, point);
			preparedStatement.executeUpdate();

			i++;
		}
		for (int j = 0; j < count; j++) {
			System.out.print(gradeArray[j] + ", ");
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
	}

	public void filterDataBasedInGrade() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff", "root", "");
		Connection connection = dbmsConnection.getConnection();
		System.out.println("Enter the grade which you wanna filter: ");
		String inputGrade = scanner.nextLine();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from " + subject + " where grade=? ");
		preparedStatement.setString(1, inputGrade);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "," + resultSet.getString(2));
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
	}

	public void getAverageGrade() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff", "root", "");
		Connection connection = dbmsConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from " + subject);
		resultSet = preparedStatement.executeQuery();
		int sumOfPoints = 0;
		int i = 0;
		while (resultSet.next()) {
			sumOfPoints += resultSet.getInt(4);
			i++;
		}
		double acerageOfPoints = sumOfPoints / i;
		System.out.println("Average grade: " + sgApp.grade(acerageOfPoints));
		dbmsConnection.closeConnection(connection, preparedStatement);
	}

	public void deleteStudentsWithGrade() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/staff", "root", "");
		Connection connection = dbmsConnection.getConnection();
		System.out.println("Enter the grade which you wanna filter: ");
		String inputGrade = scanner.nextLine();
		PreparedStatement preparedStatement = connection.prepareStatement("select * from " + subject + " where grade=? ");
		preparedStatement.setString(1, inputGrade);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "," + resultSet.getString(2));
			String sql = "delete from " + subject + " where grade = ?;";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			preparedStatement1.setString(1, inputGrade);
			preparedStatement1.executeUpdate();
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
	}
}