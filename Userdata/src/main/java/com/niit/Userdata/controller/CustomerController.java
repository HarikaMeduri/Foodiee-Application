package com.niit.Userdata.controller;

import com.niit.Userdata.domain.Address;
import com.niit.Userdata.domain.Cart;
import com.niit.Userdata.domain.Customer;
import com.niit.Userdata.domain.Orders;
import com.niit.Userdata.exception.CartisEmptyException;
import com.niit.Userdata.exception.EmailAlreadyExistsException;
import com.niit.Userdata.exception.EmailNotFoundException;
import com.niit.Userdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private ResponseEntity responseEntity;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/insertuser")
    public ResponseEntity<?> func1(@RequestBody Customer customer) throws EmailAlreadyExistsException {
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping("/fetchallusers")
    public ResponseEntity<?> func2(){

        return new ResponseEntity<>(customerService.getallCustomers(), HttpStatus.OK);
    }

    @PutMapping("/updateCustomer/{email}")
    public ResponseEntity<?> updateFunction1(@RequestBody Customer customer,@PathVariable String email)
    {
        return  new ResponseEntity<>(customerService.updateCustomerdetails(customer,email), HttpStatus.OK);
    }

    @GetMapping("/fetchcartdata/{email}")
    public ResponseEntity<?> fetchcart(@PathVariable String email){

        return new ResponseEntity<>(customerService.getcartdetailsthroughemail(email), HttpStatus.OK);
    }


    @PutMapping("/additem/{email}")
    public ResponseEntity<?> addingitem(@RequestBody Cart cart,@PathVariable String email)  {
        return new ResponseEntity<>(customerService.additemincart(cart,email), HttpStatus.CREATED);
    }

    @PutMapping("/addnewaddress/{email}")
    public ResponseEntity<?> addingaddress(@RequestBody Address address, @PathVariable String email)  {
        return new ResponseEntity<>(customerService.addnewaddress(address,email), HttpStatus.CREATED);
    }




    @DeleteMapping("/deleteitem/{email}/{name}")
    public void deleteitem(@PathVariable String email,@PathVariable String name) throws CartisEmptyException {
        customerService.deleteitemthroughname(email,name);
        responseEntity = new ResponseEntity("Deleted", HttpStatus.OK);
    }



    @PutMapping("/updatestatus/{email}/{status}")
    public ResponseEntity<?> updateFunction2(@PathVariable String email,@PathVariable String status)
    {
        return  new ResponseEntity<>(customerService.updateCustomerstatus(email,status), HttpStatus.OK);
    }

    @PutMapping("/updateorder/{email}")
    public ResponseEntity<?> getorder(@RequestBody List<Orders> orders, @PathVariable String email) {
        return new ResponseEntity<>(customerService.updateorder(orders,email), HttpStatus.CREATED);
    }

    @GetMapping("/getorder/{email}")
    public ResponseEntity<?> addingorder(@PathVariable String email) throws EmailNotFoundException {
        return new ResponseEntity<>(customerService.getOrderdetails(email), HttpStatus.CREATED);
    }

    @PutMapping("/updatesubtotal/{email}/{foodname}/{discountprice}/{quantity}")
    public ResponseEntity<?> updatetotal(@PathVariable String email,@PathVariable String foodname,@PathVariable double discountprice,@PathVariable int quantity) {
        return new ResponseEntity<>(customerService.updatesubtotal(email,foodname,discountprice,quantity), HttpStatus.CREATED);
    }

    @PutMapping("/updatequantity/{email}/{foodname}")
    public ResponseEntity<?> updatequantity(@PathVariable String email,@PathVariable String foodname) {
        return new ResponseEntity<>(customerService.updatequantity(email,foodname), HttpStatus.CREATED);
    }

    @PutMapping("/decreasequantity/{email}/{foodname}")
    public ResponseEntity<?> decreasequantity(@PathVariable String email,@PathVariable String foodname) {
        return new ResponseEntity<>(customerService.decreasingquantity(email,foodname), HttpStatus.CREATED);
    }


    @DeleteMapping("/deletefullist/{email}")
    public void deletefullist(@PathVariable String email) throws CartisEmptyException {
        customerService.deletecartItemfulllist(email);
        responseEntity = new ResponseEntity("Deleted", HttpStatus.OK);
    }

//    @PutMapping("/updatesubtotal/{email}/{foodname}/{discountprice}/{quantity}")
//    public ResponseEntity<?> updatetotal(@PathVariable String email,@PathVariable String foodname,@PathVariable double discountprice,@PathVariable int quantity) {
//        return new ResponseEntity<>(customerService.updatesubtotal(email,foodname,discountprice,quantity), HttpStatus.CREATED);
//    }

}
