import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RestaurantAddress } from '../models/restaurant-address';
import { RestaurantRegister } from '../models/restaurant-register';
import { RestaurantData } from '../models/restaurantdata';

@Injectable({
  providedIn: 'root'
})
export class RestaurantServiceService {

  addressObject: any;

  constructor(private httpClient:HttpClient,
    private router:Router) { }

  //Try For image Uploading
  public addRestaurant(restaurant:FormData)
  {
    return this.httpClient.post<RestaurantRegister>("http://localhost:9001/api/v3/addRestaurant", restaurant);
  }


  // // Add Restaurant
  // endpoint5: string = 'http://localhost:9001/api/v3/addRestaurant/'
  // public registerRestaurant(restaurant:RestaurantRegister):Observable<RestaurantRegister>{
  //   const contentType = {'content-Type':'application/json'}
  //   const jsonObject = JSON.stringify(restaurant)

  //   let emailId: any = restaurant.email;
  //   console.log('Emailid',emailId);

  //   console.log("Restaurant Details(Within Restaurant Service):")
  //   let api =`${this.endpoint5}${emailId};`
  //   console.log("FormData in restaurant service",restaurant);

  //   return this.httpClient.post<RestaurantRegister>(api,jsonObject, {'headers':contentType})
  // }
 
 
// Add Restaurant
  public registerRestaurant(restaurant:RestaurantRegister):Observable<RestaurantRegister>{
    const contentType = {'content-Type':'application/json'}
    const jsonObject = JSON.stringify(restaurant)
    console.log("Restaurant Details(Within Restaurant Service):")
    console.log(restaurant);
    return this.httpClient.post<RestaurantRegister>("http://localhost:9001/api/v3/addRestaurant",jsonObject, {'headers':contentType})
  }



  //Get All Restaurant
  public getAllRestaurant():Observable<any>{
    console.log("Restaurant Details(Within Restaurant Service):")
    return this.httpClient.get("http://localhost:9001/api/v3/getRestaurant");
  }

  //Get Restaurant With Email
  endpoint1: string = 'http://localhost:9001/api/v3/getSingleRestaurant/';
  public getRestaurantWithEmail():Observable<any>{
    console.log("Restaurant Details(Within Restaurant Service):")
    // let loginToken: any = localStorage.getItem('access_token');
    // console.log("Access Token"+ loginToken);
    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);
    let api=`${this.endpoint1}${emailId}`
    return this.httpClient.get<any>(api);
  }

  //Delete A Restaurant by email
  endpoint2: string = 'http://localhost:8088/api/v1/deleteRestaurantByEmail/';
  public deleteRestaurantWithEmail():Observable<any>{
    console.log("Restaurant Details(Within Restaurant Service):")

    // let loginToken: any = localStorage.getItem('access_token');
    // console.log("Access Token"+ loginToken);
    
    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint2}${emailId}`
    return this.httpClient.delete(api);
  }

  //Search Restaurants By City Name
  endpoint3: string = 'http://localhost:8088/api/v1/searchRestaurantsByCity/';
  public getRestaurantWithCityname(city:string):Observable<any>{
    console.log("Restaurant Details(Within Restaurant Service):")

    // let loginToken: any = localStorage.getItem('access_token');
    // console.log("Access Token"+ loginToken);

    // let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    // console.log("currentlyloggeduseremail"+ emailId);

    let api=`${this.endpoint3}${city}`
    return this.httpClient.get(api);
  }

  //Update the address of a Restaurant
  endpoint4: string = 'http://localhost:8088/api/v1/updateAddresslist/'
  public updateAddresslist(addresslist:RestaurantAddress):Observable<RestaurantAddress>{
    const contentType = {'content-Type':'application/json'}
    const jsonObject = JSON.stringify(addresslist)

    // let loginToken: any = localStorage.getItem('access_token');
    // console.log("Access Token"+ loginToken);

    let emailId: any = localStorage.getItem('currentlyloggeduseremail');
    console.log("currentlyloggeduseremail"+ emailId);

    console.log("Food Details(Within Food Service):")
    console.log(addresslist);

    let api=`${this.endpoint4}${emailId}`;
    return this.httpClient.put<RestaurantAddress>(api,jsonObject, {'headers':contentType})
  }

  public getfoodlist(): Observable<RestaurantData[]>{
    return this.httpClient.get<RestaurantData[]>("http://localhost:9001/api/v3/fetchallfoodsitems");
  }

  doLogout() {
    let removeToken = localStorage.removeItem('access_token');
    let currentlyloggeduseremail = localStorage.removeItem('currentlyloggeduseremail');
    if (removeToken == null && currentlyloggeduseremail == null) {
      this.router.navigateByUrl("/");
    }
  }

}
