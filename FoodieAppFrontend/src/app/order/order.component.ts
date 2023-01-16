import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  public products : any = [];

displayedColumns: string[] = ['Sr.No', 'Food Name', 'Price' ,'Quantity', ];
dataSource = this.products;

  constructor(private cartservice:CartService) { }

  ngOnInit(): void {
    this.cartservice.getOrderList()
    .subscribe(res=>{
      this.products = res;
      console.log("in Order:",this.products)
    })
  }

}
