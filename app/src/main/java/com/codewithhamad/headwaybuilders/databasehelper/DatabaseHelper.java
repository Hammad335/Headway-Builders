package com.codewithhamad.headwaybuilders.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.codewithhamad.headwaybuilders.analyst.BuildingModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    private static final String DB_NAME= "headway.db";
    private static final int DB_VERSION= 1;
//    private int increment= 0;

    private  String tableName= "buildings";

    private ByteArrayOutputStream imageByteArrayOutputStream;
    private byte[] imageInBytes;

    String sqlQuery="CREATE TABLE " +  tableName + " (id INTEGER PRIMARY KEY, name VARCHAR, buildingType VARCHAR, " +
            "buildingAreaInSqFt INTEGER, " +
            "numberOfFlats INTEGER, numberOfFloors INTEGER, numberOfLifts INTEGER, parkingAreaInSqFt INTEGER, details VARCHAR, " +
            "location VARCHAR, "+
            "buildingImage BLOB, dateTime DATETIME DEFAULT CURRENT_TIMESTAMP)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(sqlQuery);
            Toast.makeText(context, "table created successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert record into buildings table
    public void insertRecord(BuildingModel buildingModel){
        try{
            SQLiteDatabase sqLiteDatabaseWritableObj= this.getWritableDatabase();

            Bitmap bitmapImage= buildingModel.getBuildingImage();

            // converting bitmap image into byte array
            imageByteArrayOutputStream= new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, imageByteArrayOutputStream);
            imageInBytes= imageByteArrayOutputStream.toByteArray();

            // database record values
            ContentValues contentValues= new ContentValues();
            contentValues.put("id", buildingModel.getBuildingId());
            contentValues.put("name", buildingModel.getBuildingName());
            contentValues.put("buildingType", buildingModel.getBuildingType());
            contentValues.put("buildingAreaInSqFt", buildingModel.getBuildingAreaInSqFt());

            if(buildingModel.getNumbOfFlats() != -1)
                contentValues.put("numberOfFlats", buildingModel.getNumbOfFlats());

            if(buildingModel.getNumbOfFloors() != -1)
                contentValues.put("numberOfFloors", buildingModel.getNumbOfFloors());

            if(buildingModel.getNumbOfLifts() != -1)
                contentValues.put("numberOfLifts", buildingModel.getNumbOfLifts());

            contentValues.put("parkingAreaInSqFt", buildingModel.getParkingAreaInSqFt());
            contentValues.put("details", buildingModel.getShortDetails());
            contentValues.put("location", buildingModel.getBuildingLocation());
            contentValues.put("buildingImage", imageInBytes);

            // insert() method returns -1 if exception occurs
            long check= sqLiteDatabaseWritableObj.insert(tableName, null, contentValues);

            if(check != -1){
                Toast.makeText(context, "Building added to the database." + tableName, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Failed to add the record to database.", Toast.LENGTH_SHORT).show();
            }
            sqLiteDatabaseWritableObj.close();

        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public ArrayList<BuildingModel> getAllBuildingsFromDatabase(){
        try{

            SQLiteDatabase sqLiteDatabaseReadableObj= this.getReadableDatabase();
            ArrayList<BuildingModel> buildings= new ArrayList<>();

            String getAllDataQuery= "SELECT * FROM " + tableName + " ORDER BY dateTime";
            Cursor cursor= sqLiteDatabaseReadableObj.rawQuery(getAllDataQuery, null);

            if(cursor.getCount() != 0){
                while (cursor.moveToNext()){

                    int id= cursor.getInt(0);
                    String name= cursor.getString(1);
                    String type= cursor.getString(2);
                    int bArea= cursor.getInt(3);

                    // getType(index) return 0 if columnValue is null
                    int flats;
                    if(cursor.getType(4) == 0)
                        flats= -1;
                    else
                        flats= cursor.getInt(4);

                    int floors;
                    if(cursor.getType(5) == 0)
                        floors= -1;
                    else
                        floors= cursor.getInt(5);

                    int lifts;
                    if(cursor.getType(6) == 0)
                        lifts= -1;
                    else
                        lifts= cursor.getInt(6);

                    int pArea= cursor.getInt(7);
                    String details= cursor.getString(8);
                    String location= cursor.getString(9);
                    byte[] imageInBytes= cursor.getBlob(10);
//                    String dateTime= cursor.getString(7);

                    // converting byteArray image into bitmap
                    Bitmap bitmapImage= BitmapFactory.decodeByteArray(imageInBytes, 0, imageInBytes.length);

                    buildings.add(new BuildingModel(bitmapImage, type, id, name, bArea, flats, floors, lifts, pArea, details, location));
                }
                return buildings;
            }
            else{
                Toast.makeText(context, "No records exist in database", Toast.LENGTH_SHORT).show();
                return null;
            }

        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }


}
