/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA_connect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class Laczenie_z_baza {
    
    private java.sql.Connection connection;
    private Statement statement;
    private String hue="huee";
    
    public void cos() {
        System.out.println(hue);
    }
    
    public void wywoalnie_bazy(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/baza", "user1", "zaq1");
            statement = connection.createStatement(); 
            
            ResultSet result=statement.executeQuery("select Nazwisko, ID from studenty");
            ResultSetMetaData metadata = result.getMetaData();
            
            System.out.println(metadata.getColumnCount());
            int ilosckolumn = metadata.getColumnCount();
            
            while(result.next()) {
                for (int i=1; i<=ilosckolumn; i++) {
                    System.out.print(result.getString(i) + " ");                
                }
                System.out.println();
            }
            
            connection.close();
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
}
