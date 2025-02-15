
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalNhanVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.NhanVien;
import model.QuyenHan;
import model.TaiKhoan;
import model.TheThuVien;

/**
 *
 * @author PC
 */
public class NhanVienDAO extends DBContext {

    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        String sql = "SELECT [MaNhanVien]\n"
                + "      ,[HoVaTen]\n"
                + "      ,[Email]\n"
                + "      ,[SoDienThoai]\n"
                + "      ,[NgaySinh]\n"
                + "  FROM [dbo].[NhanVien] ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                NhanVien nv = new NhanVien();
                nv.setManhanvien(rs.getString("MaNhanVien"));
                nv.setHovaten(rs.getString("HoVaTen"));
                nv.setEmail(rs.getString("Email"));
                nv.setSdt(rs.getString("SoDienThoai"));
                nv.setNgaysinh(rs.getString("NgaySinh"));

                dsNhanVien.add(nv);
            }
        } catch (SQLException e) {

        }
        return dsNhanVien;

    }
//thêm tài khoản và thông tin nhân viên

    public boolean checkExistTaiKhoan(String username) {
        String sql = "select * from TaiKhoan where Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
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
     * This method to check Nhanvien
     *
     * @param hovaten
     * @param email
     * @param sdt
     * @return
     */
    public boolean checkExistNv(String hovaten, String email, String sdt) {
        String sql = "SELECT [MaNhanVien], [HoVaTen], [Email], [SoDienThoai], [NgaySinh] "
                + "FROM [dbo].[NhanVien] "
                + "WHERE HoVaTen = ? AND Email = ? AND SoDienThoai = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, hovaten);
            st.setString(2, email);
            st.setString(3, sdt);
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
     * This method to add nhanvien
     *
     * @param nhanvien
     */
    public void addNhanVien(NhanVien nhanVien) {
        String sql = "INSERT INTO [dbo].[NhanVien]\n"
                + "           ([MaNhanVien]\n"
                + "           ,[HoVaTen]\n"
                + "           ,[Email]\n"
                + "           ,[SoDienThoai]\n"
                + "           ,[NgaySinh])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, nhanVien.getManhanvien());
            st.setString(2, nhanVien.getHovaten());
            st.setString(3, nhanVien.getEmail());
            st.setString(4, nhanVien.getSdt());
            st.setString(5, nhanVien.getNgaysinh());
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }

    public QuyenHan getQuyenHan(int id) {
        String sql = "SELECT [IDQuyen]\n"
                + "      ,[TenQuyen]\n"
                + "  FROM [dbo].[QuyenHan] where IDQuyen = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                QuyenHan q = new QuyenHan(rs.getInt(1), rs.getString(2));
                return q;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
//kết thúc thêm tài khoản và thông tin nhân viên    

    /**
     * this method to get all information of nhanvien by manv
     *
     * @param manhanvien
     * @return
     */
    public NhanVien getNhanVienByMaNV(String manhanvien) {
        String sql = "SELECT [MaNhanVien]\n"
                + "      ,[HoVaTen]\n"
                + "      ,[Email]\n"
                + "      ,[SoDienThoai]\n"
                + "      ,[NgaySinh]\n"
                + "  FROM [dbo].[NhanVien]\n"
                + "  WHERE MaNhanVien=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, manhanvien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setManhanvien(rs.getString("MaNhanVien"));
                nv.setHovaten(rs.getString("HoVaTen"));
                nv.setEmail(rs.getString("Email"));
                nv.setSdt(rs.getString("SoDienThoai"));
                nv.setNgaysinh(rs.getString("NgaySinh"));

                return nv;

            }
        } catch (SQLException e) {

        }
        return null;
    }

    //xóa thông tin nhân viên 
    /**
     * This method to delete Nhanvien By id
     *
     * @param manhanvien
     */
    public void delelteNhanVien(String manhanvien) {
        String sql = "DELETE FROM [dbo].[NhanVien]\n"
                + "      WHERE MaNhanVien =?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, manhanvien);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    /**
     * This method to update nhanvien
     *
     * @param nhanvien
     */
    public void updateNhanVien(NhanVien nhanvien) {
        String sql = "UPDATE [dbo].[NhanVien]\n"
                + "   SET \n"
                + "      [HoVaTen] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[SoDienThoai] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + " WHERE MaNhanVien =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, nhanvien.getHovaten());
            ps.setString(2, nhanvien.getEmail());
            ps.setString(3, nhanvien.getSdt());
            ps.setString(4, nhanvien.getNgaysinh());

            ps.setString(5, nhanvien.getManhanvien());

            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateTaiKhoan(TaiKhoan t) {
        String sql = "UPDATE [dbo].[TaiKhoan]\n"
                + "   SET [Username] = ?\n"
                + "      ,[MatKhau] = ?\n"
                + "      ,[Avatar] = ?\n"
                + "      ,[IDQuyen] = ?\n"
                + "      ,[MaNhanVien] = ?\n"
                + " WHERE MaTaiKhoan = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, t.getUsername());
            ps.setString(2, t.getMatkhau());
            ps.setString(3, t.getAvartar());
            ps.setInt(4, t.getQuenhan().getId());
            ps.setString(5, t.getNhanvien().getManhanvien());

            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public boolean findCccd(String CCCD) {
        String sql = "select * from DocGia where MaDocGia = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, CCCD);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
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

    public boolean findSdt(String sdt) {
        String sql = "select * from DocGia where SoDienThoai = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sdt);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean findEmail(String email) {
        String sql = "select * from DocGia where Email = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
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
    
     public void insert(TaiKhoan t) {
        String sql = "INSERT INTO [dbo].[TaiKhoan]\n"
                + "           ([Username]\n"
                + "           ,[MatKhau]\n"
                + "           ,[Avatar]\n"
                + "           ,[IDQuyen]\n"
                + "           ,[MaThe])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getUsername());  // Set Username
            st.setString(2, t.getMatkhau());   // Set MatKhau
            st.setString(3, t.getAvartar());   // Set Avartar
            st.setInt(4, t.getQuenhan().getId());
            st.setString(5, t.getThe().getSothe());
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }
}
