import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { StudentFormComponent } from './student-form/student-form.component';
import {StudentListComponent } from './student-list/student-list.component';
import { NgModel } from "@angular/forms";

const routes: Routes = [
{ path: 'student-form', component: StudentFormComponent },
{ path: 'student-list', component: StudentListComponent }
];


@NgModule({
imports: [RouterModule.forChild(routes)],
exports: [RouterModule]
})
export class StudentRoutingModule { }

