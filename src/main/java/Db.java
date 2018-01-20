import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db {

    public List getAllProducts() throws ClassNotFoundException, SQLException {
        List<Product> listaOfProducts = new ArrayList<>();

        // aici voi insera in db
        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/ionel7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Statement st = conn.createStatement();


        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT productname,price FROM products order by productname asc");

        // 6. iterate the result set and print the values

        while (rs.next()) {

            Product p = new Product();
            p.setProductname(rs.getString("productname").trim());
            p.setPrice(rs.getDouble("price"));
            listaOfProducts.add(p);

        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return listaOfProducts;

    }

    public void insertProduct(String productName, Double price)  throws SQLException, ClassNotFoundException{

        // aici voi insera in db
        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/ionel7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO PRODUCTS (PRODUCTNAME, price) VALUES (?, ?)");
        pSt.setString(1, productName);
        pSt.setDouble(2, price);


        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();

    }
}
