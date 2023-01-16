import { FileHandle } from "./file-handle";

export interface RestaurantFoods {
    foodid:number;
    foodname:string;
    price:number;
    discountprice:number;
    categoryname:string;
    subcategoryname:string;
    description:string;
    foodimage: FileHandle[];
}
