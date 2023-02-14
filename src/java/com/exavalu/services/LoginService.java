/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;  
/**
 *
 * @author Avijit Chattopadhyay
 */
public class LoginService {
    
    public static LoginService loginService = null;
    
    private LoginService(){}
    
    public static LoginService getInstance()
    {
        if(loginService==null)
        {
            return new LoginService();
        }
        else
        {
            return loginService;
        }
    }
    
    public boolean doLogin(String emailAddress, String password) throws URISyntaxException, IOException, InterruptedException
    {
            
            
            
        boolean success = false;
        
        String sql = "Select * from employeedb2.users where emailAddress=? and password=?";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emailAddress);
            ps.setString(2, password);
            
            System.out.println("LoginService :: "+ps);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                success = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
                try {
	            String url = "https://jsonplaceholder.typicode.com/todos/1";
	            URL obj = new URL(url);
	            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	            BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = read .readLine()) != null) {
	                response.append(inputLine);
	            } 
	            System.out.println(response.toString());
	            JSONObject obj_JSONObject = new JSONObject(response.toString());
                    
	            System.out.println("Result after Reading JSON Response");
	            System.out.println("userId- "+obj_JSONObject.getString("userId"));
	            System.out.println("id- "+obj_JSONObject.getString("id"));
	            System.out.println("title- "+ obj_JSONObject.getString("title"));
	            System.out.println("completed- "+ obj_JSONObject.getString("completed"));
                    
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
        return success;
        
        
    }
    
     public boolean doSignUp(User user) {
        boolean result = false;
        boolean success=false;
        
        Connection con = JDBCConnectionManager.getConnection();

        String sql = "INSERT INTO users(emailAddress,password,firstName,lastName,status,countryId,stateId,districtId)"
                + "VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, user.getCountryId());
            preparedStatement.setInt(7, user.getStateId());
            preparedStatement.setInt(8, user.getDistrictId());
             
             System.out.println("LoginService :: " + preparedStatement);
            int rows = preparedStatement.executeUpdate();

            if (rows==1) {
                
                result = true;
            }

            con.close();
             System.out.println("LoginService :: "+preparedStatement);

            
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next())
            {
                success = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return result;
     }

    
}
