/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicketController;

import dalBook.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DocGia;
import model.MuonTraSach;
import model.MuonTraSachChiTiet;
import model.Sach;

import model.TheLoai;
import model.TheThuVien;

/**
 *
 * @author thang
 */
public class TicketDAO extends DBContext {

    public Sach findSachById(String id) {
        String sql = "select * from Sach where MaSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                TheLoai tl = findTheLoai(rs.getString(6));
                Sach s = new Sach(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getNString(5), tl, rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getNString(10),
                        rs.getInt(11), rs.getInt(12));
                return s;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public TheLoai findTheLoai(String TLId) {
        String sql = "select * from TheLoai where MaTheLoai = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, TLId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheLoai tl = new TheLoai(rs.getInt(1), rs.getString(2));
                return tl;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateAmountOfBooks(Sach s, int amount) {
        String sql = "UPDATE [dbo].[Sach]\n"
                + "   SET \n"
                + "      [SoLuong] = ?\n"
                + " WHERE MaSach = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, amount);
            st.setString(2, s.getMasach());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public MuonTraSachChiTiet getMtsctByMuonTraSachId(int mtsId) {
        String sql = "select * from MuonTraSachChiTiet where MaMuonSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mtsId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MuonTraSach mts = getMtsById(rs.getInt(1));
                Sach s = findSachById(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(mts,
                        s, rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
                return mtsct;
            }
        } catch (SQLException e) {
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

    public TheThuVien getTheById(String theId) {
        String sql = "select * from TheThuVien where MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, theId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DocGia d = findDocGiaId(rs.getString(2));
                TheThuVien t = new TheThuVien(rs.getString(1),
                        d, rs.getString(3),
                        rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7));
                return t;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public MuonTraSach getMtsById(int mtsId) {
        String sql = "select * from MuonTraSach where MaMuonSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, mtsId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheThuVien t = getTheById(rs.getString(2));
                MuonTraSach mts = new MuonTraSach(rs.getInt(1), t, rs.getString(3));
                return mts;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void insertmtsct(MuonTraSachChiTiet mtsct) {
        String sql = "INSERT INTO [dbo].[MuonTraSachChiTiet]\n"
                + "           ([MaMuonSach]\n"
                + "           ,[MaSach]\n"
                + "           ,[NgayMuon]\n"
                + "           ,[NgayHenTra]\n"
                + "           ,[NgayTra]\n"
                + "           ,[TrangThai]\n"
                + "           ,[SoLuong])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mtsct.getMuontra().getMamuon());  // Set Username
            st.setString(2, mtsct.getSach().getMasach());   // Set MatKhau
            st.setString(3, mtsct.getNgaymuon());   // Set Avartar
            st.setString(4, mtsct.getNgayhentra());
            st.setString(5, mtsct.getNgaytra());
            st.setString(6, mtsct.getTrangthai());
            st.setInt(7, mtsct.getSoluong());
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }

    public ArrayList<MuonTraSachChiTiet> getAllMtsct(String mathe) {
        ArrayList<MuonTraSachChiTiet> mtsct = new ArrayList<>();
        String sql = "select * from TheThuVien t join MuonTraSach m on t.MaThe = m.MaThe\n"
                + "join MuonTraSachChiTiet mts on mts.MaMuonSach = m.MaMuonSach where t.MaThe = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, mathe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                MuonTraSach mts = getMtsById(rs.getInt(11));
                Sach s = findSachById(rs.getString(12));
                MuonTraSachChiTiet m = new MuonTraSachChiTiet(mts,
                        s, rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getInt(17));
                mtsct.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
//17
        return mtsct;
    }

    public MuonTraSachChiTiet getMtsctByMtsId(int MtsId) {
        String sql = "select * from MuonTraSachChiTiet where MaMuonSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, MtsId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MuonTraSach m = getMtsById(rs.getInt(1));
                Sach s = findSachById(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(
                        m, s, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
                return mtsct;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public MuonTraSach getMtsByTheId(String theId) {
        String sql = "select * from MuonTraSach where MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, theId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheThuVien t = getTheById(rs.getString(2));
                MuonTraSach mts = new MuonTraSach(rs.getInt(1), t, rs.getString(3));
                return mts;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void insertmts(TheThuVien t) {
        String sql = "INSERT INTO [dbo].[MuonTraSach] ([MaThe]) VALUES (?)";

        // Use try-with-resources to ensure PreparedStatement is closed properly
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, t.getSothe());  // Set the library card ID

            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the SQL exception
            // Consider logging the error or rethrowing a custom exception
        }
    }

    public void InsertMtsct(MuonTraSachChiTiet m) {
        String sql = "INSERT INTO [dbo].[MuonTraSachChiTiet]\n"
                + "           ([MaMuonSach], [MaSach], [NgayMuon], [NgayHenTra], [NgayTra], [TrangThai], [SoLuong])\n"
                + "     VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, m.getMuontra().getMamuon());  // Set MaMuonSach
            st.setString(2, m.getSach().getMasach());  // Set MaSach
            st.setString(3, m.getNgaymuon());  // Set NgayMuon
            st.setString(4, m.getNgayhentra());  // Set NgayHenTra
            st.setString(5, m.getNgaytra());  // Set NgayTra
            st.setString(6, m.getTrangthai());  // Set TrangThai
            st.setInt(7, m.getSoluong());  // Set SoLuong
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }

    public void deleteMtsct(String maSach) {
        String sql = "DELETE FROM [dbo].[MuonTraSachChiTiet] \n"
                + "      WHERE MaSach = ? \n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, maSach);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateAmountOfMtsct(MuonTraSach mts, int amount , String masach) {
        String sql = "UPDATE [dbo].[MuonTraSachChiTiet]\n"
                + "   SET \n"
                + "      [SoLuong] = ?\n"
                + " WHERE MaMuonSach = ?  and MaSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, amount);
            st.setInt(2, mts.getMamuon());
            st.setString(3, masach);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void InsertAmountIntoMtsct(MuonTraSachChiTiet m, int amount) {
        String sql = "INSERT INTO [dbo].[MuonTraSachChiTiet]\n"
                + "           ([MaMuonSach], [MaSach], [NgayMuon], [NgayHenTra], [NgayTra], [TrangThai], [SoLuong])\n"
                + "     VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, m.getMuontra().getMamuon());  // Set MaMuonSach
            st.setString(2, m.getSach().getMasach());  // Set MaSach
            st.setString(3, m.getNgaymuon());  // Set NgayMuon
            st.setString(4, m.getNgayhentra());  // Set NgayHenTra
            st.setString(5, m.getNgaytra());  // Set NgayTra
            st.setString(6, m.getTrangthai());  // Set TrangThai
            st.setInt(7, amount);  // Set SoLuong
            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
        }
    }

    public void deleteAmountOfMtsct(MuonTraSachChiTiet m) {
        String sql = "DELETE FROM [dbo].[MuonTraSachChiTiet]\n"
                + "      WHERE MaSach = ? and MaMuonSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, m.getSach().getMasach());
            st.setInt(2, m.getMuontra().getMamuon());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public MuonTraSachChiTiet getMtsctByBookIdAndMathe(String mathe, String masach) {
        String sql = "select * from TheThuVien t join MuonTraSach m on t.MaThe = m.MaThe\n"
                + "join MuonTraSachChiTiet mts on mts.MaMuonSach = m.MaMuonSach where t.MaThe = ? and MaSach = ?  ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, mathe);
            st.setString(2, masach);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MuonTraSach m = getMtsById(rs.getInt(1));
                Sach s = findSachById(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(
                        m, s, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
                return mtsct;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    MuonTraSachChiTiet getMtsctByBookId(String masach , String mathe) {
        String sql = "select * from MuonTraSachChiTiet mt join MuonTraSach m on m.MaMuonSach = mt.MaMuonSach\n"
                + "where mt.MaSach = ? and m.MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, masach);
            st.setString(2, mathe);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MuonTraSach m = getMtsById(rs.getInt(1));
                Sach s = findSachById(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(
                        m, s, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
                return mtsct;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    MuonTraSach getMtsByMamuon(int mamuon) {
        String sql = "select * from MuonTraSach where MaMuonSach =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, mamuon);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheThuVien t = getTheById(rs.getString(2));
                MuonTraSach mts = new MuonTraSach(rs.getInt(1), t, rs.getString(3));
                return mts;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    MuonTraSach getMtsBymathe(String mathe) {
        String sql = "select * from MuonTraSach where MaThe =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, mathe);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheThuVien t = getTheById(rs.getString(2));
                MuonTraSach mts = new MuonTraSach(rs.getInt(1), t, rs.getString(3));
                return mts;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
