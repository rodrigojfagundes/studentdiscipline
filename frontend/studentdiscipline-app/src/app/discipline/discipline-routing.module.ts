import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisciplineFormComponent } from './discipline-form/discipline-form.component';
import { DisciplineListComponent } from './discipline-list/discipline-list.component';


const routes: Routes = [

{ path: 'discipline-form', component: DisciplineFormComponent },
{ path: 'discipline-form/:id', component: DisciplineFormComponent },
{ path: 'discipline-list/:id', component: DisciplineListComponent },
{ path: 'discipline-list', component: DisciplineListComponent },
{ path: '', redirectTo : '/discipline/list', pathMatch: 'full' }
];

@NgModule({
imports: [RouterModule.forChild(routes)],
exports: [RouterModule]
})
export class DisciplineRoutingModule { }
