import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedulingShowComponent } from './scheduling-show.component';

describe('SchedulingShowComponent', () => {
  let component: SchedulingShowComponent;
  let fixture: ComponentFixture<SchedulingShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedulingShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedulingShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
