import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnectTest {

	
	public static void main (String[] args) throws Exception {
		
		String y = "SELECT * FROM Pokedex_User; ";
		
		selectFROM( y );
		
		
		
	}//end main method

	public static ArrayList<String> selectFROM( String s ) throws Exception {
		
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement( s );
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<>();
			while(result.next()) {
				System.out.print(result.getString("first"));
				System.out.print(" ");
				System.out.println(result.getString("last"));
				
				array.add(result.getString("last"));
			}
			System.out.println("All records have been selected!");
			return array;
		} catch (Exception e) {
			System.out.println("Exception... "+e);
		}
		
		return null;
	}
	
	public static void insertINTO( String s ) throws Exception {
		
		try {
			//connect to database
			Connection con = getConnection();
			//prepare the statEment that is passed in
			PreparedStatement posted = con.prepareStatement(s);
			//execute update
			posted.executeUpdate();
			System.out.println("Table info inserted...");
		} catch (Exception e) {
			System.out.println("Exception caught... "+e);
		}
	}
	
	public static void createTable(String s) throws Exception {
	
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement(s);
			create.executeUpdate();
			System.out.println("Table created!");
			
		} catch (Exception e) {
			System.out.println("Exception thrown... "+e);
		}
	}
	
	public static Connection getConnection() throws Exception { 
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://daldb.c1zphnyslwre.us-east-1.rds.amazonaws.com:3306/Pokedex?useSSL=false";
			String username = "java";
			String password = "csci2141";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch(Exception e) {
			System.out.println("Exception found..." + e);
		}
		return null;
	}
}//end class