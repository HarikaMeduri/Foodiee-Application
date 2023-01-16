package com.niit.Userdata.service;


import com.niit.Userdata.domain.Address;
import com.niit.Userdata.domain.Cart;
import com.niit.Userdata.domain.Customer;
import com.niit.Userdata.domain.Orders;
import com.niit.Userdata.exception.CartisEmptyException;
import com.niit.Userdata.exception.EmailAlreadyExistsException;
import com.niit.Userdata.exception.EmailNotFoundException;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer) throws EmailAlreadyExistsException;
    List<Customer> getallCustomers();

    Customer updateCustomerdetails(Customer product,String email);


    List<Cart> getcartdetailsthroughemail(String email);



    int getSequenceNumber(String sequenceName);



    Customer additemincart(Cart cart,String email);

    Customer addnewaddress(Address address,String email);



//    void deleteitem(String email,int cartid)throws CartisEmptyException;

    void deleteitemthroughname(String email,String name)throws CartisEmptyException;

    Customer updateCustomerstatus(String email, String status);
    Customer updateorder(List<Orders> orders, String email);
    List<Orders> getOrderdetails(String email);

    Customer updatequantity(String email, String foodname);

    Customer decreasingquantity(String email, String foodname);

    Customer updatesubtotal(String email,String foodname,double discountprice,int quantity);

    void deletecartItemfulllist(String email);
//    public Cart updatesubtotal(String email,String foodname,double discountprice,int quantity) ;
}
