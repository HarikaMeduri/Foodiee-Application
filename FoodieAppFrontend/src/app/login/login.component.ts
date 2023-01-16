import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formbuilder: FormBuilder, private http: HttpClient, private userservice: UserService,
    private auth:AuthenticationService,private router: Router) { }
  loginForm !: FormGroup;

  ngOnInit(): void {
  
    this.loginForm = this.formbuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
      
    })

  }

  get email() {
    return this.loginForm.get('email');
  }
  get password() {
    return this.loginForm.get('password');
  }

  


  login(){

    this.userservice.getlogindetails()
    .subscribe(res=>{
      const user=res.find((a:any)=>{

        return a.email===this.loginForm.value.email&&a.password===this.loginForm.value.password
      })

      if(user){
        if(user.status=="yes"){
          this.userservice.getlogindetailswithtoken(this.loginForm.value);
          alert("Login Successful",);
          console.log("print email:"+user.email)
          
          this.router.navigate(['/managerdashboard']);
        }
        //this.loginForm.reset();
       // this.auth.isLogin(user);
      else{

        this.userservice.getlogindetailswithtokenuser(this.loginForm.value);
        alert("Login Successful user");
        alert("Login status user"+user.status);
        this.router.navigate(['/usernavdashboard/userdashboard']);
      }
    }
      
      else{
        alert("User not found, check email and password again");
      }
    },err=>{
      alert("Something went wrong");
    });
  }


}















// login(){
  //   this.userservice.getlogindetails()
  //     // .subscribe(res=>{
  //     //   const user=res.find((a:any)=>{
  
  //     //    // return a.email===this.loginForm.value.email&&a.password===this.loginForm.value.password
  //     //   })
  //   this.userservice.getlogindetailswithtoken(this.loginForm.value)
  //   alert("Login Done");
  //   console.log("email",this.loginForm.value.email)
  //   console.log("email",this.loginForm.value.status)
  

  // }


  // login(){

  //   this.userservice.getlogindetailswithtoken(this.loginForm.value)
  //   .subscribe(res=>{
  //     const user=res.find((a:any)=>{

  //      // return a.email===this.loginForm.value.email&&a.password===this.loginForm.value.password
  //     })
  //     console.log("ussertypppe:",user)
  //     if(user){
  //       alert("Login Successful");
  //       //this.loginForm.reset();
  //       this.auth.isLogin(user);
  //       let data = this.loginForm.value.email;
  //       localStorage.setItem('currentlyLoggedUserEmail', data);
  //     }
  //     else{
  //       alert("User not found, check email and password again");
  //     }
  //   },err=>{
  //     alert("Something went wrong");
  //   });
  // }
