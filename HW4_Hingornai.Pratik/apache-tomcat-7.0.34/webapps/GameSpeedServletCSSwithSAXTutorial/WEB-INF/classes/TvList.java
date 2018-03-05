import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TvList")

public class TvList extends HttpServlet {

	/* Accessory Page Displays all the Accessories and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Console maker whether it is microsft or sony or nintendo 
		   Add the respective product value to hashmap  */
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Tv> hm = new HashMap<String, Tv>();
		System.out.println("got to here");
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.tvs);
			name = "";
		}	
		else{
			
		
		if(CategoryName.equals("samsung"))
			{
				for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{	
					if(entry.getValue().getRetailer().equals("Samsung"))
					{
					 hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Samsung";
			}
			else if(CategoryName.equals("lg"))
			{	
				for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{	
				  if(entry.getValue().getRetailer().equals("LG"))
				 { 
					hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				name = "LG";
			}
			else if(CategoryName.equals("vizio"))
			{
				for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Vizio"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "Vizio";
			}
			else if(CategoryName.equals("toshiba"))
			{
				for(Map.Entry<String,Tv> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Toshiba"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				name = "Toshiba";
			}
		}
			
				
		/* Header, Left Navigation Bar are Printed.

		All the Accessories and Accessories information are dispalyed in the Content Section

		and then Footer is Printed*/

		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+ name +": TVs</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Tv> entry : hm.entrySet()){
			Tv tv = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+tv.getName()+"</h3>");
			
			pw.print("<strong>"+ "$" + tv.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/tv/"+tv.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tv'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tv'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReviews'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tv'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReviews' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}

}