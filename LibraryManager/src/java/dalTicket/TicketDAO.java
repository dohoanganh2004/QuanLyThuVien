/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalTicket;

import dalBook.SachDAO;
import dalThe.TheDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DocGia;
import model.MuonTraSach;
import model.MuonTraSachChiTiet;
import model.Sach;

import model.TheThuVien;

/**
 *
 * @author PC
 */
public class TicketDAO extends DBContext {

    private final SachDAO sachDao = new SachDAO();
    private final TheDAO theDao = new TheDAO();

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
                Sach s = sachDao.getBookByMaSach(rs.getString(2));
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

    public MuonTraSach getMtsById(int mtsId) {
        String sql = "select * from MuonTraSach where MaMuonSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, mtsId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                TheThuVien t = theDao.getTheById(rs.getString(2));
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

    /**
     *
     * @param mathe
     * @return
     */
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
                Sach s = sachDao.getBookByMaSach(rs.getString(12));
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
                Sach s = sachDao.getBookByMaSach(rs.getString(2));
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
                TheThuVien t = theDao.getTheById(rs.getString(2));
                MuonTraSach mts = new MuonTraSach(rs.getInt(1), t, rs.getString(3));
                return mts;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public MuonTraSachChiTiet checkempty(String theId) {
        String sql = "select * from MuonTraSach m join MuonTraSachChiTiet mt on m.MaMuonSach = mt.MaMuonSach\n"
                + "where MaThe =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, theId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
               MuonTraSach mts = getMtsById(rs.getInt(1));
                Sach s = sachDao.getBookByMaSach(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(mts,
                        s, rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
                return mtsct;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void insertmts(TheThuVien t) {
        String sql = "INSERT INTO [dbo].[MuonTraSach] ([MaThe]) VALUES (?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, t.getSothe());  // Set Username

            st.executeUpdate(); // Execute the query to insert the data
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exception that occurs
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

    public void deleteMtsct(String maSach, String maThe) {
        String sql = "DELETE MuonTraSachChiTiet\n"
                + "FROM MuonTraSachChiTiet mt\n"
                + "INNER JOIN MuonTraSach m ON mt.MaMuonSach = m.MaMuonSach\n"
                + "WHERE m.MaThe = ? and MaSach = ? ;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, maThe);
            st.setString(2, maSach);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteMts(String mathe) {
        String sql = "DELETE FROM [dbo].[MuonTraSach]\n"
                + "      WHERE MaThe = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, mathe);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateAmountOfMtsct(Sach s, int amount) {
        String sql = "UPDATE [dbo].[MuonTraSachChiTiet]\n"
                + "   SET [SoLuong] = ?\n"
                + " WHERE MaSach = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, amount);
            st.setString(2, s.getMasach());
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
                Sach s = sachDao.getBookByMaSach(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(
                        m, s, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
                return mtsct;

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public MuonTraSachChiTiet getMtsctByBookId(String masach) {
        String sql = "select * from MuonTraSachChiTiet where MaSach = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, masach);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                MuonTraSach m = getMtsById(rs.getInt(1));
                Sach s = sachDao.getBookByMaSach(rs.getString(2));
                MuonTraSachChiTiet mtsct = new MuonTraSachChiTiet(
                        m, s, rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7));
                return mtsct;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
