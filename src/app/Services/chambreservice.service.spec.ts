import { TestBed } from '@angular/core/testing';

import { ChambreserviceService } from './chambreservice.service';

describe('ChambreserviceService', () => {
  let service: ChambreserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChambreserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
