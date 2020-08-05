import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBooksPageComponent } from './view-books-page.component';

describe('ViewBooksPageComponent', () => {
  let component: ViewBooksPageComponent;
  let fixture: ComponentFixture<ViewBooksPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewBooksPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBooksPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
