import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestaurantFoods } from '../models/restaurant-foods';

@Injectable({
  providedIn: 'root'
})
export class FoodServiceService {

  constructor(private httpClient:HttpClient) { }

    //Try 1 for Foodlist with image uploading
    endpoint1: string = 'http://localhost:9001/api/v3/updateFoodlist/';
    public updateFoodlist(foods:FormData)
    {
      let emailId: any = localStorage.getItem('currentlyloggeduseremail');
      console.log("currentlyloggeduseremail"+ emailId);
      let api=`${this.endpoint1}${emailId}`;
      return this.httpClient.put<RestaurantFoods>(api, foods);
    }

  
  //Update foodlist with Email
  // endpoint1: string = 'http://localhost:8088/api/v1/updateFoodlist/';
  // public updateFoodlist(foodlist:RestaurantFoods):Observable<RestaurantFoods>{
  //   const contentType = {'content-Type':'application/json'}
  //   const jsonObject = JSON.stringify(foodlist)

  //   // let loginToken: any = localStorage.getItem('access_token');
  //   // console.log("Access Token"+ loginToken);

  //   let emailId: any = localStorage.getItem('currentlyloggeduseremail');
  //   console.log("currentlyloggeduseremail"+ emailId);

  //   console.log("Food Details(Within Food Service):")
  //   console.log(foodlist);

  //   let api=`${this.endpoint1}${emailId}`;
  //   return this.httpClient.put<RestaurantFoods>(api,jsonObject, {'headers':contentType})
  // }


  //View Foodlist with Email
  endpoint2: string = 'http://localhost:9001/api/v3/getFoodList/';
  public getFoodlistWithEmail():Observable<any>{
    console.log("Food Details(Within Food Service):")

    // let loginToken: any = localStorage.getItem('access_token');
    // console.log("Access Token"+ loginToken);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint2}${emailId}`
    return this.httpClient.get(api);
  }


  //Delete Food Item using Foodname
  endpoint3: string = 'http://localhost:8088/api/v1/deleteFoodItem/';
  public deleteFoodWithFoodname(foodname:string):Observable<any>{
    console.log("Restaurant Details(Within Restaurant Service):")

    // let loginToken: any = localStorage.getItem('access_token');
    // console.log("Access Token"+ loginToken);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint3}${emailId}${foodname}`
    return this.httpClient.delete(api);
  }


    //Search Foods By Food Name
    endpoint4: string = 'http://localhost:8088/api/v1/searchFoodByName/';
    public searchFoodsWithFoodname(foodname:string):Observable<any>{
      console.log("Food Details(Within Food Service):")
  
      // let loginToken: any = localStorage.getItem('access_token');
      // console.log("Access Token"+ loginToken);
  
      let emailId: any = localStorage.getItem('currentlyloggeduseremail');
      console.log("currentlyloggeduseremail"+ emailId);
  
      let api=`${this.endpoint4}${emailId}${foodname}`
      return this.httpClient.get(api);
    }

    public getfoodlist(): Observable<RestaurantFoods[]>{
      return this.httpClient.get<RestaurantFoods[]>("http://localhost:9001/api/v3/fetchallfoodsitems");
    }
}
