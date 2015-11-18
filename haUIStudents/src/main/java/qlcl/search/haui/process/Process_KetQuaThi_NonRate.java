package qlcl.search.haui.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qlcl.search.haui.obj.KetQuaThi;

/**
 * 
 * @author Eo Cuong Class lấy điểm thi bằng trang mà k thông qua đánh giá ý kiến
 *         giáo viên
 */
public class Process_KetQuaThi_NonRate {

	List<KetQuaThi> list;
	String maSV;

	public Process_KetQuaThi_NonRate(String maSV) {
		super();
		this.maSV = maSV;
	}

	public List<KetQuaThi> getKetQuaThi() {
		list = new ArrayList<KetQuaThi>();
		Document doc = null;

		try {
			doc = Jsoup
					.connect(
							"http://qlcl.edu.vn/searchstexre/ket-qua-thi.htm?code="
									+ this.maSV)
					.userAgent("Chrome/30.0.1599.101").get();

			Elements Etbody = doc.select("tbody");
			if (Etbody.size() > 1) {
				Element table = Etbody.get(1);
				Elements Etr = table.select("tr");
				// System.out.println(Etr.size());
				for (int i = 0; i < Etr.size() - 1; i++) {

					Elements Etd = Etr.get(i).select("td");
					KetQuaThi kqt = new KetQuaThi();
					kqt.setMonThi(Etd.get(1).text());
					kqt.setDiemLan1(Etd.get(2).text());
					kqt.setDiemLan2(Etd.get(3).text());
					kqt.setDiemTB(Etd.get(8).text());
					kqt.setDiemChu(getDiemChu(Etd.get(8).text()));
					kqt.setDiemTBC(getDiemTBC(Etd.get(8).text()));
					kqt.setGhiChu(Etd.get(11).text());
					list.add(kqt);

				}
			}
			// System.out.println(list);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	String getDiemTBC(String diem) {

		if (!diem.trim().equals("**")) {
			int index = diem.indexOf("(");
			return diem.substring(0, index);
		} else {
			return diem;
		}

	}

	String getDiemChu(String diem) {
		if (!diem.trim().equals("**")) {
			int index = diem.indexOf("(");
			return diem.substring(index + 1, index + 2);
		} else {
			return diem;
		}

	}

}
