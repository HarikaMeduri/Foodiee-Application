import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FileHandle } from '../models/file-handle';
import { RestaurantFoods } from '../models/restaurant-foods';
import { FoodServiceService } from '../services/food-service.service';
import { RestaurantServiceService } from '../services/restaurant-service.service';

@Component({
  selector: 'app-foodlist-form',
  templateUrl: './foodlist-form.component.html',
  styleUrls: ['./foodlist-form.component.css']
})
export class FoodlistFormComponent implements OnInit {

  ngOnInit(): void {
  }

  foods: RestaurantFoods = {
  foodid:0,
  foodname:"",
  price:0,
  discountprice:0,
  categoryname:"",
  subcategoryname:"",
  description:"",
  foodimage:[],
}


constructor(private restaurantService:RestaurantServiceService,
            private sanitizer: DomSanitizer,
            private foodService: FoodServiceService,
            private router: Router)
  {}


updateFoodlist(foodsForm:NgForm) {
  const foodFormData = this.prepareFormData(this.foods);

  console.log('check', foodFormData);
  this.foodService.updateFoodlist(foodFormData).subscribe(
    (response : RestaurantFoods) => {
      console.log(response);
      foodsForm.reset();
      this.foods.foodimage = [];
      this.router.navigateByUrl('/managerdashboard')
    },
    (error: HttpErrorResponse) => {
      console.log(error);
    }
  );
  console.log(this.foods);
}


prepareFormData(foods:RestaurantFoods): FormData {
  const formData = new FormData();

  formData.append(
    'foods', 
    new Blob([JSON.stringify(foods)], {type:'application/json'})
  );

  for(var i=0; i<foods.foodimage.length; i++ )
  {
    formData.append (
      'imageFile',
      foods.foodimage[i].file,
      foods.foodimage[i].file.name
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
    this.foods.foodimage.push(fileHandle);
  }
}

removeImage(i:number){
  this.foods.foodimage.splice(i, 1);
}

doLogout(){
  this.restaurantService.doLogout();
}

}