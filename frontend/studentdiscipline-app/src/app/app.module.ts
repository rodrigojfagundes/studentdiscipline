import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component'
import { DisciplineModule } from './discipline/discipline.module';
import { DisciplineService } from './discipline.service';
import { StudentModule } from './student/student.module';
import { StudentService } from './student.service';


@NgModule({
    declarations: [
        AppComponent,
        HomeComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        TemplateModule,
        DisciplineModule,
        StudentModule
    ],
    providers: [
        DisciplineService,
        StudentService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
