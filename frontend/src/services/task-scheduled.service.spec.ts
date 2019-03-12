import { TestBed } from '@angular/core/testing';

import { TaskScheduledService } from './task-scheduled.service';

describe('TaskScheduledService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TaskScheduledService = TestBed.get(TaskScheduledService);
    expect(service).toBeTruthy();
  });
});
