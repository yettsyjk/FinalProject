import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnitchComponent } from './snitch.component';

describe('SnitchComponent', () => {
  let component: SnitchComponent;
  let fixture: ComponentFixture<SnitchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnitchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SnitchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
