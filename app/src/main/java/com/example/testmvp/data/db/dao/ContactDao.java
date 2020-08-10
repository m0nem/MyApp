package com.example.testmvp.data.db.dao;


import androidx.paging.DataSource;
import androidx.room.*;
import com.example.testmvp.data.db.entity.Contact;
import io.reactivex.Single;

import java.util.List;

@Dao
public abstract class ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<Long> Insert(Contact contacts);

    @Delete
    public abstract Single<Integer> DeleteFromContact(Contact contact);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> InsertAllHelper(List<Contact> contacts);

    @Query("SELECT * FROM contact")
    public abstract DataSource.Factory<Integer, Contact> getList();

    @Query("SELECT * FROM contact ")
    public abstract Single<Contact> Select();

    @Query("SELECT * FROM (SELECT * FROM contact WHERE conID <= :requestedInitialKey ORDER BY conID DESC LIMIT :requestedLoadSize ) ORDER BY conID ASC")
    public abstract Single<List<Contact>> getInitialLoad(int requestedLoadSize , Long requestedInitialKey);

    @Query("SELECT * FROM (SELECT * FROM contact WHERE conID < :requestedInitialKey ORDER BY conID DESC LIMIT :requestedLoadSize ) ORDER BY conID ASC")
    public abstract Single<List<Contact>> getLoadBefore(int requestedLoadSize , Long requestedInitialKey);

    @Query("SELECT * FROM contact WHERE conID > :requestedInitialKey ORDER BY conID limit :requestedLoadSize")
    public abstract Single<List<Contact>> getLoadAfter(int requestedLoadSize , Long requestedInitialKey);



}
