import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskScheduledShowComponent } from './task-scheduled-show.component';

describe('TaskScheduledShowComponent', () => {
  let component: TaskScheduledShowComponent;
  let fixture: ComponentFixture<TaskScheduledShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskScheduledShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskScheduledShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
