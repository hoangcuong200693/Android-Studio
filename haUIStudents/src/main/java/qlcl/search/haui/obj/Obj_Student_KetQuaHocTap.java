package qlcl.search.haui.obj;

import java.io.Serializable;
import java.util.List;

public class Obj_Student_KetQuaHocTap implements Serializable{
	
	header_BangDanhSach header;
	List<Student_KetQuaHocTap> listDanhSach;
	public header_BangDanhSach getHeader() {
		return header;
	}
	public void setHeader(header_BangDanhSach header) {
		this.header = header;
	}
	public List<Student_KetQuaHocTap> getListDanhSach() {
		return listDanhSach;
	}
	public void setListDanhSach(List<Student_KetQuaHocTap> listDanhSach) {
		this.listDanhSach = listDanhSach;
	}
	
	

}
