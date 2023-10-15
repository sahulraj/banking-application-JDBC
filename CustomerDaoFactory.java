import java.sql.Connection;
public class CustomerDaoFactory {
private static  CustomerDao customerDao;
private CustomerDaoFactory(){};
public static CustomerDao getCustomerDao()
{
    if(customerDao==null)
    {
        customerDao=new CustomerDaoImpl();
    }
    return customerDao;

}

}
