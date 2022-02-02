import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { AdvancedsearchComponent } from './components/advancedsearch/advancedsearch.component';
import { CreateGameEventComponent } from './components/create-game-event/create-game-event.component';
import { CreateGameComponent } from './components/create-game/create-game.component';
import { CreationComponent } from './components/creation/creation.component';
import { DisplayComponent } from './components/display/display.component';
import { GamedetailsComponent } from './components/gamedetails/gamedetails.component';
import { GameeventdetailsComponent } from './components/gameeventdetails/gameeventdetails.component';
import { HomeComponent } from './components/home/home.component';
import { LoginregisterComponent } from './components/loginregister/loginregister.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
  {path: 'advancedsearch', component: AdvancedsearchComponent},
  {path: 'creation', component: CreationComponent},
  {path: 'gamedetails', component: GamedetailsComponent},
  {path: 'gamedetails/:gameId', component: GamedetailsComponent},
  {path: 'gameeventdetails', component: GameeventdetailsComponent},
  {path: 'gameeventdetails/:eventId', component: GameeventdetailsComponent},
  {path: 'home', component: HomeComponent},
  {path: 'navigation', component: NavigationComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'profile/:userId', component: ProfileComponent},
  {path: 'loginregister', component: LoginregisterComponent},
  {path: 'aboutus', component: AboutusComponent},
  {path: 'display/:keyword', component: DisplayComponent},
  {path: 'display', component: DisplayComponent},
  {path: 'createGameEvent', component: CreateGameEventComponent},
  {path: 'createGame', component: CreateGameComponent},
  {path: '**', component: NotFoundComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
