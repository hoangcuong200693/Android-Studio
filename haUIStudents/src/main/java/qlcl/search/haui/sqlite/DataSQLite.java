package qlcl.search.haui.sqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSQLite extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "suggest";
	private static final String TABLE_NAME = "table_value";
	private static final String KEY_ID = "id";
	private static final String KEY_VALUE = "value";
	private static final String CREATE = "CREATE TABLE " + TABLE_NAME + "("
			+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+KEY_VALUE+" TEXT)";
	

	public DataSQLite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		onCreate(db);

	}
	
	public void insert(String value){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues cv=new ContentValues();
		cv.put(KEY_VALUE, value);
		db.insert(TABLE_NAME, null, cv);
		db.close();
		
	}
	
	public ArrayList<String>  getAllData(){
		ArrayList<String> arr=new ArrayList<String>();
		SQLiteDatabase db=this.getWritableDatabase();
		String query="SELECT * FROM "+TABLE_NAME;
		Cursor c=db.rawQuery(query, null);
		if(c.moveToFirst()){
			do {
				arr.add(c.getString(c.getColumnIndex(KEY_VALUE)));
			} while (c.moveToNext());
		}
		
		return arr;
	}
	
	

}
