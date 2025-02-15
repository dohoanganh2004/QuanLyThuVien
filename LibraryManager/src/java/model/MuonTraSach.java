/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
/*
 MaMuonSach INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    MaThe INT NULL,
    
    GhiChu NVARCHAR(1500) NULL,
*/
public class MuonTraSach {
    private int mamuon;
    private TheThuVien the;
    private String ghichu;

    public MuonTraSach() {
    }

    public MuonTraSach(int mamuon, TheThuVien the, String ghichu) {
        this.mamuon = mamuon;
        this.the = the;
        this.ghichu = ghichu;
    }
     public MuonTraSach( TheThuVien the, String ghichu) {
        this.the = the;
        this.ghichu = ghichu;
    }
     
     public MuonTraSach( TheThuVien the) {
        this.the = the;
        
    }
    public int getMamuon() {
        return mamuon;
    }

    public void setMamuon(int mamuon) {
        this.mamuon = mamuon;
    }

    public TheThuVien getThe() {
        return the;
    }

    public void setThe(TheThuVien the) {
        this.the = the;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
    
    
}
