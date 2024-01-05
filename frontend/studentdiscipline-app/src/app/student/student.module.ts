import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { StudentRoutingModule } from './student-routing.module';
import { StudentFormComponent } from './student-form/student-form.component';
import { StudentListComponent } from './student-list/student-list.component';
import { RouterModule } from "@angular/router";


@NgModule({
    declarations: [
        StudentFormComponent,
        StudentListComponent
    ],
    imports: [
        CommonModule,
        StudentRoutingModule,
        FormsModule,
        RouterModule
    ], exports: [
        StudentFormComponent,
        StudentListComponent
    ]
})
export class StudentModule { }
