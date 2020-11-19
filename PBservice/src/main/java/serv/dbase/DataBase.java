package serv.dbase;

import java.io.Closeable;
import java.sql.*;

public class DataBase implements Closeable {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String user = "root";
    private static final String password = "123456";
    private static boolean isBase;
    private Connection connection;

    public static DataBase createConnection() throws SQLException {
        if (!isBase) {
            //��������� ������� JDBC �������� ��� ������ � ��
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //������� ���������� ���������� � ����� ������
            Connection connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("���������� � �� ���������.");
            isBase = true;
            return new DataBase(connection);
        }
        return null;
    }

    private DataBase() {

    }

    private DataBase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            isBase = false;
            System.out.println("���������� ������� �������");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public byte[] getPixelMap() {

        return null;
    }

    public void insertPixel(byte color, int pos) {

    }
}
