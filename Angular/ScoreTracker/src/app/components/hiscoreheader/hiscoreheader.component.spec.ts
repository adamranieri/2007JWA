import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HiscoreheaderComponent } from './hiscoreheader.component';

describe('HiscoreheaderComponent', () => {
  let component: HiscoreheaderComponent;
  let fixture: ComponentFixture<HiscoreheaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HiscoreheaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HiscoreheaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
