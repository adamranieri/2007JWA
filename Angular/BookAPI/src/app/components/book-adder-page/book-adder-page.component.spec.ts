import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookAdderPageComponent } from './book-adder-page.component';

describe('BookAdderPageComponent', () => {
  let component: BookAdderPageComponent;
  let fixture: ComponentFixture<BookAdderPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookAdderPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookAdderPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
