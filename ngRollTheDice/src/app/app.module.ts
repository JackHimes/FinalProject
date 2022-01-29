import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule} from '@angular/common/http'
import { AuthService } from './services/auth.service';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginregisterComponent } from './components/loginregister/loginregister.component';
import { CreationComponent } from './components/creation/creation.component';
import { GameeventdetailsComponent } from './components/gameeventdetails/gameeventdetails.component';
import { GamedetailsComponent } from './components/gamedetails/gamedetails.component';
import { AdvancedsearchComponent } from './components/advancedsearch/advancedsearch.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    NavigationComponent,
    LoginregisterComponent,
    CreationComponent,
    GameeventdetailsComponent,
    GamedetailsComponent,
    AdvancedsearchComponent,
    AboutusComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    AuthService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
