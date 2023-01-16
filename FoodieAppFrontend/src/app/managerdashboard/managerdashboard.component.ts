import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { CartService } from '../cart.service';
import { RestaurantFoods } from '../models/restaurant-foods';
import { RestaurantData } from '../models/restaurantdata';
import { FoodImageProcessingService } from '../services/food-image-processing.service';
import { FoodServiceService } from '../services/food-service.service';
import { RestaurantServiceService } from '../services/restaurant-service.service';

@Component({
  selector: 'app-managerdashboard',
  templateUrl: './managerdashboard.component.html',
  styleUrls: ['./managerdashboard.component.css']
})
export class ManagerdashboardComponent implements OnInit {

  foodlist1:any;
  foodList1:any;

  foodDetails:RestaurantFoods[] = [];

  constructor(
          private foodService: FoodServiceService, 
          private router: Router,
          private restaurantservice:RestaurantServiceService,
          private foodImageProcessingService:FoodImageProcessingService,
          private cartservice:CartService
          ) {}
  ngOnInit(): void {
    this.getFoodlistOfRestaurant();
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

  searchText:string='';
  
  onSearchTextentered(Searchvalue:string){
    this.searchText=Searchvalue;
    console.log(this.searchText);
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
}
