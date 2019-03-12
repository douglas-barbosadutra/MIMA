import { TestBed } from '@angular/core/testing';

import { ManufactoringService } from './manufactoring.service';

describe('ManufactoringService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ManufactoringService = TestBed.get(ManufactoringService);
    expect(service).toBeTruthy();
  });
});
