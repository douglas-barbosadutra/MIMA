import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineInsertComponent } from './machine-insert.component';

describe('MachineInsertComponent', () => {
  let component: MachineInsertComponent;
  let fixture: ComponentFixture<MachineInsertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineInsertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
