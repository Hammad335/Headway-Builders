package com.codewithhamad.headwaybuilders.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.codewithhamad.headwaybuilders.models.BuildingModel;
import com.codewithhamad.headwaybuilders.models.WorkerModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;
    private static final String DB_NAME = "headway.db";
    private static final int DB_VERSION = 1;

    private String table_1_buildings = "Buildings";
    private static final String COLUMN_BUILDING_ID = "id";
    private static final String COLUMN_BUILDING_NAME = "name";
    private static final String COLUMN_BUILDING_TYPE = "buildingType";
    private static final String COLUMN_BUILDING_AREA = "buildingAreaInSqFt";
    private static final String COLUMN_BUILDING_FLATS = "numberOfFlats";
    private static final String COLUMN_BUILDING_FLOORS = "numberOfFloors";
    private static final String COLUMN_BUILDING_LIFTS = "numberOfLifts";
    private static final String COLUMN_BUILDING_P_AREA = "parkingAreaInSqFt";
    private static final String COLUMN_BUILDING_DETAILS = "details";
    private static final String COLUMN_BUILDING_LOCATION = "location";
    private static final String COLUMN_BUILDING_IMAGE = "buildingImage";
    private static final String COLUMN_BUILDING_DATETIME = "datetime";

    private String table_2_workers = "Workers";
    private static final String COLUMN_WORKER_ID = "worker_id";
    private static final String COLUMN_WORKER_NAME = "worker_name";
    private static final String COLUMN_WORKER_JOB = "job";
    private static final String COLUMN_WORKER_SAL = "sal";
    private static final String COLUMN_WORKER_BUILDING_ID = "id";


    // for converting bitmap image into byte array
    private ByteArrayOutputStream imageByteArrayOutputStream;
    private byte[] imageInBytes;


    // create table queries
    String buildingTableCreateQuery = "CREATE TABLE " + table_1_buildings + " ( " + COLUMN_BUILDING_ID + " INTEGER PRIMARY KEY, " + COLUMN_BUILDING_NAME
            + " VARCHAR , " + COLUMN_BUILDING_TYPE + " VARCHAR, " + COLUMN_BUILDING_AREA + " INTEGER NOT NULL, " +
            COLUMN_BUILDING_FLATS + " INTEGER, " + COLUMN_BUILDING_FLOORS + " INTEGER, " + COLUMN_BUILDING_LIFTS + " INTEGER, "
            + COLUMN_BUILDING_P_AREA + " INTEGER, " + COLUMN_BUILDING_DETAILS + " VARCHAR, " + COLUMN_BUILDING_LOCATION
            + " VARCHAR NOT NULL, " + COLUMN_BUILDING_IMAGE + " BLOB, " + COLUMN_BUILDING_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    String workerTableCreateQuery = "CREATE TABLE " + table_2_workers + " ( " + COLUMN_WORKER_ID + " INTEGER PRIMARY KEY, " + COLUMN_WORKER_NAME
            + " VARCHAR NOT NULL, " + COLUMN_WORKER_JOB + " VARCHAR NOT NULL, " + COLUMN_WORKER_SAL + " INTEGER NOT NULL, "
            + COLUMN_WORKER_BUILDING_ID + " INTEGER , FOREIGN KEY  (" + COLUMN_WORKER_BUILDING_ID + ") REFERENCES " + table_1_buildings
            + "(" + COLUMN_BUILDING_ID + "))";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(buildingTableCreateQuery);
            db.execSQL(workerTableCreateQuery);
            Toast.makeText(context, "table created successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error creating tables", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert record into buildings table
    public void insertInToBuildingsTable(BuildingModel buildingModel) {
        SQLiteDatabase sqLiteDatabaseWritableObj = this.getWritableDatabase();

        try {
            Bitmap bitmapImage = buildingModel.getBuildingImage();

            // converting bitmap image into byte array
            imageByteArrayOutputStream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, imageByteArrayOutputStream);
            imageInBytes = imageByteArrayOutputStream.toByteArray();

            // database record values for buildingsTable
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", buildingModel.getBuildingId());
            contentValues.put("name", buildingModel.getBuildingName());
            contentValues.put("buildingType", buildingModel.getBuildingType());
            contentValues.put("buildingAreaInSqFt", buildingModel.getBuildingAreaInSqFt());

            if (buildingModel.getNumbOfFlats() != -1)
                contentValues.put("numberOfFlats", buildingModel.getNumbOfFlats());

            if (buildingModel.getNumbOfFloors() != -1)
                contentValues.put("numberOfFloors", buildingModel.getNumbOfFloors());

            if (buildingModel.getNumbOfLifts() != -1)
                contentValues.put("numberOfLifts", buildingModel.getNumbOfLifts());

            contentValues.put("parkingAreaInSqFt", buildingModel.getParkingAreaInSqFt());
            contentValues.put("details", buildingModel.getShortDetails());
            contentValues.put("location", buildingModel.getBuildingLocation());
            contentValues.put("buildingImage", imageInBytes);

            // insert() method returns -1 if exception occurs
            long check = sqLiteDatabaseWritableObj.insert(table_1_buildings, null, contentValues);

            if (check != -1) {
                Toast.makeText(context, "Building added to the database " + table_1_buildings, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to add the record to database.", Toast.LENGTH_SHORT).show();
            }
            sqLiteDatabaseWritableObj.close();

        } catch (Exception e) {
            Toast.makeText(context, "Failed to add record to database", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabaseWritableObj.close();
    }

    // get all from buildings table
    public ArrayList<BuildingModel> getAllFromBuildingsTable() {
        try {
            SQLiteDatabase sqLiteDatabaseReadableObj = this.getReadableDatabase();
            ArrayList<BuildingModel> buildings = new ArrayList<>();

            String getAllDataQuery = "SELECT * FROM " + table_1_buildings + " ORDER BY dateTime";
            Cursor cursor = sqLiteDatabaseReadableObj.rawQuery(getAllDataQuery, null);

            if (cursor.getCount() != 0) {
                while (cursor.moveToNext()) {

                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String type = cursor.getString(2);
                    int bArea = cursor.getInt(3);

                    // getType(index) return 0 if columnValue is null
                    int flats;
                    if (cursor.getType(4) == 0)
                        flats = -1;
                    else
                        flats = cursor.getInt(4);

                    int floors;
                    if (cursor.getType(5) == 0)
                        floors = -1;
                    else
                        floors = cursor.getInt(5);

                    int lifts;
                    if (cursor.getType(6) == 0)
                        lifts = -1;
                    else
                        lifts = cursor.getInt(6);

                    int pArea = cursor.getInt(7);
                    String details = cursor.getString(8);
                    String location = cursor.getString(9);
                    byte[] imageInBytes = cursor.getBlob(10);
//                    String dateTime= cursor.getString(7);

                    // converting byteArray image into bitmap
                    Bitmap bitmapImage = BitmapFactory.decodeByteArray(imageInBytes, 0, imageInBytes.length);

                    buildings.add(new BuildingModel(bitmapImage, type, id, name, bArea, flats, floors, lifts, pArea, details, location));
                }
                cursor.close();
                return buildings;
            } else {
                Toast.makeText(context, "No buildings exist in database", Toast.LENGTH_SHORT).show();
                cursor.close();
                return null;
            }

        } catch (Exception e) {
            Toast.makeText(context, "Error fetching data form buildings table", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    // update record in buildings table
    public void updateRecordInToBuildingsTable(BuildingModel buildingModel) {
        SQLiteDatabase sqLiteDatabaseWritableObj = this.getWritableDatabase();

        try {

            Bitmap bitmapImage = buildingModel.getBuildingImage();

            // converting bitmap image into byte array
            imageByteArrayOutputStream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, imageByteArrayOutputStream);
            imageInBytes = imageByteArrayOutputStream.toByteArray();

            // database record values
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_BUILDING_ID, buildingModel.getBuildingId());
            contentValues.put(COLUMN_BUILDING_NAME, buildingModel.getBuildingName());
            contentValues.put(COLUMN_BUILDING_TYPE, buildingModel.getBuildingType());
            contentValues.put(COLUMN_BUILDING_AREA, buildingModel.getBuildingAreaInSqFt());

            if (buildingModel.getNumbOfFlats() != -1)
                contentValues.put(COLUMN_BUILDING_FLATS, buildingModel.getNumbOfFlats());

            if (buildingModel.getNumbOfFloors() != -1)
                contentValues.put(COLUMN_BUILDING_FLOORS, buildingModel.getNumbOfFloors());

            if (buildingModel.getNumbOfLifts() != -1)
                contentValues.put(COLUMN_BUILDING_LIFTS, buildingModel.getNumbOfLifts());

            contentValues.put(COLUMN_BUILDING_P_AREA, buildingModel.getParkingAreaInSqFt());
            contentValues.put(COLUMN_BUILDING_DETAILS, buildingModel.getShortDetails());
            contentValues.put(COLUMN_BUILDING_LOCATION, buildingModel.getBuildingLocation());
            contentValues.put("buildingImage", imageInBytes);

            // update() method returns -1 if exception occurs
            long check = sqLiteDatabaseWritableObj.update(table_1_buildings, contentValues,
                    COLUMN_BUILDING_ID + "=?", new String[]{String.valueOf(buildingModel.getBuildingId())});

            if (check != -1)
                Toast.makeText(context, "Building updated.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Failed to update the record.", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
        Toast.makeText(context, "Error updating record.", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabaseWritableObj.close();
}

    // check whether record exists in building table or not
    public boolean doesExistInBuildingTable(int buildingId) {

        SQLiteDatabase sqLiteDatabaseReadableObj = this.getReadableDatabase();

        String getDataByIdQuery = "SELECT * FROM " + table_1_buildings + " WHERE "+COLUMN_BUILDING_ID+" = "  + buildingId;

        Cursor cursor = sqLiteDatabaseReadableObj.rawQuery(getDataByIdQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        else
            return true;
    }

    // get whole row/record by buildingId
    public BuildingModel getByIdFromBuildingTable(int buildingId){
        try{
            SQLiteDatabase sqLiteDatabaseReadableObj= this.getReadableDatabase();
            BuildingModel buildingModel = null;

            String getSingleRecordByIdQuery= "SELECT * FROM " + table_1_buildings + " WHERE "+COLUMN_BUILDING_ID+" = "  + buildingId;
            Cursor cursor= sqLiteDatabaseReadableObj.rawQuery(getSingleRecordByIdQuery, null);

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

                    buildingModel= new BuildingModel(bitmapImage, type, id, name, bArea, flats, floors, lifts, pArea, details, location);
                }
                cursor.close();
                return buildingModel;
            }
            else{
                Toast.makeText(context, "No buildings exist in database", Toast.LENGTH_SHORT).show();
                cursor.close();
                return null;
            }

        }
        catch (Exception e){
            Toast.makeText(context, "Error fetching data form buildings table", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void insertInToWorkersTable(WorkerModel workerModel) {
        try {
            SQLiteDatabase sqLiteDatabaseWritableObj = this.getWritableDatabase();

            // database record values For WorkersTable
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_WORKER_ID, workerModel.getWorkerId());
            contentValues.put(COLUMN_WORKER_NAME, workerModel.getWorkerName());
            contentValues.put(COLUMN_WORKER_JOB, workerModel.getJob());
            contentValues.put(COLUMN_WORKER_SAL, workerModel.getSalary());
            contentValues.put(COLUMN_WORKER_BUILDING_ID, workerModel.getBuildingId());

            // insert() method returns -1 if exception occurs
            long check = sqLiteDatabaseWritableObj.insert(table_2_workers, null, contentValues);

            if (check != -1) {
                Toast.makeText(context, "Worker added to the database " + table_2_workers, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Failed to add the record to database.", Toast.LENGTH_SHORT).show();
            }
            sqLiteDatabaseWritableObj.close();

        }
        catch (Exception e) {
            Toast.makeText(context, "Failed to add record to database", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<WorkerModel> getAllFromWorkersTable(){
        try {
            SQLiteDatabase sqLiteDatabaseReadableObj = this.getReadableDatabase();
            ArrayList<WorkerModel> workers = new ArrayList<>();

            String getAllDataQuery = "SELECT * FROM " + table_2_workers;
            Cursor cursor = sqLiteDatabaseReadableObj.rawQuery(getAllDataQuery, null);

            if (cursor.getCount() != 0) {
                while (cursor.moveToNext()) {

                    int wId = cursor.getInt(0);
                    String wName = cursor.getString(1);
                    String wJob = cursor.getString(2);
                    int wSal = cursor.getInt(3);
                    int bId = cursor.getInt(4);

//                    String dateTime= cursor.getString(8);

                    workers.add(new WorkerModel(wId, bId, wName, wJob, wSal));
                }
                cursor.close();
                return workers;
            } else {
                Toast.makeText(context, "No worker exists in database", Toast.LENGTH_SHORT).show();
                cursor.close();
                return null;
            }
        }
        catch (Exception e){
            Toast.makeText(context, "Error fetching data form workers table", Toast.LENGTH_SHORT).show();
            return null;
        }
    }



}
