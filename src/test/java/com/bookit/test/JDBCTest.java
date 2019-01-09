package com.bookit.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.bookit.utilities.DBUtils;

public class JDBCTest {
	
	String dbUrl = "jdbc:postgresql://localhost:5432/hr";
    String dbUsername = "postgres";
    String dbPassword = "fd";
    
  @Test(enabled=false)
  public void PostgreSQL() throws SQLException {
	  
	  
	  Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	  Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  ResultSet resultset=statement.executeQuery("Select * from countries");
	  
	  
//	  while(resultset.next()){
//		  System.out.println(resultset.getString(1)+"-"+resultset.getString("country_name")+"-"+resultset.getInt(3));
//	  }
//	  resultset.next();
//	  
//	  System.out.println(resultset.getString(1));
//	  System.out.println(resultset.getString(2));
//	 
//	  resultset.next();
//	  System.out.println(resultset.getRow());
//	  
//	  resultset.next();
//	  resultset.next();
//	  
//	  resultset.first();
//	  System.out.println(resultset.getRow());
//	  
//	  
//	  System.out.println(resultset.getString(1)+"-"+resultset.getString("country_name")+"-"+resultset.getInt(3));
	  
	//find out how many record in the resultset?
	  resultset.last();
	  int rowsCount = resultset.getRow();
	  System.out.println("Total number of rows: "+rowsCount);
	  
	  resultset.beforeFirst();
	 
	  while(resultset.next()){
	  System.out.println(resultset.getString(1)+"-"+resultset.getString("country_name")+"-"+resultset.getInt(3));
	  }
	  
	  resultset.close();
	  statement.close();
	  connection.close();
	   
  }
  
  
  
  @Test(enabled=false)
  public void JDBCMetaData() throws SQLException {
	  Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	  Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  ResultSet resultset=statement.executeQuery("Select * from countries");
	  
	  //database metadata(cerate object)
	  DatabaseMetaData dbMetaData=connection.getMetaData();
	  
	  // which username are we using ?
	  System.out.println("User: "+dbMetaData.getUserName());
	  //database product name
	  
	  System.out.println("Database roduct name: "+dbMetaData.getDatabaseProductName());
	  
	//database product version
	  System.out.println("Database Product Version: "+ dbMetaData.getDatabaseProductVersion());
	  
	  
	  
	  
	ResultSetMetaData rsMetaData=resultset.getMetaData();
	
	
	System.out.println("Column number : "+rsMetaData.getColumnCount());
	System.out.println("Column name : "+rsMetaData.getColumnName(2));
	System.out.println("table name : "+rsMetaData.getTableName(1));
	  
	for(int i=1; i<=rsMetaData.getColumnCount(); i++) {
		System.out.println("Column name : "+ rsMetaData.getColumnName(i));
	}
	
	  resultset.close();
	  statement.close();
	  connection.close();
	   
  }

  @Test(enabled=false)
  public void dbData() throws SQLException {
      
      
      Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);;
      ResultSet resultset=statement.executeQuery("Select first_name,last_name,salary,job_id from employees limit 5;");
      
      //database metadata(create object)
      DatabaseMetaData dbMetadata = connection.getMetaData();
      
      //resultset metadata create object
      ResultSetMetaData rsMetadata = resultset.getMetaData();
     
      //our main structure, it will keep whole quesry result
      List<Map<String, Object>> queryData = new ArrayList<>();
      
      //we will add the first row data to this map
      Map<String, Object> row1 = new HashMap<>();
      
      //point the first row
      resultset.next();
      
//      //key is column name, value is value of that column
//      row1.put("first_name", "Steven");
//      row1.put("last_name", "King");
//      row1.put("salary", 24000);
//      row1.put("job_id", "AD_PRES");
//      System.out.println(row1.toString());
//      
//      //push row1 map to list as a whole row
//      queryData.add(row1);
//      
//      System.out.println(queryData.get(0).get("first_name"));
      
    //key is column name, value is value of that column
      row1.put("first_name", resultset.getObject("first_name"));
      row1.put("last_name", resultset.getObject("last_name"));
      row1.put("Salary", resultset.getObject("salary"));
      row1.put("Job_id", resultset.getObject("job_id"));
      
     
      
      //push row1 map to list as a whole row 
      queryData.add(row1);
      
      
      //verify map is keeping all values 
      System.out.println(row1.toString());
      
      
      System.out.println(queryData.get(0).get("first_name"));
      //--------------ADDING ONE MORE ROW----------
      Map<String,Object> row2 = new HashMap<>();
      
      resultset.next();
      
      row2.put("first_name", resultset.getObject("first_name"));
      row2.put("last_name", resultset.getObject("last_name"));
      row2.put("Salary", resultset.getObject("salary"));
      row2.put("Job_id", resultset.getObject("job_id"));
      
      queryData.add(row2);

      System.out.println("First Row: "+queryData.get(0).toString());
      System.out.println("Second Row: "+queryData.get(1).toString());
        
      
      
      resultset.close();
      statement.close();
      connection.close();
       
   }
  
  
  
  
  @Test(enabled=false)
  public void dbDynamic() throws SQLException{
	  
	  Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	  Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  ResultSet resultset=statement.executeQuery("Select * from countries");
	  
	  //database metadata(cerate object)
	  DatabaseMetaData dbMetaData=connection.getMetaData(); 
	  
	  ResultSetMetaData rsMetaData=resultset.getMetaData();  
	  
	  List<Map<String,Object>> queryData= new ArrayList<>();
	   int colCount=rsMetaData.getColumnCount();
	   
	   while(resultset.next()) {
		    Map<String,Object> row= new HashMap<>();
		    
		    
		    for(int i=1;i<=colCount;i++) {
		          
		          row.put(rsMetaData.getColumnName(i), resultset.getObject(i));
		        }
		      
		            
		      //adding each row map to list   
		      queryData.add(row);
		      
		      }
		      
		      //printing first 4 row
		      System.out.println(queryData.get(0).values());
		      System.out.println(queryData.get(1));
		      System.out.println(queryData.get(2));
		      System.out.println(queryData.get(3));
		      
		      
		      
		       for(Map<String,Object> key :queryData) {
		    	   
		    	 System.out.println(key.get(rsMetaData.getColumnName(1))+"  ===  "+ key.get(rsMetaData.getColumnName(2)));
		    	   
		       }
		      
		    resultset.close();
		    statement.close();
		    connection.close();
  
}
  
  @Test
   public void useDBUtils() {
	  DBUtils.createConnection();
	  
	  String query="Select first_name,last_name,salary,job_id FROM employees ";
	  
	  List<Map<String,Object>> queryData=DBUtils.getQueryResultMap(query);
	  
	  System.out.println(queryData.get(0).get("salary"));
	  DBUtils.destroy();
	  
  }
  
  @Test
  public void useDBUtils2() {
    
    //create connection with given information 
    DBUtils.createConnection();
    
    String query = "SELECT first_name,last_name,salary,job_id FROM employees where employee_id = 107";
    
    
    Map<String,Object> onerowresult = DBUtils.getRowMap(query);
    
    //print first row salary value 
    System.out.println(onerowresult.get("job_id"));
    
    //close connection 
    DBUtils.destroy();
    
  }
  
}
