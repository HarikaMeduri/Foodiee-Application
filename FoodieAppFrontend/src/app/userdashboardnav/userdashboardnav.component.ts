import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';
import { RestaurantData } from '../models/restaurantdata';
import { RestaurantServiceService } from '../services/restaurant-service.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-userdashboardnav',
  templateUrl: './userdashboardnav.component.html',
  styleUrls: ['./userdashboardnav.component.css']
})
export class UserdashboardnavComponent implements OnInit {
  public totalItem : number = 0;
  public numberlength:any;
  foodlist1:any;
  constructor(
    private cartservice: CartService, private router: Router,
    private restaurantservice: RestaurantServiceService,
    private userService: UserService
    ) { }

  ngOnInit(): void {
    this.restaurantservice.getAllRestaurant()
    .subscribe((data)=>{
      console.log("restaurant data",data);
      this.foodlist1=data;
      console.log("thisfoodlist.length",this.foodlist1.length)     
      
    }
    );
  }

  logout() {
    this.userService.doLogout();
  }


  // check(){
  //    this.cartservice.getparticulatcartList()
  //   .subscribe(res=>{
  //     console.log("response length:",res.length)
  //     this.totalItem = res.length;
  //     if(this.totalItem==null)
  //     {
  //       this.totalItem=0;
  //     }
  //     else{
  //       this.totalItem;
  //     }
  //       console.log("TotalItems:",this.totalItem)
  //   });
  // }
  

}
