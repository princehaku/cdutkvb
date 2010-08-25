package net._3haku.buildkvb;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WishH
 */
public class Builder extends HttpServlet {

    static String tmpPath="E:/JAVA/KVB/BuildKVB/tmp/";
    static String zipPath="E:/JAVA/KVB/BuildKVB/exec/";
    static String javaPath="E:/JAVA/KVB/BuildKVB/exec/";
    static String sourcePath="E:/JAVA/KVB/BuildKVB/S/";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key=MD5.getMD5(((new Date()).getTime()+"").getBytes());
        //创建key的java文件
        GeneralKey gk=new GeneralKey(key);
        gk.run();
        //创建key的class文件
        Combiner cb=new Combiner(key);
        cb.run();
        //创建jar文件
        JarBuilder jb=new JarBuilder();
        jb.compress();
        response.getOutputStream().print(gk.getFileContent());
       
    }



// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/** 
 * Handles the HTTP <code>GET</code> method.
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
    protected

void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        
} 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

        @Override
        protected void

doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);


}

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */

        @Override
        public String

getServletInfo() {
        return "Short description";

}// </editor-fold>

}
