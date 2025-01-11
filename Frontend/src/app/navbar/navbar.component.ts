import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl:"navbar.component.html",
  styleUrl:"navbar.component.css",
  imports: [CommonModule, RouterLink, RouterLinkActive]
})
export class NavbarComponent {}
