import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Item")
public class Item extends HttpServlet{

	private String id;
	private String name;
	private String price;
	private String image;
	private String retailer;
	private String condition;
	private String discount;
	
	public Item(String id,String name, String price, String image, String retailer,String condition,String discount2){
		this.id=id;
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount2;
		this.retailer = retailer;
	}
	public Item() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
}
