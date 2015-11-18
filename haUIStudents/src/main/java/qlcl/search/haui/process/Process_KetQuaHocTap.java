package qlcl.search.haui.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import qlcl.search.haui.obj.KetQuaHocTap;
import qlcl.search.haui.obj.Obj_KetQuaHocTap;
import qlcl.search.haui.obj.Student;

public class Process_KetQuaHocTap {

	List<KetQuaHocTap> listKetQuaHocTap;
	Student student;
	Obj_KetQuaHocTap obj_KetQuaHocTap;
	String maSV;
	String time;

	public Process_KetQuaHocTap(String maSV) {
		super();
		this.maSV = maSV;
	}

	public List<KetQuaHocTap> getListKetQuaHocTap() {
		return listKetQuaHocTap;
	}

	public Student getStudent() {
		return student;
	}

	public Obj_KetQuaHocTap getObj_KetQuaHocTap() {
		return obj_KetQuaHocTap;
	}

	public void load_KetQuaHocTap() {

		listKetQuaHocTap = new ArrayList<KetQuaHocTap>();
		student = new Student();
		obj_KetQuaHocTap = new Obj_KetQuaHocTap();
		Document doc;
		try {
			doc = Jsoup
					.connect(
							"http://qlcl.edu.vn/examre/ket-qua-hoc-tap.htm?code="
									+ maSV)
					.userAgent(
							"Chrome/30.0.1599.101")
					.get();
			Elements element = doc.select("tbody");
			Elements Estrong = element.get(0).select("strong");
			student.setName(Estrong.get(0).text());
			student.setMaSv(Estrong.get(1).text());
			student.setLop(Estrong.get(2).text());
			/*
			 * Elements Estrong = element.get(0).select("strong");
			 * student.setName(Estrong.get(0).text());
			 * student.setMaSv(Estrong.get(1).text());
			 * student.setLop(Estrong.get(2).text());
			 */
			Elements Etr = element.get(1).select("tr");

			for (int i = 0; i < Etr.size(); i++) {
				KetQuaHocTap ketqua = new KetQuaHocTap();
				Elements Etd = Etr.get(i).select("td");
				ketqua.setStt(Etd.get(0).text());
				ketqua.setTenMonHoc(Etd.get(1).text());

				Elements link_a = Etd.get(1).select("a");
				ketqua.setLinkDsLop("http://qlcl.edu.vn"
						+ link_a.get(0).attr("href"));

				ketqua.setMaLopDocLap(Etd.get(2).text());
				ketqua.setDiem1(Etd.get(3).text());
				ketqua.setDiem2(Etd.get(4).text());
				ketqua.setDiem3(Etd.get(5).text());
				ketqua.setDiem4(Etd.get(6).text());
				ketqua.setDiem5(Etd.get(7).text());
				ketqua.setDiem6(Etd.get(8).text());
				ketqua.setDiemGiuaHocPhan(Etd.get(9).text());
				ketqua.setDiemChuyenCan(Etd.get(10).text());
				ketqua.setDiemTrungBinh(Etd.get(11).text());
				ketqua.setSoTietNghi(Etd.get(12).text());
				ketqua.setDieuKienThi(Etd.get(13).text());

				listKetQuaHocTap.add(ketqua);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		obj_KetQuaHocTap.setStudent(student);
		obj_KetQuaHocTap.setListKetQuaHocTap(listKetQuaHocTap);
		time = thoiGian(System.currentTimeMillis());
		obj_KetQuaHocTap.setTime(time);

	}

	String thoiGian(long time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm  - dd/MM/yyyy");
		Date date = new Date(time);
		return format.format(date);
	}
}
