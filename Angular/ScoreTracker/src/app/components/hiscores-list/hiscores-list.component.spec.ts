import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HiscoresListComponent } from './hiscores-list.component';

describe('HiscoresListComponent', () => {
  let component: HiscoresListComponent;
  let fixture: ComponentFixture<HiscoresListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HiscoresListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HiscoresListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
