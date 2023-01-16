import { FileHandle } from "./file-handle";
import { RestaurantAddress } from "./restaurant-address";
import { RestaurantFoods } from "./restaurant-foods";

export interface RestaurantRegister {
    restaurantid:number;
    restaurantname:string;
    phonenumber:string;
    email:string;
    status:string;
    foodsList:RestaurantFoods[];
    addressList:RestaurantAddress[];
    buildingnumber:number;
    street:string;
    city:string;
    pincode:number;
    state:string;
    country:string;
    restaurantimage:FileHandle[];
}
