import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';

import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { MapsComponent } from '../../pages/maps/maps.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ListFoyerComponent } from 'src/app/Foyer/list-foyer/list-foyer.component';
import { AddFoyerComponent } from 'src/app/Foyer/add-foyer/add-foyer.component';
import { ModifierFoyerComponent } from 'src/app/Foyer/modifier-foyer/modifier-foyer.component';
import { AfficherblocsComponent } from 'src/app/Foyer/afficherblocs/afficherblocs.component';
import { ChambreComponent } from 'src/app/chambre/chambre.component';
import { AddchambreComponent } from 'src/app/chambre/addchambre/addchambre.component';
import { UpdateChambreComponent } from 'src/app/chambre/updatechambre/updatechambre.component';
import { BlocComponent } from 'src/app/bloc/bloc.component';
// import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule,
  ],
  declarations: [
    DashboardComponent,
ListFoyerComponent,
    TablesComponent,
    IconsComponent,
    MapsComponent,
    AddFoyerComponent ,
    ListFoyerComponent ,
    ModifierFoyerComponent,
    AfficherblocsComponent,
    ChambreComponent,
    AddchambreComponent,
    UpdateChambreComponent,
    BlocComponent

  ]
})

export class AdminLayoutModule {}
