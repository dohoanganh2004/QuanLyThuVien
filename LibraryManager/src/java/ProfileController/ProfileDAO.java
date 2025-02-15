/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProfileController;

import dalAccount.TaiKhoanDAO;
import dalBook.DBContext;
import model.TaiKhoan;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.DocGia;
import model.NhanVien;
import model.QuyenHan;
import model.TheThuVien;

/**
 *
 * @author thang
 */
public class ProfileDAO extends DBContext {

    public TaiKhoan findTaiKhoanByAccId(int id) {
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avartar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "  FROM [dbo].[TaiKhoan] where MaTaiKhoan = ?";

        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                TaiKhoan acc = new TaiKhoan();
                acc.setMataikhoan(rs.getInt(1));
                acc.setUsername(rs.getString(2));
                acc.setMatkhau(rs.getString(3));
                acc.setAvartar(rs.getString(4));
                TaiKhoanDAO dao = new TaiKhoanDAO();
                QuyenHan qh = dao.findQhId(rs.getInt(5));
                acc.setQuenhan(qh);
                TheThuVien t = dao.findTheId(rs.getString(6));
                acc.setThe(t);
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkExistPhoneUpdateProfile(String phone) {
        String sql = "select * from TaiKhoan t join TheThuVien the on t.MaThe = the.MaThe\n"
                + "join DocGia d on d.MaDocGia = the.MaDocGia where SoDienThoai = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkExistUsernameUpdateProfile(String username) {
        String sql = "select * from TaiKhoan t join TheThuVien the on t.MaThe = the.MaThe\n"
                + "join DocGia d on d.MaDocGia = the.MaDocGia where Username = ?  ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkExistEmailUpdateProfile(String email) {
        String sql = "select * from TaiKhoan t join TheThuVien the on t.MaThe = the.MaThe\n"
                + "join DocGia d on d.MaDocGia = the.MaDocGia where Email = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void updateTaiKhoan(TaiKhoan t, String mathe) {
        String sql = "UPDATE [dbo].[TaiKhoan]\n"
                + "   SET [Username] = ?\n"
                + "      ,[MatKhau] = ?\n"
                + "      ,[Avartar] = ?\n"
                + " WHERE MaThe = ?  ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getMatkhau());
            st.setString(3, t.getAvartar());
            st.setString(4, mathe);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateDocGia(DocGia d, String cccd) {
        String sql = "UPDATE [dbo].[DocGia]\n"
                + "   SET \n"
                + "      [TenDocGia] = ?\n"
                + "      ,[GioiTinh] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      ,[DiaChi] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[SoDienThoai] = ?\n"
                + " WHERE [MaDocGia] = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, d.getTen());
            st.setString(2, d.getGioitinh());
            st.setString(3, d.getNgaysinh());
            st.setString(4, d.getDiachi());
            st.setString(5, d.getEmail());
            st.setString(6, d.getSdt());
            st.setString(7, cccd);
            st.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public boolean checkExistPhoneUpdateProfileEmp(String phone) {
        String sql = "select * from NhanVien n join TaiKhoan t on n.MaTaiKhoan = t.MaTaiKhoan\n"
                + "where SoDienThoai = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkExistUsernameUpdateProfileEmp(String username) {
        String sql = "select * from NhanVien n join TaiKhoan t on n.MaTaiKhoan = t.MaTaiKhoan\n"
                + "where Username = ?  ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkExistEmailUpdateProfileEmp(String email) {
        String sql = "select * from NhanVien n join TaiKhoan t on n.MaTaiKhoan = t.MaTaiKhoan\n"
                + "where Email = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void updateTaiKhoanEmp(TaiKhoan t, String maTaiKhoan) {
        String sql = "UPDATE [dbo].[TaiKhoan]\n"
                + "   SET [Username] = ?\n"
                + "      ,[MatKhau] = ?\n"
                + "      ,[Avartar] = ?\n"
                + " WHERE MaTaiKhoan = ?  ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getMatkhau());
            st.setString(3, t.getAvartar());
            st.setString(4, maTaiKhoan);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateNhanVien(NhanVien t, String maNv) {
        String sql = "UPDATE [dbo].[NhanVien]\n"
                + "   SET \n"
                + "	   [HoVaTen] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[SoDienThoai] =?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      \n"
                + " WHERE MaNhanVien = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getHovaten());
            st.setString(2, t.getEmail());
            st.setString(3, t.getSdt());
            st.setString(4,  t.getNgaysinh());
            st.setString(5, maNv);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
