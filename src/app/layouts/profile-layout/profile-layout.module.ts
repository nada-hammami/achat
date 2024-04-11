import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';



import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { profileLayoutRoutes } from './profile-layout.routing';
import { NavbarComponent } from 'src/app/components/navbar/navbar.component';
// import { ToastrModule } from 'ngx-toastr';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(profileLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule,
    ReactiveFormsModule
  ],
  declarations: [
   
    UserProfileComponent,


  ]
})

export class ProfileLayoutModule {}
