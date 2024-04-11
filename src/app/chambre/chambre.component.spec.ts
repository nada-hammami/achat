import { ChambreComponent } from 'src/app/chambre/chambre.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';



describe('chambreComponent', () => {
  let component: ChambreComponent;
  let fixture: ComponentFixture<ChambreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChambreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChambreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
