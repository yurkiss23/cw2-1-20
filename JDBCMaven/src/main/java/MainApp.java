import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		// System.out.println("Hello App");
		try {
			Connection conn = GetConnection();
//			GetVersionPostgres(conn);
//			InsertAuthor(conn, "poi");
//			SelectAuthors(conn);
//			DeleteAuthor(conn, 28);
//			SelectAuthors(conn);
			int action = 0;
			do {
				System.out.println("select opation");
				System.out.println("0. exit");
				System.out.println("1. show all");
				System.out.println("2. add");
				System.out.println("3. delete");
				Scanner in = new Scanner(System.in);
				action = in.nextInt();
				switch (action) {
				case 1:{
					SelectAuthors(conn);
					break;
				}
				case 2:{
					System.out.println("input name: ");
					String name = in.next();
					InsertAuthor(conn, name);
					break;
				}
				case 3:{
					System.out.println("input id: ");
					int id = in.nextInt();
					DeleteAuthor(conn, id);
					break;
				}
				}
			} while (action != 0);
			
//			if (conn != null) {
//				System.out.println("Підключення успішне");
//			} else {
//				System.out.println("Проблеми при підключенні");
//			}

		} catch (Exception e) {
			System.out.println("У нас проблеми " + e.getMessage());
		}

	}
	
	private static Connection GetConnection()
			throws SQLException, ClassNotFoundException{
		String hostName = "10.7.24.196";
		String dbName = "paraskadb";
		String userName = "vova";
		String password = "Qwerty1-";

		Class.forName("org.postgresql.Driver");

		Connection conn = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, userName,
				password);
		return conn;
	}
	
	private static void GetVersionPostgres(Connection conn)
			throws SQLException{
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT VERSION()");
		
		if(rs.next()) {
			System.out.println(rs.getString(1));
		}

	}
	
	private static void InsertAuthor(Connection conn, String name)
			throws SQLException{
		String author = "qwe";
		String query = "INSERT INTO authors(name) VALUES(?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, name);
		pst.executeUpdate();
	}
	
	private static void SelectAuthors(Connection conn)
			throws SQLException{
		PreparedStatement pst = conn.prepareStatement("SELECT id, name FROM authors");
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print(": ");
			System.out.print(rs.getString(2)+"\n");
		}
	}
	
	private static void DeleteAuthor(Connection conn, int id)
			throws SQLException{
		String query = "DELETE FROM authors WHERE id = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, id);
		pst.executeUpdate();
	}
	
	
	
}