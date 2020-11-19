package serv.dbase;

import java.io.Closeable;
import java.sql.*;

public class DataBase implements Closeable {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/canvas";
    private static final String user = "root";
    private static final String password = "123456";
    private static boolean isBase;
    private final Connection connection;

    public static DataBase createConnection() throws SQLException {
        if (!isBase) {
            //Проверяем наличие JDBC драйвера для работы с БД
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //Попытка установить соединение с базой данных
            Connection connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Соединение с БД выполнено.");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select color from colors");
            if (!rs.next()) {
                System.out.println("База данных пуста, первичное заполнение");
                for (int i = 0; i < 5000; i++) {
                    System.out.println("id: " + i);
                    st.executeUpdate("INSERT INTO colors (color) \n" + " VALUES (0);");
                }
            }
            isBase = true;
            return new DataBase(connection);
        }
        return null;
    }

    private DataBase(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            isBase = false;
            System.out.println("Соединение успешно закрыто");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public byte[] getPixelMap() throws SQLException {
        byte[] result = new byte[5000];
        ResultSet rs = connection.createStatement().executeQuery("select color from colors");
        int i = 0;
        for (; rs.next(); i++) {
            result[i] = rs.getByte(1);
        }
        System.out.println("Кол-во элементов: " + i);
        return result;
    }

    public void insertPixel(byte color, int pos) throws SQLException {

    }
}
