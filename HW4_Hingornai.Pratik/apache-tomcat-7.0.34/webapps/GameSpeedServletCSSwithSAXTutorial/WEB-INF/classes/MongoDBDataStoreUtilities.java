import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@WebServlet("/MongoDBDataStoreUtilities")
public class MongoDBDataStoreUtilities extends HttpServlet{

	static DBCollection myReviews; 
	
	
	public static void getConnection() { 
		MongoClient mongo; 
		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CustomerReviews"); 
		myReviews= db.getCollection("myReviews"); }
		
	
	public static HashMap<String, ArrayList<Review>> selectReview() { 
		getConnection(); 
		HashMap<String, ArrayList<Review>> reviewHashmap=new HashMap<String, ArrayList<Review>>(); 
		DBCursor cursor = myReviews.find(); 
		while (cursor.hasNext()) { BasicDBObject obj = (BasicDBObject) cursor.next(); 
		if(! reviewHashmap.containsKey(obj.getString("productName"))) { 
			ArrayList<Review> arr = new ArrayList<Review>(); 
			reviewHashmap.put(obj.getString("productName"), arr); 
			} 
		ArrayList<Review> listReview = reviewHashmap.get(obj.getString("productName")); 
		Review review =new Review(obj.getString("productName"),obj.getString("userName"),obj.getString("productType"),obj.getString("reviewRating"),obj.getString("reviewDate"),obj.getString("reviewText"),obj.getString("state"),obj.getString("city"),obj.getString("age"),obj.getString("occupation"),obj.getString("maker"),obj.getString("gender"),obj.getString("rebate"),obj.getString("retailername"),obj.getString("onsale"),obj.getString("zipcode")); 
		listReview.add(review); 
		} 
		return  reviewHashmap; 
		} 
	
	
	
	public static void insertReview(String productname,String username,String producttype,String reviewrating,String reviewdate,String reviewtext, String state, String city, String age, String occupation, String maker, String gender, String rebate, String retailername, String onsale) { 
		getConnection(); 
		System.out.println("here is the "+ productname+ " "+username+" "+producttype+" "+reviewrating+" "+reviewdate+" "+reviewtext+" "+state+ " "+city+" "+age+" "+occupation+" "+maker+" "+gender+" "+rebate+" "+retailername+" "+onsale);
		BasicDBObject doc = new BasicDBObject("title", "myReviews"). append("userName", username). append("productName", productname). append("productType", producttype). append("reviewRating", reviewrating). append("reviewDate", reviewdate). append("reviewText", reviewtext) .append("state", state). append("city", city). append("age", age). append("occupation", occupation). append("maker", maker). append("gender", gender). append("rebate", rebate).append("retailername", retailername).append("onsale", onsale); 
		myReviews.insert(doc);
	}
	
	
	public static void mostLikedProducts(PrintWriter pw) throws MongoException, IOException{
		getConnection();
		DBObject match=new BasicDBObject("$match",new BasicDBObject("reviewRating",5));
		DBObject groupFields=new BasicDBObject("_id",0);
		groupFields.put("_id","$productName");
		groupFields.put("count",new BasicDBObject("$sum",1));
		DBObject group=new BasicDBObject("$group",groupFields);
		DBObject projectFields=new BasicDBObject("_id",0);
		projectFields.put("productName","$_id");
		projectFields.put("Count of top rating","$count");
		DBObject project=new BasicDBObject("$project",projectFields);
		DBObject sort=new BasicDBObject();
		sort.put("Count of top rating",-1);
		DBObject orderby=new BasicDBObject();
		orderby=new BasicDBObject("$sort",sort);
		DBObject limit=new BasicDBObject();
		limit=new BasicDBObject("$limit",5);
		AggregationOutput aggregate=myReviews.aggregate(match, group, project, orderby, limit);	
		for (DBObject result: aggregate.results()){
			BasicDBObject bobj=(BasicDBObject)result;
			pw.print("<tr><td>"+bobj.getString("productName")+"</td><td>"+ bobj.getInt("Count of top rating")+"</td></tr>");
		}
	}

		
		public static void topFiveProducts(PrintWriter pw) throws MongoException, IOException{
			getConnection();
			DBObject groupFields=new BasicDBObject("_id",0);
			groupFields.put("_id","$productName");
			groupFields.put("count",new BasicDBObject("$sum",1));
			DBObject group=new BasicDBObject("$group",groupFields);
			DBObject projectFields=new BasicDBObject("_id",0);
			projectFields.put("productName","$_id");
			projectFields.put("Number of product","$count");
			DBObject project=new BasicDBObject("$project",projectFields);
			DBObject sort=new BasicDBObject();
			sort.put("Number of product",-1);
			DBObject orderby=new BasicDBObject();
			orderby=new BasicDBObject("$sort",sort);
			DBObject limit=new BasicDBObject();
			limit=new BasicDBObject("$limit",5);
			AggregationOutput aggregate=myReviews.aggregate(group, project, orderby, limit);
			for (DBObject result: aggregate.results()){
				BasicDBObject bobj=(BasicDBObject)result;
				pw.print("<tr><td>"+bobj.getString("productName")+"</td><td>"+ bobj.getInt("Number of product")+"</td></tr>");
			}
	}
		public static void zipCodes(PrintWriter pw) throws MongoException, IOException{
			getConnection();
			DBObject groupFields=new BasicDBObject("_id",0);
			groupFields.put("_id","$zipcode");
			groupFields.put("count",new BasicDBObject("$sum",1));
			DBObject group=new BasicDBObject("$group",groupFields);

			DBObject projectFields=new BasicDBObject("_id",0);
			projectFields.put("zipcode","$_id");
			projectFields.put("Count of zip code","$count");
			DBObject project=new BasicDBObject("$project",projectFields);

			DBObject sort=new BasicDBObject();
			sort.put("Count of zip code",-1);
			DBObject orderby=new BasicDBObject();
			orderby=new BasicDBObject("$sort",sort);
			DBObject limit=new BasicDBObject();
			limit=new BasicDBObject("$limit",5);
			AggregationOutput aggregate=myReviews.aggregate(group, project, orderby, limit);
			for (DBObject result: aggregate.results()){
				BasicDBObject bobj=(BasicDBObject)result;
				pw.print("<tr><td>"+bobj.getString("zipcode")+"</td><td>"+ bobj.getInt("Count of zip code")+"</td></tr>");
			}
		}

}
