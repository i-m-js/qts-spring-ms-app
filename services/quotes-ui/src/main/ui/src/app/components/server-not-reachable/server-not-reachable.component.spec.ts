import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerNotReachableComponent } from './server-not-reachable.component';

describe('ServerNotReachableComponent', () => {
  let component: ServerNotReachableComponent;
  let fixture: ComponentFixture<ServerNotReachableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServerNotReachableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServerNotReachableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
