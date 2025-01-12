import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import {ApplicationConfig} from '@angular/core';
import {provideHttpClient} from '@angular/common/http';
import {provideRouter, RouterModule} from '@angular/router';
import {routes} from './app/app.routes';


const appConfig: ApplicationConfig = {
  providers: [ provideHttpClient(),  provideRouter(routes)]
};
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));


