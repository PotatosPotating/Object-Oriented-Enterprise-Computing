import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product")

public class Product extends HttpServlet {
	private ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String item = request.getParameter("item");
		StringBuffer sb = new StringBuffer();
		PrintWriter pw = response.getWriter();

		if (action.equals("lookup")) {
			if (!item.equals("")){
				MySqlDataStoreUtilities a = new MySqlDataStoreUtilities();
				Item hm = new Item();
				hm=a.getItem(item); 
				Utilities utility = new Utilities(request,pw);
				utility.printHtml("Header.html");
				utility.printHtml("LeftNavigationBar.html");
				pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
				pw.print("<a style='font-size: 24px;'>"+hm.getName()+" Product</a>");
				pw.print("</h2><div class='entry'><table id='bestseller'>");
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+hm.getName()+"</h3>");
				pw.print("<strong>$"+hm.getPrice()+"</strong><ul>");
				pw.print("<li id='item'><img src='images/smartphones/"+hm.getImage()+"' alt='' /></li>");
				pw.print("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+hm.getName()+"'>"+
						"<input type='hidden' name='type' value='product'>"+
						"<input type='hidden' name='maker' value='"+hm.getRetailer()+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+hm.getName()+"'>"+
						"<input type='hidden' name='type' value='product'>"+
						"<input type='hidden' name='maker' value='"+hm.getRetailer()+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReviews'>"+"<input type='hidden' name='name' value='"+hm.getName()+"'>"+
						"<input type='hidden' name='type' value='smartphone'>"+
						"<input type='hidden' name='maker' value='"+hm.getRetailer()+"'>"+
						"<input type='hidden' name='access' value=''>"+
						"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
				pw.print("</ul></div></td>");
				pw.print("</table></div></div></div>");		
				utility.printHtml("Footer.html");
			}
		}
	}
}

