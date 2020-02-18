package dao.impl;

import dao.BucketDao;
import domain.Bucket;
import utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BucketDaoImpl implements BucketDao {

    private static String READ_ALL = "select * from bucket";
    private static String CREATE = "insert into bucket(`user_id`, `product_id`, `purchase_date`) values (?,?,?)";
    private static String READ_BY_ID = "select * from bucket where id =?";
    private static String DELETE_BY_ID = "delete from bucket where id=?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDaoImpl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        connection = ConnectionUtils.openConnection();
    }


    @Override
    public Bucket create(Bucket bucket) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setDate(3, bucket.getPurchaseDate());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            bucket.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bucket;
    }

    @Override
    public Bucket read(Integer id) {
        Bucket bucket = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            int bucketId = rs.getInt("id");
            int userId = rs.getInt("user_id");
            int productId = rs.getInt("product_id");
            Date purchaseDate = rs.getDate("purchase_date");
            bucket = new Bucket(bucketId, userId, productId, purchaseDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bucket;
    }

    @Override
    public Bucket update(Integer id, Bucket bucket) {
        throw new IllegalStateException("Bucket can`t be updated!!!");
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bucket> readAll() {
        List<Bucket> listOfBuckets = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int bucketId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int productId = rs.getInt("product_id");
                Date purchaseDate = rs.getDate("purchase_date");
                listOfBuckets.add(new Bucket(bucketId, userId, productId, purchaseDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfBuckets;
    }
}
