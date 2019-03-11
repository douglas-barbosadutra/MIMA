import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WbsShowComponent } from './wbs-show.component';

describe('WbsShowComponent', () => {
  let component: WbsShowComponent;
  let fixture: ComponentFixture<WbsShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WbsShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WbsShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
