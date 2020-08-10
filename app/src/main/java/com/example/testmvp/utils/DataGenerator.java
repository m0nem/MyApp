package com.example.testmvp.utils;

import com.example.testmvp.data.db.entity.Contact;
import com.example.testmvp.data.db.entity.News;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private DataGenerator() {
    }

    public static Single<List<News>> apiGetNews(final long lastId, final int limit, final boolean getNewItems, final boolean getCurrentItem) {

        return Single.create(new SingleOnSubscribe<List<News>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<News>> emitter) throws Exception {

                Thread.sleep(1000);
                List<News> news = new ArrayList<>();

                if (lastId > 1000) {
                    emitter.onSuccess(news);
                    return;
                }

                int mLimit = limit;
                long mLastId = lastId;
                if (mLimit > 0) {
                    while (mLimit > 0) {
                        News news1 = new News();
                        long currentId = mLastId - 1;
                        if (currentId < 0) {
                            emitter.onSuccess(news);
                            return;
                        }

                        if (getCurrentItem) {

                            if (getNewItems)
                                mLastId--;
                            else
                                mLastId++;

                        }

                        if (getNewItems)
                            news1.setNewsID(++mLastId);
                        else
                            news1.setNewsID(--mLastId);

                        news1.setBody(mLastId + "jfdhfjdf0");
                        news1.setDate("sdfsdfsdf");
                        news1.setTitle("sdfsdfsdf");
                        news.add(news1);
                        mLimit--;
                    }

                }
                emitter.onSuccess(news);
            }
        });

    }


    public static List<News> apiGetNewsHelper(final long lastId, final int limit, final boolean getNewItems, final boolean getCurrentItem) {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<News> news = new ArrayList<>();

        if (lastId > 1000) {
            return news;
        }

        int mLimit = limit;
        long mLastId = lastId;
        if (mLimit > 0) {
            while (mLimit > 0) {
                News news1 = new News();
                long currentId = mLastId - 1;
                if (currentId < 0) {
                    return news;
                }

                if (getCurrentItem) {

                    if (getNewItems)
                        mLastId--;
                    else
                        mLastId++;

                }

                if (getNewItems)
                    news1.setNewsID(++mLastId);
                else
                    news1.setNewsID(--mLastId);

                news1.setBody(mLastId + "japi");
                news1.setDate("api");
                news1.setTitle("api");
                news.add(news1);
                mLimit--;
            }

        }
        return news;
    }

    public static Single<List<Contact>> apiGetContact(final long lastId, final int limit, final boolean getNewItems, final boolean getCurrentItem) {

        return Single.create(new SingleOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Contact>> emitter) throws Exception {

                Thread.sleep(1000);
                List<Contact> contacts = new ArrayList<>();

                if (lastId > 1000) {
                    emitter.onSuccess(contacts);
                    return;
                }

                int mLimit = limit;
                long mLastId = lastId;
                if (mLimit > 0) {
                    while (mLimit > 0) {
                        Contact con1 = new Contact();
                        long currentId = mLastId - 1;
                        if (currentId < 0) {
                            emitter.onSuccess(contacts);
                            return;
                        }

                        if (getCurrentItem) {

                            if (getNewItems)
                                mLastId--;
                            else
                                mLastId++;

                        }


                        con1.setConName(mLastId + "japi_name");
                        con1.setDate(" 23/23/ "+mLastId+" 1992");
                        con1.setCode("code : "+mLastId);
                        con1.setMail("mrafdf@"+mLastId+".com");
                        con1.setPhone("0938"+mLastId);
                        contacts.add(con1);
                        mLimit--;
                    }

                }
                emitter.onSuccess(contacts);
            }
        });

    }


    public static List<Contact> apiGetContactHelper(final long lastId, final int limit, final boolean getNewItems, final boolean getCurrentItem) {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Contact> contacts = new ArrayList<>();

        if (lastId > 1000) {
            return contacts ;
        }

        int mLimit = limit;
        long mLastId = lastId;
        if (mLimit > 0) {
            while (mLimit > 0) {
                Contact con1 = new Contact();
                long currentId = mLastId - 1;
                if (currentId < 0) {
                    return contacts;
                }

                if (getCurrentItem) {

                    if (getNewItems)
                        mLastId--;
                    else
                        mLastId++;

                }


                if (getNewItems)
                  ++mLastId;
                else
                    --mLastId;

                con1.setConName(mLastId + "japi_name");
                con1.setDate(" 23/23/ "+mLastId+" 1992");
                con1.setCode("code : "+mLastId);
                con1.setMail("mrafdf@"+mLastId+".com");
                con1.setPhone("0938"+mLastId);
                contacts.add(con1);
                mLimit--;
            }

        }
        return contacts;
    }


}
