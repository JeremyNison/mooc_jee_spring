import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;


@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		if ( login == null || password == null ) throw new ServletException("no login/password");
		boolean succeed = "admin@foo.com".equals(login) && "admin".equals(password);
		
		if(succeed){ 
			req.getSession().setAttribute("logged", (boolean) true);
			//req.getRequestDispatcher("welcome.jsp").forward(req,resp);
			resp.sendRedirect("welcome.jsp");
		} else{
			req.setAttribute("errorMessage", "Error : Wrong username or password");
			req.getRequestDispatcher("auth.jsp").forward(req,resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		String logout = (String) req.getParameter("logout");

		if(logout != null){
			req.getSession().setAttribute("logged", (boolean) false);
			req.setAttribute("logoutMessage", "Sucessfully logged out.");
			req.getRequestDispatcher("auth.jsp").forward(req,resp);
		}
		resp.sendError(500);
	}

}