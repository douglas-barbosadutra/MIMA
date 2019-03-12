import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskEmployeeShowComponent } from './task-employee-show.component';

describe('TaskEmployeeShowComponent', () => {
  let component: TaskEmployeeShowComponent;
  let fixture: ComponentFixture<TaskEmployeeShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskEmployeeShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskEmployeeShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
