
package backend;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class teszt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KonyvklubPU");
        EntityManager em = emf.createEntityManager();

//        Konyv.addNewKonyv(em, "Kincs", "én", "gyerek");
//        Konyv.addNewKonyv(em, "Sziget", "Gábor", "krimi");

//        Konyv.updateKonyv(em, "Bogyó és babóca", "Kiss Ági", "gyerek", 8);

//          Konyv.deleteKonyv(em, 11);

        Konyv.addNewKonyv(em, "Sakk", "Áron", "oktatás");

    }
    
}
