/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author valerioparodi
 */
@Local
public interface GestoreBibliotecaLocal {

    void aggiungiLibro(String titolo, String autore);

    List<Libro> getLibri();

    void eliminaLibro(String titolo, String autore);
    
}
