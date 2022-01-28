import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(
    private userSvc: UserService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
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
}
