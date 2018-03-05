import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/MySqlDataStoreUtilities")

public class MySqlDataStoreUtilities extends HttpServlet {

	static Connection conn = null; 


	public static void insertUser(String username,String password,String usertype)
	{ 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?autoReconnect=true&useSSL=false" ,"root","Digunder363");

			String insertIntoCustomerRegisterQuery= "INSERT INTO Registration(username,password,repassword,usertype) " 
					+ "VALUES (?,?,?,?);"; 
			PreparedStatement pst= conn.prepareStatement(insertIntoCustomerRegisterQuery); 
			pst.setString(1,username); 
			pst.setString(2,password); 
			pst.setString(3,password); 
			pst.setString(4,usertype); 
			pst.execute();
			conn.close();
		} 
		catch(Exception e){}
	}

	public static HashMap<String, User> selectUser(){
		try{

			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?autoReconnect=true&useSSL=false" ,"root","Digunder363");

			ResultSet rs = null;
			Statement st = conn.createStatement();
			String sql = ("select * from registration");
			rs=st.executeQuery(sql);
			HashMap<String, User> user = new HashMap<String, User>();
			while (rs.next()) { 
				String name=rs.getString("username");
				String password=rs.getString("password");
				String usertype=rs.getString("usertype");
				user.put(name, new User(name,password,usertype));
				System.out.println("Got till "+ user.get(name).getName());      
			}
			rs.close();
			st.close();
			conn.close();
			return user; 


		}catch (Exception e) {e.printStackTrace();}
		return null;
	}
	public static HashMap<String, Item> selectItem(){
		try{

			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?autoReconnect=true&useSSL=false" ,"root","Digunder363");

			ResultSet rs = null;
			Statement st = conn.createStatement();
			String sql = ("select * from products");
			rs=st.executeQuery(sql);
			HashMap<String, Item> items = new HashMap<String, Item>();
			while (rs.next()) { 
				String id=rs.getString("id");
				String name=rs.getString("name");
				String price=rs.getString("price");
				String image=rs.getString("image");
				String manufacturer=rs.getString("manufacturer");
				String conditions=rs.getString("conditions");
				String discount=rs.getString("discount");
				items.put(id, new Item(id,name,price,image,manufacturer,conditions,discount));      
			}
			rs.close();
			st.close();
			conn.close();
			return items; 


		}catch (Exception e) {e.printStackTrace();}
		return null;
	}
	public StringBuffer readdata(String searchId)
	{
		StringBuffer sb = new StringBuffer();
		HashMap<String,Item> data;
		data=selectItem();
		Iterator it = data.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pi = (Map.Entry)it.next();
			Item p=(Item)pi.getValue();
			if (p.getName().toLowerCase().startsWith(searchId)||p.getRetailer().toLowerCase().startsWith(searchId))
			{
				sb.append("<product>");
				sb.append("<id>" + p.getId() + "</id>");
				sb.append("<productName>" + p.getName() + "</productName>");
				sb.append("<productPrice>" + p.getPrice() + "</productPrice>");
				sb.append("<productImage>" + p.getImage() + "</productImage>");
				sb.append("<productManufacturer>" + p.getRetailer() + "</productManufacturer>");
				sb.append("<productCondition>" + p.getCondition() + "</productCondition>");
				sb.append("</product>");
			}
		}
		return sb;
	}
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/

	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() 
	{ 
		HashMap<Integer,ArrayList<OrderPayment>> orderPayments=new HashMap<Integer,ArrayList<OrderPayment>>(); 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?autoReconnect=true&useSSL=false" ,"root","Digunder363");
			String selectOrderQuery="select * from customerorders"; 
			PreparedStatement pst= conn.prepareStatement(selectOrderQuery); 
			ResultSet rs= pst.executeQuery(); 
			ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>(); 
			while(rs.next()) 
			{
				if(!orderPayments.containsKey(rs.getInt("orderid"))) {
					ArrayList<OrderPayment> arr= new ArrayList<OrderPayment>(); 
					orderPayments.put(rs.getInt("orderid"), arr);
				} 
				ArrayList<OrderPayment> listOrderPayment=orderPayments.get(rs.getInt("orderid")); 
				OrderPayment order= new OrderPayment(rs.getInt("orderid"),rs.getString("username"),rs.getString("ordername"),rs.getDouble("orderprice"), rs.getString("useraddress"), rs.getString("creditcard"));
				listOrderPayment.add(order); 
			}
			conn.close();
		}
		catch(Exception e){} 
		return orderPayments;
	} 
	public static void insertOrder(int orderid,String username,String ordername, double orderprice, String useraddress, String creditcard) 
	{
		try 
		{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?autoReconnect=true&useSSL=false" ,"root","Digunder363");
			String insertIntoCustomerOrderQuery= "INSERT INTO customerorders(OrderId,userName,orderName,orderPrice,userAddress,creditCard) " + "VALUES (?,?,?,?,?,?);"; 
			PreparedStatement pst=  conn.prepareStatement(insertIntoCustomerOrderQuery); 
			System.out.println(orderid+" "+username+" "+ordername+" "+orderprice+" "+useraddress+" "+creditcard);
			pst.setInt(1,orderid); 
			pst.setString(2,username); 
			pst.setString(3,ordername);
			pst.setDouble(4,orderprice); 
			pst.setString(5,useraddress);
			pst.setString(6,creditcard); 
			pst.execute(); 
			conn.close();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		} 
	}

	public static void deleteOrder(int orderid, String ordername){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?autoReconnect=true&useSSL=false", "root", "Digunder363");
			String deleteFromCustomerOrderQuery = "DELETE FROM customerorders WHERE orderid = ? and ordername = ?";
			PreparedStatement ps = conn.prepareStatement(deleteFromCustomerOrderQuery);
			ps.setInt(1,  orderid);
			ps.setString(2, ordername);
			ps.execute();
			conn.close();
		}catch(Exception e){}
	}

	public Item getItem(String item) {
		HashMap<String, Item> all = new HashMap<String, Item>();
		System.out.println("Item in product is"+item);
		Item hm=new Item();
		all=selectItem();
		for(Map.Entry<String, Item> singleitem : all.entrySet())
		{
			System.out.println("Item is"+item);
			System.out.println("Item in mysql is"+singleitem.getValue().getId());
			if(singleitem.getValue().getId().equals(item))
			{
				hm=singleitem.getValue();
			}
		}
		System.out.println("Value of item in product is: "+hm.getName());
		return hm;
	}	
}
