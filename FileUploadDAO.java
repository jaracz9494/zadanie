/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATAconnect;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class FileUploadDAO extends DAO {
    
    
    public void wpisywanie(String id, String folder, String name){
        try{
            pobieranie_sterownikow();
            
            statement.executeUpdate("INSERT INTO pictures VALUES ('" + id + "', '" + folder + "', '" + name + "')");          
            
            connection.close();
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
    
    public boolean sprIstnienia (String id, String folder, String name) {
        try{
            pobieranie_sterownikow();
            
            result=statement.executeQuery("select ID, FOLDER, NAME from pictures WHERE ID like '" + id +"' and FOLDER like '" + folder +"' and NAME like '" + name +"'");
            
            if (result.next()) {
                System.out.println("nazwa: " + result.getString(3) + " jest juz w folderze " + result.getString(2));
                connection.close();
                return true;
            } else {
          
                connection.close();
                return false;
            
            }
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
            return true;
        }
    }
}
