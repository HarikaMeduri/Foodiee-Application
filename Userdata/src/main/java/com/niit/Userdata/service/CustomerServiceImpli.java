package com.niit.Userdata.service;

import com.niit.Userdata.config.Producer;
import com.niit.Userdata.domain.*;
import com.niit.Userdata.exception.CartisEmptyException;
import com.niit.Userdata.exception.EmailAlreadyExistsException;
import com.niit.Userdata.exception.EmailNotFoundException;
import com.niit.Userdata.repository.CustomerRepository;
import com.niit.rabbitmq.domain.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

//@AllArgsConstructor
@Service
public class CustomerServiceImpli implements CustomerService{
    private CustomerRepository customerRepository;
    private MongoOperations mongoOperations;

    @Autowired
    private Producer producer;

    @Autowired
    public CustomerServiceImpli(CustomerRepository customerRepository, MongoOperations mongoOperations) {
        this.customerRepository = customerRepository;
        this.mongoOperations = mongoOperations;
    }






    ////////////////Customer Id sequence///////////////////////
    @Override
    public int getSequenceNumber(String sequenceName) {

        //getsequenceno
        Query query=new Query(Criteria.where("id").is(sequenceName));
        //update the sequence no
        Update update=new Update().inc("seq",1);
        //modify in document
        DbSequence counter=mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),DbSequence.class);


        return !Objects.isNull(counter) ? counter.getSeq() : 1001;
    }



    //////////////////////////Add new item in cart for particular customer///////////////////////////////
    @Override
    public Customer additemincart(Cart cart,String email) {
        Customer customer1 = customerRepository.findByEmail(email);
        if(customer1.getCartList()==null)
        {
           List<Cart> cartList = customer1.getCartList();
            customer1.setCartList(Arrays.asList(cart));
            return customerRepository.save(customer1);
        }
        else {
            List<Cart> cartList = customer1.getCartList();
            List<Cart> filteredCartList = cartList.stream().filter((c -> c.getFoodname().equalsIgnoreCase(cart.getFoodname()))).
                    collect(Collectors.toList());
            if(filteredCartList.size()==1)
            {
                System.out.println("Fooditem Already Exists");
                return null;
            }else {
                cartList.add(cart);
                customer1.setCartList(cartList);
                System.out.println("cartlist in elseeeee:" + cartList);
                return customerRepository.save(customer1);
            }
        }

    }


    ////////////////Add new customer addresss for particular customer///////////////////////
    @Override
    public Customer addnewaddress(Address address, String email) {
        Customer customer1 = customerRepository.findByEmail(email);
        if(customer1.getAddressList().isEmpty())
        {
            customer1.setAddressList(Arrays.asList(address));
            return customerRepository.save(customer1);
        }
        else {
            List<Address> addressList = customer1.getAddressList();
            addressList.add(address);
            customer1.setAddressList(addressList);
            return customerRepository.save(customer1);
        }

    }

//    @Override
//    public void deleteitem(String email, int cartid) throws  CartisEmptyException {
//        Customer customer1 = customerRepository.findByEmail(email);
//
//            List<Cart> list1 = customer1.getCartList();
//            System.out.println("HIIIIIIIIIIIIIIIII");
//            if (list1.isEmpty()) {
//                throw new CartisEmptyException();
//            } else {
//
//                list1 = list1.stream().filter(cart -> cart.getCartid() != cartid).
//                        collect(Collectors.toList());
//                customer1.setCartList(list1);
//                System.out.println("Afterfilter::::" + list1);
//                customerRepository.save(customer1);
//            }
//
//    }


    ////////////////delete item in cart through item name for particular customer///////////////////////
    @Override
    public void deleteitemthroughname(String email, String name) throws  CartisEmptyException {
        Customer customer1 = customerRepository.findByEmail(email);

//        boolean flag=false;
            List<Cart> list1 = customer1.getCartList();
            if (list1.isEmpty()) {
                throw new CartisEmptyException();
            } else {

                list1 = list1.stream().filter(not(c -> c.getFoodname().equalsIgnoreCase(name))).
                        collect(Collectors.toList());
                customer1.setCartList(list1);
                System.out.println("AfterDeletion List::::" + list1);
                customerRepository.save(customer1);
//                flag=true;
            }
//            return flag;

    }




    ////////////////Saving customer details (Registration)///////////////////////
    @Override
    public Customer saveCustomer(Customer customer) throws EmailAlreadyExistsException {
        Customer customer1;
        Optional<Customer> result = Optional.ofNullable(customerRepository.findByEmail(customer.getEmail()));
        if(result.isPresent())
        {
            throw new EmailAlreadyExistsException();
        }
        else{
            customer.setUserid(getSequenceNumber(Customer.SEQUENCE_NAME));


            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setUserid(customer.getUserid());
            customerDTO.setUsername(customer.getUsername());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setPassword(customer.getPassword());
            customerDTO.setStatus("no");
            producer.sendMessageToRabbitMq(customerDTO);
            System.out.println("--"+customerDTO.getStatus());
            customerRepository.save(customer);
            customer1=customerRepository.save(customer);
            System.out.println("Output: "+customer1);
        }
        return customer1;
    }

    ////////////////Get all customer details///////////////////////
    @Override
    public List<Customer> getallCustomers() {

        return customerRepository.findAll();
    }


    ////////////////Customer Profile update ///////////////////////
    @Override
    public Customer updateCustomerdetails(Customer customer,String email) {

        Customer customerUpdate=customerRepository.findByEmail(email);
        customerUpdate.setUsername(customer.getUsername());

        customerUpdate.setPassword(customer.getPassword());
        customerUpdate.setPhonenumber(customer.getPhonenumber());

        return customerRepository.save(customerUpdate);
    }


    ////////////////Get only cart details of particular customer///////////////////////
    @Override
    public List<Cart> getcartdetailsthroughemail(String email) {

        List<Cart> cust1=customerRepository.findByEmail(email).getCartList();
        System.out.println("Got Cart Details through email::::::::::::"+cust1);
        return cust1;
    }

    @Override
    public Customer updateCustomerstatus(String email, String status) {
        Customer customer1 = customerRepository.findByEmail(email);
        customer1.setStatus(status);
        return  customerRepository.save(customer1);

    }


    ///////////////////////updating orders/////////////////////////

    @Override
    public Customer updateorder(List<Orders> orders, String email){
        Customer customer1 = customerRepository.findByEmail(email);
        if(customer1.getOrdersList()==null){

           // List<Orders> ordersList = customer1.getOrdersList();
            customer1.setOrdersList(orders);
            System.out.println("cartlist in ifff:"+customer1);
            return customerRepository.save(customer1);
        }
        else {
            List<Orders> ordersList = customer1.getOrdersList();
            ordersList.addAll(orders);
            customer1.setOrdersList(ordersList);
            System.out.println("cartlist in elseeeee:"+ordersList);
            return customerRepository.save(customer1);
        }

    }

    @Override
    public List<Orders> getOrderdetails(String email) {
        List<Orders> cust1=customerRepository.findByEmail(email).getOrdersList();
        System.out.println("Got Order Details through email::::::::::::"+cust1);
        return cust1;
    }

    @Override
    public Customer updatequantity(String email, String foodname) {
        Customer cust1=customerRepository.findByEmail(email);
        List<Cart> cart1=cust1.getCartList();
        List<Cart> cart2 = cart1.stream().filter(c->c.getFoodname().equalsIgnoreCase(foodname)).collect(Collectors.toList());
        System.out.println("cart22222"+cart2);

        int i = (cart2.get(0).getQuantity()) + 1;
        cart2.get(0).setQuantity(i);
        //cart1.add((Cart) cart2);

        //cust1.setCartList(cart1);
        System.out.println("cuatomer details::"+cust1);
        customerRepository.save(cust1);
        return cust1;
    }

    @Override
    public Customer decreasingquantity(String email, String foodname) {
        Customer cust1=customerRepository.findByEmail(email);
        List<Cart> cart1=cust1.getCartList();
        List<Cart> cart2 = cart1.stream().filter(c->c.getFoodname().equalsIgnoreCase(foodname)).collect(Collectors.toList());
        System.out.println("cart22222"+cart2);

        int i = (cart2.get(0).getQuantity()) - 1;
        cart2.get(0).setQuantity(i);
        //cart1.add((Cart) cart2);

        //cust1.setCartList(cart1);
        System.out.println("cuatomer details::"+cust1);
        customerRepository.save(cust1);
        return cust1;
    }

    @Override
    public Customer updatesubtotal(String email, String foodname, double discountprice, int quantity) {
        Customer cust1=customerRepository.findByEmail(email);
        List<Cart> cart1 = cust1.getCartList();
        //Cart cart1=customerRepository.
        System.out.println(cart1);
        //Cart cart2=customerRepository.findFirstByFoodname(foodname);
        List<Cart> cart2 = cart1.stream().filter(c->c.getFoodname().equalsIgnoreCase(foodname)).collect(Collectors.toList());
        System.out.println("cart22222"+cart2);

        double subtotal1=discountprice*quantity;
        cart2.get(0).setSubtotal(subtotal1);
//        cart1.stream().filter(x->x.)

        customerRepository.save(cust1);
        return cust1;
    }

    @Override
    public void deletecartItemfulllist(String email) {
        Customer cust1=customerRepository.findByEmail(email);
        List<Cart> cart1 = cust1.getCartList();
        cust1.getCartList().removeAll(cart1);
        customerRepository.save(cust1);
    }


//    @Override
//    public Cart updatesubtotal(String email,String foodname,double discountprice,int quantity) {
//        List<Cart> cart1 = customerRepository.findByEmail(email).getCartList();
//        //Cart cart1=customerRepository.
//        System.out.println(cart1);
//        //Cart cart2=customerRepository.findFirstByFoodname(foodname);
//        List<Cart> cart2 = cart1.stream().filter(c->c.getFoodname().equalsIgnoreCase(foodname)).collect(Collectors.toList());
//        System.out.println("cart22222"+cart2);
//        Cart cart3 = cart2.get(0);
//        double sub = cart3.getSubtotal();
//        double subtotal1=discountprice*quantity;
//        cart3.setSubtotal(subtotal1);
//        cart1.stream().filter(x->x.)
//
//        customerRepository.save(cart3);
//        return cart3;
//    }


}




//    @Override
//    public boolean deletecartitem(String email, int cartid) throws EmailNotFoundException {
//        Customer customer1 = customerRepository.findByEmail(email);
//        System.out.println("HIIIIIIIIIIIIIIIII");
//        if(customer1.getEmail().isEmpty()){
//            throw new EmailNotFoundException();
//        }
//        else{
//            if(customer1.getCartList().isEmpty())
//            {
//                return false;
//            }
//            else {
//                List<Cart> cartList = customer1.getCartList();
//                System.out.printf("cartlist"+cartList);
//                cartList.remove(cartid);
//                customer1.setCartList(cartList);
//                System.out.printf("AFter"+cartList);
//                customerRepository.save(customer1);
//                return true;
//            }
//
//        }
//
//    }