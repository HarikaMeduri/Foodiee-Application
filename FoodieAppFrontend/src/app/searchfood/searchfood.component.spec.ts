import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchfoodComponent } from './searchfood.component';

describe('SearchfoodComponent', () => {
  let component: SearchfoodComponent;
  let fixture: ComponentFixture<SearchfoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchfoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchfoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
