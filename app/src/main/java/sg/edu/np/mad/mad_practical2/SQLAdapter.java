package sg.edu.np.mad.mad_practical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class SQLAdapter extends SQLiteOpenHelper {
    public SQLAdapter(Context context)
    {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE= "CREATE TABLE Users" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT," + "DESCRIPTION TEXT," + "FOLLOWED TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }
    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("ID",user.id );
        values.put("NAME",user.name);
        values.put("DESCRIPTION",user.description);
        values.put("FOLLOWED",user.followed);
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Users", null, values);
        db.close();

    }

    public ArrayList<User> GetUser() {
        String query = "SELECT * FROM Users";
        ArrayList<User> userList=new ArrayList<User>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            userList.add(new User(cursor.getString(1), cursor.getString(2), cursor.getInt(0), Boolean.parseBoolean(cursor.getString(3))));
        }
        return userList;
    }
    public boolean UpdateUser(User user)
    {
        String username=user.name;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FOLLOWED",String.valueOf(user.followed));
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE NAME=?", new String[]{username});
        if(cursor.getCount()>0){
            db.update("Users",values,"NAME=?",new String[]{username});
        }
        return user.followed;

    }
}
