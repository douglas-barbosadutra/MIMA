import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructionShowComponent } from './instruction-show.component';

describe('InstructionShowComponent', () => {
  let component: InstructionShowComponent;
  let fixture: ComponentFixture<InstructionShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InstructionShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructionShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
