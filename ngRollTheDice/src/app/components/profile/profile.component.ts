import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from 'src/app/models/game';
import { Gameevent } from 'src/app/models/gameevent';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { GameeventService } from 'src/app/services/gameevent.service';
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
  eventsHosted: Gameevent[] = [];
  games: Game[] = [];
  game = '';
  beginEdit = false;
  editUser: User = new User();
  isFriend = false;

  constructor(
    private userSvc: UserService,
    private authService: AuthService,
    private gameEventService: GameeventService,
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
      this.loadUser(userId);
    } else {
      this.router.navigateByUrl('home'); //change this later to error page
    }
  }

  loadUser(userId: number){
    if (userId > 0) {
      this.userSvc.show(userId).subscribe({
        next: (u) => {
          this.user = u;
          this.displayEvents();
          this.displayFriends();
          this.displayGames();
          this.displayEventsHosted();
          this.findOutIfFriend();
        },
        error: (fail) => {
          console.error('ERROR RETREIVING USER' + fail);
        },
      });
    } else {
      this.router.navigateByUrl('home'); //change this later to error page
    }
  }

  findOutIfFriend() {
    console.log("In findOutIfFriend()");
    let loggedInId = this.authService.getCurrentUserId();
    this.userSvc.show(loggedInId).subscribe({
      next: (u) => {
        console.log("IN findOutIfFriend.next(). u: " + u.username);
        console.log("IN findOutIfFriend.next(). boolean isFriend: " + this.isFriend);
        if (this.user.friends) {
          console.log("IN findOutIfFriend.next(). boolean isFriend: " + this.isFriend);
          if (this.user.friends.includes(u))
          this.isFriend = true;
          console.log("IN findOutIfFriend.next(). boolean isFriend should now be true is we made it here: " + this.isFriend);
        }
      },
      error: (f) => {
        console.error("Error rectreiving logged in user: " +f)
      }
    })
  }

  addFriend() {
    let loggedInId = this.authService.getCurrentUserId();
    this.userSvc.addFriend(loggedInId, this.user.id).subscribe({
      next: (u) => {
        this.user = u;
      },
      error: (f) => {
        console.error('Error adding friend in profile component: ' + f);
      }
    });
  }

  checkIfYou() {
    let idStr = this.route.snapshot.paramMap.get('userId');
    let userId = this.authService.getCurrentUserId();
    if (idStr) {
      if (Number.parseInt(idStr) === userId) {
        this.isItYou = true;
      } else this.isItYou = false;
    }
  }

  displayGames() {
    if (this.user.games) {
      this.games = this.user.games;
    }
  }

  displayFriends() {
    if (this.user.friends) {
      this.friends = this.user.friends;
    }
  }

  displayEvents() {
    if (this.user.gameEvents) {
      this.events = this.user.gameEvents;
    }
  }

  displayEventsHosted() {
    if (this.user.hostedGameEvents) {
      for (const e of this.user.hostedGameEvents) {
        if (e.enabled) {
          this.eventsHosted.push(e);
        }
      }
    }
  }

  toggleCollapseOne() {
    let collapseOneDiv = document.getElementById("collapseOne");
    collapseOneDiv?.classList.toggle("show");
  }

  toggleCollapseTwo() {
    let collapseTwoDiv = document.getElementById("collapseTwo");
    collapseTwoDiv?.classList.toggle("show");
  }

  toggleCollapseThree() {
    let collapseThreeDiv = document.getElementById("collapseThree");
    collapseThreeDiv?.classList.toggle("show");
  }

  toggleCollapseHosted() {
    let collapseThreeDiv = document.getElementById("collapseHosted");
    collapseThreeDiv?.classList.toggle("show");
  }


  loadGame(friend: User) {
    this.game = '';
    if (friend.games) {
      return friend.games.map((x) => x.name).join(', ');
    }
    return 'Empty';

  }

  navigateToFriendProfile (friend: User){
    this.isItYou = false;
    let friendId = friend.id;
    this.router.navigateByUrl('/profile/' + friend.id);
    this.loadUser(friend.id);
    this.checkIfYou();
  }

  updateUser(userToUpdate: User) {
    this.userSvc.update(userToUpdate, this.user.id).subscribe({
      next: (a) => {
        this.user = a;
        this.editUser = new User();
        this.loadUser(a.id);
      },
      error: (f) => {
        console.error('error updating user: ' + f)
      }
    })
  }

  loadUpdateUser() {
    this.editUser = Object.assign({}, this.user);
  }

}
