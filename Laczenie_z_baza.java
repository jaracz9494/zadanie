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
    private String adres="../../../Baza/";
    private static final String Sterownik = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/baza";
    private static final String BLogin = "user1";
    private static final String BPass = "zaq1";
        

    
    public String getAdres() {
        //String a=adres;
        //adres="../../../Baza/";
        System.out.println(adres);                 
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    
    public boolean sprawdzenieLP(String login, String haslo) {
        try {
            Class.forName(Sterownik);
            connection = DriverManager.getConnection(URL, BLogin, BPass);
            statement = connection.createStatement(); 
            
            ResultSet result=statement.executeQuery("select LOGIN, PASSWORD from USERS");
            ResultSetMetaData metadata = result.getMetaData();
            
            String l;
            String p;
            
            while(result.next()) {
                    l=result.getString(1);
                    p=result.getString(2);
                    //System.out.print(l+" "+p);
                    
                    if (l.equals(login) && p.equals(haslo)) {
                        connection.close();
                        return true;
                    } 

            }
            
            connection.close();
            return false;
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
            return false;
        }
    }
    
    public String wywolanie_bazy(String nazwa){
        try {
            adres="../../../Baza/";
            Class.forName(Sterownik);
            connection = DriverManager.getConnection(URL, BLogin, BPass);
            statement = connection.createStatement(); 
            
            ResultSet result=statement.executeQuery("select NAME from pictures WHERE ID like '" + nazwa +"'");
            ResultSetMetaData metadata = result.getMetaData();
            
            System.out.println(metadata.getColumnCount());
            int ilosckolumn = metadata.getColumnCount();
            
            while(result.next()) {                
                adres = adres+nazwa+result.getString(1);
                System.out.println(adres);                 
            }
            
            connection.close();
            return adres;
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
            return null;
        }
    }
}
