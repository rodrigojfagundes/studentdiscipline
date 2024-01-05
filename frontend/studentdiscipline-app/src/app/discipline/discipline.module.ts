


import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { DisciplineRoutingModule } from './discipline-routing.module';
import { DisciplineFormComponent } from './discipline-form/discipline-form.component';
import { DisciplineListComponent } from './discipline-list/discipline-list.component';


@NgModule({
    declarations: [DisciplineFormComponent, DisciplineListComponent],
    imports: [
        CommonModule,
        DisciplineRoutingModule,
        FormsModule
    ], exports: [
        DisciplineFormComponent,
        DisciplineListComponent
    ]
})
export class DisciplineModule { }

