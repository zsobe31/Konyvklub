/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author zsobe31
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        
        try (
            PrintWriter out = response.getWriter()) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("KonyvklubPU");
            EntityManager em = emf.createEntityManager();
            
            if(request.getParameter("task").equals("konvyLista")){
                List<Konyv> elemek = Konyv.getAllKonyvById(em);
                JSONArray valasz = new JSONArray();
                for(Konyv e : elemek){
                    JSONObject o = new JSONObject();
                    o.put("id", e.getId());
                    o.put("cim", e.getCim());
                    o.put("szerzo", e.getSzerzo());
                    o.put("mufaj", e.getMufaj());
                    valasz.put(o);
                }
                out.print(valasz.toString());
            }
            
            if(request.getParameter("task").equals("ujKonyv")){
                String cimF = request.getParameter("cim");
                String szerzoF = request.getParameter("szerzo");
                String mufajF = request.getParameter("mufaj");
                
                Konyv.addNewKonyv(em, cimF, szerzoF, mufajF);
                out.print("minden ok!");
                
            }
            
            if(request.getParameter("task").equals("modKonyv")){
                int idM = Integer.parseInt(request.getParameter("id"));
                String cimM = request.getParameter("cim");
                String szerzoM = request.getParameter("szerzo");
                String mufajM = request.getParameter("mufaj");
                
                
                Konyv.updateKonyv(em, cimM, szerzoM, mufajM, idM);
                out.print("OK!");
            }
           
            if(request.getParameter("task").equals("torolKonyv")){
                int idT = Integer.parseInt(request.getParameter("id"));
                
                Konyv.deleteKonyv(em, idT);
                out.print("OK!");
            }
            
            if(request.getParameter("task").equals("login")){
                String nevL = request.getParameter("nev");
                String jelszoL = request.getParameter("jelszo");
                Klubtag k = Klubtag.login(em, nevL, jelszoL);
                if(k != null){
                    request.getSession().setAttribute("a", k);
                    JSONObject j = new JSONObject();
                    j.put("result", "Üdvözlünk kedves " + k.getNev());
                    j.put("success", "1");
                    out.print(j.toString());
                }
                else{
                    JSONObject j = new JSONObject();
                    j.put("result", "Hibás felhasználónév vagy jelszó!");
                    j.put("success", "0");
                    out.print(j.toString());
                }
            }
            
            if(request.getParameter("task").equals("ujTag")){
                String nevF = request.getParameter("nev");
                String jelszoF = request.getParameter("jelszo");
                
                Klubtag.addNewTag(em, nevF, jelszoF);
                out.print("OK!");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
