import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructionInsertComponent } from './instruction-insert.component';

describe('InstructionInsertComponent', () => {
  let component: InstructionInsertComponent;
  let fixture: ComponentFixture<InstructionInsertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstructionInsertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructionInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
