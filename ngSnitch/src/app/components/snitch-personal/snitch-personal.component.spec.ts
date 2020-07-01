import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnitchPersonalComponent } from './snitch-personal.component';

describe('SnitchPersonalComponent', () => {
  let component: SnitchPersonalComponent;
  let fixture: ComponentFixture<SnitchPersonalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnitchPersonalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SnitchPersonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
