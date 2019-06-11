package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CORBA.PUBLIC_MEMBER;
 
public class JavaDB {
 
	/**
	 * Nazwa bazy
	 */
    public final static String baseName = "VehicleManager";
    
    /**
     * konstruktor bezparametrowy
     */
    public JavaDB() {  }
    
    /**
     * Metoda odpowiedzialna za po��czenie z baz�
     * je�li bazy nie ma to zostaje utworzona
     * zwraca obiekt po��czenia
     */
    public static Connection connectToDB() {
        Connection connection = null;
        try {
            /**
             * Wskazanie jaki rodzaj bazy danych b�dzie wykorzystany, tu sqlite
             */
            Class.forName("org.sqlite.JDBC");           
            /**
             * Po��czenie, wskazujemy rodzaj bazy i jej nazw�
             */
            connection = DriverManager.getConnection("jdbc:sqlite:"+baseName+".db");
            System.out.println("Po��czy�em si� z baz� "+baseName);
        } catch (Exception e) {
            System.err.println("B��d w po��czeniu z baz�: \n" + e.getMessage());
            return null;
        }
        return connection;
    }
    /**
     * metoda usuwaj�ca wiersz o danym Id z danej tabeli
     * @param vehicleId - ID pojazdu
     * @param table - tablica
     */
    public static Boolean delete(int vehicleId, String table) {
        Connection connection = null;
        Statement stat = null;
        /**
         * Polecenie wyszukania
         */
        String searchSQL = "DELETE FROM "+table + " WHERE  Id =='" + vehicleId + "';";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + baseName + ".db");
            stat = connection.createStatement();
            stat.executeQuery(searchSQL);
            //stat.close();
            //connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Nie mog� wykona� akcji " + e.getMessage());
            return true;
        }

    }
}