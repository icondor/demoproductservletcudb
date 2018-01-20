import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/insertProduct")
public class ProductServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        String action = req.getParameter("action"); // citesc de a browser

        if(action!=null && action.equals("insert")) {


            String val = req.getParameter("productName"); // citesc de a browser
            String price = req.getParameter("price"); // citesc de a browser


            Db db = new Db();
            try {
                double d = Double.parseDouble(price);

                db.insertProduct(val, d);

                try {
                    PrintWriter pw = resp.getWriter();
                    pw.println("great ! product is now in our db"); //scriu la browser
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (NumberFormatException nfe) {

                try {
                    PrintWriter pw = resp.getWriter();
                    pw.println("amice, bag pretul numeric si cu virgula sub forma de punct"); //scriu la browser
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException exdb) {

                exdb.printStackTrace();
                try {
                    PrintWriter pw = resp.getWriter();
                    pw.println("error on insert, pls email bb@bdfgd.ff"); //scriu la browser
                    pw.println(exdb.fillInStackTrace());
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }

            } catch (ClassNotFoundException exdb) {

                exdb.printStackTrace();
                try {
                    PrintWriter pw = resp.getWriter();
                    pw.println("error on loading the driver"); //scriu la browser
                    pw.println(exdb.fillInStackTrace());
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }

            }
        }
        else
            if(action!=null && action.equals("list")) {


                Db db = new Db();

                try {
                    List listOfProducts = db.getAllProducts();

                    for (int i = 0; i <listOfProducts.size() ; i++) {

                        Product p = (Product)listOfProducts.get(i);
                        System.out.println(p.getProductname()+":"+p.getPrice());
                        try {
                            PrintWriter pw = resp.getWriter();
                            pw.println(p.getProductname()+":"+p.getPrice());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }



                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }

    }
}
