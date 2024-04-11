import { TestBed } from '@angular/core/testing';

import { BlocservicesService } from './blocservices.service';

describe('BlocservicesService', () => {
  let service: BlocservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlocservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
