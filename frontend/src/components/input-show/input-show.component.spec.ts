import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputShowComponent } from './input-show.component';

describe('InputShowComponent', () => {
  let component: InputShowComponent;
  let fixture: ComponentFixture<InputShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
