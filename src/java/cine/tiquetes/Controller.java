/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.tiquetes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import cine.logic.Compra;
import cine.logic.Proyeccion;
import cine.logic.Tiquete;
import com.itextpdf.text.DocumentException;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "tiquetesController", urlPatterns = {"/presentation/Tiquetes/showRecord", "/presentation/Tiquetes/pdf"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("model", new Model());

        String viewUrl = "";
        switch (request.getServletPath()) {
            case "/presentation/Tiquetes/showRecord":
                viewUrl = this.showRecord(request);

                break;
            case "/presentation/Tiquetes/pdf":
                viewUrl = this.print(request, response);
                break;

        }
        request.getRequestDispatcher(viewUrl).forward(request, response);
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

    public String showRecord(HttpServletRequest request) {
        return this.showActionRecords(request);
    }

    public String showActionRecords(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        cine.logic.Service service = cine.logic.Service.instance();

        List<Compra> compras = service.getListaCompras();

        model.setCompras(compras);

        return "/presentation/Tiquetes/View.jsp";
    }

    private String print(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Model model = (Model) request.getAttribute("model");
        
        cine.logic.Service service = cine.logic.Service.instance();
        List<Compra> compras = service.getListaCompras() ;
        int id = Integer.parseInt(request.getParameter("numeroCompra"));
        System.out.println("El id que se obtuvo es:" +id);
        Compra compraSelec = new Compra();
        for (Compra c : compras) {
            if (c.getId() == id) {
                compraSelec = c;
            }

        }
        Proyeccion proyec = null;
        try {
            proyec = service.getProyeccionesPorId(compraSelec.getidProyeccion());
        } catch (Exception ex) {
        }
        List<Tiquete> tiquetes = service.getListaTiquetesCompra(compraSelec.getId());

        try {

            Document document = new Document();

            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();


            for (Tiquete t : tiquetes) {
                Paragraph  titulo = new Paragraph("=========================================" ,
                        FontFactory.getFont("arial",
                                14,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
                document.add(titulo);
                
                titulo = new Paragraph("                        GNC        ",
                        FontFactory.getFont("arial",
                                22,
                                Font.BOLD,
                                BaseColor.BLUE
                        )
                );
                document.add(titulo);
                                
                titulo = new Paragraph(" ",
                        FontFactory.getFont("Times New Roman",
                                14,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
                document.add(titulo);
                
                
                
               titulo = new Paragraph("Cedula : " + compraSelec.getCedula()+"                      Nombre : " + compraSelec.getNombre(),
                        FontFactory.getFont("Times New Roman",
                                12,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );

                document.add(titulo);               
                titulo = new Paragraph("Codigo de tiquete : " + t.getId()+ "       Precio : " + proyec.getPelicula().getPrecio(),
                        FontFactory.getFont("Times New Roman",
                                12,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
//                
                document.add(titulo);            
                
                titulo = new Paragraph("Pelicula : " + proyec.getPelicula().getNombre(),
                        FontFactory.getFont("Times New Roman",
                                18,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
//                
                document.add(titulo);
                


                titulo = new Paragraph("Numero de asiento : " + t.getNumeroAsiento()+"   Numero de sala : " +proyec.getSala().getNumeroSala(),
                        FontFactory.getFont("Times New Roman",
                                16,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
                document.add(titulo);
                                
                
                titulo = new Paragraph("Fecha : " + proyec.getFecha()+"                   Hora : " +proyec.getHora(),
                        FontFactory.getFont("Times New Roman",
                                16,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
                
                
                document.add(titulo);
                
                
                titulo = new Paragraph(" ",
                        FontFactory.getFont("Times New Roman",
                                14,
                                Font.BOLD,
                                BaseColor.BLACK
                        )
                );
                document.add(titulo);

            }

            document.close();

            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline");
            return null;

        } catch (DocumentException | IOException e) {
            return "/presentation/Tiquetes/View.jsp";
        }

    }
    
    
    

}
