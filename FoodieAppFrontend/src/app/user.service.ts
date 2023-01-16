import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Observable } from 'rxjs';
import {Model } from './model';
import { Loginmodel } from './models/loginmodel';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url="http://localhost:9001/api/v2/insertuser";
  constructor(private httpclient : HttpClient, private router:Router) { }

  // public addReg(model1:Model){
  //   return this.httpclient.post<Model>("http://localhost:8080/api/v2/insertuser",model1);
  // }

  public addReg(model:Model):Observable<Model>{
    const headers={'content-Type':'application/json'}
    const Stringify=JSON.stringify(model);
    console.log('String',Stringify);
    return this.httpclient.post<Model>(this.url,Stringify,{'headers':headers})
  }
  public getlogindetails(){
    return this.httpclient.get<any>("http://localhost:9001/api/v2/fetchallusers");
    // return this.httpclient.get<any>("http://localhost:9001/api/v1/users/getalldetails");
  }

  token:any;
  public getlogindetailswithtoken(model1:Loginmodel){

    const headers={'content-Type':'application/json'}
    const Stringify=JSON.stringify(model1);
    console.log('Stringfy string',Stringify);

    return this.httpclient.post<Loginmodel>("http://localhost:9001/api/v1/login",Stringify,{'headers':headers})
    .subscribe((res:any) => {
      this.token = res.token;
      console.log("Token"+res.token);
      localStorage.setItem('access_token', res.token);
      localStorage.setItem("currentlyloggeduseremail",model1.email);
      //if(res.)
      this.router.navigateByUrl('/managerdashboard');
    });
    }

    public getlogindetailswithtokenuser(model1:Loginmodel){

      const headers={'content-Type':'application/json'}
      const Stringify=JSON.stringify(model1);
      console.log('Stringfy string',Stringify);
  
      return this.httpclient.post<Loginmodel>("http://localhost:9001/api/v1/login",Stringify,{'headers':headers})
      .subscribe((res:any) => {
        this.token = res.token;
        console.log("Token"+res.token);
        localStorage.setItem('access_token', res.token);
        localStorage.setItem("currentlyloggeduseremail",model1.email);
        this.router.navigateByUrl('/usernavdashboard/userdashboard');
       
      });
      }


      /////////////update profile////////////////

      endpoint3: string = 'http://localhost:9001/api/v2/updateCustomer/';
      saveUser(model1:Model):Observable<Model>{
        const headers={'content-Type':'application/json'}
        const Stringify=JSON.stringify(model1);
        console.log('Stringfy string',Stringify);
        let emailId: any = localStorage.getItem('currentlyloggeduseremail');
        console.log("currentlyloggeduseremail"+ emailId);
        let api=`${this.endpoint3}${emailId}`
      console.log("api is called");
        return this.httpclient.put<Model>(api,Stringify, {'headers':headers});

      }

      
      // saveUser(usd:any):Observable<object>{
      //   console.log(usd);
      //   return this.httpclient.put(`${this.baseURL}/v2/updateCustomer/${usd.email}`, usd);
    

      //
      endpoint4: string = 'http://localhost:9001/api/v2/updatestatus/';
      public updateUserStatus(model:Model):Observable<Model>{
        const headers={'content-Type':'application/json'}
        const Stringify=JSON.stringify(model);

        let emailId: any = localStorage.getItem('currentlyloggeduseremail');
        console.log("currentlyloggeduseremail"+ emailId);

        let userstatus = "yes";
        console.log("status"+ userstatus);

        let api=`${this.endpoint4}${emailId}/${userstatus}`
        return this.httpclient.put<Model>(api,Stringify,{'headers':headers});

      }

      doLogout() {
        let removeToken = localStorage.removeItem('access_token');
        let currentlyloggeduseremail = localStorage.removeItem('currentlyloggeduseremail');
        if (removeToken == null && currentlyloggeduseremail == null) {
          this.router.navigateByUrl("/");
        }
      }
    
  }


