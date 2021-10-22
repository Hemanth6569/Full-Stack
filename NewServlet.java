import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleDriver;
public class NewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DriverManager.registerDriver(new OracleDriver());    //This is for loading the odbc driver
            System.out.println("Driver loaded Successfully");  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hemanth","6569");
            String uname=request.getParameter("un");
            String upswd=request.getParameter("pwd");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from regi where name=('"+uname+"') and pswd=('"+upswd+"')");
            if (rs.next()){
                out.print("<h1>Login Successful</h1>");
            }
            else{
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");
                out.println("<script>");
                out.println("alert('Login Failed')");
                out.println("</script>");

  rd.include(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Some problem in connection");
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
