/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalBook;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.TheLoai;

/**
 *
 * @author PC
 */
public class TheLoaiDAO extends DBContext {

    public ArrayList<TheLoai> getAll() {
        ArrayList<TheLoai> theLoai = new ArrayList<>();
        String sql = "SELECT [MaTheLoai]\n"
                + "      ,[TenTheLoai]\n"
                + "  FROM [dbo].[TheLoai]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TheLoai tl = new TheLoai(rs.getInt("MaTheLoai"), rs.getString("TenTheLoai"));
                theLoai.add(tl);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return theLoai;
    }

    /**
     * This method to get TheLoai by id
     *
     * @param matheloai input matheloai
     * @return theloai if found , null id cannot found
     */
    public TheLoai getTheLoaiByID(int matheloai) {
        String sql = "SELECT [MaTheLoai]\n"
                + "      ,[TenTheLoai]\n"
                + "  FROM [dbo].[TheLoai]\n"
                + "  where MaTheLoai= ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, matheloai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TheLoai tl = new TheLoai(rs.getInt("MaTheLoai"), rs.getString("TenTheLoai"));
                return tl;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }
}
