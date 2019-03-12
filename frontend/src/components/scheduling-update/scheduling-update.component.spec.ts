import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulingUpdateComponent } from './scheduling-update.component';

describe('SchedulingUpdateComponent', () => {
  let component: SchedulingUpdateComponent;
  let fixture: ComponentFixture<SchedulingUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedulingUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedulingUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
