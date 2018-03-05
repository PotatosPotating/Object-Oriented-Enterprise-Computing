import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) {
		response.setContentType("text/html");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
		}

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'> Trending</a>");
		pw.print("</h2><div class='entry'>");
		
		pw.print("<table><tr><h3> Most liked Products </h3></tr>");
		try {
			MongoDBDataStoreUtilities.mostLikedProducts(pw);
		} catch (MongoException | IOException e) {
		}
		pw.print("</table>");
						
		pw.print("<table><tr><h3> Most Sold Products regardless of rating </h3></tr>");
		try {
			MongoDBDataStoreUtilities.topFiveProducts(pw);
		} catch (MongoException e) {

		} catch (IOException e) {
		}
		pw.print("</table>");
		
		pw.print("<table><tr><h3> Most Liked Products based on Zip code </h3></tr>");
		try {
			MongoDBDataStoreUtilities.zipCodes(pw);
		} catch (MongoException e) {

		} catch (IOException e) {

		}
		pw.print("</table>");
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	}

	}