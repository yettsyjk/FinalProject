import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnitchUpdateComponent } from './snitch-update.component';

describe('SnitchUpdateComponent', () => {
  let component: SnitchUpdateComponent;
  let fixture: ComponentFixture<SnitchUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnitchUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SnitchUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
