/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DATAconnect.FileUploadDAO;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class FileUpload extends GalleryData{
    private Part file1;


    public Part getFile1() {       
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    
    public String upload(String nazwa) throws IOException{
        if (file1 == null){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("Blad", new FacesMessage("Nie wybrano obrazka"));
            return null;
        } else {
        String filename=getFilename(file1);
        
        file1.write("C:\\Users\\Dominik\\Desktop\\Server\\Tomcat\\webapps\\Baza\\" + nazwa + "\\" + getNazwaGalerii() + "\\" + filename);
        
        FileUploadDAO data = new FileUploadDAO();
        data.wpisywanie(nazwa, getNazwaGalerii(), filename);
        
        return "success";
        }
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
