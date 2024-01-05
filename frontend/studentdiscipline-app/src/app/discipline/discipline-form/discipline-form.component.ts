import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Discipline } from '../discipline'; 
import { DisciplineService } from 'src/app/discipline.service';
import { Observable } from 'rxjs';


@Component({
    selector: 'app-discipline-form',
    templateUrl: './discipline-form.component.html',
    styleUrls: ['./discipline-form.component.css']
})
export class DisciplineFormComponent implements OnInit {
    discipline: Discipline;
    success: boolean = false;
    errors: String[];
    id: number;

    constructor(
        private service: DisciplineService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.discipline = new Discipline();
    }

    ngOnInit(): void {
        let params: Observable<Params> = this.activatedRoute.params
        params.subscribe(urlParams => {
            this.id = urlParams['id'];
            if (this.id) {
                this.service.findById(this.id)
                    .subscribe(response => this.discipline = response,
                        errorResponse => this.discipline = new Discipline()
                    )
            }
        })
    }

    backToList() {
        this.router.navigate(['/discpline-list'])
    }


    onSubmit() {
        if (this.id) {
            this.service
                .update(this.discipline)
                .subscribe(response => {
                    this.success = true;
                    this.errors = null;
                },
                    errorResponse => {
                        this.errors = ['Error in update discipline']
                    })
        } else {
            this.service.insert(this.discipline)
                .subscribe(response => {
                    this.success = true;
                    this.errors = null;
                    this.discipline = response
                }, errorResponse => {
                    this.success = false;
                    this.errors = errorResponse.error.errors;
                })

        }

    }


}
