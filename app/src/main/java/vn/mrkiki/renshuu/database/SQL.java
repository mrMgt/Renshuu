package vn.mrkiki.renshuu.database;

/**
 * Created by linhnd on 2016/08/19.
 */
public final class SQL {
    public static final String SQL_CREATE_TABLE_CHINESE = "CREATE TABLE chinese(" +
            "ID INT PRIMARY KEY NOT NULL AUTOINCREMENT , " +
            "NAME TEXT NOT NULL, " +
            "MEAN TEXT NOT NULL)";
    public static final String SQL_CREATE_TABLE_EXERCISE = "CREATE TABLE exercise(" +
            "ID INT PRIMARY KEY NOT NULL AUTOINCREMENT , " +
            "NAME TEXT NOT NULL, " +
            "MEAN TEXT NOT NULL, " +
            "CHINESE TEXT NOT NULL, " +
            "EXAMPLE TEXT NOT NULL)";
    public static final String SQL_01 = "insert into chinese(NAME,MEAN) values (?,?)";

    public static final String SQL_02 = "insert into exercice(name, mean, chinese, exercise) values (?,?,?,?)";
}
