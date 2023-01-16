export interface RestaurantData {
    restaurantid:number;
    restaurantname:string;
    phonenumber:number;
    email:string;
    foodsList:fooddata[];
    addressList:addressdata[];
    restaurantimage:string;
}

export class fooddata {

    foodid:number;
    foodname:string;
    price:number;
    discountprice:number;
    categoryname:string;
    subcategoryname:string;
    description:string;
    constructor()
    {
        this.foodid=0;
        this.foodname="";
        this.price=0;
        this.discountprice=0;
        this.categoryname="";
        this.subcategoryname="";
        this.description="";
    }
    
}
export class addressdata {

    buildingnumber:number;
    street:string;
    city:string;
    pincode:number;
    state:string;
    country:string;
    constructor()
    {
        this.buildingnumber=0;
        this.street="";
        this.city="";
        this.pincode=0;
        this.state="";
        this.country="";
    }
    
}