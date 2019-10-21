import {Subject} from "./subject.model";

export class Person {
  id: string;
  username: string;
  name: string;
  role: string;
  subjects: Subject[]
}
