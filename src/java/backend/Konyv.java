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
@Table(name = "konyv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konyv.findAll", query = "SELECT k FROM Konyv k")
    , @NamedQuery(name = "Konyv.findById", query = "SELECT k FROM Konyv k WHERE k.id = :id")
    , @NamedQuery(name = "Konyv.findByCim", query = "SELECT k FROM Konyv k WHERE k.cim = :cim")
    , @NamedQuery(name = "Konyv.findBySzerzo", query = "SELECT k FROM Konyv k WHERE k.szerzo = :szerzo")
    , @NamedQuery(name = "Konyv.findByMufaj", query = "SELECT k FROM Konyv k WHERE k.mufaj = :mufaj")
    , @NamedQuery(name = "Konyv.findByWebcim", query = "SELECT k FROM Konyv k WHERE k.webcim = :webcim")})
public class Konyv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cim")
    private String cim;
    @Basic(optional = false)
    @Column(name = "szerzo")
    private String szerzo;
    @Basic(optional = false)
    @Column(name = "mufaj")
    private String mufaj;
    @Basic(optional = false)
    @Column(name = "webcim")
    private String webcim;

    public Konyv() {
    }

    public Konyv(Integer id) {
        this.id = id;
    }

    public Konyv(Integer id, String cim, String szerzo, String mufaj, String webcim) {
        this.id = id;
        this.cim = cim;
        this.szerzo = szerzo;
        this.mufaj = mufaj;
        this.webcim = webcim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public String getMufaj() {
        return mufaj;
    }

    public void setMufaj(String mufaj) {
        this.mufaj = mufaj;
    }

    public String getWebcim() {
        return webcim;
    }

    public void setWebcim(String webcim) {
        this.webcim = webcim;
    }
    
    // az adatok listázása
    public static List<Konyv> getAllKonyvById(EntityManager em){
        List<Konyv> elemek = new ArrayList();
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllKonyvById");
        List<Object[]> lista = spq.getResultList();
        for(Object[] elem : lista){
            Konyv e = new Konyv();
            e = em.find(Konyv.class, elem[0]);
            elemek.add(e);
        }
        return elemek;
    }
    
    // felvitel
//    public static void addNewKonyv(EntityManager em, String cim, String szerzo, String mufaj){
//        StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewKonyv");
//        em.getTransaction().begin();
//        spq.registerStoredProcedureParameter("cimIN", String.class, ParameterMode.IN);
//        spq.registerStoredProcedureParameter("szerzoIN", String.class, ParameterMode.IN);
//        spq.registerStoredProcedureParameter("mufajIN", String.class, ParameterMode.IN);
//        spq.setParameter("cimIN", cim);    
//        spq.setParameter("szerzoIN", szerzo);    
//        spq.setParameter("mufajIN", mufaj);
//        spq.execute();
//        em.getTransaction().commit();
//    }
    
    public static Konyv addNewKonyv(EntityManager em, String cim, String szerzo, String mufaj, String webcim){
        Konyv k = null;
        StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewKonyv");
        spq.registerStoredProcedureParameter("cimIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("szerzoIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("mufajIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("webcimIN", String.class, ParameterMode.IN);
        spq.setParameter("cimIN", cim);
        spq.setParameter("szerzoIN", szerzo);
        spq.setParameter("mufajIN", mufaj);
        spq.setParameter("webcimIN", webcim);
        spq.execute();
        return k;
    }
    
    // törlés
    public static void deleteKonyv(EntityManager em, int id){
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteKonyv");
        em.getTransaction().begin();
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("idIN", id);    
        spq.execute();
        em.getTransaction().commit();
    }
    
    // módosít
    public static Konyv findByIdKonyv(EntityManager em, int id){
        List<Konyv> elemek = Konyv.getAllKonyvById(em);
        Konyv e = em.find(Konyv.class, id);
        return e;
    }
    public static void updateKonyv(EntityManager em, String cim, String szerzo, String mufaj, String webcim, int id){
        Konyv e = Konyv.findByIdKonyv(em, id);
        em.getTransaction().begin();
        e.setCim(cim);
        e.setSzerzo(szerzo);
        e.setMufaj(mufaj);
        e.setWebcim(webcim);
        em.getTransaction().commit();
    }
    
//    public static Konyv updateKonyv(EntityManager em, String cim, String szerzo, String mufaj, int id){
//        Konyv k = null;
//        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateKonyv");
//        spq.registerStoredProcedureParameter("cimIN", String.class, ParameterMode.IN);
//        spq.registerStoredProcedureParameter("szerzoIN", String.class, ParameterMode.IN);
//        spq.registerStoredProcedureParameter("mufajIN", String.class, ParameterMode.IN);
//        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
//        spq.setParameter("cimIN", cim);
//        spq.setParameter("szerzoIN", szerzo);
//        spq.setParameter("mufajIN", mufaj);
//        spq.setParameter("idIN", id);
//        spq.execute();
//        return k;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konyv)) {
            return false;
        }
        Konyv other = (Konyv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "backend.Konyv[ id=" + id + " ]";
    }
    
}
