package qlcl.search.haui.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class SaveBitmap {

	Context mContext;

	public SaveBitmap(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public final static String APP_PATH_SD_CARD = "/HaUI/";
	public final static String APP_THUMBNAIL_PATH_SD_CARD = "thumbnails";
	
	
	public boolean saveImageToInternalStorage(Bitmap image) {

		try {
		// Use the compress method on the Bitmap object to write image to
		// the OutputStream
		FileOutputStream fos = mContext.openFileOutput("avatar.png", Context.MODE_PRIVATE);

		// Writing the bitmap to the output stream
		image.compress(Bitmap.CompressFormat.PNG, 100, fos);
		fos.close();

		return true;
		} catch (Exception e) {
		Log.e("saveToInternalStorage()", e.getMessage());
		return false;
		}
		}

	public boolean saveImageToExternalStorage(Bitmap image) {
		String fullPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ APP_PATH_SD_CARD
				+ APP_THUMBNAIL_PATH_SD_CARD;

		try {
			File dir = new File(fullPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			OutputStream fOut = null;
			File file = new File(fullPath, "avatar.png");
			file.createNewFile();
			fOut = new FileOutputStream(file);

			// 100 means no compression, the lower you go, the stronger the
			// compression
			image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();

			MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
					file.getAbsolutePath(), file.getName(), file.getName());

			return true;

		} catch (Exception e) {
			Log.e("saveToExternalStorage()", e.getMessage());
			return false;
		}
	}

	public boolean isSdReadable() {

		boolean mExternalStorageAvailable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = true;
			Log.i("isSdReadable", "External storage card is readable.");
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			Log.i("isSdReadable", "External storage card is readable.");
			mExternalStorageAvailable = true;
		} else {
			// Something else is wrong. It may be one of many other
			// states, but all we need to know is we can neither read nor write
			mExternalStorageAvailable = false;
		}

		return mExternalStorageAvailable;
	}

	public Bitmap getThumbnail(String filename) {

		String fullPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ APP_PATH_SD_CARD
				+ APP_THUMBNAIL_PATH_SD_CARD;
		Bitmap thumbnail = null;

		// Look for the file on the external storage
		try {
			if (isSdReadable() == true) {
				thumbnail = BitmapFactory.decodeFile(fullPath + "/" + filename);
			}
		} catch (Exception e) {
			Log.e("getThumbnail() on external storage", e.getMessage());
		}

		// If no file on external storage, look in internal storage
		if (thumbnail == null) {
			try {
				File filePath = mContext.getFileStreamPath(filename);
				FileInputStream fi = new FileInputStream(filePath);
				thumbnail = BitmapFactory.decodeStream(fi);
			} catch (Exception ex) {
				Log.e("getThumbnail() on internal storage", ex.getMessage());
			}
		}
		return thumbnail;
	}

}
