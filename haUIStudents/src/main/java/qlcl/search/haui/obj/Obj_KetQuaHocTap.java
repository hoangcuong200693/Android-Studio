package qlcl.search.haui.obj;

import java.io.Serializable;
import java.util.List;

public class Obj_KetQuaHocTap implements Serializable {

	Student student;
	List<KetQuaHocTap> listKetQuaHocTap;
	String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<KetQuaHocTap> getListKetQuaHocTap() {
		return listKetQuaHocTap;
	}

	public void setListKetQuaHocTap(List<KetQuaHocTap> listKetQuaHocTap) {
		this.listKetQuaHocTap = listKetQuaHocTap;
	}

}
