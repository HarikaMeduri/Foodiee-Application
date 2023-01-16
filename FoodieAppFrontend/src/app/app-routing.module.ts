import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { DashboardNavComponent } from './dashboard-nav/dashboard-nav.component';
import { DashboardService } from './dashboard.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FoodlistFormComponent } from './foodlist-form/foodlist-form.component';
import { LoginComponent } from './login/login.component';
import { ManagerdashboardnavComponent } from './managerdashboardnav/managerdashboardnav.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { OrderComponent } from './order/order.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { RestaurantFormComponent } from './restaurant-form/restaurant-form.component';
import { RestaurantNavComponent } from './restaurant-nav/restaurant-nav.component';
import { UpdateprofileComponent } from './updateprofile/updateprofile.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { UserdashboardnavComponent } from './userdashboardnav/userdashboardnav.component';
import { ViewRestaurantComponent } from './view-restaurant/view-restaurant.component';

const routes: Routes = [
  { path: '', component:  DashboardNavComponent,
    children: [
      {path: 'register', component:CustomerRegisterComponent},
      {path: 'login', component:LoginComponent},
      {path: '', component:DashboardComponent},
    ]
},
// { path: 'userdashboard',  redirectTo: 'userdashboard',pathMatch: 'full'},
{ path: 'usernavdashboard', component:  UserdashboardnavComponent,
  children: [
    {path: 'userdashboard', component: UserdashboardComponent},
    {path: 'cart', component: CartComponent},
    {path: 'orders', component: OrderComponent},
    {path: 'updateprofile', component:UpdateprofileComponent},
    {path: 'restaurantform', component:RestaurantFormComponent},
    {path: 'ordersuccess', component:OrderSuccessComponent}
  ]
},
{path: 'managerdashboard', component: ManagerdashboardnavComponent},
{path: 'restaurantform', component: RestaurantFormComponent},
{path: 'foodlistform', component:FoodlistFormComponent},
{path: 'viewrestaurant', component:ViewRestaurantComponent},


{path:'**', component: PagenotfoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
