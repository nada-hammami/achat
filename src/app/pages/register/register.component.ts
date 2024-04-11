import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  signUpForm: FormGroup;
  serverError: string = '';
  serverSucess: boolean = false;
 
  constructor(private http: HttpClient, private formBuilder: FormBuilder,private router: Router,private authService : AuthServiceService) {
    this.signUpForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get form() {
    return this.signUpForm.controls;
  }

  onSubmit(): void {
    this.serverError=""
    console.log("aaaa")
    if (this.signUpForm.invalid) {
      console.log("bbbbbbb")
      this.signUpForm.markAllAsTouched();
      return;
    }

    const userData = {
      firstname: this.signUpForm.get('firstName')?.value,
      lastname: this.signUpForm.get('lastName')?.value,
      email: this.signUpForm.get('email')?.value,
      password: this.signUpForm.get('password')?.value
    };

    this.authService.register(userData)
      .subscribe(
        (response) => {
          console.log('User registered successfully!', response);
          this.serverSucess=true
          // You can redirect the user or perform any other actions here
        },
        (error: HttpErrorResponse) => {
          if (error.error.error) {
            console.log(error.error.error)
            this.serverError = error.error.error;
            // Display the error in a pop-up using alert or a custom modal
          } else {
            console.error('Error occurred during registration:', error);
            this.serverError=error.message
          }
        }
      );
  }
}
