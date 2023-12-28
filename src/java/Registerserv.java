import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registerserv")
public class Registerserv extends HttpServlet {
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    ResultSet rs;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("connecteds");
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjdb", "root", "gkuzh#n3p");

            String email = request.getParameter("email");
            if (email == null || email.isEmpty()) {
                out.println("Email is required");
                return;
            }

            String password = request.getParameter("password");
            if (password == null || password.isEmpty()) {
                out.println("Password is required");
                return;
            }

          pst = con.prepareStatement("INSERT INTO register (email_address, password) VALUES (?, ?)");
pst.setString(1, email);
pst.setString(2, password);

            try {
    // existing code here
    pst.executeUpdate();
    out.println("Data inserted successfully");
   
     response.sendRedirect("/Javaproject/pages/login.html");
    
    out.flush();
} catch (Exception ex) {
   out.println(ex);
}


            pst1 = con.prepareStatement("SELECT MAX(id) FROM register");
            rs = pst1.executeQuery();
            rs.next();
            int regno = rs.getInt(1);
            out.println("Your account is created");
            out.println("Your registration ID is " + regno);
        } catch (ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(Registerserv.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst1 != null)
                    pst1.close();
                if (pst != null)
                    pst.close();
                if (con != null)
                    con.close();
                System.out.println("Fine");
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(Registerserv.class.getName()).log(java.util.logging.Level.SEVERE,
                        null, ex);
            }
        }
    }
}
