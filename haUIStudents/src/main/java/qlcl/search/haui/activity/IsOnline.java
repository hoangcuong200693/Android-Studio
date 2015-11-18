package qlcl.search.haui.activity;

import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author Eo
 * <b>Class cho phép kiểm tra tình trạng kết nối mạng</b>
 *
 */
public class IsOnline {
	Context ct;

	public IsOnline(Context ct) {
		this.ct = ct;
	}

	public boolean checkOnline() {
		ConnectivityManager conMgr = (ConnectivityManager) ct
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
	
		if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable() ||!netInfo.isConnectedOrConnecting() ) {
			return false;
		} else {
		}
		return true;
	}

}
