import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Discipline } from '../discipline'; 
import { DisciplineService } from 'src/app/discipline.service';

@Component({
    selector: 'app-discipline-list',
    templateUrl: './discipline-list.component.html',
    styleUrls: ['./discipline-list.component.css']
})
export class DisciplineListComponent implements OnInit {

    disciplines: Discipline[];
    disciplineSelect: Discipline;
    messageSuccess: string;
    messageError: string;

    constructor(
        private service: DisciplineService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.findAll();
    }

    findAll() {
        this.service.findAll().
            subscribe(data => { this.disciplines = data.content })
    }


    prepareDelete(discipline: Discipline) {
        this.disciplineSelect = discipline;
    }

    newRegister(){
    this.router.navigate(['/discipline-form'])
    }

    deleteDiscipline() {
        this.service
            .delete(this.disciplineSelect)
            .subscribe(response => {
                this.messageSuccess = 'discipline selected with success'
                this.ngOnInit();
            }, erro => this.messageError = 'Occurred the problem at moment of delete discipline'
            )
    }
}