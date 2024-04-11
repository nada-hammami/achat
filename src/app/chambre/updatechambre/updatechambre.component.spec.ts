import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateChambreComponent } from './updatechambre.component';

describe('UpdatechambreComponent', () => {
  let component: UpdateChambreComponent;
  let fixture: ComponentFixture<UpdateChambreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateChambreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateChambreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
