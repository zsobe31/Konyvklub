/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zsobe31
 */
@Entity
@Table(name = "klubtag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klubtag.findAll", query = "SELECT k FROM Klubtag k")
    , @NamedQuery(name = "Klubtag.findById", query = "SELECT k FROM Klubtag k WHERE k.id = :id")
    , @NamedQuery(name = "Klubtag.findByNev", query = "SELECT k FROM Klubtag k WHERE k.nev = :nev")
    , @NamedQuery(name = "Klubtag.findByJelszo", query = "SELECT k FROM Klubtag k WHERE k.jelszo = :jelszo")})
public class Klubtag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @Column(name = "jelszo")
    private String jelszo;

    public Klubtag() {
    }

    public Klubtag(Integer id) {
        this.id = id;
    }

    public Klubtag(Integer id, String nev, String jelszo) {
        this.id = id;
        this.nev = nev;
        this.jelszo = jelszo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }
    
    // az adatok listázása
    public static List<Klubtag> getAllTagByNev(EntityManager em){
        List<Klubtag> elemek = new ArrayList();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllTagByNev");
        List<Object[]> lista = spq.getResultList();
        for(Object[] elem : lista){
            Klubtag e = new Klubtag();
            e = em.find(Klubtag.class, elem[0]);
            elemek.add(e);
        }
        return elemek;
    }
    
    // felvitel
    public static void 	addNewTag(EntityManager em, String nev, String jelszo){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewTag");
        em.getTransaction().begin();
        spq.registerStoredProcedureParameter("nevIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("jelszoIN", String.class, ParameterMode.IN);
        spq.setParameter("nevIN", nev);    
        spq.setParameter("jelszoIN", jelszo);    
        spq.execute();
        em.getTransaction().commit();
    }
    
    // törlés
    public static void deleteTag(EntityManager em, int id){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteTag");
        em.getTransaction().begin();
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);    
        spq.execute();
        em.getTransaction().commit();
    }
    
    // módosít
    public static Klubtag findByIdTag(EntityManager em, int id){
        List<Klubtag> elemek = Klubtag.getAllTagByNev(em);
        Klubtag e = em.find(Klubtag.class, id);
        return e;
    }
    public static void updateTag(EntityManager em, String nev, String jelszo, int id){
        Klubtag e = Klubtag.findByIdTag(em, id);
        em.getTransaction().begin();
        e.setNev(nev);
        e.setJelszo(jelszo);
        em.getTransaction().commit();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klubtag)) {
            return false;
        }
        Klubtag other = (Klubtag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Klubtag[ id=" + id + " ]";
    }
    
}
