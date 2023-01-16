import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';
import { RestaurantFoods } from '../models/restaurant-foods';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

 

  public products : any = [];
  public orders:any=[];
  public grandTotal : number | undefined;

  public productquantity!: number;
  public subtotal1:number | undefined;
sub1: any;

productD: RestaurantFoods[] = [];
displayedColumns: string[] = ['Sr.No' ,'Food Name', 'Price' ,'Quantity', 'Discounted Price', 'Sub Total', 'Delete'];
dataSource = this.products;
index:any = 0;

  constructor(
    private cartservice:CartService, 
    private router:Router, 
    private httpClient:HttpClient
    ) { }

  ngOnInit(): void {
    // this.userhomepage();
    this.getCartProduct();
  }


  getCartProduct(){
    this.cartservice.getparticulatcartList()
    .subscribe(res => {
      this.products = res;
      console.log('Trying', res)
    })
  }


  userhomepage(){
    //  this.subtotal();
    this.cartservice.getparticulatcartList()
  .subscribe(res=>{

    this.products = res;
    console.log("in cart:",this.products)
    let total = 0;   
    this.products.map((a:any)=>{
      
      total += a.subtotal;
      this.grandTotal=total;
      console.log("Grand Total:"+this.grandTotal)
    })    
  });
  }

  removeItem(item:any){
    console.log(item,"Response item")
    this.cartservice.deletecartitem(item)
    .subscribe(res=>{
      console.log(res,"Response deleted")
     // this.router.navigate(['/userdashboard']);
      // this.ngOnInit();
      this.userhomepage();
    });
  }

  subtotal(){
    console.log(this.products)
    this.products.map((a:any)=>{
        this.cartservice.calculatesubtotal(a).
    subscribe(respo=>{
      a = respo;
      });
      
    });  console.log(this.products," subtotal 1")
  }
  


  checkout(){
    this.cartservice.getparticulatcartList()
    .subscribe(res=>{
      this.products=res
      console.log("checout cartList:",this.products)
      this.cartservice.addOrderdetailsforsingleuser(this.products)
      .subscribe(orderres=>{
        console.log("Orderedresponse:",orderres)
        this.orders=orderres
        console.log("OrderedList:",this.orders)
        this.cartservice.deletefullcartlist(this.products)
        .subscribe(cartres=>{
          this.products=cartres
          console.log("cartfullist:",this.products)
        });
        this.router.navigate(['/usernavdashboard/ordersuccess']);
      })

    })
  }


  inc(prod:any){
    console.log(prod,"Response product")
    if(prod.quantity != 5)
    {
        prod.quantity += 1;
        this.cartservice.UpdateQuantity(prod)
        .subscribe(res=> {
          console.log("quantity Updated:",prod.quantity);
          this.userhomepage();
        });
    }
    //this.productquantity=prod.quantity;
  }


  dec(prod:any){
    if(prod.quantity != 1){
    prod.quantity -= 1;
    this.cartservice.decreaseQuantity(prod)
    .subscribe(res=>{
      console.log("quantity Updated:",prod.quantity);
      this.userhomepage();
    });
    }
  }



}
