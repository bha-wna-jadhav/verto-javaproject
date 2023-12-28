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

@WebServlet("/Loginserv")
public class Loginserv extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection con;
        String n = request.getParameter("email");
        String p = request.getParameter("password");
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjdb", "root", "gkuzh#n3p");
            PreparedStatement pstmt = con.prepareStatement("select * from register where email_address=? and password=?");
            pstmt.setString(1, n);
            pstmt.setString(2, p);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //if username and password true than go to menu.html file
                response.sendRedirect("/Javaproject/pages/canteen.html");
            } else {
                out.println("wrong username and password...");
            }
            con.close();

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
