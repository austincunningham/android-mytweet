package app.mytweet.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import app.mytweet.models.Tweet;

public class DbHelper extends SQLiteOpenHelper
{
    static final String TAG = "DbHelper";
    static final String DATABASE_NAME = "tweets.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_TWEET = "tableTweets";

    static final String PRIMARY_KEY = "id";
    static final String TWEETCONTENT = "tweetcontent";
    static final String DATE = "date";

    Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "CREATE TABLE tableTweets " +
                        "(id text primary key, " +
                        "tweetcontent text," +
                        "date text)";

        db.execSQL(createTable);
        Log.d(TAG, "DbHelper.onCreated: " + createTable);
    }

    /**
     * @param tweet Reference to Tweet object to be added to database
     */
    public void addTweet(Tweet tweet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRIMARY_KEY, tweet.id);
        values.put(TWEETCONTENT, tweet.tweetContent);
        values.put(DATE, tweet.date);


        // Insert record
        db.insert(TABLE_TWEET, null, values);
        db.close();
    }

    /**
     * Persist a list of tweets
     *
     * @param tweets The list of Tweet object to be saved to database.
     */
    public void addTweets(List<Tweet> tweets) {
        for (Tweet twe : tweets) {
            addTweet(twe);
        }
    }

    public Tweet selectTweet(UUID resId) {
        Tweet tweet;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            tweet = new Tweet();

            cursor = db.rawQuery("SELECT * FROM tableTweets WHERE uuid = ?", new String[]{resId.toString() + ""});

            if (cursor.getCount() > 0) {
                int columnIndex = 0;
                cursor.moveToFirst();
                tweet.id = cursor.getLong(columnIndex++);
                tweet.tweetContent = cursor.getString(columnIndex++);
                tweet.date = Long.parseLong(cursor.getString(columnIndex++));
            }
        }
        finally {
            cursor.close();
        }
        return tweet;
    }

    public void deleteTweet(Tweet tweet) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete("tableTweets", "id" + "=?", new String[]{tweet.id + ""});
        }
        catch (Exception e) {
            Log.d(TAG, "delete tweet failure: " + e.getMessage());
        }
    }

    /**
     * Query database and select entire table Tweets.
     *
     * @return A list of Tweet object records
     */
    public List<Tweet> selectTweets() {
        List<Tweet> tweets = new ArrayList<Tweet>();
        String query = "SELECT * FROM " + "tableTweets";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int columnIndex = 0;
            do {
                Tweet tweet = new Tweet();
                tweet.id = cursor.getLong(columnIndex++);
                tweet.tweetContent = cursor.getString(columnIndex++);
                tweet.date = Long.parseLong(cursor.getString(columnIndex++));
                columnIndex = 0;

                tweets.add(tweet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tweets;
    }

    /**
     * Delete all records
     */
    public void deleteAllTweet() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("delete from tableTweets");
        }
        catch (Exception e) {
            Log.d(TAG, "delete tweets failure: " + e.getMessage());
        }
    }

    /**
     * Queries the database for the number of records.
     *
     * @return The number of records in the dataabase.
     */
    public long getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long numberRecords = DatabaseUtils.queryNumEntries(db, TABLE_TWEET);
        db.close();
        return numberRecords;
    }

    /**
     * Update an existing Tweet record.
     * All fields except record id updated.
     *
     * @param tweet The Tweet record being updated.
     */
    public void updateTweet(Tweet tweet) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(TWEETCONTENT, tweet.tweetContent);
            values.put(DATE, tweet.date);
            db.update("tableTweets", values, "id" + "=?", new String[]{tweet.id + ""});
        }
        catch (Exception e) {
            Log.d(TAG, "update tweet failure: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TWEET);
        Log.d(TAG, "onUpdated");
        onCreate(db);
    }
}