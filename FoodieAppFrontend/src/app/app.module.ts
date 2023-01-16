import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RestaurantNavComponent } from './restaurant-nav/restaurant-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { RestaurantFormComponent } from './restaurant-form/restaurant-form.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import {MatChipsModule} from '@angular/material/chips';
import {MatMenuModule} from '@angular/material/menu';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { HttpClientModule } from '@angular/common/http';
import { FoodlistFormComponent } from './foodlist-form/foodlist-form.component';
import { DashboardNavComponent } from './dashboard-nav/dashboard-nav.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { LoginComponent } from './login/login.component';
import { UserdashboardComponent } from './userdashboard/userdashboard.component';
import { ManagerdashboardComponent } from './managerdashboard/managerdashboard.component';
import { UserdashboardnavComponent } from './userdashboardnav/userdashboardnav.component';
import { ManagerdashboardnavComponent } from './managerdashboardnav/managerdashboardnav.component';
import { SearchComponent } from './search/search.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { SearchfoodComponent } from './searchfood/searchfood.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { UpdateprofileComponent } from './updateprofile/updateprofile.component';
import { ViewRestaurantComponent } from './view-restaurant/view-restaurant.component';
import { MatTableModule } from '@angular/material/table';
import { OrderSuccessComponent } from './order-success/order-success.component'


@NgModule({
  declarations: [
    AppComponent,
    RestaurantNavComponent,
    RestaurantFormComponent,
    FoodlistFormComponent,
    DashboardNavComponent,
    DashboardComponent,CustomerRegisterComponent,LoginComponent,
    UserdashboardComponent,
    ManagerdashboardComponent,
    UserdashboardnavComponent,
    ManagerdashboardnavComponent,
    SearchComponent,
    SearchfoodComponent,
    CartComponent,
    OrderComponent,
    PagenotfoundComponent,
    UpdateprofileComponent,
    ViewRestaurantComponent,
    OrderSuccessComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatChipsModule,
    MatMenuModule,
    MatTooltipModule,
    MatCardModule,
    MatInputModule,
    MatRadioModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,MatGridListModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
