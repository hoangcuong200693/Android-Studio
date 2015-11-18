package qlcl.search.haui.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import qlcl.search.haui.obj.LichThi;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.obj.Student;

public class Process_XemLichThi {

	List<LichThi> listLichThi;
	Student student;
	String maSV;
	Obj_LichThi obj_lichTHi;
	String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Process_XemLichThi(String ma) {
		super();
		this.maSV = ma;
	}

	public void load_LichThi() {

		listLichThi = new ArrayList<LichThi>();
		student = new Student();
		obj_lichTHi = new Obj_LichThi();

		Document doc;
		try {
			doc = Jsoup
					.connect(
							"http://qlcl.edu.vn/examplanuser/ke-hoach-thi.htm?code="
									+ maSV)
					.userAgent(
							"Chrome/30.0.1599.101")
					.get();
			Elements element = doc.select("tbody");
			Elements Estrong = element.get(0).select("strong");
			student.setName(Estrong.get(0).text());
			student.setMaSv(Estrong.get(1).text());
			student.setLop(Estrong.get(2).text());

			// Elements Estrong = element.get(0).select("strong");
			Elements Etr = element.get(1).select("tr");
			/*
			 * student.setName(Estrong.get(0).text());
			 * student.setMaSv(Estrong.get(1).text());
			 * student.setLop(Estrong.get(2).text());
			 */

			// System.out.println(student);
			for (int i = 0; i < Etr.size(); i++) {
				LichThi lich = new LichThi();
				Elements Etd = Etr.get(i).select("td");
				lich.setStt(Etd.get(0).text());
				lich.setTenMonThi(Etd.get(1).text());
				lich.setNgayThi(Etd.get(2).text() + "-" + Etd.get(3).text());
				lich.setCaThi(Etd.get(4).text());
				lich.setSBD(Etd.get(5).text());
				lich.setLanThi(Etd.get(6).text());
				lich.setPhongThi(Etd.get(7).text());
				lich.setDiaDiem(Etd.get(8).text());
				lich.setLePhiThi(Etd.get(9).text());
				lich.setGhiChu(Etd.get(10).text());
				listLichThi.add(lich);

			}

			/*
			 * Elements Etd=Etr.get(1).select("td"); for(int
			 * i=0;i<Etd.size();i++){ System.out.println(Etd.get(i).text()); }
			 */

			// System.out.println(element.size());
			// System.out.println(Etr.size());
		} catch (Exception e) {
			// TODO: handle exception
		}

		// System.out.println("ok");
		obj_lichTHi.setList_LichThi(listLichThi);
		obj_lichTHi.setStudent(student);
		time=thoiGian(System.currentTimeMillis());
		obj_lichTHi.setTime(time);

	}

	public List<LichThi> getListLichThi() {
		return listLichThi;
	}

	public Student getStudent() {
		return student;
	}

	public Obj_LichThi getObj_lichTHi() {
		return obj_lichTHi;
	}
	String thoiGian(long time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm  - dd/MM/yyyy");
		Date date = new Date(time);
		return format.format(date);
	}

}
