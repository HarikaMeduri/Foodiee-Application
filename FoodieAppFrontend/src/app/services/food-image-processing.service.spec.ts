import { TestBed } from '@angular/core/testing';

import { FoodImageProcessingService } from './food-image-processing.service';

describe('FoodImageProcessingService', () => {
  let service: FoodImageProcessingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodImageProcessingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
