import { TestBed } from '@angular/core/testing';

import { ISSService } from './iss.service';

describe('ISSService', () => {
  let service: ISSService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ISSService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
