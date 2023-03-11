import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProductDdetailsComponent } from './show-product-ddetails.component';

describe('ShowProductDdetailsComponent', () => {
  let component: ShowProductDdetailsComponent;
  let fixture: ComponentFixture<ShowProductDdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowProductDdetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowProductDdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
