package com.egeetouch.egeetouch_manager;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/* loaded from: classes.dex */
public class DatabaseVariable extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static String DB_name_lock = "lockdb";
    public static String DB_name_lock_new = "lockdbnew";
    public static SQLiteDatabase dbLockNew = null;
    public static SQLiteDatabase db_audittrail = null;
    public static SQLiteDatabase db_location = null;
    public static SQLiteDatabase db_lock = null;
    public static SQLiteDatabase db_otp = null;
    public static SQLiteDatabase db_tag = null;
    public static SQLiteDatabase db_user = null;
    public static String sample_colunm = "upgrade";
    public static String Element_name_lock = "Lock";
    public static String D1_lock = "Name";
    public static String D2_lock = "Password";
    public static String D3_lock = "Model";
    public static String D4_lock = "Version";
    public static String D5_lock = "Setting";
    public static String D6_lock = "ip45Serial";
    public static String D7_lock = "MACAddress";
    public static String D8_lock = "Type";
    public static String D9_lock = "ShareStartTime";
    public static String D10_lock = "ShareEndTime";
    public static String D11_lock = "sharedBy";
    public static String D12_lock = "shareAccessToken";
    public static String D13_lock = "isOfflineLock";
    public static String Create_lock_db = "CREATE TABLE IF NOT EXISTS " + Element_name_lock + "(" + D1_lock + " VARCHAR, " + D2_lock + " VARCHAR, " + D3_lock + " VARCHAR, " + D4_lock + " VARCHAR, " + D5_lock + " VARCHAR, " + D6_lock + " VARCHAR, " + D7_lock + " VARCHAR, " + D8_lock + " VARCHAR, " + D9_lock + " VARCHAR, " + D10_lock + " VARCHAR, " + D11_lock + " VARCHAR, " + D12_lock + " VARCHAR, " + D13_lock + " VARCHAR);";
    public static String Element_name_lock_new = "LockNew";
    public static String D1_lock_new = "Name";
    public static String D2_lock_new = "Password";
    public static String D3_lock_new = "Model";
    public static String D4_lock_new = "Version";
    public static String D5_lock_new = "Setting";
    public static String D6_lock_new = "Serial";
    public static String D7_lock_new = "MACAddress";
    public static String D6A_lock_new = "ip45SerialNumber";
    public static String D8_lock_new = "Type";
    public static String D9_lock_new = "ShareStartTime";
    public static String D10_lock_new = "ShareEndTime";
    public static String D11_lock_new = "sharedBy";
    public static String Create_lock_db_new = "CREATE TABLE IF NOT EXISTS " + Element_name_lock_new + "(" + D1_lock_new + " VARCHAR, " + D2_lock_new + " VARCHAR, " + D3_lock_new + " VARCHAR, " + D4_lock_new + " VARCHAR, " + D5_lock_new + " VARCHAR, " + D6_lock_new + " VARCHAR, " + D7_lock_new + " VARCHAR, " + D6A_lock_new + " VARCHAR, " + D8_lock_new + " VARCHAR, " + D9_lock_new + " VARCHAR, " + D10_lock_new + " VARCHAR, " + D11_lock_new + " VARCHAR);";
    public static String Query_lock_db = "SELECT * FROM " + Element_name_lock;
    public static String Query_lock_name_db = "SELECT " + D1_lock + " FROM " + Element_name_lock;
    public static String DB_name_tag = "tagdb";
    public static String Element_name_tag = "Tag";
    public static String D1_tag = "TagName";
    public static String D2_tag = "UID";
    public static String D3_tag = "Lock";
    public static String Create_tag_db = "CREATE TABLE IF NOT EXISTS " + Element_name_tag + "(" + D1_tag + " VARCHAR, " + D2_tag + " VARCHAR, " + D3_tag + " VARCHAR);";
    public static String Query_tag_db = "SELECT * FROM " + Element_name_tag;
    public static String Query_tag_name_db = "SELECT " + D1_tag + " FROM " + Element_name_tag;
    public static String Query_tag_inlock_db = "SELECT " + D1_tag + " FROM " + Element_name_tag;
    public static String Query_tag_id_db = "SELECT " + D2_tag + " FROM " + Element_name_tag;
    public static String DB_name_user = "userdb";
    public static String Element_name_user = "User";
    public static String D1_user = "UserName";
    public static String D2_user = "UserPassword";
    public static String D3_user = "Lock";
    public static String Create_user_db = "CREATE TABLE IF NOT EXISTS " + Element_name_user + "(" + D1_user + " VARCHAR, " + D2_user + " VARCHAR, " + D3_user + " VARCHAR);";
    public static String Query_user_db = "SELECT * FROM " + Element_name_user;
    public static String Query_user_name_db = "SELECT " + D1_user + " FROM " + Element_name_user;
    public static String DB_name_audittrail = "audittraildb";
    public static String Element_name_audittrail = "AuditTrail";
    public static String D1_audittrail = "Serial";
    public static String D2_audittrail = "User";
    public static String D3_audittrail = "Date_Time";
    public static String D4_audittrail = "Location_Latitude";
    public static String D5_audittrail = "Location_longitude";
    public static String D6_audittrail = "Last_updated_time";
    public static String D7_audittrail = "Lock";
    public static String Create_audittrail_db = "CREATE TABLE IF NOT EXISTS " + Element_name_audittrail + "(" + D1_audittrail + " VARCHAR, " + D2_audittrail + " VARCHAR, " + D3_audittrail + " VARCHAR, " + D4_audittrail + " VARCHAR, " + D5_audittrail + " VARCHAR, " + D6_audittrail + " VARCHAR, " + D7_audittrail + " VARCHAR);";
    public static String Query_audittrail_db = "SELECT * FROM " + Element_name_audittrail;
    public static String Query_audittrail_name_db = "SELECT " + D1_audittrail + " FROM " + Element_name_audittrail;
    public static String DB_name_location = "locationdb";
    public static String Element_name_location = "Location";
    public static String D1_location = "Serial";
    public static String D2_location = "Formatted_address";
    public static String D3_location = "Lock";
    public static String Create_location_db = "CREATE TABLE IF NOT EXISTS " + Element_name_location + "(" + D1_location + " VARCHAR, " + D2_location + " VARCHAR, " + D3_location + " VARCHAR);";

    public static String lockdb_Query() {
        return "SELECT * FROM Lock";
    }

    public DatabaseVariable(Context context) {
        super(context, DB_name_lock, (SQLiteDatabase.CursorFactory) null, 1);
        Log.i("SQLDatabase", "HEY DatabaseVariable with verson: 1 is getting called");
        db_lock = getWritableDatabase();
    }

    public String lockdb_insert_value(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        db_lock = getWritableDatabase();
        return "INSERT INTO " + Element_name_lock + " VALUES(" + DatabaseUtils.sqlEscapeString(str) + ",'" + str2 + "','" + str3 + "','" + str4 + "','" + str5 + "','" + str6 + "','" + str7 + "','" + str8 + "','" + str9 + "','" + str10 + "','" + str11 + "','" + str12 + "','" + str13 + "');";
    }

    public static String lockdb_new_format_from_firebase(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Double d, Double d2, String str10) {
        return "INSERT INTO " + Element_name_lock + " VALUES(" + DatabaseUtils.sqlEscapeString(str) + ",'" + str2 + "','" + str3 + "','" + str4 + "','" + str5 + "','" + str6 + "','" + str8 + "' ,'" + str7 + "' , '" + str9 + "', '" + d + "', '" + d2 + "', '" + str10 + "');";
    }

    public static String lockdb_delete_value(String str) {
        return "DELETE FROM " + Element_name_lock + " WHERE Name =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String lockdb_delete_everything() {
        System.out.println("HEY I AM DELETING EVERYTHING ON PHONE DB");
        return "DELETE FROM Lock";
    }

    public static String lockdb_Query_name_exist(String str) {
        return "SELECT * FROM Lock WHERE Name =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String lockdb_Query_model(String str) {
        return "SELECT * FROM Lock WHERE Name =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String lockdb_getversion(String str) {
        Cursor rawQuery = db_lock.rawQuery(lockdb_Query_model(BLEService.selected_lock_name), null);
        String str2 = "";
        while (rawQuery.moveToNext()) {
            str2 = rawQuery.getString(3);
        }
        rawQuery.close();
        return str2;
    }

    public static String lockdb_update_new_password(String str, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str2);
        return "UPDATE " + Element_name_lock + " SET Password = " + String.valueOf(DatabaseUtils.sqlEscapeString(str)) + " WHERE Name = " + String.valueOf(sqlEscapeString);
    }

    public static String lockdb_update_version(String str, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        String sqlEscapeString2 = DatabaseUtils.sqlEscapeString(str2);
        Log.i("sql", "version: " + sqlEscapeString + " lock: " + sqlEscapeString2);
        return "UPDATE " + Element_name_lock + " SET Version = " + String.valueOf(sqlEscapeString) + " WHERE Name = " + String.valueOf(sqlEscapeString2);
    }

    public static String lockdb_update_new_battery_level(String str, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        return "UPDATE " + Element_name_lock + " SET Setting = " + String.valueOf(DatabaseUtils.sqlEscapeString(str2)) + " WHERE Name = " + String.valueOf(sqlEscapeString);
    }

    public static String tagdb_insert_value(String str, String str2, String str3) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        return "INSERT INTO " + Element_name_tag + " VALUES(" + sqlEscapeString + ",'" + str2 + "'," + DatabaseUtils.sqlEscapeString(str3) + ");";
    }

    public static String tagdb_delete_value(String str, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        return "DELETE FROM Tag WHERE TagName =" + sqlEscapeString + " AND Lock =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String tagdb_delete_value_bylock(String str) {
        return "DELETE FROM Tag WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String tagdb_Query_name_exist(String str, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        return "SELECT * FROM Tag WHERE TagName =" + sqlEscapeString + " AND Lock =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String tagdb_Query_UID_exist(String str, String str2) {
        DatabaseUtils.sqlEscapeString(str);
        return "SELECT * FROM Tag WHERE Lock =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String tagdb_Query_UID_exist2(String str, String str2) {
        return "SELECT * FROM Tag WHERE Lock =" + DatabaseUtils.sqlEscapeString(str2) + " AND TagName = " + DatabaseUtils.sqlEscapeString(str);
    }

    public static String tagdb_Query_name_UID_exist(String str, String str2, String str3) {
        return "SELECT * FROM Tag WHERE UID = " + str2 + " AND TagName =" + str + " AND Lock =" + DatabaseUtils.sqlEscapeString(str3);
    }

    public static String tagdb_Query_model(String str) {
        return "SELECT * FROM Tag WHERE TagName =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String tagdb_Query_tag_name_in_lock(String str) {
        return Query_tag_name_db + " WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String tagdb_Query_tag_Id_in_lock(String str) {
        return Query_tag_id_db + " WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String tagdb_number_lock_exist(String str) {
        return "SELECT * FROM Tag WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String tagdb_tag_UID_retriving(String str) {
        return "SELECT * FROM Tag WHERE TagName =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_insert_value(String str, String str2, String str3) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        String sqlEscapeString2 = DatabaseUtils.sqlEscapeString(str2);
        return "INSERT INTO " + Element_name_user + " VALUES(" + sqlEscapeString + "," + sqlEscapeString2 + "," + DatabaseUtils.sqlEscapeString(str3) + ");";
    }

    public static String userdb_delete_value(String str) {
        return "DELETE FROM User WHERE UserName =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_delete_value_bylock(String str) {
        return "DELETE FROM User WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_Query_name_exist(String str) {
        return "SELECT * FROM User WHERE Name =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_Query_model(String str) {
        return "SELECT * FROM User WHERE Name =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_Query_user_name_in_lock(String str) {
        return Query_user_name_db + " WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_Query_user_name_in_lock2(String str) {
        return "SELECT * FROM User WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String userdb_Query_user_name_exist_in_lock(String str, String str2) {
        return "SELECT * FROM User WHERE Lock =" + DatabaseUtils.sqlEscapeString(str) + " AND UserName =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String userdb_Query_password_exist_in_lock(String str, String str2) {
        return "SELECT * FROM User WHERE Lock =" + DatabaseUtils.sqlEscapeString(str) + " AND UserPassword =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String db_audittrail_update_value(int i, String str, String str2, Double d, Double d2, String str3, String str4) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str4);
        Cursor rawQuery = db_audittrail.rawQuery(Query_audittrail_db + " WHERE " + D1_audittrail + " = " + String.valueOf(i), null);
        if (rawQuery.moveToNext()) {
            return "UPDATE " + Element_name_audittrail + " SET " + D1_audittrail + " = '" + String.valueOf(i) + "'," + D2_audittrail + " = '" + str + "'," + D3_audittrail + " = '" + str2 + "'," + D4_audittrail + " = '" + String.valueOf(d) + "'," + D5_audittrail + " = '" + String.valueOf(d2) + "'," + D6_audittrail + " = '" + str3 + "'," + D7_audittrail + " = " + sqlEscapeString + " WHERE " + D1_audittrail + " = " + String.valueOf(i);
        }
        rawQuery.close();
        return "INSERT INTO " + Element_name_audittrail + " VALUES('" + i + "','" + str + "','" + str2 + "','" + String.valueOf(d) + "','" + String.valueOf(d2) + "','" + str3 + "'," + sqlEscapeString + ")";
    }

    public static String db_audittrail_update_value_bt_1(int i, String str, Double d, String str2, String str3) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str3);
        Cursor rawQuery = db_audittrail.rawQuery(Query_audittrail_db + " WHERE " + D1_audittrail + " = " + String.valueOf(i), null);
        if (rawQuery.moveToNext()) {
            return "UPDATE " + Element_name_audittrail + " SET " + D1_audittrail + " = '" + String.valueOf(i) + "'," + D3_audittrail + " = '" + str + "'," + D4_audittrail + " = '" + String.valueOf(d) + "'," + D6_audittrail + " = '" + str2 + "'," + D7_audittrail + " = " + sqlEscapeString + " WHERE " + D1_audittrail + " = " + String.valueOf(i);
        }
        rawQuery.close();
        return "INSERT INTO " + Element_name_audittrail + " VALUES('" + i + "','0','" + str + "','" + String.valueOf(d) + "','" + String.valueOf(0) + "','" + str2 + "'," + sqlEscapeString + ")";
    }

    public static String db_audittrail_update_value_bt_2(int i, String str, Double d, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str2);
        Cursor rawQuery = db_audittrail.rawQuery(Query_audittrail_db + " WHERE " + D1_audittrail + " = " + String.valueOf(i), null);
        if (rawQuery.moveToNext()) {
            return "UPDATE " + Element_name_audittrail + " SET " + D1_audittrail + " = '" + String.valueOf(i) + "'," + D2_audittrail + " = '" + str + "'," + D5_audittrail + " = '" + String.valueOf(d) + "'," + D7_audittrail + " = " + sqlEscapeString + " WHERE " + D1_audittrail + " = " + String.valueOf(i);
        }
        rawQuery.close();
        return "INSERT INTO " + Element_name_audittrail + " VALUES('" + i + "','" + str + "','0','" + String.valueOf(0) + "','" + String.valueOf(d) + "','0'," + sqlEscapeString + ")";
    }

    public static String db_audittrail_lock(String str) {
        return "SELECT * FROM AuditTrail WHERE Lock = " + DatabaseUtils.sqlEscapeString(str);
    }

    public static String db_audittrail_lock2(String str) {
        return "SELECT * FROM AuditTrail WHERE Lock = " + DatabaseUtils.sqlEscapeString(str) + "ORDER BY " + D6_audittrail + " ASC";
    }

    public static String audittrail_db_delete_value_bylock(String str) {
        return "DELETE FROM AuditTrail WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    public static String locationdb_insert_value(String str, String str2, String str3) {
        return "INSERT INTO " + Element_name_location + " VALUES('" + str + "','" + str2 + "'," + DatabaseUtils.sqlEscapeString(str3) + ");";
    }

    public static String locationdb_Query_addess(String str, String str2) {
        return "SELECT Formatted_address FROM Location WHERE Serial = " + str + " AND Lock =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String locationdb_delete_value(String str, String str2) {
        String sqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        return "DELETE FROM " + Element_name_location + " WHERE Serial =" + sqlEscapeString + " AND Lock =" + DatabaseUtils.sqlEscapeString(str2);
    }

    public static String locationdb_delete_value_bylock(String str) {
        return "DELETE FROM Location WHERE Lock =" + DatabaseUtils.sqlEscapeString(str);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(Create_lock_db);
        System.out.println("HEY DatabaseVariable onCreate is getting called ");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        System.out.println("HEY DatabaseVariable upgrade!! oldVersion+" + i + " new verson:" + i2);
    }

    public static String getTableAsString(SQLiteDatabase sQLiteDatabase, String str) {
        System.out.println("HEY getTableAsString called");
        String format = String.format("Table %s:\n", str);
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str, null);
        if (rawQuery.moveToFirst()) {
            String[] columnNames = rawQuery.getColumnNames();
            do {
                for (String str2 : columnNames) {
                    format = format + String.format("%s: %s\n", str2, rawQuery.getString(rawQuery.getColumnIndex(str2)));
                }
                format = format + "\n";
            } while (rawQuery.moveToNext());
            return format;
        }
        return format;
    }
}
