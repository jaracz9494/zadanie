/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DATAconnect.FileDeleteDAO;
import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class FileDelete extends GalleryData{
    
    public String delete(String nazwa, String filename) throws IOException {
        System.out.println(filename);
        File file = new File("C:\\Users\\Dominik\\Desktop\\Server\\Tomcat\\webapps\\Baza\\" + nazwa + "\\" + getNazwaGalerii() + "\\" + filename);
        
        if(file.delete()){
            System.out.println(file.getName() + " is deleted!");
        }else{
            System.out.println("Delete operation is failed.");
        }
        
        FileDeleteDAO del = new FileDeleteDAO();
        del.usuwanie(nazwa, getNazwaGalerii(), filename);
        
        return "success";
    }
}
