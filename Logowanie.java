/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dominik
 */
@ManagedBean
@SessionScoped
//WAZNE! w index.xhtml musisz wpisywac Logowanie z pierwszej litery jako małej! (czyli Logowanie = logowanie)
public class Logowanie {
    
    private String nazwa="Stud";
    private String haslo="";

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
    
    //public Logowanie(){       
    //}

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public String sprawdz() {
        if (nazwa.equals("admin") && haslo.equals("pass")) {
            return "views/mainView";
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("test", new FacesMessage("Błąd logowania"));
            return null;
            //document.getElementById('test').innerHTML = 'blad'
        }
    }
    
    
}
