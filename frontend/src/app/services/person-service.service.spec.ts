import {TestBed, inject, getTestBed} from '@angular/core/testing';

import { PersonServiceService } from './person-service.service';
import {HttpClientModule} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('PersonServiceService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        // no more boilerplate code w/ custom providers needed :-)
        HttpClientModule,
        HttpClientTestingModule
      ],
      providers: [PersonServiceService]
    });
    injector = getTestBed();
    httpMock = injector.get(HttpTestingController);
  });

  it('should be created', inject([PersonServiceService], (service: PersonServiceService) => {
    expect(service).toBeTruthy();
  }));
});
