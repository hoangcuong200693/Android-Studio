package qlcl.search.haui.obj;

import java.io.Serializable;
import java.util.List;

public class Obj_DiemThi_TheoLop implements Serializable{

	header_BangDanhSach header;
	List<diemthi_theolop> listDanhSachDiemThi;

	public header_BangDanhSach getHeader() {
		return header;
	}

	public void setHeader(header_BangDanhSach header) {
		this.header = header;
	}

	public List<diemthi_theolop> getListDanhSachDiemThi() {
		return listDanhSachDiemThi;
	}

	public void setListDanhSachDiemThi(List<diemthi_theolop> listDanhSachDiemThi) {
		this.listDanhSachDiemThi = listDanhSachDiemThi;
	}

}
