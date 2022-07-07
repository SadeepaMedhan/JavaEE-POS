package servlet;

import bo.BoFactory;
import bo.custom.OrderBO;
import dto.ItemDetailsDTO;
import dto.OrderDTO;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource ds;
    private final OrderBO orderBO = (OrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PLACE_ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("case");
        switch (option) {
            case "allOrders": {
                //
                break;
            }
            case "getOrderID": {
                try {
                    final Connection connection = ds.getConnection();
                    String orderId = orderBO.getOrderId(connection);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsOb = reader.readObject();
        String cusId = jsOb.getString("cusId");
        int totalCostInt = jsOb.getInt("totalCost");
        String totalCost=String.valueOf(totalCostInt);
        int discountInt = jsOb.getInt("discount");
        String discount = String.valueOf(discountInt);
        JsonArray jsItems = jsOb.getJsonArray("items");
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss a");

        ArrayList<ItemDetailsDTO> items=new ArrayList<>();
        System.out.println("order received");



        try {
            final Connection connection = ds.getConnection();
            String oId=orderBO.getOrderId(connection);
            for (JsonValue jsonValue:jsItems) {
                JsonObject jsonObject = jsonValue.asJsonObject();

                System.out.println(jsonObject);

                String itemID = jsonObject.getString("id");
                double price = Double.parseDouble(jsonObject.getString("price"));
                int qty = Integer.parseInt(jsonObject.getString("qyt"));
                double total = Double.parseDouble(jsonObject.getString("cost"));

                System.out.println(itemID+"-"+price+"-"+qty+"-"+total);

                items.add(new ItemDetailsDTO(itemID,oId,price,qty,total));
                System.out.println(jsonObject.getString("id"));
                System.out.println(oId);
            }
            OrderDTO orderDTO=new OrderDTO(oId,cusId,f.format(date),sdf.format(new Date()),Double.parseDouble(totalCost),Double.parseDouble(discount),items);
            System.out.println(orderBO.saveOrder(orderDTO,connection));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
