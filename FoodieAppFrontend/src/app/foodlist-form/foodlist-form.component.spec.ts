import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodlistFormComponent } from './foodlist-form.component';

describe('FoodlistFormComponent', () => {
  let component: FoodlistFormComponent;
  let fixture: ComponentFixture<FoodlistFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodlistFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodlistFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
