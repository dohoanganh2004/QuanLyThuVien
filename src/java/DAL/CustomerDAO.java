/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class CustomerDAO extends DBContext{
     public Customer getCustomerByUsenamePassword(String username, String password) {
        String sql = "SELECT [CustomerID]\n"
                + "      ,[UsernameC]\n"
                + "      ,[PasswordC]\n"
                + "      ,[Name]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  WHERE UsernameC =? AND PasswordC =? ;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setUsernameC(rs.getString("UsernameC"));
                customer.setPasswordC(rs.getString("PasswordC"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhone(rs.getString("Phone"));
                customer.setAddress(rs.getString("Address"));

                return customer;

            }
        } catch (Exception e) {

        }
        return null;
    }

    public void changePassword(Customer c) {
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [PasswordC] = ?\n"
                + "      \n"
                + " WHERE UsernameC = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getPasswordC());
            ps.setString(2, c.getPasswordC());
            ps.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT [CustomerID]\n"
                + "      ,[UsernameC]\n"
                + "      ,[PasswordC]\n"
                + "      ,[Name]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  WHERE Email =?;"; 
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setUsernameC(rs.getString("UsernameC"));
                customer.setPasswordC(rs.getString("PasswordC"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhone(rs.getString("Phone"));
                customer.setAddress(rs.getString("Address"));

                return customer;
            }
        } catch(SQLException e) {
            
        }
        return null;
    }
}
