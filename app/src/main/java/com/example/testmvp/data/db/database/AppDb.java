package com.example.testmvp.data.db.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.testmvp.data.db.dao.ContactDao;
import com.example.testmvp.data.db.dao.NewsDao;
import com.example.testmvp.data.db.dao.UserDao;
import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.data.db.entity.News;
import com.example.testmvp.data.db.entity.User;

@Database(entities = {User.class , News.class , Contact.class}, version =4, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract NewsDao newsDao();
    public abstract ContactDao  contactDao();

    private static volatile AppDb INSTANCE;

    public static AppDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, "room_new_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
