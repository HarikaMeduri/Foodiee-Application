import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserdashboardnavComponent } from './userdashboardnav.component';

describe('UserdashboardnavComponent', () => {
  let component: UserdashboardnavComponent;
  let fixture: ComponentFixture<UserdashboardnavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserdashboardnavComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserdashboardnavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
