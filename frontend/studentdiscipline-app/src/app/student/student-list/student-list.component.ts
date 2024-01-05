import { Component, OnInit } from "@angular/core";
import { StudentService } from "src/app/student.service";
import { Student } from "../student";
import { Discipline } from "src/app/discipline/discipline";
import { DisciplineService } from "src/app/discipline.service";

@Component({
    selector: 'app-student-list',
    templateUrl: './student-list.component.html',
    styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

    id: number;
    name: string;
    city: string;
    salary: string;
    cpf: string;
    disciplines: Discipline[];
    students: Student[];
    message: string;
    //discipline: Discipline;

    constructor(
        private studentService: StudentService,
        private disciplineService: DisciplineService
    ) { }

    ngOnInit(): void {
        this.studentService.findAll()
            .subscribe(data => {
                this.setStudents(data);
            })
    }
    consult() { }

    private setStudents(data: any): void {
        this.students = data.content?.map(item => {
            return {
                id: item.id,
                name: item.name,
                city: item.city,
                salary: item.salary,
                cpf: item.cpf,
                disciplinesFormated: this.setDisciplinesFormated(item.disciplines || [])
            }
        })

    }

    private setDisciplinesFormated(disciplines: Discipline[]): string {
        let disciplineStr = '';
        disciplines.forEach(d => {
            disciplineStr += `${d.name} |`
        }
        )
        return disciplineStr
    }

    showDisciplines(id: number) {
        let student = this.students.find(s => {
            return s.id == id;
        })
        alert(`
    Disciplinas: 
    ${student.disciplinesFormated || 'Nenhuma disciplina cadastrada'}`
        )
    }

}