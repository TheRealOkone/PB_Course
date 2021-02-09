package serv.dbase;

import java.io.Closeable;
import java.sql.*;
import java.util.Collections;

public class DataBase implements Closeable {
    private static final String DB_URL = "jdbc:postgresql://10.10.10.142:5432/backtosch";
    private static final String user = "ostrankovkd";
    private static final String password = "Tjed_913";
    private static boolean isBase;
    private final Connection connection;

    /**
     *
     * @return ������� ���������� � ��
     * @throws SQLException ������� ����������, ���� �� ����������
     */
    public static DataBase createConnection() throws SQLException {
        if (!isBase) {
            //��������� ������� JDBC �������� ��� ������ � ��
            DriverManager.registerDriver(new org.postgresql.Driver());
            //������� ���������� ���������� � ����� ������
            Connection connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("���������� � �� ���������.");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select color from colors");
            String s = "0";
            s = s.replace(s, String.join("", Collections.nCopies(400, s)));
            if (!rs.next()) {
                System.out.println("���� ������ �����, ��������� ����������");
                for (int i = 0; i < 400; i++) {
                    st.executeUpdate("INSERT INTO public.colors (color) VALUES ('" + s + "');");
                }
            }
            isBase = true;
            return new DataBase(connection);
        }
        return null;
    }

    /**
     *
     * @param connection ��������� ����������
     */
    private DataBase(Connection connection) {
        this.connection = connection;
    }

    /**
     * �������� ����������
     */
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

    /**
     *
     * @return ������� ������������ �����������
     * @throws SQLException ������� ����������, ���� ��� �����������
     */
    public byte[] getPixelMap() throws SQLException {
        byte[] result = new byte[160000];
        ResultSet rs = connection.createStatement().executeQuery("select color from colors order by id");
        int i = 0;
        int ii = 0;
        String b;
        for (i = 0 ; rs.next(); i+=400) {
            b = rs.getString("color");
            ii++;
            for(int j=0; j<400;j++) {
                result[i+j] = (byte)b.charAt(j);
            }
        }
        System.out.println("���-�� ���������: " + i);
        return result;
    }

    /**
     *
     * @param color ���� �������
     * @param pos ������� �������
     * @throws SQLException ������� ����������, ���� ��� �����������
     */
    public void insertPixel(byte color, int pos) throws SQLException {
        int ost = pos % 400;
        int ln = pos / 400;
        String br = "";
        Statement sta = connection.createStatement();
        ResultSet res = sta.executeQuery("select color from public.colors where id = " + (ln+1));
        if(res.next())
        br = res.getString("color");
        StringBuilder strb = new StringBuilder(br);
        strb.setCharAt(ost, (char)color);
        connection.createStatement().executeUpdate("UPDATE public.colors SET color='" + strb +"' WHERE id=" + (ln + 1) + ";");
    }
}
