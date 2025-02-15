/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalAccount;

import dalNhanVien.NhanVienDAO;
import dalQuyenHan.QuyenHanDAO;
import dalThe.TheDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DocGia;
import model.NhanVien;
import model.QuyenHan;
import model.TaiKhoan;
import model.TheThuVien;

/**
 *
 * @author thang
 */
public class TaiKhoanDAO extends DBContext {

    private final QuyenHanDAO qdao = new QuyenHanDAO();
    private final TheDAO tdao = new TheDAO();
    private final NhanVienDAO nvDao = new NhanVienDAO();

    public QuyenHan findQhId(int qhId) {
        String sql = "select * from QuyenHan where IDQuyen = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, qhId);

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

    public TheThuVien findTheId(String theid) {
        String sql = "select * from TheThuVien where MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, theid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheThuVien t = new TheThuVien();
                t.setSothe(rs.getString(1));
                DocGia d = findDocGiaId(rs.getString(2));
                t.setDocgia(d);
                t.setNgaycap(rs.getString(3));
                t.setNgayhethan(rs.getString(4));
                t.setTrangthai(rs.getString(5));
                t.setSosachduocmuon(rs.getInt(6));
                t.setSosachdangmuon(rs.getInt(7));
                return t;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public DocGia findDocGiaId(String code) {
        String sql = "select * from DocGia where MaDocGia = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DocGia d = new DocGia();
                d.setMadocgia(rs.getString(1));  // Assuming column name is 'madocgia'
                d.setTen(rs.getString(2));
                d.setGioitinh(rs.getString(3));
                d.setNgaysinh(rs.getString(4));
                d.setDiachi(rs.getString(5));
                d.setEmail(rs.getString(6));
                d.setSdt(rs.getString(7));
                return d;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean findUserName(String username) {
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

    public TaiKhoan findTkId(int tkId) {
        String sql = "SELECT [MaTaiKhoan],\n"
                + "      [Username],\n"
                + "      [MatKhau],\n"
                + "      [Avatar],\n"
                + "      [IDQuyen],\n"
                + "      [MaThe],\n"
                + "      [MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]\n"
                + "  WHERE MaTaiKhoan = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tkId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                return taikhoan;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * This method to get tai khoan by username
     *
     * @param username
     * @return
     */
    public TaiKhoan getIdTaiKhoanByUsername(String username) {
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avatar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "      ,[MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]\n"
                + "  WHERE Username = ?";

        try {
            PreparedStatement st = connection.prepareCall(sql);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                return taikhoan;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * username
     *
     * @param username
     * @param card
     * @return
     */
    public TaiKhoan getForgetPassword(String username, String card) {
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avatar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "      ,[MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]\n"
                + "  WHERE Username =? AND MaThe =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, username);
            st.setString(2, card);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                return taikhoan;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<TaiKhoan> getAll() {
        ArrayList<TaiKhoan> listtaikhoan = new ArrayList<>();
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avatar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "      ,[MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                listtaikhoan.add(taikhoan);

            }
        } catch (Exception e) {

        }
        return listtaikhoan;
    }

    public void addTaikhoanNhanVien(TaiKhoan t) {
        String sql = "INSERT INTO [dbo].[TaiKhoan]\n"
                + "           ([Username]\n"
                + "           ,[MatKhau]\n"
                + "           ,[Avatar]\n"
                + "           ,[IDQuyen]\n"
                + "           ,[MaNhanVien])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getUsername());  // Set Username
            st.setString(2, t.getMatkhau());   // Set MatKhau
            st.setString(3, t.getAvartar());   // Set Avartar
            st.setInt(4, t.getQuenhan().getId());
            st.setString(5, t.getNhanvien().getManhanvien());
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }

    public void addTaikhoanDocGia(TaiKhoan t) {
    String sql = "INSERT INTO [dbo].[TaiKhoan] " +
                 "([Username], [MatKhau], [Avatar], [IDQuyen], [MaThe]) " +  // Đóng phần cột
                 "VALUES (?, ?, ?, ?, ?)";  // Dấu ngoặc đã được sửa

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, t.getUsername());  // Set Username
        st.setString(2, t.getMatkhau());   // Set MatKhau
        st.setString(3, t.getAvartar());   // Set Avatar
        st.setInt(4, t.getQuenhan().getId());
        st.setString(5, t.getThe().getSothe());
        st.executeUpdate(); // Execute the query to insert the data
    } catch (SQLException e) {
        e.printStackTrace(); // Handle any SQL exception that occurs
    }
}

    /**
     * This method to get tai khoan by manhanvien and pass word
     *
     * @param manhanvien
     * @param matkhau
     * @return
     */
    public TaiKhoan loginNhanVien(String username, String matkhau, String manhanvien) {
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avatar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "      ,[MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]\n"
                + "  WHERE Username =? and MatKhau=? and MaNhanVien =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, matkhau);
            ps.setString(3, manhanvien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                return taikhoan;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * Dang nhap cua doc gia
     *
     * @param username
     * @param matkhau
     * @param mathe
     * @return
     */
    public TaiKhoan loginDocGia(String username, String matkhau, String mathe) {
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avatar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "      ,[MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]\n"
                + "  WHERE Username =? and MatKhau=? and MaThe =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, matkhau);
            ps.setString(3, mathe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                return taikhoan;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * Cap nhat tai khoan cho nhan vien
     *
     * @param t
     */
    public void updateTaiKhoanNhanVien(TaiKhoan t) {
        String sql = "UPDATE [dbo].[TaiKhoan]\n"
                + "   SET [Username] = ?\n"
                + "      ,[MatKhau] = ?\n"
                + "      ,[Avatar] = ?\n"
                + "      ,[IDQuyen] = ?\n"
                + "      ,[MaThe] = ?\n"
                + " WHERE MaNhanVien = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getUsername());           // Set Username
            st.setString(2, t.getMatkhau());            // Set MatKhau
            st.setString(3, t.getAvartar());            // Set Avatar
            st.setInt(4, t.getQuenhan().getId());       // Set IDQuyen
            st.setString(5, t.getThe() != null ? t.getThe().getSothe() : null); // Set MaThe nếu có
            st.setString(6, t.getNhanvien().getManhanvien()); // Set MaNhanVien cho WHERE

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    /**
     * Cap nhat tai khoan doc gia
     *
     * @param t
     */
    public void updateTaiKhoanDocGia(TaiKhoan t) {
        String sql = "UPDATE [dbo].[TaiKhoan]\n"
                + "   SET [Username] = ?\n"
                + "      ,[MatKhau] = ?\n"
                + "      ,[Avatar] = ?\n"
                + "      ,[IDQuyen] = ?\n"
                + "      ,[MaThe] = ?\n"
                + "      ,[MaNhanVien] = ?\n"
                + " WHERE MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t.getUsername());  // Set Username
            st.setString(2, t.getMatkhau());   // Set MatKhau
            st.setString(3, t.getAvartar());   // Set Avartar
            st.setInt(4, t.getQuenhan().getId());
            st.setString(5, t.getThe().getSothe());
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {

        }
    }

    public TaiKhoan getTaiKhoanBYID(int id) {
        String sql = "SELECT [MaTaiKhoan]\n"
                + "      ,[Username]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Avatar]\n"
                + "      ,[IDQuyen]\n"
                + "      ,[MaThe]\n"
                + "      ,[MaNhanVien]\n"
                + "  FROM [dbo].[TaiKhoan]\n"
                + "  WHERE MaTaiKhoan = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan();
                taikhoan.setMataikhoan(rs.getInt("MaTaiKhoan"));
                taikhoan.setUsername(rs.getString("Username"));
                taikhoan.setMatkhau(rs.getString("MatKhau"));
                taikhoan.setAvartar(rs.getString("Avatar"));
                QuyenHan quyenhan = qdao.findQuyenHanNameByQHID(rs.getInt("IDQuyen"));
                taikhoan.setQuenhan(quyenhan);
                TheThuVien thethuvien = tdao.getTheById(rs.getString("MaThe"));
                taikhoan.setThe(thethuvien);
                NhanVien nhanvien = nvDao.getNhanVienByMaNV(rs.getString("MaNhanVien"));
                taikhoan.setNhanvien(nhanvien);
                return taikhoan;
            }

        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        TaiKhoanDAO t = new TaiKhoanDAO();
        TaiKhoan tk = t.getTaiKhoanBYID(4);
        System.out.println(tk.getMatkhau());
    }
}
