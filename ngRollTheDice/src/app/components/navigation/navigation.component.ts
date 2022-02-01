import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from 'src/app/models/game';
import { AuthService } from 'src/app/services/auth.service';
import { GameService } from 'src/app/services/game.service';
import { CreationComponent } from '../creation/creation.component';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {



  constructor(
    private authService: AuthService
    ) { }

  ngOnInit(): void {
  }

  loggedIn(): boolean {
    return this.authService.checkLogin();
  }

  logout(): void {
    return this.authService.logout();
  }

}
