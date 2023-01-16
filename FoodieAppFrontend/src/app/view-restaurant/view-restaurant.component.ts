import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { RestaurantRegister } from '../models/restaurant-register';
import { FoodImageProcessingService } from '../services/food-image-processing.service';
import { ImageProcessingService } from '../services/image-processing.service';
import {RestaurantServiceService} from '../services/restaurant-service.service'; 

@Component({
  selector: 'app-view-restaurant',
  templateUrl: './view-restaurant.component.html',
  styleUrls: ['./view-restaurant.component.css']
})
export class ViewRestaurantComponent implements OnInit {

  restaurantDetails:RestaurantRegister[] = [];

  constructor( 
    private restaurantService:RestaurantServiceService,
    private imageProcessingService:ImageProcessingService,
    private foodImageProcessingService:FoodImageProcessingService 
  ) { }

  ngOnInit(): void {
    this.getRestaurantOfAUser();
    // this.getAllRestaurant();
  }

  public getAllRestaurant() {
    this.restaurantService.getAllRestaurant()
    .pipe(
      map((x:RestaurantRegister[],index) => x.map((restaurant:RestaurantRegister) => this.imageProcessingService.createImages(restaurant)))
    )
    .subscribe(
      (response: RestaurantRegister[]) => {
        console.log(response);
        this.restaurantDetails = response;
      },
      (error:HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  // public newMethod()
  // {
  //   let emailid = localStorage.getItem('')
  //   if(emailid == this.restaurantDetails.find('email'))
  // }




public getRestaurantOfAUser(){
  this.restaurantService.getRestaurantWithEmail()
  // .pipe(
  //   map((x:RestaurantRegister[],index) => x.map((restaurant:RestaurantRegister) => this.imageProcessingService.createImages(restaurant)))
  // )
  .subscribe(
    (response:RestaurantRegister[]) => {
      console.log(response);
      this.restaurantDetails = response;
    },
    (error:HttpErrorResponse) => {
      console.log(error);
      }
    );
}

}
