import { Component, OnInit } from "@angular/core";
import { Discipline } from "src/app/discipline/discipline";
import { DisciplineService } from "src/app/discipline.service";
import { Student } from "../student";
import { StudentService } from "src/app/student.service";
import {FormControl} from '@angular/forms';

type Teste = {
    id: number,
    name: string,
}

@Component({
    selector: 'app-student-form',
    templateUrl: './student-form.component.html',
    styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent implements OnInit {

    disciplines: Discipline[];
    student: Student;
    success: boolean = false;
    errors: String[];
    discipline: Discipline[];
    disciplineSelected: number[];
    
   // teste: string[] = ['math', 'english']
   // selectedTest: string[]

    constructor(
        private disciplineService: DisciplineService,
        private studentService: StudentService,
    ) {
        this.student = new Student();
      
    }


ngOnInit(): void {
    this.disciplineService
    .findAll()
    .subscribe(data => {
        this.disciplines = data.content;
    })
}


public setStudent(discipline: number[]) {
this.student = {
...this.student,
disciplines: discipline?.map(item => {
return {id: item}
})
}

}

    onSubmit() {
        console.log('Selected Discipline:', this.discipline);
        this.setStudent(this.disciplineSelected);
        console.log(this.student)
        this.studentService
            .insert(this.student)
            .subscribe(response => {
                console.log('caiu aqui', response);
                this.success = true;
                this.errors = null;
                this.student = new Student();
                
                // this.student.discipline = this.discipline

                console.log({
                  //  st: this.student.discipline,
                  //  disc: this.discipline
                });

                response.disciplines = this.discipline 
                
            }, errorResponse => {
                this.success = false;
                this.errors = errorResponse.error.errors;
            })
    }


} 