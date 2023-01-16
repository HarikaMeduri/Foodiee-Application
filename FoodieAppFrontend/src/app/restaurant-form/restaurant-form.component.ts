import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ReadVarExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, FormGroupName, NgForm, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FileHandle } from '../models/file-handle';
import { RestaurantServiceService } from '../services/restaurant-service.service';
import {RestaurantRegister} from '../models/restaurant-register';
import { RestaurantAddress } from '../models/restaurant-address';
import { UserService } from '../user.service';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent implements OnInit {
  


  userStatus :any;

  updateSt(){
    this.userStatus = 'yes';
  }

  ngOnInit(): void {
    
  }

  restaurant: RestaurantRegister = {
    restaurantid:0,
    restaurantname:"",
    phonenumber:"",
    email:"",
    status:"yes",
    foodsList:[],
    addressList:[],
    buildingnumber:0,
    street:"",
    city:"",
    pincode:0,
    state:"",
    country:"",
    restaurantimage: [],
  }

  updateStatus(){
    this.updateSt();
    console.log(this.userStatus);
    this.userService.updateUserStatus(this.userStatus).subscribe((response1) => {
      console.log('user status updated'+response1)
    })
  }

  addressList: RestaurantAddress = {
    buildingnumber:0,
    street:"",
    city:"",
    pincode:0,
    state:"",
    country:""
  }


constructor(private restaurantService:RestaurantServiceService,
            private sanitizer: DomSanitizer,
            private userService:UserService,
            private router:Router)
            {}


addRestaurant(restaurantForm:NgForm)
{
  const restaurantFormData = this.prepareFormData(this.restaurant);
  console.log("check" ,restaurantFormData);
  this.restaurantService.addRestaurant(restaurantFormData).subscribe(
    (response:RestaurantRegister) => {
      console.log(response);
      restaurantForm.reset();
      this.restaurant.restaurantimage = [];
      // this.router.navigateByUrl('/usernavdashboard/userdashboard');
      this.router.navigateByUrl('/managerdashboard');
    },
    (error: HttpErrorResponse) => {
      console.log(error);
    }
  );
    console.log(this.restaurant);
    this.updateStatus();
}

prepareFormData(restaurant:RestaurantRegister): FormData {
    const formData = new FormData();

    formData.append(
      'restaurant', 
      new Blob([JSON.stringify(restaurant)], {type:'application/json'})
    );

    for(var i=0; i<restaurant.restaurantimage.length; i++ )
    {
      formData.append (
        'imageFile',
        restaurant.restaurantimage[i].file,
        restaurant.restaurantimage[i].file.name
      );
    }
    console.log(formData);
    return formData;
  }


onFileSelected(event:any){
      if(event.target.files){
        const fileKey = event.target.files[0];

        const fileHandle: FileHandle = {
          file: fileKey,
          url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(fileKey)
          )
        }
        this.restaurant.restaurantimage.push(fileHandle);
      }
  }

  removeImage(i:number){
    this.restaurant.restaurantimage.splice(i, 1);
  }

  selectedFile!: File;
  imageData!: string;
  currentlyLoggedUser:any;

}
