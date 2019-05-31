package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
 
public class JavaDB {
 
    public static void main(String[] args) {
        // Nazwa bazy
        String baza = "VehicleManager";
        // Wywo�anie metody polacz, kt�ra zwraca obiekt typu Connection
        Connection polaczenie = polacz(baza);   
    }
    
    /**
     * Metoda odpowiedzialna za po��czenie z baz�
     * je�li bazy nie ma to zostaje utworzona
     */
    public static Connection polacz(String baza) {
        Connection polaczenie = null;
        try {
            // Wskazanie jaki rodzaj bazy danych b�dzie wykorzystany, tu sqlite
            Class.forName("org.sqlite.JDBC");           
            // Po��czenie, wskazujemy rodzaj bazy i jej nazw�
            polaczenie = DriverManager.getConnection("jdbc:sqlite:"+baza+".db");
            System.out.println("Po��czy�em si� z baz� "+baza);
        } catch (Exception e) {
            System.err.println("B��d w po��czeniu z baz�: \n" + e.getMessage());
            return null;
        }
        return polaczenie;
    }
}