import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateGameEventComponent } from './create-game-event.component';

describe('CreateGameEventComponent', () => {
  let component: CreateGameEventComponent;
  let fixture: ComponentFixture<CreateGameEventComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateGameEventComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGameEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
