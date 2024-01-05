import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Discipline } from './discipline/discipline';
import { Observable } from 'rxjs';
import { ResponsePageable } from "./discipline/responsePageable.model"; 

@Injectable({
    providedIn: 'root'
})
export class DisciplineService {

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'applicaiton/json'
        })
    };

    constructor(private http: HttpClient) { }

    insert(discipline: Discipline): Observable<Discipline> {
        return this.http.post<Discipline>('http://localhost:8080/disciplines', discipline);
    }

    update(discipline: Discipline): Observable<any> {
        return this.http.put<ResponsePageable>(`http://localhost:8080/disciplines/${discipline.id}`, discipline);
    }

    findAll(): Observable<ResponsePageable> {
        return this.http.get<ResponsePageable>('http://localhost:8080/disciplines');
    }

    findById(id: number): Observable<any> {
        return this.http.get<Discipline>(`http://localhost:8080/disciplines/${id}`);
    }

    delete(discipline: Discipline): Observable<any> {
        return this.http.delete<any>(`http://localhost:8080/disciplines/${discipline.id}`);
    }

}