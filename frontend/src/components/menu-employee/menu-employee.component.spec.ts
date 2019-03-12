import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuEmployeeComponent } from './menu-employee.component';

describe('MenuEmployeeComponent', () => {
  let component: MenuEmployeeComponent;
  let fixture: ComponentFixture<MenuEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
