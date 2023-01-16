import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { FoodServiceService } from '../services/food-service.service';
import { RestaurantFoods } from '../models/restaurant-foods';
import { RestaurantData } from '../models/restaurantdata';
import { RestaurantRegister } from '../models/restaurant-register';
import { RestaurantServiceService } from '../services/restaurant-service.service';
import { FormControl, FormGroup } from '@angular/forms';
import { ImageProcessingService } from '../services/image-processing.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  foodlist1: any;

  //foods:RestaurantFoods[] | undefined;
  restaurantDetails:RestaurantRegister[] = [];

  constructor(
    private router: Router,
    private restaurantservice: RestaurantServiceService,
    private imageProcessingService:ImageProcessingService 
  ) {}
  ngOnInit(): void {
    this.getAllRestaurant();
  }

  public getAllRestaurant(){
    this.restaurantservice.getAllRestaurant()
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



  searchText: string = '';

  onSearchTextentered(Searchvalue: string) {
    this.searchText = Searchvalue;
    console.log(this.searchText);
  }
}

function addtocart(item: any, any: any) {
  throw new Error('Function not implemented.');
}

function onSearchtext() {
  throw new Error('Function not implemented.');
}
