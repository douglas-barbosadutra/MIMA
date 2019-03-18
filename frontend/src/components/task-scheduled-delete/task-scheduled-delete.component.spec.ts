import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskScheduledDeleteComponent } from './task-scheduled-delete.component';

describe('TaskScheduledDeleteComponent', () => {
  let component: TaskScheduledDeleteComponent;
  let fixture: ComponentFixture<TaskScheduledDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskScheduledDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskScheduledDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
