package be.bnair;

import be.bnair.utils.DatabaseUtils;

public class Main {
    public static void main(String[] args) {
        DatabaseUtils.createTableIfNotExists();
        DatabaseUtils.insertIntoTable("Brian", 25);
        DatabaseUtils.selectAllFromTable();
        DatabaseUtils.updateTable(1, "Jean-Marc", 56);
        DatabaseUtils.selectAllFromTable();
        DatabaseUtils.deleteFromTable(1);
        DatabaseUtils.selectAllFromTable();
    }
}