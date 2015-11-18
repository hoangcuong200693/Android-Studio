package qlcl.search.haui.obj;

public class KetQuaThi {

	/**
	 * Điểm thi chưa đầy đủ xem ở trang k thông qua đánh giá của giáo viên
	 */
	String monThi;
	String diemTB;
	String diemLan1;
	String diemLan2;
	String diemTBC;
	String diemChu;
	String ghiChu;
	
	

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getDiemTBC() {
		return diemTBC;
	}

	public void setDiemTBC(String diemTBC) {
		this.diemTBC = diemTBC;
	}

	public String getMonThi() {
		return monThi;
	}

	public void setMonThi(String monThi) {
		this.monThi = monThi;
	}

	public String getDiemTB() {
		return diemTB;
	}

	public void setDiemTB(String diemTB) {
		this.diemTB = diemTB;
	}

	public String getDiemLan1() {
		return diemLan1;
	}

	public void setDiemLan1(String diemLan1) {
		this.diemLan1 = diemLan1;
	}

	public String getDiemLan2() {
		return diemLan2;
	}

	public void setDiemLan2(String diemLan2) {
		this.diemLan2 = diemLan2;
	}

	public String getDiemChu() {
		return diemChu;
	}

	public void setDiemChu(String diemChu) {
		this.diemChu = diemChu;
	}

	@Override
	public String toString() {
		return "KetQuaThi [monThi=" + monThi + ", diemTB=" + diemTB
				+ ", diemLan1=" + diemLan1 + ", diemLan2=" + diemLan2
				+ ", diemChu=" + diemChu + "]";
	}

}
