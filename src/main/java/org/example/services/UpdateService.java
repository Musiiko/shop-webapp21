package org.example.services;

import org.example.DbConnector;
import org.example.domain.Order;
import org.example.domain.Product;
import org.example.domain.ProductInOrder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdateService {
    public void insertProduct(Product product) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Products(price, name, description) VALUES(?,?,?)");
        statement.setFloat(1, product.getPrice());
        statement.setString(2, product.getName());
        statement.setString(3, product.getDescription());

        statement.executeUpdate();
    }

    public void insertOrder(Order order) throws SQLException {
        Connection connection = DbConnector.getConnection();
        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Orders(order_date, order_number) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        statement.setDate(1, java.sql.Date.valueOf(order.getDate()));
        statement.setString(2, order.getNumber());
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            int id = Integer.parseInt(rs.getString(1));
            order.setId(id);
        }
    }

    public void addProductToOrder(int orderId, int productId, int count) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO Product_Order(FK_Order, FK_Product, count_products) VALUES(?,?,?)");
        statement.setInt(1, orderId);
        statement.setInt(2, productId);
        statement.setInt(3, count);

        statement.executeUpdate();
    }

    public void deleteInfoOrder(int productId, int count) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement("DELETE FROM product_order po WHERE po.FK_Product = ? AND count_products = ?;");
        statement.setInt(1, productId);
        statement.setInt(2, count);

        statement.executeUpdate();
    }

    public Order generateNewOrderFromProductsOrderedToday(String orderNumber) throws SQLException {
        Order order = null;
        Connection connection = DbConnector.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT DISTINCT(product_order.FK_Product) AS product_id FROM orders\n" +
                                "JOIN product_order ON orders.order_id = product_order.FK_Order\n" +
                                "WHERE orders.order_date = CURDATE();");
        statement.executeQuery();
        List<Integer> products = new ArrayList<>();
        try(ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                products.add(productId);
            }
        }
        if(products.size() > 0) {
            order = new Order(LocalDate.now(), orderNumber);
            insertOrder(order);
        }

        for (Integer productId : products) {
            addProductToOrder(order.getId(), productId,1);
        }
        SelectService service = new SelectService();
        order = service.showOrderByNumber(order.getNumber());
        return order;
    }

    public void deleteOrdersWithGivenCountAndProduct(int productCount, String productName) throws SQLException {
        Connection connection = DbConnector.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(
                        "SELECT product_order.FK_Order AS order_id FROM products\n" +
                        "JOIN product_order ON product_order.FK_Product = products.product_id\n" +
                        "WHERE products.name = ? AND product_order.count_products = ?");
        statement.setString(1, productName);
        statement.setInt(2, productCount);
        statement.executeQuery();
        List<Integer> orders = new ArrayList<>();
        try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                orders.add(orderId);
            }
        }
        for (Integer orderId : orders){
            PreparedStatement deleteStatement =
                    connection.prepareStatement("DELETE FROM orders WHERE order_id = ?;");
            deleteStatement.setInt(1, orderId);
            deleteStatement.executeUpdate();
        }
    }

}
