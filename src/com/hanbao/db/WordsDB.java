package com.hanbao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsDB extends SQLiteOpenHelper {


	public static final String WORD_MEAN = "word_mean";
	public static final String WORD_PROUN = "word_proun";
	public static final String WORD_NAME = "word_name";
	public static final String TABLE_NAME = "words_table";

	public WordsDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table words_table ( id integer primary key autoincrement, word_name text, word_proun text, word_mean text);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "drop table if exists words_table";
		db.execSQL(sql);
		onCreate(db);
	}

	public long add(String wordName, String wordProun, String wordMean) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(WORD_NAME, wordName);
		cv.put(WORD_PROUN, wordProun);
		cv.put(WORD_MEAN, wordMean);
		
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
		
	}
	
	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = "id = ?";
		String[] value = {Integer.toString(id)};
		db.delete(TABLE_NAME, where, value);
		
	}
	
	
}
