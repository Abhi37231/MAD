// DBHelper.java (REPLACE FULL FILE)

package com.example.expensesplitter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.expensesplitter.models.Group;

import java.util.ArrayList;
import java.util.HashMap;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "ExpenseDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE groups(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        db.execSQL("CREATE TABLE members(id INTEGER PRIMARY KEY AUTOINCREMENT, group_id INTEGER, name TEXT, UNIQUE(group_id, name))");
        db.execSQL("CREATE TABLE expenses(id INTEGER PRIMARY KEY AUTOINCREMENT, group_id INTEGER, amount REAL, paid_by TEXT)");
        db.execSQL("CREATE TABLE splits(id INTEGER PRIMARY KEY AUTOINCREMENT, expense_id INTEGER, member_name TEXT, amount REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS groups");
        db.execSQL("DROP TABLE IF EXISTS expenses");
        db.execSQL("DROP TABLE IF EXISTS splits");
        onCreate(db);
    }

    // Add Group - returns group_id
    public long addGroup(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("name", name);
        return db.insert("groups", null, v);
    }

    // Get Groups
    public ArrayList<Group> getGroups() {
        ArrayList<Group> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM groups", null);
        while (c.moveToNext()) {
            list.add(new Group(c.getInt(0), c.getString(1)));
        }
        c.close();
        return list;
    }

    // Add Expense - per group, split among group members
    public void addExpense(int groupId, double amount, String paidBy) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Get group members
        ArrayList<String> members = getGroupMembers(groupId);
        if (members.isEmpty()) return;

        ContentValues v = new ContentValues();
        v.put("group_id", groupId);
        v.put("amount", amount);
        v.put("paid_by", paidBy);

        long expenseId = db.insert("expenses", null, v);

        double split = amount / members.size();

        for (String m : members) {
            ContentValues sp = new ContentValues();
            sp.put("expense_id", expenseId);
            sp.put("member_name", m);
            sp.put("amount", split);

            db.insert("splits", null, sp);
        }
    }

    // Get Balances per group (paid - owed share)
    public HashMap<String, Double> getBalances() {
        return getBalancesForGroup(-1); // Global all groups

    }

    private HashMap<String, Double> getBalancesForGroup(int groupId) {
        HashMap<String, Double> map = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String whereClause = "";
        String[] args = null;
        if (groupId != -1) {
            whereClause = " AND e.group_id = ?";
            args = new String[]{String.valueOf(groupId)};
        }

// Collect unique people from payments and splits (no members table dep)
        ArrayList<String> members = new ArrayList<>();
java.util.HashSet<String> people = new java.util.HashSet<>();

        Cursor paidNames = db.rawQuery("SELECT DISTINCT paid_by FROM expenses" + whereClause, args);
        while (paidNames.moveToNext()) {
            people.add(paidNames.getString(0));
        }
        paidNames.close();

        Cursor owedNames = db.rawQuery("SELECT DISTINCT member_name FROM splits s JOIN expenses e ON s.expense_id = e.id" + whereClause, args);
        while (owedNames.moveToNext()) {
            people.add(owedNames.getString(0));
        }
        owedNames.close();

        members.addAll(people);

        // Paid sums
        Cursor paidCursor = db.rawQuery("SELECT paid_by, SUM(amount) as total FROM expenses" + whereClause + " GROUP BY paid_by", args);
        while (paidCursor.moveToNext()) {
            String payer = paidCursor.getString(0);
            double total = paidCursor.getDouble(1);
            map.put(payer, map.getOrDefault(payer, 0.0) + total);
        }
        paidCursor.close();

        // Owed
        Cursor owedCursor = db.rawQuery("SELECT member_name, SUM(amount) as total FROM splits s JOIN expenses e ON s.expense_id = e.id" + whereClause + " GROUP BY member_name", args);
        while (owedCursor.moveToNext()) {
            String member = owedCursor.getString(0);
            double total = owedCursor.getDouble(1);
            map.put(member, map.getOrDefault(member, 0.0) - total);
        }
        owedCursor.close();

        for (String m : members) {
            map.putIfAbsent(m, 0.0);
        }

        return map;
    }

    // Get group members
    public ArrayList<String> getGroupMembers(int groupId) {
        ArrayList<String> members = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM members WHERE group_id = ? ORDER BY name", new String[]{String.valueOf(groupId)});
        while (c.moveToNext()) {
            members.add(c.getString(0));
        }
        c.close();
        return members;
    }

    // Add member to group
    public void addMember(int groupId, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("group_id", groupId);
        v.put("name", name.trim());
        db.insert("members", null, v);
    }
}