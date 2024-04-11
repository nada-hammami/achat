import { TestBed } from '@angular/core/testing';
import { FoyerservicesService } from './foyerservices.service';
describe('FoyerservicesService', () => {
  let service: FoyerservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoyerservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
