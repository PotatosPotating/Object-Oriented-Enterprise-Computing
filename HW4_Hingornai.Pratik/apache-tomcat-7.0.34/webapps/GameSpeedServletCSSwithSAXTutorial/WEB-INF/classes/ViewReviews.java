import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@WebServlet("/ViewReviews")

public class ViewReviews extends HttpServlet{
	boolean flag=false;
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		Utilities utility = new Utilities(request, pw);
		String productName = request.getParameter("name");
		System.out.println(productName);
		HashMap<String, ArrayList<Review>> reviewHashmap=new HashMap<String, ArrayList<Review>>(); 
		reviewHashmap = MongoDBDataStoreUtilities.selectReview();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<table>");
		for(String a: reviewHashmap.keySet())
		{
			for(Review r: reviewHashmap.get(a))
			{
//				if(r.getUsername().equals(productName)){
				pw.print("<tr><td></td></tr><tr><td>User Id: </td><td>" +
						r.getUsername() + "</td></tr>"
						+ "<tr><td>Rating:</td><td>" + r.getReviewRating() + "</td></tr>"
						+ "<tr><td>Date:</td><td>" + r.getReviewDate() + "</td></tr>"
						+ "<tr><td>Review Text:</td><td>" + r.getReviewText()+"</td><tr>"
						+ "<tr><td>RetailerCity:</td><td>" + r.getCity()+"</td><tr>"
						+ "<tr><td>RetailerState:</td><td>" + r.getState()+"</td><tr>"
						+ "<tr><td>Zipcode:</td><td>" + r.getzipcode()+"</td><tr>"
						+ "<tr><td>Onsale</td><td>" + r.getProductonsale()+"</td><tr>"
						+ "<tr><td>Occupation:</td><td>" + r.getOccupation()+"</td><tr>"
						+ "<tr><td>Age:</td><td>" + r.getAge()+"</td><tr>"
						+ "<tr><td>Maker:</td><td>" + r.getMaker()+"</td><tr>");
				flag=true;
				}
			}
//		}
		pw.print("</table>");
		if(flag==false){
			pw.print("<h2>No Data Found</h2>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
}
