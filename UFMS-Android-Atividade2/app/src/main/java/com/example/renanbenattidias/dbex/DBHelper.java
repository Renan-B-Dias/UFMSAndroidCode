package com.example.renanbenattidias.dbex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renanbenattidias on 02/05/18.
 */

interface EmployeeDataBase {
    boolean create(Employee employee);
    List<Employee> getEmployees();
    boolean update(Employee employee);
    boolean deleteEmployee(int employeeId);
}

interface PositionDataBase {
    boolean create(Position position);
    List<Position> getPositions();
    boolean update(Position position);
    boolean delete(int positionId);
}

public class DBHelper extends SQLiteOpenHelper implements PositionDataBase, EmployeeDataBase {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "exdb.db";

    // Employees
    private static final String EMPLOYEE_TABLE_NAME = "employees";

    private static final String ID = "id";
    private static final String EMPLOYEE_POSITION = "position_id";
    private static final String NAME = "name";
    private static final String SALARY = "salary";

    private static final int ID_ID = 0;
    private static final int NAME_ID = 1;
    private static final int SALARY_ID = 2;
    private static final int EMPLOYEE_POSITION_ID = 3;

    // Employees

    // Position
    private static final String POSITION_TABLE_NAME = "position";

    private static final String POSITION_ID = "id";
    private static final String POSITION_NAME = "name";

    private static final int POSITION_ID_ID = 0;
    private static final int POSITION_NAME_ID = 1;
    // Position

    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + EMPLOYEE_TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT NOT NULL, " + SALARY + " REAL NOT NULL, " + EMPLOYEE_POSITION + " INTEGER REFERENCES " + POSITION_TABLE_NAME  + ")";
    private static final String CREATE_TABLE_POSITION = "CREATE TABLE " + POSITION_TABLE_NAME + "(" + POSITION_ID + " INTEGER PRIMARY KEY, " + POSITION_NAME + " TEXT NOT NULL" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_POSITION);
        database.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
        onCreate(database);
    }

    public boolean create(Employee employee) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(NAME, employee.name);
            values.put(SALARY, employee.salary);
            values.put(EMPLOYEE_POSITION, employee.position.id);

        long result = database.insert(EMPLOYEE_TABLE_NAME, null, values);
        database.close();
        return result > -1;
    }

    public boolean create(Position position) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put(POSITION_NAME, position.name);
        long result = database.insert(POSITION_TABLE_NAME, null, values);
        database.close();
        return result > -1;
    }

    private Position getPosition(int positionId) {
        String sql = "SELECT * FROM " + POSITION_TABLE_NAME + " WHERE id=" + positionId;
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);

        Position position = null;
        if(cursor.moveToFirst()) {
            position = new Position(cursor.getInt(POSITION_ID_ID), cursor.getString(POSITION_NAME_ID));
        }

        database.close();

        return position;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM " + EMPLOYEE_TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            do {
                int positionId = cursor.getInt(EMPLOYEE_POSITION_ID);
                Position position = getPosition(positionId);

                if(position != null) {
                    employees.add(new Employee(cursor.getInt(ID_ID), position, cursor.getString(NAME_ID), cursor.getDouble(SALARY_ID)));
                } else {
                    employees.add(new Employee(cursor.getInt(ID_ID), cursor.getString(NAME_ID), cursor.getDouble(SALARY_ID)));
                }
            } while(cursor.moveToNext());
        }

        return employees;
    }

    public List<Position> getPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM " + POSITION_TABLE_NAME;

        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            do {
                positions.add(new Position(cursor.getInt(POSITION_ID_ID), cursor.getString(POSITION_NAME_ID)));
            } while(cursor.moveToNext());
        }
        return positions;
    }

    // TODO Remove all strings concats They're ugly.
    public boolean update(Employee employee) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, employee.name);
        values.put(SALARY, employee.salary);
        if(employee.position != null)
            values.put(EMPLOYEE_POSITION, employee.position.id);
        long result = database.update(EMPLOYEE_TABLE_NAME, values, ID + " = " + employee.id, null);
        database.close();
        return result > -1;
    }

    public boolean delete(int positionId) {
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(POSITION_TABLE_NAME, POSITION_ID + " = " + positionId, null);
        database.close();
        return  result > -1;
    }

    public boolean deleteEmployee(int employeeId) {
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(EMPLOYEE_TABLE_NAME, ID+ " = " + employeeId, null);
        database.close();
        return  result > -1;
    }


    public boolean update(Position position) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POSITION_NAME, position.name);
        long result = database.update(POSITION_TABLE_NAME, values, POSITION_ID + " = " + position.id, null);
        database.close();
        return result > -1;
    }
}
