import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-loginregister',
  templateUrl: './loginregister.component.html',
  styleUrls: ['./loginregister.component.css']
})
export class LoginregisterComponent implements OnInit {
  loginSelected = true;
  registerSelected = false;
  loggingInUser: User = new User();
  createUser: User = new User();
  confirmPword = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  createAcct(user: User) {
    if (user.password === this.confirmPword) {
      this.authService.register(user).subscribe({
        next: () => {
          this.registerSelected = false;
          this.loginSelected = true;
          this.createUser = new User();
          this.loggingInUser = new User();
        },
        error: (err) => {
          console.error('loginComponent.login(): broke ' + err);
          this.router.navigateByUrl('oof');
        }
      })
    }
  }

  loginUser(user: User) {
    if (user.username && user.password){
      this.authService.login(user.username, user.password).subscribe({
        next: () => {
          console.log('in loginuser next');
          this.loggingInUser = new User();
          this.router.navigateByUrl('home')
        },
        error: (soSad) => {
          console.error('loginComponent.login(): broke ' + soSad);
          this.router.navigateByUrl('oof');
        }
      })
      }
  }

  ngOnInit(): void {
  }

}
