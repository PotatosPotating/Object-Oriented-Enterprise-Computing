import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Review")
public class Review extends HttpServlet{

		private String productName;
		private String productType;
		private String username;
		private String zipcode;
		private String reviewDate;
		private String reviewText;
		private String reviewRating;
		private String state;
		private String city;
		private String age;
		private String occupation;
		private String maker;
		private String gender;
		private String rebate;
		private String retailername;
		private String productonsale;

		public Review(String productName, String username, String productType, String reviewRating, String reviewDate,
				String reviewText, String state, String city, String age, String occupation, String maker, String gender, String rebate, String retailername, String productonsale,String zipcode) 
		{
			super();
			this.productName = productName;
			this.productType = productType;
			this.username = username;
			this.zipcode = zipcode;
			this.reviewDate = reviewDate;
			this.reviewText = reviewText;
			this.reviewRating = reviewRating;
			this.state = state;
			this.city = city;
			this.age = age;
			this.occupation = occupation;
			this.maker = maker;
			this.gender = gender;
			this.rebate = rebate;
			this.retailername = retailername;
			this.productonsale = productonsale;

		}

		public String getzipcode() 
		{return zipcode;}

		public void setUserZip(String zipcode) 
		{this.zipcode = zipcode;}

		public String getReviewRating() 
		{return reviewRating;}

		public void setReviewRating(String reviewRating) 
		{this.reviewRating = reviewRating;}

		public String getProductName() 
		{return productName;}

		public void setProductName(String productName) 
		{this.productName = productName;}

		public String getProductType() 
		{return productType;}

		public void setProductType(String productType) 
		{this.productType = productType;}

		public String getUsername() 
		{return username;}

		public void setUsername(String username) 
		{this.username = username;}

		public String getReviewDate() 
		{return reviewDate;}

		public void setReviewDate(String reviewDate) 
		{this.reviewDate = reviewDate;}

		public String getReviewText() 
		{return reviewText;}

		public void setReviewText(String reviewText) 
		{this.reviewText = reviewText;}

		public String getState() 
		{return state;}

		public void setState(String state) 
		{this.state = state;}

		public String getCity()
		{return city;}

		public void setCity(String city) 
		{this.city = city;}

		public String getAge() 
		{return age;}

		public void setAge(String age) 
		{this.age = age;}
		
		public String getOccupation() 
		{return occupation;}
		
		public void setOccupation(String occupation) 
		{this.occupation = occupation;}
		
		public String getMaker() 
		{return maker;}
		
		public void setMaker(String maker) 
		{this.maker = maker;}
		
		public String getGender() 
		{return gender;}
		
		public void setGender(String gender) 
		{this.gender = gender;}
		
		public String getRebate() 
		{return rebate;}
		
		public void setRebate(String rebate) 
		{this.rebate = rebate;}
		
		public String getRetailername() 
		{return retailername;}
		
		public void setRetailername(String retailername) 
		{this.retailername = retailername;}

		public String getProductonsale() 
		{return productonsale;}
		
		public void setProductonsale(String productonsale) 
		{this.productonsale = productonsale;}
	}

