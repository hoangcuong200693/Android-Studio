package qlcl.search.haui.process;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import qlcl.search.haui.obj.MonHoc;

/**
 * 
 * @author Eo Cuong Class thực hiện việc tính điểm tích lũy của một sinh viên
 */
public class Process_TinhTichTuy {

	String time;

	List<MonHoc> listMonHoc;
	int soDiemA = 0;
	int soDiemB = 0;
	int soDiemC = 0;
	int soDiemD = 0;
	int soDiemF = 0;

	int tinchA = 0;
	int tinchB = 0;
	int tinchC = 0;
	int tinchD = 0;
	int tinchF = 0;

	int tinchi;
	List<String> listA = new ArrayList<String>();
	List<String> listB = new ArrayList<String>();
	List<String> listC = new ArrayList<String>();
	List<String> listD = new ArrayList<String>();
	List<String> listF = new ArrayList<String>();

	public int getSoDiemA() {
		return soDiemA / 2;
	}

	public void setSoDiemA(int soDiemA) {
		this.soDiemA = soDiemA;
	}

	public int getSoDiemB() {
		return soDiemB / 2;
	}

	public void setSoDiemB(int soDiemB) {
		this.soDiemB = soDiemB;
	}

	public int getSoDiemC() {
		return soDiemC / 2;
	}

	public void setSoDiemC(int soDiemC) {
		this.soDiemC = soDiemC;
	}

	public int getSoDiemD() {
		return soDiemD / 2;
	}

	public void setSoDiemD(int soDiemD) {
		this.soDiemD = soDiemD;
	}

	public int getSoDiemF() {
		return soDiemF / 2;
	}

	public void setSoDiemF(int soDiemF) {
		this.soDiemF = soDiemF;
	}

	public int getTinchi() {
		return tinchi;
	}

	public void setTinchi(int tinchi) {
		this.tinchi = tinchi;
	}

	/**
	 * Khởi tạo tham số truyền vào là 1 danh sách các môn thi
	 * 
	 * @param listMonHoc
	 * 
	 */
	public Process_TinhTichTuy(List<MonHoc> listMonHoc) {
		super();
		this.listMonHoc = listMonHoc;
	}

	/**
	 * 
	 * @return điểm tích lũy của một sinh viên
	 */
	public String getTichLuy() {

		try {

			DecimalFormat format = new DecimalFormat("#0.00");
			int tong = 0;
			tinchi = 0;
			for (MonHoc mh : this.listMonHoc) {
			
				if (layMonHoc(mh)) {
					tong += getDiemSo(mh.getDiemTinChi())
							* Integer.parseInt(mh.getSoTinChi());
					tinchi += Integer.parseInt(mh.getSoTinChi());

					if (mh.getDiemTinChi().trim().equals("A")) {
						soDiemA++;
						tinchA += Integer.parseInt(mh.getSoTinChi());
						if (!listA.contains(mh.getTenMonHoc() + " ("
								+ mh.getSoTinChi() + " tín chỉ" + ")")) {
							listA.add(mh.getTenMonHoc() + " ("
									+ mh.getSoTinChi() + " tín chỉ" + ")");
						}
					} else if (mh.getDiemTinChi().trim().equals("B")) {
						soDiemB++;
						tinchB += Integer.parseInt(mh.getSoTinChi());
						if (!listB.contains(mh.getTenMonHoc() + " ("
								+ mh.getSoTinChi() + " tín chỉ" + ")")) {
							listB.add(mh.getTenMonHoc() + " ("
									+ mh.getSoTinChi() + " tín chỉ" + ")");
						}
					} else if (mh.getDiemTinChi().trim().equals("C")) {
						soDiemC++;
						tinchC += Integer.parseInt(mh.getSoTinChi());
						if (!listC.contains(mh.getTenMonHoc() + " ("
								+ mh.getSoTinChi() + " tín chỉ" + ")")) {
							listC.add(mh.getTenMonHoc() + " ("
									+ mh.getSoTinChi() + " tín chỉ" + ")");
						}
					} else if (mh.getDiemTinChi().trim().equals("D")) {
						soDiemD++;
						tinchD += Integer.parseInt(mh.getSoTinChi());
						if (!listD.contains(mh.getTenMonHoc() + " ("
								+ mh.getSoTinChi() + " tín chỉ" + ")")) {
							listD.add(mh.getTenMonHoc() + " ("
									+ mh.getSoTinChi() + " tín chỉ" + ")");
						}
					}
				} else {
					if (mh.getDiemTinChi().trim().equals("F")) {
						soDiemF++;
						tinchF += Integer.parseInt(mh.getSoTinChi());
						if (!listF.contains(mh.getTenMonHoc() + " ("
								+ mh.getSoTinChi() + " tín chỉ" + ")")) {
							listF.add(mh.getTenMonHoc() + " ("
									+ mh.getSoTinChi() + " tín chỉ" + ")");
						}
					}
				}
			}

			// System.out.println("Tin chi tich luy "+tinchi);

			long t = System.currentTimeMillis();
			time = thoiGian(t);

			return format.format((double) tong / tinchi);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 
	 * @param Môn
	 *            học Kiểm tra môn học có đạt điều kiện để lấy tính điểm tích
	 *            lũy hay k
	 * @return true or false
	 */
	boolean layMonHoc(MonHoc mh) {
		if (!mh.getDiemTBC().trim().equals("")) {
			if (mh.getDiemTinChi().trim().equals("A")
					|| mh.getDiemTinChi().trim().equals("B")
					|| mh.getDiemTinChi().trim().equals("C")
					|| mh.getDiemTinChi().trim().equals("D")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param điểm
	 *            tín chỉ chữ
	 * @return điểm số ứng theo điểm tín chỉ
	 */
	int getDiemSo(String diemchu) {
		if (diemchu.equals("A")) {
			return 4;
		}

		if (diemchu.equals("B")) {
			return 3;
		}

		if (diemchu.equals("C")) {
			return 2;
		}

		if (diemchu.equals("D")) {
			return 1;
		}

		if (diemchu.equals("F")) {
			return 0;
		}
		return -1;

	}

	String thoiGian(long time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm  - dd/MM/yyyy");
		Date date = new Date(time);
		return format.format(date);
	}

	/**
	 * Kiểm tra xem danh sách có chuẩn chưa, nếu có môn chưa nộp lệ phí thì thì
	 * chưa chuẩn
	 * 
	 * @return
	 */
	public boolean hopLe() {
		int count = 0;
		for (MonHoc mh : this.listMonHoc) {
			if (mh.getDiemTinChi().trim().equals("...")
					|| mh.getDiemTinChi().trim().equals("**")) {
				count++;
			}
		}

		if (count != 0) {
			return false;
		} else {
			return true;
		}

	}

	public List<String> getListA() {
		return listA;
	}

	public void setListA(List<String> listA) {
		this.listA = listA;
	}

	public List<String> getListB() {
		return listB;
	}

	public void setListB(List<String> listB) {
		this.listB = listB;
	}

	public List<String> getListC() {
		return listC;
	}

	public void setListC(List<String> listC) {
		this.listC = listC;
	}

	public List<String> getListD() {
		return listD;
	}

	public void setListD(List<String> listD) {
		this.listD = listD;
	}

	public List<String> getListF() {
		return listF;
	}

	public void setListF(List<String> listF) {
		this.listF = listF;
	}

	public int getTinchA() {
		return tinchA / 2;
	}

	public void setTinchA(int tinchA) {
		this.tinchA = tinchA;
	}

	public int getTinchB() {
		return tinchB / 2;
	}

	public void setTinchB(int tinchB) {
		this.tinchB = tinchB;
	}

	public int getTinchC() {
		return tinchC / 2;
	}

	public void setTinchC(int tinchC) {
		this.tinchC = tinchC;
	}

	public int getTinchD() {
		return tinchD / 2;
	}

	public void setTinchD(int tinchD) {
		this.tinchD = tinchD;
	}

	public int getTinchF() {
		return tinchF / 2;
	}

	public void setTinchF(int tinchF) {
		this.tinchF = tinchF;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
