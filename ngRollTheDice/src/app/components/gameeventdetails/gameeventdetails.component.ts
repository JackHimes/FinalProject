import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Eventtag } from 'src/app/models/eventtag';
import { Game } from 'src/app/models/game';
import { Gameevent } from 'src/app/models/gameevent';
import { User } from 'src/app/models/user';
import { Comment } from 'src/app/models/comment';
import { AuthService } from 'src/app/services/auth.service';
import { GameeventService } from 'src/app/services/gameevent.service';
import { UserService } from 'src/app/services/user.service';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-gameeventdetails',
  templateUrl: './gameeventdetails.component.html',
  styleUrls: ['./gameeventdetails.component.css'],
})
export class GameeventdetailsComponent implements OnInit {
  gameEvent: Gameevent = new Gameevent();
  id: number = 0;
  guestNames = '';
  tags = '';
  loggedInUser: User = new User();
  isLoggedIn = false;
  games = '';
  comment: Comment = new Comment();
  isHost = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private gameEventSvc: GameeventService,
    private auth: AuthService,
    private userSvc: UserService,
    private commentSvc: CommentService
  ) {}

  ngOnInit(): void {
    let idStr = this.route.snapshot.paramMap.get('eventId');
    if (idStr) {
      this.id = Number.parseInt(idStr);
    }
    this.load();
    this.loadUser();
    this.checkLogin();
    this.checkHost();
  }

  load() {
    this.gameEventSvc.show(this.id).subscribe({
      next: (g) => {
        this.gameEvent = g;
        this.loadTags();
        this.loadGames();
        this.loadGuests();
      },
      error: (fail) => {
        console.error(
          'error in GameEventDetails Component.load... Here the error: ' + fail
        );
      },
    });
  }

  loadGuests() {
    this.guestNames = '';
    if (this.gameEvent.guests) {
      this.guestNames = this.gameEvent.guests.map((x) => x.username).join(', ');
    }
  }

  loadTags() {
    this.tags = '';
    if (this.gameEvent.eventTags) {
      this.tags = this.gameEvent.eventTags.map((x) => x.name).join(', ');
    }
  }

  loadGames() {
    this.games = '';
    if (this.gameEvent.games) {
      this.games = this.gameEvent.games.map((x) => x.name).join(', ');
    }
  }

  loadUser() {
    let id = this.auth.getCurrentUserId();
    if (id > 0) {
      this.userSvc.show(id).subscribe({
        next: (u) => {
          this.loggedInUser = u;
        },
        error: (fail) => {
          console.error(
            'error in game event details component... loadUser(): ' + fail
          );
        },
      });
    }
  }

  checkLogin() {
    this.isLoggedIn = this.auth.checkLogin();
  }

  createComment(c: Comment) {
    if (this.gameEvent.id) {
      this.commentSvc.create(c, this.gameEvent.id).subscribe({
        next: () => {
          this.load();
          this.comment = new Comment();
        },
        error: (f) => {
          console.error('error adding comment to game event: ' + f);
        },
      });
    }
  }

  checkHost() {
    if (this.loggedInUser.id === this.gameEvent.host.id) {
      this.isHost = true;
    } else this.isHost = false;
  }

  joinEvent() {

  }


}
