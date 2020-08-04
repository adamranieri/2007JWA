import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HiscoreFormComponent } from './hiscore-form.component';

describe('HiscoreFormComponent', () => {
  let component: HiscoreFormComponent;
  let fixture: ComponentFixture<HiscoreFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HiscoreFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HiscoreFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
