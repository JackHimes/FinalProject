import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginregisterComponent } from './components/loginregister/loginregister.component';

const routes: Routes = [
  {path: 'loginregister', component: LoginregisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
