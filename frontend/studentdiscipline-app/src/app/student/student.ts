import { Discipline } from "../discipline/discipline";

export class Student {

id: number;
city: string;
name: string;
cpf: string;
salary: number
disciplines: Discipline[]
discipline: Discipline;
disciplinesFormated: string;
}