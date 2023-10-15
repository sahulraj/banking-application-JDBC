import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{
    Connection connection;
    public CustomerDaoImpl()
    {
        connection = ConnectionFactory.getConnection();

    }
    @Override
    public void addCustomer(Customer customer) throws SQLException
    {
        String query = "insert into customer(name,password,amount,pin)values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,customer.getName());
        preparedStatement.setString(2,customer.getPassword());
        preparedStatement.setInt(3,customer.getAmount());
        preparedStatement.setInt(4,customer.getPin());
        int count = preparedStatement.executeUpdate();
        if(count>0)
        {
            System.out.println("customer added");
        }
        else
        {
            System.out.println("oops!!!something is wrong please try again");
        }
    }
    public void updateCustomer(Customer customer) throws SQLException
    {
            String query= "update customer set amount=? where cust_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,customer.getAmount());
            preparedStatement.setInt(2,customer.getCust_id());
            int count = preparedStatement.executeUpdate();
        if(count>0)
        {
            System.out.println("customer updated");
        }
        else
        {
            System.out.println("oops!!!something is wrong please try again");
        }



    }
    public Customer getCustomer(String name,String password) throws SQLException
    {
        String query = "select * from customer where name = ? and password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

            int id= resultSet.getInt(1);
            String username = resultSet.getNString(2);
            String pass = resultSet.getNString(3);
            int amount = resultSet.getInt(4);
            int pin = resultSet.getInt(5);
            Customer customer = new Customer(username,pass,amount,pin);
            customer.setCust_id(id);


        return customer;



    }

    public Customer getCustomerById(int id) throws SQLException
    {
        String query = "select * from customer where cust_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int cust_id= resultSet.getInt(1);
        String username = resultSet.getNString(2);
        String pass = resultSet.getNString(3);
        int amount = resultSet.getInt(4);
        int pin = resultSet.getInt(5);
        Customer customer = new Customer(username,pass,amount,pin);
        customer.setCust_id(cust_id);


        return customer;



    }

}
