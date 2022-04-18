import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContracctListComponent } from './contracct-list.component';

describe('ContracctListComponent', () => {
  let component: ContracctListComponent;
  let fixture: ComponentFixture<ContracctListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContracctListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContracctListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
