import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SnitchListComponent } from './snitch-list.component';

describe('SnitchListComponent', () => {
  let component: SnitchListComponent;
  let fixture: ComponentFixture<SnitchListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SnitchListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SnitchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
