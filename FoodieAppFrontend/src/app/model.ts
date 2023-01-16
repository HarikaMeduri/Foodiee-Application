export class Model {
  username: string;
  email: string;
  password: string;
  status: string;
  phonenumber: number;
  addressList: Taps[];
  cartList: Carts[];
  ordersList: Orders[];
  constructor() {
    this.username = '';
    this.email = '';
    this.password = '';
    this.status = '';
    this.phonenumber = 0;
    this.addressList = [];
    this.cartList = [];
    this.ordersList = [];
  }
}
export class Taps {
  dno: string;
  street: string;
  city: string;
  state: String;
  pincode: number;
  constructor() {
    this.dno = '';
    this.street = '';
    this.city = '';
    this.state = '';
    this.pincode = 0;
  }
}
export class Carts {
  
  foodname: string;
  price: number;
  quantity: number;
  discountprice: number;
  categoryname: string;
  subcategoryname: string;
  subtotal:number;

  constructor() {
    
    this.foodname = '';
    this.price = 0;
    this.quantity = 1;
    this.discountprice = 0;
    this.categoryname = '';
    this.subcategoryname = '';
    this.subtotal = 0;
  }
}
export class Orders{
    orderid:number;
    foodname:string;
    date:string;
    quantity:number;
    price:number

    constructor(){
        this.orderid=0;
        this.foodname="";
        this.date="";
        this.quantity=0;
        this.price=0;

    }
}