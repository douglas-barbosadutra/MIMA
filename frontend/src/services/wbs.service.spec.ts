import { TestBed } from '@angular/core/testing';

import { WbsService } from './wbs.service';

describe('WbsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WbsService = TestBed.get(WbsService);
    expect(service).toBeTruthy();
  });
});
