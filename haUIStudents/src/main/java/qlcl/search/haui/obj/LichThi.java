package qlcl.search.haui.obj;

import java.io.Serializable;

public class LichThi implements Serializable{
	
	String stt;
	String tenMonThi;
	String ngayThi;
	String caThi;
	String SBD;
	String lanThi;
	String phongThi;
	String diaDiem;
	String lePhiThi;
	String ghiChu;
	
	public LichThi(){
		 stt="";
		 tenMonThi="";
		 ngayThi="";
		 caThi="";
		 SBD="";
		 lanThi="";
		 phongThi="";
		 diaDiem="";
		 lePhiThi="";
		 ghiChu="";
	}
	
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getTenMonThi() {
		return tenMonThi;
	}
	public void setTenMonThi(String tenMonThi) {
		this.tenMonThi = tenMonThi;
	}
	public String getNgayThi() {
		return ngayThi;
	}
	public void setNgayThi(String ngayThi) {
		this.ngayThi = ngayThi;
	}
	public String getCaThi() {
		return caThi;
	}
	public void setCaThi(String caThi) {
		this.caThi = caThi;
	}
	public String getSBD() {
		return SBD;
	}
	public void setSBD(String sBD) {
		SBD = sBD;
	}
	public String getLanThi() {
		return lanThi;
	}
	public void setLanThi(String lanThi) {
		this.lanThi = lanThi;
	}
	public String getPhongThi() {
		return phongThi;
	}
	public void setPhongThi(String phongThi) {
		this.phongThi = phongThi;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	public String getLePhiThi() {
		return lePhiThi;
	}
	public void setLePhiThi(String lePhiThi) {
		this.lePhiThi = lePhiThi;
	}
	@Override
	public String toString() {
		return "LichThi [stt=" + stt + ", tenMonThi=" + tenMonThi
				+ ", ngayThi=" + ngayThi + ", caThi=" + caThi + ", SBD=" + SBD
				+ ", lanThi=" + lanThi + ", phongThi=" + phongThi
				+ ", diaDiem=" + diaDiem + ", lePhiThi=" + lePhiThi + "]";
	}
	
	
	
	

}
