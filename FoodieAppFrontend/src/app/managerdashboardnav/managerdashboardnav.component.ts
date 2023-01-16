import { Component, OnInit } from '@angular/core';
import { RestaurantServiceService } from '../services/restaurant-service.service';

@Component({
  selector: 'app-managerdashboardnav',
  templateUrl: './managerdashboardnav.component.html',
  styleUrls: ['./managerdashboardnav.component.css']
})
export class ManagerdashboardnavComponent implements OnInit {

  constructor(private restaurantService:RestaurantServiceService) { }

  ngOnInit(): void {
  }

  doLogout(){
    this.restaurantService.doLogout();
  }
}
