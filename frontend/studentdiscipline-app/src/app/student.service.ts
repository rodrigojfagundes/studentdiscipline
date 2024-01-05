import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Student } from "./student/student";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { ResponsePageable } from "./discipline/responsePageable.model";
import { ResponsePageableStudents } from "./student/ResponsePageableStudents";


@Injectable({
    providedIn: 'root'
})
export class StudentService {
    constructor(private http: HttpClient) { }

    insert(student: Student): Observable<Student> {
        return this.http.post<Student>('http://localhost:8080/students', student);
    }


    findAll(): Observable<ResponsePageableStudents> {  
        return this.http.get<ResponsePageableStudents>('http://localhost:8080/students');
    }

}