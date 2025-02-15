/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalBook;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sach;
import model.TheLoai;

/**
 *
 * @author PC
 */
public class SachDAO extends DBContext{

    private final TheLoaiDAO tl = new TheLoaiDAO();

    /**
     * This class to get all book from DB
     *
     * @return
     */
    public ArrayList<Sach> getAll() {
        ArrayList<Sach> bookList = new ArrayList<>();
        String sql = "SELECT [MaSach]\n"
                + "      ,[Nxb]\n"
                + "      ,[TenSach]\n"
                + "      ,[MoTa]\n"
                + "      ,[TacGia]\n"
                + "      ,[MaTheLoai]\n"
                + "      ,[NamXuatBan]\n"
                + "      ,[NgonNgu]\n"
                + "      ,[AnhBia]\n"
                + "      ,[TrangThai]\n"
                + "      ,[Gia]\n"
                + "      ,[SoLuong]\n"
                + "  FROM [dbo].[Sach]\n"
                + "  ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMasach(rs.getString("MaSach")); // Kiểm tra tên cột
                sach.setNxb(rs.getString("Nxb"));
                sach.setTensach(rs.getString("TenSach")); // Kiểm tra tên cột
                sach.setMota(rs.getString("MoTa")); // Kiểm tra tên cột
                sach.setTacgia(rs.getString("TacGia"));
                TheLoai theloai = tl.getTheLoaiByID(rs.getInt("MaTheLoai")); // Kiểm tra tên cột
                sach.setTheloai(theloai);
                sach.setNamxb(rs.getString("NamXuatBan")); // Kiểm tra tên cột
                sach.setNgonngu(rs.getString("NgonNgu")); // Kiểm tra tên cột
                sach.setAnhbia(rs.getString("AnhBia")); // Kiểm tra tên cột
                sach.setTrangthai(rs.getString("TrangThai")); // Kiểm tra tên cột
                sach.setGia(rs.getInt("Gia")); // Kiểm tra tên cột
                sach.setSoluong(rs.getInt("SoLuong")); // Kiểm tra tên cột
                bookList.add(sach);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return bookList;
    }

    /**
     * This method to get book by matheloai
     *
     * @param matheloai
     * @return
     */
    public ArrayList<Sach> getBookByTheLoai(int matheloai) {

        ArrayList<Sach> bookList = new ArrayList<>();
        String sql = "SELECT [MaSach]\n"
                + "      ,[Nxb]\n"
                + "      ,[TenSach]\n"
                + "      ,[MoTa]\n"
                + "      ,[TacGia]\n"
                + "      ,[MaTheLoai]\n"
                + "      ,[NamXuatBan]\n"
                + "      ,[NgonNgu]\n"
                + "      ,[AnhBia]\n"
                + "      ,[TrangThai]\n"
                + "      ,[Gia]\n"
                + "      ,[SoLuong]\n"
                + "  FROM [dbo].[Sach]\n"
                + "  WHERE MaTheLoai =?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, matheloai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMasach(rs.getString("MaSach")); 
                sach.setNxb(rs.getString("Nxb"));
                sach.setTensach(rs.getString("TenSach")); 
                sach.setMota(rs.getString("MoTa"));
                sach.setTacgia(rs.getString("TacGia"));
                TheLoai theloai = tl.getTheLoaiByID(rs.getInt("MaTheLoai")); 
                sach.setTheloai(theloai);
                sach.setNamxb(rs.getString("NamXuatBan")); 
                sach.setNgonngu(rs.getString("NgonNgu")); 
                sach.setAnhbia(rs.getString("AnhBia")); 
                sach.setTrangthai(rs.getString("TrangThai")); 
                sach.setGia(rs.getInt("Gia")); 
                sach.setSoluong(rs.getInt("SoLuong"));
                bookList.add(sach);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;

    }

    /**
     * This method to get book by masach
     *
     * @param masach
     * @return
     */
    public Sach getBookByMaSach(String masach) {

        String sql = "SELECT [MaSach]\n"
                + "      ,[Nxb]\n"
                + "      ,[TenSach]\n"
                + "      ,[MoTa]\n"
                + "      ,[TacGia]\n"
                + "      ,[MaTheLoai]\n"
                + "      ,[NamXuatBan]\n"
                + "      ,[NgonNgu]\n"
                + "      ,[AnhBia]\n"
                + "      ,[TrangThai]\n"
                + "      ,[Gia]\n"
                + "      ,[SoLuong]\n"
                + "  FROM [dbo].[Sach]\n"
                + "  WHERE MaSach=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, masach);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Sach sach = new Sach();
                sach.setMasach(rs.getString("MaSach")); // Kiểm tra tên cột
                sach.setNxb(rs.getString("Nxb"));
                sach.setTensach(rs.getString("TenSach")); // Kiểm tra tên cột
                sach.setMota(rs.getString("MoTa")); // Kiểm tra tên cột
                sach.setTacgia(rs.getString("TacGia"));
                TheLoai theloai = tl.getTheLoaiByID(rs.getInt("MaTheLoai")); // Kiểm tra tên cột
                sach.setTheloai(theloai);
                sach.setNamxb(rs.getString("NamXuatBan")); // Kiểm tra tên cột
                sach.setNgonngu(rs.getString("NgonNgu")); // Kiểm tra tên cột
                sach.setAnhbia(rs.getString("AnhBia")); // Kiểm tra tên cột
                sach.setTrangthai(rs.getString("TrangThai")); // Kiểm tra tên cột
                sach.setGia(rs.getInt("Gia")); // Kiểm tra tên cột
                sach.setSoluong(rs.getInt("SoLuong")); // Kiểm tra tên cột
                return sach;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method to delete book
     *
     * @param masach
     */
    public void deleteBook(String masach) {
        String sql = "DELETE FROM [dbo].[Sach]\n"
                + "      WHERE MaSach =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, masach);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * THis method to add book
     *
     * @param sach
     */
    public void addBook(Sach sach) {
        String sql = "INSERT INTO [dbo].[Sach]\n"
                + "           ([MaSach]\n"
                + "           ,[Nxb]\n"
                + "           ,[TenSach]\n"
                + "           ,[MoTa]\n"
                + "           ,[TacGia]\n"
                + "           ,[MaTheLoai]\n"
                + "           ,[NamXuatBan]\n"
                + "           ,[NgonNgu]\n"
                + "           ,[AnhBia]\n"
                + "           ,[TrangThai]\n"
                + "           ,[Gia]\n"
                + "           ,[SoLuong])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sach.getMasach());
            ps.setString(2, sach.getNxb());
            ps.setString(3, sach.getTensach());
            ps.setString(4, sach.getMota());
            ps.setString(5, sach.getTacgia());
            ps.setInt(6, sach.getTheloai().getMatheloai());
            ps.setString(7, sach.getNamxb());
            ps.setString(8, sach.getNgonngu());
            ps.setString(9, sach.getAnhbia()); // Tên file ảnh bìa
            ps.setString(10, sach.getTrangthai());
            ps.setInt(11, sach.getGia());
            ps.setInt(12, sach.getSoluong());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * This method to update book
     *
     * @param sach
     */
    public void updateBook(Sach sach) {
        String sql = "UPDATE [dbo].[Sach] SET "
                + "[Nxb] = ?, "
                + "[TenSach] = ?, "
                + "[MoTa] = ?, "
                + "[TacGia] = ?, "
                + "[MaTheLoai] = ?, "
                + "[NamXuatBan] = ?, "
                + "[NgonNgu] = ?, "
                + "[AnhBia] = ?, "
                + "[TrangThai] = ?, "
                + "[Gia] = ?, "
                + "[SoLuong] = ? "
                + "WHERE MaSach = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, sach.getNxb());
            ps.setString(2, sach.getTensach());
            ps.setString(3, sach.getMota());
            ps.setString(4, sach.getTacgia());
            ps.setInt(5, sach.getTheloai().getMatheloai());
            ps.setString(6, sach.getNamxb());
            ps.setString(7, sach.getNgonngu());
            ps.setString(8, sach.getAnhbia()); // Tên file ảnh bìa
            ps.setString(9, sach.getTrangthai());
            ps.setInt(10, sach.getGia());
            ps.setInt(11, sach.getSoluong());
            ps.setString(12, sach.getMasach());

            ps.executeUpdate();
        } catch (SQLException e) {

        }

    }
    /**
     * This method to search book by name
     * @param txt
     * @return 
     */
    public ArrayList<Sach> getBookByName(String txt) {

        ArrayList<Sach> bookList = new ArrayList<>();
        String sql = "SELECT [MaSach]\n"
                + "      ,[Nxb]\n"
                + "      ,[TenSach]\n"
                + "      ,[MoTa]\n"
                + "      ,[TacGia]\n"
                + "      ,[MaTheLoai]\n"
                + "      ,[NamXuatBan]\n"
                + "      ,[NgonNgu]\n"
                + "      ,[AnhBia]\n"
                + "      ,[TrangThai]\n"
                + "      ,[Gia]\n"
                + "      ,[SoLuong]\n"
                + "  FROM [dbo].[Sach]\n"
                + "  WHERE TenSach like ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+txt+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sach sach = new Sach();
                sach.setMasach(rs.getString("MaSach")); // Kiểm tra tên cột
                sach.setNxb(rs.getString("Nxb"));
                sach.setTensach(rs.getString("TenSach")); // Kiểm tra tên cột
                sach.setMota(rs.getString("MoTa")); // Kiểm tra tên cột
                sach.setTacgia(rs.getString("TacGia"));
                TheLoai theloai = tl.getTheLoaiByID(rs.getInt("MaTheLoai")); // Kiểm tra tên cột
                sach.setTheloai(theloai);
                sach.setNamxb(rs.getString("NamXuatBan")); // Kiểm tra tên cột
                sach.setNgonngu(rs.getString("NgonNgu")); // Kiểm tra tên cột
                sach.setAnhbia(rs.getString("AnhBia")); // Kiểm tra tên cột
                sach.setTrangthai(rs.getString("TrangThai")); // Kiểm tra tên cột
                sach.setGia(rs.getInt("Gia")); // Kiểm tra tên cột
                sach.setSoluong(rs.getInt("SoLuong")); // Kiểm tra tên cột
                bookList.add(sach);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;

    }
}
