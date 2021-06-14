
package servlet;

import classes.Price;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CalculaParcela", urlPatterns = {"/CalculaParcela"})
public class CalculaParcela extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //verificar a sessao
        HttpSession sessao=request.getSession(false);
        if(sessao==null || sessao.getAttribute("login")==null)
        {
            response.sendRedirect("index.html");
        }
            
        double valor=0,juros=0,parcela=-1;
        int meses=0;
        try
        {
            valor=Double.parseDouble(request.getParameter("valor"));
            meses=Integer.parseInt(request.getParameter("meses"));
            juros=Double.parseDouble(request.getParameter("juros"));
            parcela=Price.calcular(valor,meses,juros);
        }
        catch(Exception e)
        {
            //response.sendRedirect("index.html");
            
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculaParcela</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<form action=\"CalculaParcela\">");
            out.println("<p><label>Valor: </label><input type=\"text\" name=\"valor\" value='"+valor+"' size=\"40\" placeholder=\"Informe o valor a ser emprestado\" /></p>");
            out.println("<p><label>Qtos meses: </label><input type=\"text\" name=\"meses\" value='"+meses+"' size=\"40\" placeholder=\"Informe a quantidade de meses\"/></p>");
            out.println("<p><label>Juros: </label><input type=\"text\" name=\"juros\" value='"+juros+"' size=\"40\" placeholder=\"Informe os juros\"/></p>");
            out.println("<p><input type=\"submit\" value=\"Calcular\" /></p>");
            out.println("</form>");
            out.println("</div>");
            if(parcela!=-1)
            {
                out.println("<hr>");
                out.println("<h1>Parcela do empréstimo R$" + String.format("%10.2f",Price.calcular(valor,meses,juros))+ "</h1>");
                out.println("</body>");
                out.println("</html>");
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
