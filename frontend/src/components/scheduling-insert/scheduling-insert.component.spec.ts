import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulingInsertComponent } from './scheduling-insert.component';

describe('SchedulingInsertComponent', () => {
  let component: SchedulingInsertComponent;
  let fixture: ComponentFixture<SchedulingInsertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedulingInsertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedulingInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
