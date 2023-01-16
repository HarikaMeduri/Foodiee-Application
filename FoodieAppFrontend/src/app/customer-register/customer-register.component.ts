import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {

  

  ngOnInit(): void {
  }

  RegForm: FormGroup;
  constructor(private userservice: UserService, private formbuilder: FormBuilder, private router: Router) {

    this.RegForm = this.formbuilder.group(
      {
      username:new FormControl('', Validators.required),
        email: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required),
        phonenumber: new FormControl('', Validators.required),
        addressList: this.formbuilder.group({
          dno: new FormControl(null),
          street: new FormControl(null),
          city: new FormControl(null),
          state: new FormControl(null),
          pincode: new FormControl()
        }),
        cartList: new FormControl(),
        ordersList: new FormControl()
     });

        



    //  username: ['',Validators.required],
    //  email: ['',Validators.required],        
    //  password: ['', Validators.required],
    //  phonenumber: ['', Validators.required],        
    //  addressList: this.formbuilder.group([{
    //    dno: ['', Validators.required],
    //    street: ['', Validators.required],
    //    city: ['', Validators.required],
    //    state: ['', Validators.required],
    //    pincode:['', Validators.required]
    //   }])

  }

  // addAddressFormGroup():FormGroup{
  //   return this.formbuilder.group({
  //     dno: ['', Validators.required],
  //         street: ['', Validators.required],
  //         city: ['', Validators.required],
  //         state: ['', Validators.required],
  //         pincode:['', Validators.required]
  //   })
  // }

 
 

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
  get dno() {
    return this.RegForm.get('dno');
  }
  get street() {
    return this.RegForm.get('street');
  }
  get city() {
    return this.RegForm.get('city');
  }
  get state() {
    return this.RegForm.get('state');
  }
  get pincode() {
    return this.RegForm.get('pincode');
  }


  onSubmit(){
    console.log("Inside submit");

    let arr = this.RegForm.value;
    const addfun = this.userservice.addReg(arr);
    addfun.subscribe((para: any) => {
      console.log("Registration Success", para);
      alert('Details submitted successfully');
      this.RegForm.reset();
      this.router.navigate(['register'])

    })

  }

}
