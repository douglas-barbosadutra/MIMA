import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineShowComponent } from './machine-show.component';

describe('MachineShowComponent', () => {
  let component: MachineShowComponent;
  let fixture: ComponentFixture<MachineShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
