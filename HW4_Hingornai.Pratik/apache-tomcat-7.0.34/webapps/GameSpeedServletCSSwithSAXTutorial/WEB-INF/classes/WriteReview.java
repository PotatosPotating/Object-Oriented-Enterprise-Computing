import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WriteReview")
public class WriteReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to review");
			try {
				response.sendRedirect("Login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String name = request.getParameter("username");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String onsale = request.getParameter("onsale");
		String rebate = request.getParameter("rebate");
		String zipcode = request.getParameter("zipcode");	
		Date dt = new Date();
		SimpleDateFormat mdy = new SimpleDateFormat("MM/dd/yyyy");
		String date = mdy.format(dt);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");		
		pw.println("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.println("<a style='font-size: 24px;'>Review Form</a>");
		pw.println("</h2><div class='entry'>");
		pw.print("<form method = 'post' action = 'ReviewForm'>");
		pw.print("<table class = 'writereview1'>");
		pw.print("<tr>");
		pw.print("<td>ProductName: </td>");
		pw.print("<td>"+name+"</td>");
		pw.print("</tr>");
		pw.print("<tr>");
		pw.print("<td>ProductType: </td>");
		pw.print("<td>"+type+"</td>");
		pw.print("</tr>");
		pw.print("<tr>");
		pw.print("<td>");
		pw.print("Product Maker: ");
		pw.print("</td>");
		pw.print("<td>"+maker+"</td>");
		pw.print("</tr>");
		pw.print("</table>");
		pw.print("<table>");
		pw.print("<tr>");
		pw.print("<td>Review Rating: </td>");
		pw.print("<td><select name = 'rating'> "
				+ "<option value='5'>5</option>"
				+ "<option value='4'>4</option>"
				+ "<option value='3'>3</option>"
				+ "<option value='2'>2</option>"
				+ "<option value='1'>1</option>"
				+ "</select> "
				+ "</td>");
		pw.print("</tr>");
		pw.print("<tr>");
		pw.print("<td>Review Date: </td>");
		pw.print("<td name = 'date'>"+date+"<input type ='hidden' name = 'date' value = "+date+"></input></td>");
		pw.print("</tr>");
		
		pw.print("<p>User age: </p><input type='number' name='age' value=''></br>"+
				"<p>Gender: </p><input type='radio' name='gender' value='Male'> Male <input type='radio' name='gender' value='Female'> Female <input type='radio' name='gender' value='Other'> Other</br>"+
				"<p>Occupation: </p><input type='text' name='occupation' value=''></br>");
		pw.print("<input type ='hidden' name = 'type' value = '"+type+"'>");
		pw.print("<input type ='hidden' name = 'name' value = '"+name+"'>");
		pw.print("<input type ='hidden' name = 'maker' value = '"+maker+"'>");
		pw.print("<input type='hidden' name='state' value='"+"IL"+"'>");
		pw.print("<input type='hidden' name='city' value='"+"Chicago"+"'>");
		pw.print("<input type='hidden' name='zipcode' value='"+"60614"+"'>");
		pw.print("<input type='hidden' name='onsale' value='"+"yes"+"'>");
		pw.print("<input type='hidden' name='retailername' value='BestDeal'>");
		pw.print("<input type='hidden' name='rebate' value='"+"None"+"'>");
		pw.print("<tr>");
		pw.print("<td>Review Text: </td>");
		pw.print("<td><textarea rows = '4' cols = '40' name = 'reviewtext' ></textarea></td>");
		pw.print("</tr>");
		pw.print("</table>");
		pw.print("<input type= 'submit' class = 'btnreview' value = 'Submit'>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
}
