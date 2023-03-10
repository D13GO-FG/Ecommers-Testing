package TestsData;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.assertEquals;


public class DataVerificationTest {
    private static Connection connection;
    private static ResultSet result;
    private static Statement statement;

    @BeforeTest
    public void initiateConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/sampleDB",
                "postgres", "qwe123");
    }

    public void executeQuery(String query) throws SQLException {
        initiateConnection();
        statement = connection.createStatement();
        result = statement.executeQuery(query);
    }

    @Test
    public void verifyOrderDetails() throws SQLException {
        executeQuery("select * from orders where item_sku='ABCD0001' and quantity=1");
        System.out.println(result);
        while (result.next()){
            assertEquals(result.getString("quantity"), "1");
            assertEquals(result.getString("order_id"), "PR123");
        }
    }

    @AfterTest
    public void closeConnection() throws SQLException {
        result.close();
        statement.close();
    }

}
