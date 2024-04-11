import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { etudiant } from 'src/app/Models/etudiant';
import { reservation } from 'src/app/Models/reservation';
import { NavbarComponent } from 'src/app/components/navbar/navbar.component';
import { EtudiantsServiceService } from 'src/app/services/etudiants-service.service';
import { AuthServiceService } from 'src/app/Services/auth-service.service';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  accessToken: string = '';
  studentConEmail = '';
  changePasswordForm: FormGroup;
  serverMessage='';
  constructor(private etudiantService: EtudiantsServiceService, private fb: FormBuilder,private authService:AuthServiceService ) {
    this.changePasswordForm = this.fb.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    }, { validators: this.passwordsMatchValidator });
   }
  userProfileForm: FormGroup;
  addReservationForm: FormGroup;
  connectedStudent!: etudiant;
  passwordsMatchValidator(formGroup: FormGroup) {
    const newPassword = formGroup.get('newPassword').value;
    const confirmPassword = formGroup.get('confirmPassword').value;
  
    if (newPassword === confirmPassword) {
      return true; // Passwords match
    } else {
      this.serverMessage="passwords dont match"
      return false; // Passwords don't match
    }
  }
  ngOnInit(): void {
    this.accessToken = localStorage.getItem('access_token') ;
    this.authService.setAccessToken(this.accessToken)
    this.authService.getUserDataByToken().subscribe(data=>{
      this.etudiantService.findStudentWithEmail(data.email).subscribe(data => {
        this.connectedStudent = data;
        console.log(this.connectedStudent);
  
        // Move form initialization inside the subscribe callback
        this.userProfileForm = this.fb.group({
          nom: [this.connectedStudent.nomEt, [Validators.required]],
          prenom: [this.connectedStudent.prenomEt, [Validators.required]],
          cin: [this.connectedStudent.cin, [Validators.required]],
        });
        this.addReservationForm = this.fb.group({
          idReservation: ['', [Validators.required]],
          anneeUniversitaire: ['', [Validators.required]],
        });
      });
    })
    
   
  }

   // Method to open the "Change Password" modal
   openChangePasswordModel() {
    const modelDiv = document.getElementById('changePasswordModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'block';
    }
  }

  // Method to close the "Change Password" modal
  closeChangePasswordModel() {
    const modelDiv = document.getElementById('changePasswordModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'none';
    }
  }

  // Method to handle the "Change Password" form submission
 // Method to handle the "Change Password" form submission
changePassword() {
 if(this.passwordsMatchValidator(this.changePasswordForm))
 { this.serverMessage = "Passwords don't match";}
  else if (this.changePasswordForm.valid ) {
    // Prepare the data to be sent to the service
    const data = {
      currentPassword: this.changePasswordForm.value.currentPassword,
      newPassword: this.changePasswordForm.value.newPassword,
    };

    // Call the changePassword service with the prepared data
    this.authService.changePassword(data).subscribe(
      (response) => {
        // Handle success, e.g., display a success message
        console.log('Password changed successfully', response.message);
        this.serverMessage = response.message;
      },
      (error) => {
        // Handle error, e.g., display an error message
        console.error('Error changing password', error.error.message);
        this.serverMessage = error.error.message;
      }
    );

    }
}


  EditProfile() {
    console.log("aaaaa")
    if (this.userProfileForm.invalid) {
      console.log("bbbbbbb")
      // If the form is invalid, mark all fields as touched to display validation errors
      this.userProfileForm.markAllAsTouched();
      return;
    }

    const userData: etudiant = {
      idEtudiant: this.connectedStudent.idEtudiant,
      nomEt: this.userProfileForm.get('nom')?.value,
      prenomEt: this.userProfileForm.get('prenom')?.value,
      cin: this.userProfileForm.get('cin')?.value,
      ecole: this.connectedStudent.ecole,
      email: this.connectedStudent.email,
      dateNaissance: this.connectedStudent.dateNaissance,
      reservations: this.connectedStudent.reservations

    };
    console.log(userData)
    this.etudiantService.updateStudent(userData).subscribe(
      (response) => {
        console.log('Update successful:', response);
        // Handle success, if needed
        this.closeModel()
      },
      (error) => {
        console.error('Error during update:', error);
        // Handle error, if needed
      }
    );
  }
  openModel() {
    const modelDiv = document.getElementById('myModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'block';
    }
  }

  closeModel() {
    const modelDiv = document.getElementById('myModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'none';
    }
  }

  addReservation(){ 
    console.log("ccccc")
    if (this.addReservationForm.invalid) {
      console.log("ffffff")
      // If the form is invalid, mark all fields as touched to display validation errors
      this.addReservationForm.markAllAsTouched();
      return;
    }
    const reservationData: reservation = {
      idReservation: this.addReservationForm.get('idReservation')?.value,
      anneeUniversitaire:this.addReservationForm.get('anneeUniversitaire')?.value,
      estValid: false,
      etudiants: this.connectedStudent
      
      //nomEt: this.userProfileForm.get('nom')?.value,
      //prenomEt: this.userProfileForm.get('prenom')?.value,
    }
    console.log(reservationData)
    this.etudiantService.passReservation(this.connectedStudent.idEtudiant,reservationData).subscribe(
      (response) => {
        console.log('Update successful:', response);
        // Handle success, if needed
        this.closeModel2()
      },
      (error) => {
        console.error('Error during update:', error);
        // Handle error, if needed
      }
    );
   }
  openModel2(){
    const modelDiv = document.getElementById('newModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'block';
    }
  }
  closeModel2(){
    const modelDiv = document.getElementById('newModal');
    if (modelDiv != null) {
      modelDiv.style.display = 'none';
    }
  }
}
