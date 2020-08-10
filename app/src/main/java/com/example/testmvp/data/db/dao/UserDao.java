package com.example.testmvp.data.db.dao;

import androidx.room.*;
import com.example.testmvp.data.db.entity.User;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<List<Long>> Insert(User... user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void Update(User user);

    @Query("SELECT * FROM USER WHERE pass= :pass AND username= :userName LIMIT 1")
    Single<User> Select(String pass , String userName);
//ss



}
