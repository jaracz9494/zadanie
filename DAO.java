/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATAconnect;

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
public class DAO {
    
    public java.sql.Connection connection;
    public Statement statement;
    public ResultSet result;

    private static final String Sterownik = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/baza";
    private static final String BLogin = "user1";
    private static final String BPass = "zaq1";    
        
   
    
    public void pobieranie_sterownikow() {
        try {
            Class.forName(Sterownik);
            connection = DriverManager.getConnection(URL, BLogin, BPass);
            statement = connection.createStatement();
            
                    
        } catch(Exception ex){
            System.out.println("blad pobierania sterownikow, adresu, loginu lub hasla" + ex.getMessage());
        }
    }
    
    public boolean sprawdzenieLP(String login, String haslo) {
        try {
            pobieranie_sterownikow(); 
            
            result=statement.executeQuery("select LOGIN, PASSWORD from USERS");
            
            String l;
            String p;
            
            while(result.next()) {
                    l=result.getString(1);
                    p=result.getString(2);
                    
                    if (l.equals(login) && p.equals(haslo)) {
                        connection.close();
                        return true;
                    } 

            }
            
            connection.close();
            return false;
            
        } catch(Exception ex){
            System.out.println("blad sprawdzaniaLP " + ex.getMessage());
            return false;
        }
    }   
    
    
    public boolean sprawdzRoleName(String login) {
        
        try {
            pobieranie_sterownikow(); 
            
            result=statement.executeQuery("select LOGIN, ROLE_NAME from ROLES where LOGIN like '" + login +"' and ROLE_NAME like 'admins'");
            
            while(result.next()) {               
                connection.close();
                return true;
            }
            
            connection.close();
            return false;
            
        } catch(Exception ex){
            System.out.println("blad pobierania roli " + ex.getMessage());
            return false;
        }
    }
    
}
