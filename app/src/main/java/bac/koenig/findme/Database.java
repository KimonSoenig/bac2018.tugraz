package bac.koenig.findme;

import android.content.Context;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//represents Database
@androidx.room.Database(entities = {RoomIDs.class}, version = 1, exportSchema = false)

public abstract class Database extends RoomDatabase {
    private static Database INSTANCE;

    //Method to call DAO
    public abstract RoomDataAccessObject dao();


    //Get Instance of Database for Database(Instance == Null)
    public synchronized static Database getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }



    //Database Builder. WIll build Data as given in populateData in RoomIds EntityClass
    //Overwrites the Room.callback onCreate method to create Database when App is first opened
    private static Database buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                Database.class,
                "RoomDatabase")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).dao().insertAll(RoomIDs.populateData());
                            }
                        });
                    }
                })
                .build();
    }

}
