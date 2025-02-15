/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dalQuyenHan;

import dalAccount.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.QuyenHan;
import model.TaiKhoan;

/**
 *
 * @author thang
 */
public class QuyenHanDAO extends DBContext {

    public QuyenHan findQuyenHanNameByQHID(int qhId) {
        String sql = "SELECT [IDQuyen]\n"
                + "      ,[TenQuyen]\n"
                + "  FROM [dbo].[QuyenHan] where IDQuyen = ?";
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

    /**
     * This method to get all information
     *
     * @return
     */
    public ArrayList<QuyenHan> getAll() {
        ArrayList<QuyenHan> quyenhan = new ArrayList<>();
        String sql = "SELECT [IDQuyen]\n"
                + "      ,[TenQuyen]\n"
                + "  FROM [dbo].[QuyenHan]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuyenHan qh = new QuyenHan();
                qh.setId(rs.getInt("IDQuyen"));
                qh.setTenquyen(rs.getString("TenQuyen"));
                quyenhan.add(qh);
            }
        } catch (Exception e) {

        }
        return quyenhan;

    }

    public static void main(String[] args) {
        QuyenHanDAO a = new QuyenHanDAO();
        ArrayList<QuyenHan> q = a.getAll();
        for (QuyenHan qh : q) {
            System.out.println(qh.getId());
        }

    }
}
