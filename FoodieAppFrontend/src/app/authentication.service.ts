import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private router:Router) { }

  value:boolean=false;
 

  isLogin(details:any){  
    if(details!=null){
      this.value=true;
      alert("sign-in successfull"+details.status);
      if(details.status=="no")
      {
        this.router.navigate(['/userdashboard']);
      }
      else{

        this.router.navigate(['/managerdashboard']);
        
        
      }
      
    }
    // }else{
    //   alert("enter valid credentials");
    // }  
  }


  isAuthenticated() {
    return this.value;
  }
}

// function getemailfromdashboard() {
//   throw new Error('Function not implemented.');
// }

