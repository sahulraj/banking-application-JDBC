import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws SQLException {
        Scanner scanner = new Scanner(System.in);


        //CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();
boolean temp=true;
        System.out.println("welcome to our royal bank");
while(temp)
{
    System.out.println("please select 1->if you are an employer");
    System.out.println("please select 2->if you are a customer");
    System.out.println("please select 3->if you want to exit");
    int num = scanner.nextInt();
    if(num==3)break;
    if(num==2)
    {
        System.out.println("welcome dear customer please select 1 you are an existing customer,select 2 if you are a new customer");
        int n = scanner.nextInt();
        if(n==2)
        {
            System.out.println("please enter your details to create an account in our bank");
            System.out.println("please enter your name");
            String name = scanner.next();
            System.out.println("please set a strong password to login in later");
            String password = scanner.next();
            System.out.println("a minimum balance of 5000 rupees should be maintained in your account");
            int amount = 5000;
            System.out.println("set a pin for performing transactions after logging in");
            int pin = scanner.nextInt();
            Customer customer = new Customer(name,password,amount,pin);
            CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();
            customerDao.addCustomer(customer);
            Customer customer1 = customerDao.getCustomer(name,password);
            System.out.println(customer1.getCust_id() + "is your customer id");
            System.out.println("thank you for creating account in our bank");


        }
        if(n==1)
        {
            System.out.println("please enter your username and password to login" );
            String username = scanner.next();
            String password = scanner.next();
            CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();
            Customer customer1 = customerDao.getCustomer(username,password);
            System.out.println("enter 1 if you want to view the balance");
            System.out.println("enter 2 if you want to withdraw money from your account");
            System.out.println("enter 3 if you want to deposit money in your account");
            System.out.println("enter 4 if you want to transfer money from your account to another account");
            System.out.println("enter 5 if you want to logout");
            int task = scanner.nextInt();
            System.out.println("please enter your pin");
            int p = scanner.nextInt();
            if(p==customer1.getPin()) {
                switch (task) {

                    case 1: {
                        System.out.println(customer1);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("enter the amount you want to withdraw");
                        int amount = scanner.nextInt();
                        if(amount<=customer1.getAmount())
                        {
                            System.out.println(amount+" withdrawn");
                            int pre_amount = customer1.getAmount();
                            customer1.setAmount(pre_amount-amount);
                            customerDao.updateCustomer(customer1);
                            System.out.println("remaining amount "+ (customer1.getAmount()-amount));
                        }
                        else
                        {
                            System.out.println("insufficient balance please check your balance");
                        }



                        break;
                    }
                    case 3: {
                        System.out.println("enter the amount you want to deposit");
                        int amount=scanner.nextInt();
                        int balance=customer1.getAmount();
                        customer1.setAmount(balance+amount);
                        customerDao.updateCustomer(customer1);

                        
                        break;
                    }
                    case 4: {
                        System.out.println("enter the id of the customer you want to transfer the money");
                        int id = scanner.nextInt();
                        int amount = scanner.nextInt();
                        Customer receiver= customerDao.getCustomerById(id);
                        int balance= receiver.getAmount();
                        receiver.setAmount(balance+amount);
                        customerDao.updateCustomer(receiver);


                        break;
                    }
                    case 5:
                        break;
                }
                default:
                    break;
            }
            else
            {
                System.out.println("entered password is incorrect");
            }

            }




    }




}




    }
}