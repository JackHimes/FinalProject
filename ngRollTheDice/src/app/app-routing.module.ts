import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdvancedsearchComponent } from './components/advancedsearch/advancedsearch.component';
import { CreationComponent } from './components/creation/creation.component';
import { GamedetailsComponent } from './components/gamedetails/gamedetails.component';
import { GameeventdetailsComponent } from './components/gameeventdetails/gameeventdetails.component';
import { HomeComponent } from './components/home/home.component';
import { LoginregisterComponent } from './components/loginregister/loginregister.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
  {path: 'advancedsearch', component: AdvancedsearchComponent},
  {path: 'creation', component: CreationComponent},
  {path: 'gamedetails', component: GamedetailsComponent},
  {path: 'gameeventdetails', component: GameeventdetailsComponent},
  {path: 'home', component: HomeComponent},
  {path: 'navigation', component: NavigationComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'loginregister', component: LoginregisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
