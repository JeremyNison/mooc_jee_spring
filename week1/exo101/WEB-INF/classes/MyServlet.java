import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dist")
public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		Writer out = resp.getWriter();
		out.write("<body>");
		out.write("<form method=POST>");
		out.write("p1lat : <input name=\"p1lat\"> p1lng : <input name=\"p1lng\"></br>");
		out.write("p2lat : <input name=\"p2lat\"> p2lng : <input name=\"p2lng\"></br>");
		out.write("<input type=SUBMIT value=\"Envoi\"></form>");
		out.write("</body>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		final int R = 6371; //Rayon de la terre en km

		double p1lat = Double.parseDouble((String) req.getParameter("p1lat"));
		double p1lng = Double.parseDouble((String) req.getParameter("p1lng"));
		double p2lat = Double.parseDouble((String) req.getParameter("p2lat"));
		double p2lng = Double.parseDouble((String) req.getParameter("p2lng"));

		double deltaLat = (p2lat - p1lat) * Math.PI / 180; //Distance latitiude en radians
		double deltaLng = (p2lng - p1lng) * Math.PI / 180; //Distance longitude en radians 

		double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
		Math.cos(p1lat * (Math.PI / 180)) * Math.cos(p2lat * (Math.PI / 180)) *
		Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		double distance = R * c; // Disance entre les deux points en km

		Writer out = resp.getWriter();
		out.write("<body>");
		out.write("Distance : "+(Math.round(distance * 10.0)/10.0));
		out.write("</body>");
	}
}