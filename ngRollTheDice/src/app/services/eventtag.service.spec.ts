import { TestBed } from '@angular/core/testing';

import { EventtagService } from './eventtag.service';

describe('EventtagService', () => {
  let service: EventtagService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventtagService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
