import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/models/game';
import { Gameevent } from 'src/app/models/gameevent';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: User = new User();
  isItYou = false;
  friends: User[] = [];
  events: Gameevent[] = [];
  games: Game[] = [];

  constructor(
    private userSvc: UserService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.checkIfYou();
    let idStr = this.route.snapshot.paramMap.get('userId');
    let userId;
    if (idStr) {
      userId = Number.parseInt(idStr);
    } else {
      userId = this.authService.getCurrentUserId();
    }
    if (userId > 0) {
      this.userSvc.show(userId).subscribe({
        next: (u) => {
          this.user = u;
        },
        error: (fail) => {
          console.error('ERROR RETREIVING USER' + fail);
        },
      });
    } else {
      this.router.navigateByUrl('home'); //change this later to error page
    }
  }

  checkIfYou() {
    let idStr = this.route.snapshot.paramMap.get('userId');
    let userId = this.authService.getCurrentUserId();
    if (idStr) {
      if (Number.parseInt(idStr) === userId) {
        this.isItYou = true;
      }
    }
  }

  displayGames() {
    this.friends = [];
    this.events = [];
    if (this.user.games) {
      this.games = this.user.games;
    }
  }

  displayFriends() {
    this.games = [];
    this.events = [];
    if (this.user.friends) {
      this.friends = this.user.friends;
    }
  }

  displayEvents() {
    this.friends = [];
    this.games = [];
    if (this.user.gameEvents) {
      this.events = this.user.gameEvents;
    }
  }
}
