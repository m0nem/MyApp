package com.example.testmvp.data.db.dao;


import androidx.paging.DataSource;
import androidx.room.*;
import com.example.testmvp.data.db.entity.News;
import io.reactivex.Single;

import java.util.List;

@Dao
public abstract class NewsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<List<Long>> InsertAll(List<News> news);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> InsertAllHelper(List<News> news);


    @Query("SELECT * FROM news")
    public abstract DataSource.Factory<Integer, News> getList();


    @Query("SELECT * FROM news ")
    public abstract Single<News> Select();


    @Query("SELECT * FROM (SELECT * FROM news WHERE NewsID <= :requestedInitialKey ORDER BY NewsID DESC LIMIT :requestedLoadSize ) ORDER BY NewsID ASC")
    public abstract Single<List<News>> getInitialLoad(int requestedLoadSize , Long requestedInitialKey);



    @Query("SELECT * FROM (SELECT * FROM news WHERE NewsID < :requestedInitialKey ORDER BY NewsID DESC LIMIT :requestedLoadSize ) ORDER BY NewsID ASC")
    public abstract Single<List<News>> getLoadBefore(int requestedLoadSize , Long requestedInitialKey);


    @Query("SELECT * FROM news WHERE NewsID > :requestedInitialKey ORDER BY NewsID limit :requestedLoadSize")
    public abstract Single<List<News>> getLoadAfter(int requestedLoadSize , Long requestedInitialKey);


}
