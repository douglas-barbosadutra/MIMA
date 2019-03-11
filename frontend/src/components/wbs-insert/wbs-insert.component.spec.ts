import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WbsInsertComponent } from './wbs-insert.component';

describe('WbsInsertComponent', () => {
  let component: WbsInsertComponent;
  let fixture: ComponentFixture<WbsInsertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WbsInsertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WbsInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
