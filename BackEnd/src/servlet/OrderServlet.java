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


        try {
            final Connection connection = ds.getConnection();
            String id=orderBO.getOrderId(connection);
            for (JsonValue jsonValue:jsItems) {
                JsonObject jsonObject = jsonValue.asJsonObject();
                items.add(new ItemDetailsDTO(jsonObject.getString("id"),id,Double.parseDouble(jsonObject.getString("price")),Integer.parseInt(jsonObject.getString("qyt")),Double.parseDouble(jsonObject.getString("cost"))));
            }
            OrderDTO orderDTO=new OrderDTO(id,cusId,f.format(date),sdf.format(new Date()),Double.parseDouble(totalCost),Double.parseDouble(discount),items);
            System.out.println(orderBO.saveOrder(orderDTO,connection));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
