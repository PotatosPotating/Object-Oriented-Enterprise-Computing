import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/autocomplete")

public class AutoCompleteServlet extends HttpServlet {
	private ServletContext context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		String action = request.getParameter("action");
		String targetId = request.getParameter("searchId");
		StringBuffer sb = new StringBuffer();

		boolean namesAdded = false;
		if (action.equals("complete")) {

			// check if user sent empty string
			if (!targetId.equals("")){
				MySqlDataStoreUtilities a = new MySqlDataStoreUtilities();
				sb=a.readdata(targetId); 
				if(sb!=null || !sb.equals("")) { 
					namesAdded=true; 
				}
			}

			if (namesAdded) {
				response.setContentType("text/xml"); 
				response.setHeader("Cache-Control", "no-cache");
				try {
					response.getWriter().write("<products>"+sb.toString()+"</products>");
					System.out.println(sb.toString());
					System.out.println(response.toString());
					System.out.println(response.getContentType());
					System.out.println(response.getStatus());
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		//		if (action.equals("lookup")) {
		//            if ((targetId != null) && composers.containsKey(targetId.trim())) {
		//                request.setAttribute("composer", composers.get(targetId));
		//                context.getRequestDispatcher("/composer.jsp").forward(request, response);
		//            }
		//        }
	}

}


