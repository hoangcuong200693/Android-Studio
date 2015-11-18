package qlcl.search.haui.obj;

import java.io.Serializable;

public class KetQuaHocTap implements Serializable{
	String stt;
	String tenMonHoc;
	String maLopDocLap;
	String diem1;
	String diem2;
	String diem3;
	String diem4;
	String diem5;
	String diem6;
	String diemGiuaHocPhan;
	String diemChuyenCan;
	String diemTrungBinh;
	String soTietNghi;
	String dieuKienThi;
	String LinkDsLop;
	
	public KetQuaHocTap(){
		 stt="";
		 tenMonHoc="";
		 maLopDocLap="";
		 diem1="";
		 diem2="";
		 diem3="";
		 diem4="";
		 diem5="";
		 diem6="";
		 diemGiuaHocPhan="";
		 diemChuyenCan="";
		 diemTrungBinh="";
		 soTietNghi="";
		 dieuKienThi="";
		String LinkDsLop="";
	}
	
	public String getLinkDsLop() {
		return LinkDsLop;
	}
	public void setLinkDsLop(String linkDsLop) {
		LinkDsLop = linkDsLop;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public String getMaLopDocLap() {
		return maLopDocLap;
	}
	public void setMaLopDocLap(String maLopDocLap) {
		this.maLopDocLap = maLopDocLap;
	}
	public String getDiem1() {
		return diem1;
	}
	public void setDiem1(String diem1) {
		this.diem1 = diem1;
	}
	public String getDiem2() {
		return diem2;
	}
	public void setDiem2(String diem2) {
		this.diem2 = diem2;
	}
	public String getDiem3() {
		return diem3;
	}
	public void setDiem3(String diem3) {
		this.diem3 = diem3;
	}
	public String getDiem4() {
		return diem4;
	}
	public void setDiem4(String diem4) {
		this.diem4 = diem4;
	}
	public String getDiem5() {
		return diem5;
	}
	public void setDiem5(String diem5) {
		this.diem5 = diem5;
	}
	public String getDiem6() {
		return diem6;
	}
	public void setDiem6(String diem6) {
		this.diem6 = diem6;
	}
	public String getDiemGiuaHocPhan() {
		return diemGiuaHocPhan;
	}
	public void setDiemGiuaHocPhan(String diemGiuaHocPhan) {
		this.diemGiuaHocPhan = diemGiuaHocPhan;
	}
	public String getDiemChuyenCan() {
		return diemChuyenCan;
	}
	public void setDiemChuyenCan(String diemChuyenCan) {
		this.diemChuyenCan = diemChuyenCan;
	}
	public String getDiemTrungBinh() {
		return diemTrungBinh;
	}
	public void setDiemTrungBinh(String diemTrungBinh) {
		this.diemTrungBinh = diemTrungBinh;
	}
	public String getSoTietNghi() {
		return soTietNghi;
	}
	public void setSoTietNghi(String soTietNghi) {
		this.soTietNghi = soTietNghi;
	}
	public String getDieuKienThi() {
		return dieuKienThi;
	}
	public void setDieuKienThi(String dieuKienThi) {
		this.dieuKienThi = dieuKienThi;
	}
	@Override
	public String toString() {
		return "KetQuaHocTap [stt=" + stt + ", tenMonHoc=" + tenMonHoc
				+ ", maLopDocLap=" + maLopDocLap + ", diem1=" + diem1
				+ ", diem2=" + diem2 + ", diem3=" + diem3 + ", diem4=" + diem4
				+ ", diem5=" + diem5 + ", diem6=" + diem6
				+ ", diemGiuaHocPhan=" + diemGiuaHocPhan + ", diemChuyenCan="
				+ diemChuyenCan + ", diemTrungBinh=" + diemTrungBinh
				+ ", soTietNghi=" + soTietNghi + ", dieuKienThi=" + dieuKienThi
				+ "]";
	}
	

}
