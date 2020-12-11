import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeEncoursComponent } from './liste-encours.component';

describe('ListeEncoursComponent', () => {
  let component: ListeEncoursComponent;
  let fixture: ComponentFixture<ListeEncoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeEncoursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeEncoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
