import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Carts, Orders } from './model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public cartItemList : any =[]
  public productList = new BehaviorSubject<any>([]);
  constructor(private httpClient:HttpClient,private router:Router) { }

/////////getcartdetails//////////////////
  endpoint11: string = 'http://localhost:9001/api/v2/fetchcartdata/';
  public getparticulatcartList():Observable<any>{
    
    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint11}${emailId}`
    console.log("api is called");
    return this.httpClient.get(api);
  }


  //Get add item in cart With Email
  endpoint1: string = 'http://localhost:9001/api/v2/additem/';
  public addcartdetailsforsingleuser(product:Carts):Observable<Carts>{
    
    const contentType = {'content-Type':'application/json'}
    const jsonObject = JSON.stringify(product)
    console.log("json object:::",jsonObject);
    console.log("product:::",product);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint1}${emailId}`
    console.log("api is called");
    return this.httpClient.put<Carts>(api,jsonObject, {'headers':contentType});
  }


    // add item in order With Email
    endpoint3: string = 'http://localhost:9001/api/v2/updateorder/';
    public addOrderdetailsforsingleuser(product:Carts):Observable<Orders>{
      
      const contentType = {'content-Type':'application/json'}
      const jsonObject = JSON.stringify(product)
      console.log("json objectin order:::",jsonObject);
      console.log("product in order:::",product);
  
      let emailId: any = localStorage.getItem('currentlyloggeduseremail');
      console.log("currentlyloggeduseremail"+ emailId);

      let api=`${this.endpoint3}${emailId}`
      console.log("api is called");
      return this.httpClient.put<Orders>(api,jsonObject, {'headers':contentType});
    }


  /////////getOrderList//////////////////
  endpoint2: string = 'http://localhost:9001/api/v2/getorder/';
  public getOrderList():Observable<any>{
    
    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint2}${emailId}`
    console.log("api is called");
    return this.httpClient.get(api);
  }


  //Delete Cart Item
  endpoint4: string = 'http://localhost:9001/api/v2/deleteitem/';
  public deletecartitem(item:any):Observable<any>{
  
    console.log("product:::",item);
    // const contentType = {'content-Type':'application/json'}
    // const jsonObject = JSON.stringify(item)
    
    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);
   
    let api=`${this.endpoint4}${emailId}/${item}`
    console.log("api is called");
    return this.httpClient.delete<any>(api);
    
  }
  

  //Remove CartItem
  removeCartItem(product: any){
    this.cartItemList.map((a:any, index:any)=>{
      if(product.id=== a.id){
        this.cartItemList.splice(index,1);
      }
    })
    this.productList.next(this.cartItemList);
  }


  //Remove All Item
  removeAllCart(){
    this.cartItemList = []
    this.productList.next(this.cartItemList);
  }

//Updating Quantity In Cart (Inc)
  endpoint7: string = 'http://localhost:9001/api/v2/updatequantity/';
  public UpdateQuantity(product:Carts):Observable<Carts>{
      
    const contentType = {'content-Type':'application/json'}
    const jsonObject = JSON.stringify(product)
    console.log("json objectin order:::",jsonObject);
    console.log("product in order:::",product);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint7}${emailId}/${product.foodname}`
    console.log("api is called");
    return this.httpClient.put<Carts>(api,jsonObject, {'headers':contentType});
  }
  

  //Updating Quantity In Cart (Dec)
  endpoint8: string = 'http://localhost:9001/api/v2/decreasequantity/';
  public decreaseQuantity(product:Carts):Observable<Carts>{
      
    const contentType = {'content-Type':'application/json'}
    const jsonObject = JSON.stringify(product)
    console.log("json objectin order:::",jsonObject);
    console.log("product in order:::",product);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);
    
    let api=`${this.endpoint8}${emailId}/${product.foodname}`
    console.log("api is called");
    return this.httpClient.put<Carts>(api,jsonObject, {'headers':contentType});
  }


//Calculating Subtotal Of A Product
  endpoint9: string = 'http://localhost:9001/api/v2/updatesubtotal/';
  public calculatesubtotal(product:Carts):Observable<Carts>{
      
    const contentType = {'content-Type':'application/json'}
    const jsonObject = JSON.stringify(product)
    console.log("json objectin order:::",jsonObject);
    console.log("product in order:::",product);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId); 
    
    let foodn : any = product.foodname;
    console.log("foodn"+ foodn); 
    let discountprice1 : any = product.discountprice;
    console.log("foodn"+ discountprice1); 
    let quantity1 : any = product.quantity;
    console.log("foodn"+ quantity1); 
    let api=`${this.endpoint9}${emailId}/${foodn}/${discountprice1}/${quantity1}`
    console.log("api is called");
    return this.httpClient.put<Carts>(api,jsonObject, {'headers':contentType});
  }

  endpoint10: string = 'http://localhost:9001/api/v2/deletefullist/';
  public deletefullcartlist(product:Carts):Observable<Carts>{

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId); 

    let api=`${this.endpoint10}${emailId}`
    console.log("api deletefullcartlist is called");
    return this.httpClient.delete<Carts>(api);
  }

}
