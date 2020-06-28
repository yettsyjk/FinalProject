import { TestBed } from '@angular/core/testing';

import { SnitchService } from './snitch.service';

describe('SnitchService', () => {
  let service: SnitchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SnitchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
