/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DATAconnect.DAO;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dominik
 */
@ManagedBean
@SessionScoped
//WAZNE! w index.xhtml musisz wpisywac Logowanie z pierwszej litery jako małej! (czyli Logowanie = logowanie)
public class Logowanie implements Serializable {
    
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
        DAO baza = new DAO();
        if (baza.sprawdzenieLP(nazwa, haslo)) { 
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", nazwa);
            return "views/mainView?faces-redirect=true";
            
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("test", new FacesMessage("Błąd logowania"));
            return null;
            //document.getElementById('test').innerHTML = 'blad'
        }
    }
    
    public String logout() {
	HttpSession session = SessionUtils.getSession();
	session.invalidate();
	return "/index?faces-redirect=true";
    }
    
    
}
