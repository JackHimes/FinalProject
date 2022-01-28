import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameeventdetailsComponent } from './gameeventdetails.component';

describe('GameeventdetailsComponent', () => {
  let component: GameeventdetailsComponent;
  let fixture: ComponentFixture<GameeventdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameeventdetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameeventdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
