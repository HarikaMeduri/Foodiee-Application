import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerdashboardnavComponent } from './managerdashboardnav.component';

describe('ManagerdashboardnavComponent', () => {
  let component: ManagerdashboardnavComponent;
  let fixture: ComponentFixture<ManagerdashboardnavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManagerdashboardnavComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagerdashboardnavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
