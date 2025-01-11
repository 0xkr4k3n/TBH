import {Component, NgModule} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavbarComponent} from './navbar/navbar.component';
import {ChallengesComponent} from './challenges/challenges.component';



@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, ChallengesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Frontend';
}
