import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnectTest {

	
	public static void main (String[] args) throws Exception {

		String b = "SUCC";
		String c = "Kanto";
		updateName(b,c);

/*		String x = "INSERT INTO Region (Region_Code, Region_Name) VALUES (3,'HI')";
		insertINTO(x);*/
		//String y = "Kanto";
		selectFROM( c );
		/*String s = "DELETE FROM Region WHERE Region_Name = 'Hi'";
		deleteFROM(s);
		selectFROM(y);*/
		

	}//end main method

	public static ArrayList<String> selectFROM( String s) throws Exception {
		
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Region WHERE Region_Name  = '" + s +"'");
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<>();
			while(result.next()) {
				array.add(result.getString("Region_Code"));
				array.add(result.getString("Region_Name"));
			}
			return array;
		} catch (Exception e) {
			System.out.println("Exception... "+e);
		}
		return null;
	}

	public static void updateName(String o, String n) throws Exception {
		try {
			//connect to database
			Connection con = getConnection();
			//prepare the statEment that is passed in
			PreparedStatement posted = con.prepareStatement("UPDATE Region SET Region.Region_Name = '"+ n +"' " +
					"WHERE Region.Region_Name = '"+ o +"' AND Region.Region_Code = " +
					"(SELECT R2.Region_Code FROM (SELECT * FROM Region)R2 WHERE R2.Region_Name = '"+ o +"');");
			//execute update
			posted.executeUpdate();
			System.out.println("Update complete.");
		} catch (Exception e) {
			System.out.println("Exception caught... "+e);
		}
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

	public static void deleteFROM(String entry,String col,  String table) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement delete = con.prepareStatement("DELETE FROM " + table + " WHERE " + "" +
					"" + col + " = '" + entry + "'");
			delete.executeUpdate();
			//System.out.println("Item deleted");

		} catch (Exception e) {
			System.out.println("Exception thrown... "+e);
		}
	}
	
	public static Connection getConnection() throws Exception { 
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/pokedex?useSSL=false";
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