import { TestBed } from '@angular/core/testing';

import { GameeventService } from './gameevent.service';

describe('GameeventService', () => {
  let service: GameeventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameeventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
