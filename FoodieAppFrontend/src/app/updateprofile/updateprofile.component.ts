import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.css']
})
export class UpdateprofileComponent implements OnInit {


  ngOnInit(): void {
  }

  RegForm: FormGroup;
  constructor(private userservice: UserService, private formbuilder: FormBuilder, private router: Router) {

    this.RegForm = this.formbuilder.group(
      {
        username: ['',Validators.required],
       password: ['', Validators.required],
        phonenumber: ['', Validators.required],  
        email:  ['', Validators.required],     
      
      
   
       });

      ;

  }

 

 
 

  get username() {
    return this.RegForm.get('username');
  }
  

  get email() {
    return this.RegForm.get('email');
  }
  
  get password() {
    return this.RegForm.get('password');
  }
  get phonenumber() {
    return this.RegForm.get('phonenumber');
  }
 
  saveData(){
    
    this.userservice.saveUser(this.RegForm.value).subscribe(data=>{
      console.log("save metod"+data);
      alert("Update successfull..")
    },
    error=>console.log(error));
    
  }


  }

