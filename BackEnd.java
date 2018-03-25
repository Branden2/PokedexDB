import java.sql.*;
import java.util.ArrayList;

public class BackEnd {
    public static void main (String[] args) throws Exception {

    }//end main method

    public static ArrayList<String> selectFROM( String column, String table) throws Exception {

        try (Connection con = getConnection()){

            String SQL = "SELECT " + column + " FROM " + table + ";";

            PreparedStatement statement = con.prepareStatement(SQL);
            ResultSet result = statement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();

            ArrayList<String> array = new ArrayList<>();
            while(result.next()) {

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    array.add(result.getString(i));
                }
            }
            return array;
        } catch (Exception e) {
            System.out.println("Exception... "+e);
        }
        return null;
    }

    public static ArrayList<String> selectFROM( ArrayList<String> columns, ArrayList<String> tables) throws Exception {

        try (Connection con = getConnection()){

            String colNames = parseList(columns);
            String tableNames = parseList(tables);

            String SQL = "SELECT " + colNames + " FROM " + tableNames + ";";

            PreparedStatement statement = con.prepareStatement(SQL);
            ResultSet result = statement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();

            ArrayList<String> array = new ArrayList<>();
            while(result.next()) {

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    array.add(result.getString(i));
                }
            }
            return array;
        } catch (Exception e) {
            System.out.println("Exception... "+e);
        }
        return null;
    }

    public static ArrayList<String> selectFROM( ArrayList<String> columns , ArrayList<String> tables, String key1, String key2) throws Exception {

        try (Connection con = getConnection()){

            String colNames = parseList(columns);
            String tableNames = parseList(tables);

            String SQL = "SELECT " + colNames + " FROM " + tableNames + " WHERE " + key1 + " = " + key2 + ";";

            PreparedStatement statement = con.prepareStatement(SQL);
            ResultSet result = statement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();

            ArrayList<String> array = new ArrayList<>();
            while(result.next()) {

                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    array.add(result.getString(i));
                }
            }
            return array;
        } catch (Exception e) {
            System.out.println("Exception... "+e);
        }

        return null;
    }

    public static void insertINTO(String table, ArrayList<String> columns, ArrayList<String> values) throws Exception {

        try (Connection con = getConnection()){

            String SQL = "INSERT INTO `" + table + "` (" + parseList(columns) + ") VALUES (" + parseList(values) + ");";

            PreparedStatement insert = con.prepareStatement(SQL);

            insert.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception caught... "+e);
        }
    }

    public static void deleteFROM(String table, String condition) throws Exception {

        try (Connection con = getConnection()){

            String SQL = "DELETE FROM " + table + " WHERE " + condition;

            PreparedStatement delete = con.prepareStatement(SQL);
            delete.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception thrown... "+e);
        }
    }

    public static void update(String table, String columns, String condition){

        try (Connection con = getConnection()){

            String SQL = "UPDATE " + table + " SET " + columns + " WHERE " + condition + ";";

            PreparedStatement update = con.prepareStatement(SQL);
            update.executeUpdate();

        } catch (Exception e) {
            System.out.println("Exception thrown... " + e);
        }

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

    private static String parseList(ArrayList<String> data){
        return String.valueOf(data).replaceAll("\\[|\\]","");
    }
}//end class