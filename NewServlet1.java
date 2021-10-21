import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author DELL
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet1 extends HttpServlet {

    private Connection con;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DriverManager.registerDriver(new OracleDriver());    //This is for loading the odbc driver
            System.out.println("Driver loaded Successfully");  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hemanth","6569");
            out.print("<h1>REGISTERED</h1>");
            String name=request.getParameter("un");
            String pswd=request.getParameter("pwd");
            PreparedStatement ps=con.prepareStatement("insert into regi values(?,?)");
            ps.setString(1,name);
            ps.setString(2,pswd);
            out.print(ps.executeUpdate());
           } catch (SQLException e) {
            System.out.println("Some problem in connection");
            // TODO Auto-generated catch block


           } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }



