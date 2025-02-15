/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalThe;

import dalQuyenHan.*;
import dalAccount.*;
import dalDocGia.DocGiaDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DocGia;
import model.TheThuVien;

/**
 *
 * @author thang
 */
public class TheDAO extends DBContext {

    private final DocGiaDAO docgiaDao = new DocGiaDAO();

    /**
     * This method to get all infomation of TheThuVien
     *
     * @return
     */
    public ArrayList<TheThuVien> getAll() {
        ArrayList<TheThuVien> thethuvien = new ArrayList<>();

        String sql = "SELECT [MaThe]\n"
                + "      ,[MaDocGia]\n"
                + "      ,[NgayCapThe]\n"
                + "      ,[NgayHetHan]\n"
                + "      ,[TrangThai]\n"
                + "      ,[SoSachDuocMuon]\n"
                + "      ,[SoSachDangMuon]\n"
                + "  FROM [dbo].[TheThuVien]";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TheThuVien the = new TheThuVien();
                the.setSothe(rs.getString("MaThe"));

                DocGia docgia = docgiaDao.getDocGiaByMaDocGia(rs.getString("MaDocGia"));
                the.setDocgia(docgia);

                the.setNgaycap(rs.getString("NgayCapThe"));
                the.setNgayhethan(rs.getString("NgayHetHan"));
                the.setTrangthai(rs.getString("TrangThai"));
                the.setSosachduocmuon(rs.getInt("SoSachDuocMuon"));
                the.setSosachdangmuon(rs.getInt("SoSachDangMuon"));

                // Thêm đối tượng thẻ vào danh sách
                thethuvien.add(the);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi để biết nguyên nhân lỗi
        }
        return thethuvien;
    }

    /**
     * This method to get the by id
     *
     * @param mathe
     * @return
     */
    public TheThuVien getTheById(String mathe) {
        String sql = "SELECT [MaThe], [MaDocGia], [NgayCapThe], [NgayHetHan], [TrangThai], [SoSachDuocMuon], [SoSachDangMuon] "
                + "FROM [dbo].[TheThuVien] "
                + "WHERE MaThe = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mathe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TheThuVien the = new TheThuVien();
                the.setSothe(rs.getString("MaThe"));
                // Lấy thông tin độc giả từ mã độc giả
                DocGia docgia = docgiaDao.getDocGiaByMaDocGia(rs.getString("MaDocGia"));
                the.setDocgia(docgia);

                the.setNgaycap(rs.getString("NgayCapThe"));
                the.setNgayhethan(rs.getString("NgayHetHan"));
                the.setTrangthai(rs.getString("TrangThai"));
                the.setSosachduocmuon(rs.getInt("SoSachDuocMuon"));
                the.setSosachdangmuon(rs.getInt("SoSachDangMuon"));
                return the;
            }
        } catch (SQLException e) {

        }
        return null;

    }

    /**
     * Them the thu vien
     *
     * @param t
     */
    public void insert(TheThuVien t) {
        String sql = "INSERT INTO [dbo].[TheThuVien]\n"
                + "           ([MaThe]\n"
                + "           ,[MaDocGia]\n"
                + "           ,[NgayCapThe]\n"
                + "           ,[NgayHetHan]\n"
                + "           ,[TrangThai]\n"
                + "           ,[SoSachDuocMuon]\n"
                + "           ,[SoSachDangMuon])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getSothe());

            st.setString(2, t.getDocgia().getMadocgia());
            st.setString(3, t.getNgaycap());
            st.setString(4, t.getNgayhethan());
            st.setString(5, t.getTrangthai());
            st.setInt(6, t.getSosachduocmuon());
            st.setInt(7, t.getSosachdangmuon());

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public DocGia findMaDocGia(String maDocGia) {
        String sql = "SELECT [MaDocGia]\n"
                + "      ,[TenDocGia]\n"
                + "      ,[GioiTinh]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[DiaChi]\n"
                + "      ,[Email]\n"
                + "      ,[SoDienThoai]\n"
                + "  FROM [dbo].[DocGia] where MaDocGia = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, maDocGia);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DocGia d = new DocGia();
                d.setMadocgia(rs.getString("MaDocGia"));
                d.setTen(rs.getString("TenDocGia"));
                d.setGioitinh(rs.getString("GioiTinh"));
                d.setNgaysinh(rs.getString("NgaySinh"));
                d.setDiachi(rs.getString("DiaChi"));
                d.setEmail(rs.getString("Email"));
                d.setSdt(rs.getString("SoDienThoai"));

                return d;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean findMaThe(String Mathe) {
        String sql = "select * from TheThuVien where MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Mathe);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Cap Nhat tai khoan
     *
     * @param the
     */
    public void update(TheThuVien the) {
        String sql = "UPDATE TheThuVien SET "
                + "MaDocGia = ?, "
                + "NgayCapThe = ?, "
                + "NgayHetHan = ?, "
                + "TrangThai = ?, "
                + "SoSachDuocMuon = ?, "
                + "SoSachDangMuon = ? "
                + "WHERE MaThe = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, the.getDocgia().getMadocgia());
            ps.setString(2, the.getNgaycap());
            ps.setString(3, the.getNgayhethan());
            ps.setString(4, the.getTrangthai());
            ps.setInt(5, the.getSosachdangmuon());
            ps.setInt(6, the.getSosachduocmuon());
            ps.setString(7, the.getSothe());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để dễ dàng gỡ lỗi
        }
    }
}
