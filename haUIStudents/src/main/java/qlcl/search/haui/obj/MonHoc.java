package qlcl.search.haui.obj;

import java.io.Serializable;
/**
 * 
 * @author Eo Cuong
 *Kết quả thi
 */
public class MonHoc implements Serializable{

	String stt;
	String maLopDoclap;
	String tenMonHoc;
	String soTinChi;
	String diemTBKT;
	String diemThiL1;
	String diemThiL2;
	String diemTBC;
	String diemTinChi;
	String hocKi;
	String ghiChu;
	String linkTheoLop;
	String linkYKien;
	
	public MonHoc(){
		 stt="";
		 maLopDoclap="";
		 tenMonHoc="";
		 soTinChi="";
		 diemTBKT="";
		 diemThiL1="";
		 diemThiL2="";
		 diemTBC="";
		 diemTinChi="";
		 hocKi="";
		 ghiChu="";
		 linkTheoLop="";
		 linkYKien="";
	}

	public String getLinkYKien() {
		return linkYKien;
	}

	public void setLinkYKien(String linkYKien) {
		this.linkYKien = linkYKien;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getMaLopDoclap() {
		return maLopDoclap;
	}

	public void setMaLopDoclap(String maLopDoclap) {
		this.maLopDoclap = maLopDoclap;
	}

	public String getTenMonHoc() {
		return tenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}

	public String getSoTinChi() {
		return soTinChi;
	}

	public void setSoTinChi(String soTinChi) {
		this.soTinChi = soTinChi;
	}

	public String getDiemTBKT() {
		return diemTBKT;
	}

	public void setDiemTBKT(String diemTBKT) {
		this.diemTBKT = diemTBKT;
	}

	public String getDiemThiL1() {
		return diemThiL1;
	}

	public void setDiemThiL1(String diemThiL1) {
		this.diemThiL1 = diemThiL1;
	}

	public String getDiemThiL2() {
		return diemThiL2;
	}

	public void setDiemThiL2(String diemThiL2) {
		this.diemThiL2 = diemThiL2;
	}

	public String getDiemTBC() {
		return diemTBC;
	}

	public void setDiemTBC(String diemTBC) {
		this.diemTBC = diemTBC;
	}

	public String getDiemTinChi() {
		return diemTinChi;
	}

	public void setDiemTinChi(String diemTinChi) {
		this.diemTinChi = diemTinChi;
	}

	public String getHocKi() {
		return hocKi;
	}

	public void setHocKi(String hocKi) {
		this.hocKi = hocKi;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	

	public String getLinkTheoLop() {
		return linkTheoLop;
	}

	public void setLinkTheoLop(String linkTheoLop) {
		this.linkTheoLop = linkTheoLop;
	}

	@Override
	public String toString() {
		return "MonHoc [stt=" + stt + ", maLopDoclap=" + maLopDoclap
				+ ", tenMonHoc=" + tenMonHoc + ", soTinChi=" + soTinChi
				+ ", diemTBKT=" + diemTBKT + ", diemThiL1=" + diemThiL1
				+ ", diemThiL2=" + diemThiL2 + ", diemTBC=" + diemTBC
				+ ", diemTinChi=" + diemTinChi + ", hocKi=" + hocKi
				+ ", ghiChu=" + ghiChu + ", linkYKien=" + linkYKien + "]";
	}



	
}
