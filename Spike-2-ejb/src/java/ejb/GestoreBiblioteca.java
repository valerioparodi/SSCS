/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author valerioparodi
 */
@Stateless
public class GestoreBiblioteca implements GestoreBibliotecaLocal {

    @EJB
    private LibroFacadeLocal libroFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method
    @Override
    public void aggiungiLibro(String titolo, String autore) {
        Libro l = new Libro();
        l.setTitolo(titolo);
        l.setAutore(autore);
        libroFacade.create(l);
    }

    @Override
    public List<Libro> getLibri() {
        List<Libro> listaLibri = libroFacade.findAll();
        return listaLibri;
    }

    @Override
    public void eliminaLibro(String titolo, String autore) {
        Libro l = new Libro();
        l.setTitolo(titolo);
        l.setAutore(autore);
        Libro libroElim = libroFacade.find(l);
        libroFacade.remove(libroElim);
    }

}
