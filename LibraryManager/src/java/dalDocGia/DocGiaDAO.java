/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalDocGia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DocGia;

/**
 *
 * @author thang
 */
public class DocGiaDAO extends DBContext {

    public void insert(DocGia d) {
        String sql = "INSERT INTO [dbo].[DocGia]\n"
                + "           ([MaDocGia]\n"
                + "           ,[TenDocGia]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[DiaChi]\n"
                + "           ,[Email]\n"
                + "           ,[SoDienThoai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, d.getMadocgia());
            st.setString(2, d.getTen());
            st.setString(3, d.getGioitinh());
            st.setString(4, d.getNgaysinh());
            st.setString(5, d.getDiachi());
            st.setString(6, d.getEmail());
            st.setString(7, d.getSdt());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }

    public DocGia getDocGiaByMaDocGia(String madocgia) {
        String sql = "SELECT [MaDocGia], [TenDocGia], [GioiTinh], [NgaySinh], [DiaChi], [Email], [SoDienThoai] "
                + "FROM [dbo].[DocGia] "
                + "WHERE MaDocGia = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, madocgia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    DocGia docgia = new DocGia();
                    docgia.setMadocgia(rs.getString("MaDocGia"));
                    docgia.setTen(rs.getString("TenDocGia"));
                    docgia.setGioitinh(rs.getString("GioiTinh"));
                    docgia.setNgaysinh(rs.getString("NgaySinh"));
                    docgia.setDiachi(rs.getString("DiaChi"));
                    docgia.setEmail(rs.getString("Email"));
                    docgia.setSdt(rs.getString("SoDienThoai"));
                    return docgia;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi để biết nguyên nhân lỗi
        }
        return null;
    }

    /**
     * This method to delete information of docgia
     *
     * @param madocgia
     */
    public void deleteDocGia(String madocgia) {
        String sql = "DELETE FROM [dbo].[DocGia]\n"
                + "      WHERE MaDocGia =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, madocgia);
            ps.executeUpdate();
        } catch (Exception e) {

        }

    }

    public void addDocGia(DocGia docgia) {
        String sql = "INSERT INTO [dbo].[DocGia]\n"
                + "           ([MaDocGia]\n"
                + "           ,[TenDocGia]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[DiaChi]\n"
                + "           ,[Email]\n"
                + "           ,[SoDienThoai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, docgia.getMadocgia());
            ps.setString(1, docgia.getTen());
            ps.setString(1, docgia.getGioitinh());
            ps.setString(1, docgia.getNgaysinh());
            ps.setString(1, docgia.getDiachi());
            ps.setString(1, docgia.getEmail());
            ps.setString(1, docgia.getSdt());
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    /**
     * This
     *
     * @return
     */
    public ArrayList<DocGia> getAll() {
        ArrayList<DocGia> listDg = new ArrayList<>();
        String sql = "SELECT [MaDocGia]\n"
                + "      ,[TenDocGia]\n"
                + "      ,[GioiTinh]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[DiaChi]\n"
                + "      ,[Email]\n"
                + "      ,[SoDienThoai]\n"
                + "  FROM [dbo].[DocGia]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DocGia docgia = new DocGia();
                docgia.setMadocgia(rs.getString("MaDocGia"));
                docgia.setTen(rs.getString("TenDocGia"));
                docgia.setGioitinh(rs.getString("GioiTinh"));
                docgia.setNgaysinh(rs.getString("NgaySinh"));
                docgia.setDiachi(rs.getString("DiaChi"));
                docgia.setEmail(rs.getString("Email"));
                docgia.setSdt(rs.getString("SoDienThoai"));
                listDg.add(docgia);
            }
        } catch (Exception e) {

        }
        return listDg;
    }

    /**
     * Lay doc gia theo ten
     *
     * @param tendocgia
     * @return
     */
    public DocGia getDocGiaByThongTin(String email, String sdt) {
        String sql = "SELECT [MaDocGia]\n"
                + "      ,[TenDocGia]\n"
                + "      ,[GioiTinh]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[DiaChi]\n"
                + "      ,[Email]\n"
                + "      ,[SoDienThoai]\n"
                + "  FROM [dbo].[DocGia]\n"
                + "  WHERE SoDienThoai =? AND Email=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sdt);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DocGia docgia = new DocGia();
                docgia.setMadocgia(rs.getString("MaDocGia"));
                docgia.setTen(rs.getString("TenDocGia"));
                docgia.setGioitinh(rs.getString("GioiTinh"));
                docgia.setNgaysinh(rs.getString("NgaySinh"));
                docgia.setDiachi(rs.getString("DiaChi"));
                docgia.setEmail(rs.getString("Email"));
                docgia.setSdt(rs.getString("SoDienThoai"));
                return docgia;
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Ham update
     *
     * @param docgia
     */
    public void update(DocGia docgia) {
        String sql = "UPDATE [dbo].[DocGia] "
                + "SET [TenDocGia] = ?, [GioiTinh] = ?, [NgaySinh] = ?, [DiaChi] = ?, [Email] = ?, [SoDienThoai] = ? "
                + "WHERE MaDocGia = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, docgia.getTen());
            ps.setString(2, docgia.getGioitinh());
            ps.setString(3, docgia.getNgaysinh());
            ps.setString(4, docgia.getDiachi());
            ps.setString(5, docgia.getEmail());
            ps.setString(6, docgia.getSdt());
            ps.setString(7, docgia.getMadocgia());
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }
}
