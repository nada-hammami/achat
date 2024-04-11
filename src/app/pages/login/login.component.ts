import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  signInForm: FormGroup;
  serverError: string = '';
  accessToken: string = '';
  userData: any = {};
  constructor(private http: HttpClient, private formBuilder: FormBuilder,private router: Router,private authService:AuthServiceService) {
    this.signInForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    this.accessToken = localStorage.getItem('access_token') || '';
    console.log('Access Token:', this.accessToken);
    if (this.accessToken) {
      this.getUserDataByToken();
     
    }
  }

  get form() {
    return this.signInForm.controls;
  }
  getUserDataByToken(): void {
   this.authService.setAccessToken(this.accessToken)

    this.authService.getUserDataByToken()
      .subscribe(
        (response) => {
          // Handle success response - store or use the user data retrieved
          console.log('User data retrieved successfully!', response);
          this.userData = response; // Store the user data for use in the component
          // Perform any other actions with the retrieved user data
          
          if(this.userData.role == 'USER'){
            this.router.navigate(['profile'])
          }else if(this.userData.role == 'ADMIN'){
            this.router.navigate(['tables'])
          }
        },
        (error: HttpErrorResponse) => {
          console.error('Error occurred while fetching user data:', error);
          // Handle error response, e.g., show an error message
        }
      );
  }
  onSubmit(): void {
    
    console.log("aaaaa")
    if (this.signInForm.invalid) {
      console.log("bbbbbbb")
      // If the form is invalid, mark all fields as touched to display validation errors
      this.signInForm.markAllAsTouched();
      return;
    }

    const userData = {
      email: this.signInForm.get('email')?.value,
      password: this.signInForm.get('password')?.value
    };
    // Make a POST request to your backend API for authentication
   this.authService.login(userData)
      .subscribe(
        (response) => {
          // Handle success response (e.g., store token, redirect user)
          console.log('User authenticated successfully!', response);
          if (response.access_token) {
            localStorage.setItem('access_token', response.access_token);
            this.accessToken = response.access_token
            this.getUserDataByToken()
            // You can redirect the user or perform any other actions here
          }
        },
        (error: HttpErrorResponse) => {
          if (error.error.error) {
            console.log(error.error.error);
            this.serverError = error.error.error;
            // Display the error in a pop-up using alert or a custom modal
          } else {
            console.error('Error occurred during authentication:', error);
            this.serverError=error.message
          }
        }
      );
  }
}
