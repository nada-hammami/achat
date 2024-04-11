import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AfficherblocsComponent } from './afficherblocs.component';

describe('AfficherblocsComponent', () => {
  let component: AfficherblocsComponent;
  let fixture: ComponentFixture<AfficherblocsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AfficherblocsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AfficherblocsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
