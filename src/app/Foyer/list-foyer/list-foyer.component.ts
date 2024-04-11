import { Component, OnInit } from '@angular/core';
import { Foyer } from 'src/app/Models/Foyer';
import { FoyerservicesService } from 'src/app/services/foyerservices.service';
@Component({
  selector: 'app-list-foyer',
  templateUrl: './list-foyer.component.html',
  styleUrls: ['./list-foyer.component.scss']
})
export class ListFoyerComponent implements OnInit {
accessToken=''
foyer: Foyer[] = [];
   constructor(private servicef: FoyerservicesService ) { }
ngOnInit(): void {
  
    this.show()
  }
  show(){
    this.accessToken = localStorage.getItem('access_token') ;
  this.servicef.setAccessToken(this.accessToken);
    this.servicef.getFoyers().subscribe(
      data=>this.foyer=data
    )
  }
deleteFoyer(id: number) {
  this.servicef.deleteFoyer(id).subscribe(
    () => {
      // If the delete operation is successful, remove the deleted Foyer from the list.
      this.foyer = this.foyer.filter(foyer => foyer.idFoyer !== id);
      // Optionally, you can also show some success message to the user
      alert(`Foyer with ID ${id} has been deleted.`);
    },
    (error) => {
      // Handle any errors that occur during deletion
      console.error('Error occurred while trying to delete the foyer:', error);
      // Optionally, display an error message to the user
      alert(`Failed to delete Foyer with ID ${id}.`);
    }
  );
}

  

  
}
