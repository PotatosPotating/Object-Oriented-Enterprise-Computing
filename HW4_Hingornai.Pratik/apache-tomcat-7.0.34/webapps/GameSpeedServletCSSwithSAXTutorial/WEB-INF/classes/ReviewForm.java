import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReviewForm")

public class ReviewForm extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
		}
		response.setContentType("text/html");
		Utilities utility = new Utilities(request, pw);
		String productName = request.getParameter("name");
		String productType = request.getParameter("type");
		String rating = request.getParameter("rating");
		String date = request.getParameter("date");
		String review = request.getParameter("reviewtext");
		String zipcode = request.getParameter("zipcode");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String age = request.getParameter("age");
		String occupation = request.getParameter("occupation");
		String maker = request.getParameter("maker");
		String gender = request.getParameter("gender");
		String rebate = request.getParameter("rebate");
		String retailername = request.getParameter("retailername");
		String onsale = request.getParameter("onsale");
		String username = request.getParameter("username");
		MongoDBDataStoreUtilities.insertReview(productName,username, productType, rating, date, review,state,city,age,occupation,
				maker,gender,rebate,retailername,onsale);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.println("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.println("<a style='font-size: 24px;'>Review Form</a>");
		pw.println("</h2><div class='entry'>");
		pw.print("<h4>Review for "+productName+" stored. Thank you!</h4>");
		utility.printHtml("Footer.html");
	}
}
