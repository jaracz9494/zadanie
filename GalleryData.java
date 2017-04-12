/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class GalleryData {
    
    private static String NazwaGalerii;

    public static String getNazwaGalerii() {
        return NazwaGalerii;
    }

    public void setNazwaGalerii(String NazwaGalerii) {
        this.NazwaGalerii = NazwaGalerii;
    }
}
