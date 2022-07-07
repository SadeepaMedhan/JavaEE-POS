package servlet;

import bo.BoFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource ds;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        try {
            final Connection connection = ds.getConnection();
            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jOb = reader.readObject();
            if (itemBO.saveProduct(new ItemDTO(jOb.getString("id"), jOb.getString("name"), Integer.parseInt(jOb.getString("qty")), Double.parseDouble(jOb.getString("price"))), connection)) {
                objectBuilder.add("message", "Product has been saved!");
                objectBuilder.add("status", 200);
            } else {
                objectBuilder.add("message", "The Id that you entered is not valid!");
                objectBuilder.add("status", 500);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            objectBuilder.add("message", "Product hasn't been saved!");
            objectBuilder.add("status", 500);
        }
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().print(objectBuilder.build());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String option = req.getParameter("case");
        switch (option) {
            case "allItems": {
                try {
                    final Connection connection = ds.getConnection();
                    JsonObjectBuilder oBu1 = Json.createObjectBuilder();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (ItemDTO item : itemBO.getAllProducts(connection)) {
                        JsonObjectBuilder oBu2 = Json.createObjectBuilder();
                        oBu2.add("id", item.getId());
                        oBu2.add("name", item.getName());
                        oBu2.add("qty", item.getQty());
                        oBu2.add("price", item.getPrice());
                        arrayBuilder.add(oBu2.build());
                    }
                    connection.close();
                    oBu1.add("data", arrayBuilder.build());
                    oBu1.add("message", "success");
                    oBu1.add("status", 200);
                    resp.getWriter().print(oBu1.build());
                    resp.setContentType("application/json");
                    resp.setStatus(200);

                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
            case "allID": {
                try {
                    final Connection connection = ds.getConnection();
                    JsonObjectBuilder oBu1 = Json.createObjectBuilder();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (ItemDTO item : itemBO.getAllProducts(connection)) {
                        JsonObjectBuilder oBu2 = Json.createObjectBuilder();
                        oBu2.add("id", item.getId());
                        arrayBuilder.add(oBu2.build());
                    }
                    connection.close();
                    oBu1.add("data", arrayBuilder.build());
                    oBu1.add("message", "success");
                    oBu1.add("status", 200);
                    resp.getWriter().print(oBu1.build());
                    resp.setContentType("application/json");
                    resp.setStatus(200);

                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
            case "getItem": {
                try {
                    final Connection connection = ds.getConnection();
                    JsonObjectBuilder oBu1 = Json.createObjectBuilder();
                    ItemDTO product = itemBO.searchForProduct(req.getParameter("id"), connection);
                    connection.close();

                    JsonObjectBuilder oBu2 = Json.createObjectBuilder();
                    oBu2.add("id", product.getId());
                    oBu2.add("name", product.getName());
                    oBu2.add("qty", product.getQty());
                    oBu2.add("price", product.getPrice());

                    oBu1.add("data", oBu2.build());
                    oBu1.add("message", "success");
                    oBu1.add("status", 200);
                    resp.getWriter().print(oBu1.build());
                    resp.setContentType("application/json");
                    resp.setStatus(200);

                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        try {
            final Connection connection = ds.getConnection();
            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jOb = reader.readObject();
            if (itemBO.updateProduct(new ItemDTO(jOb.getString("id"), jOb.getString("name"), Integer.parseInt(jOb.getString("qty")), Double.parseDouble(jOb.getString("price"))), connection)) {
                objectBuilder.add("message", "Product has been Updated!");
                objectBuilder.add("status", 200);
            } else {
                objectBuilder.add("message", "Product hasn't been Updated!");
                objectBuilder.add("status", 500);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            objectBuilder.add("message", "Product hasn't been Updated!");
            objectBuilder.add("status", 500);
        }
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().print(objectBuilder.build());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        try {
            final Connection connection = ds.getConnection();
            if (itemBO.deleteProduct(req.getParameter("id"), connection)) {
                objectBuilder.add("message", "Product has been deleted!");
                objectBuilder.add("status", 200);
            } else {
                objectBuilder.add("message", "The Id that you entered is not valid!");
                objectBuilder.add("status", 500);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().print(objectBuilder.build());
    }
}
