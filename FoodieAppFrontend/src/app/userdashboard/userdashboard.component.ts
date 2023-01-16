import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { CartService } from '../cart.service';
import { RestaurantFoods } from '../models/restaurant-foods';
import { RestaurantRegister } from '../models/restaurant-register';
import { RestaurantData } from '../models/restaurantdata';
import { FoodImageProcessingService } from '../services/food-image-processing.service';
import { FoodServiceService } from '../services/food-service.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { RestaurantServiceService } from '../services/restaurant-service.service';

@Component({
  selector: 'app-userdashboard',
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.css'],
})
export class UserdashboardComponent implements OnInit {
  foodlist1: RestaurantData[] | undefined;

  
  restaurantDetails:RestaurantRegister[] = [];
  foodDetails:RestaurantFoods[] = [];
  foods: any = [];

  constructor(
    private cartservice: CartService,
    private router: Router,
    private restaurantService: RestaurantServiceService,
    private foodService:FoodServiceService, 
    private foodImageProcessingService:FoodImageProcessingService,
    private imageProcessingService:ImageProcessingService 
  ) {}
  ngOnInit(): void {
    // this.getFoodlistOfRestaurant();
    this.getAllRestaurant();

  }


  public getAllRestaurant(){
    this.restaurantService.getAllRestaurant()
    .pipe(
      map((x:RestaurantRegister[],index) => x.map((restaurant:RestaurantRegister) => this.imageProcessingService.createImages(restaurant)))
      )
      .subscribe(
        (response : RestaurantRegister[]) => {
          console.log(response);
          this.restaurantDetails = response;
          // this.restaurantDetails.map((a:any) => {
          //     a.map((foods:RestaurantFoods) => this.foodImageProcessingService.createImages(foods))
          //     .subscribe((response:RestaurantFoods[]) => {
          //       console.log("userdashboard response",response)
          //     },
          //     (error:HttpErrorResponse) => {
          //       console.log(error);
          //     })
          // })
          // this.getFoodlistOfRestaurant();

        },
        (error:HttpErrorResponse) => {
          console.log(error);
        }
      );
  }


  public getFoodlistOfRestaurant() {
    this.foodService.getFoodlistWithEmail()
    .pipe(
      map((x:RestaurantFoods[],index) => x.map((foods:RestaurantFoods) => this.foodImageProcessingService.createImages(foods)))
    )
    .subscribe(
      (response: RestaurantFoods[]) => {
        console.log(response);
        this.foodDetails = response;
      },
      (error:HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  Addtocart(x:any){
    this.cartservice.addcartdetailsforsingleuser(x)
    .subscribe(
      (response: any) => {
        console.log(response);
        if(response==null)
        {
          alert('This food item is already in cart');
        }
        else{
          console.log(response);
          alert('Item Added to the cart')
          // this.cartservice.addcartdetailsforsingleuser(x).subscribe((res) => {
          //   console.log('cart response', res);
          // });
        }
      },
      (error:HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  searchText: string = '';
  onSearchTextentered(Searchvalue: string) {
    this.searchText = Searchvalue;
    console.log(this.searchText);
  }


// addtocart(item: any) {
//   console.log('food item name', item.foodname);
//   let foodname12: any;
//   this.cartservice.getparticulatcartList().subscribe((res11) => {
//     console.log('response',res11)
//     this.foods=res11;
//     this.foods.forEach((element: { foodname: any; }) => {
//       foodname12=element.foodname;
//       console.log("response element of food item name", foodname12)
//     });
//     if (item.foodname == foodname12) {
//       alert('This food item is already in cart');
//     }
//     else{
//       this.cartservice.addcartdetailsforsingleuser(item).subscribe((res) => {
//         console.log('cart response', res);
//       });
//     }
    
//   });
// }

}
