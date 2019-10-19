insert into person(username, name, role) values('student1', 'Kis János', 'STUDENT');
insert into person(username, name, role) values('student2', 'Kis Márk', 'STUDENT');
insert into person(username, name, role) values('student3', 'Kis Edit', 'STUDENT');
insert into person(username, name, role) values('instructor1', 'Nagy Péter', 'INSTRUCTOR');

insert into university (name, address, popularity) values ('ELTE', 'Pázmány Péter sétány 1/C', 5);
insert into university (name, address, popularity) values ('BME', 'Egry József u. 1', 4);
insert into university (name, address, popularity) values ('Testnevelési egyetem', 'Alkotás u. 44, 1123', 2);

insert into subject (name, code, description, university_id) values ('Alkalmazások fejlesztése', 'IK01', 'Spring boot és Angular fejlesztés',1);
insert into subject (name, code, description, university_id) values ('Valószínűségszámítás', 'IK02', 'Kötelező',1);
insert into subject (name, code, description, university_id) values ('Analízis 3.', 'IK03', 'Integrálás',1);
insert into subject (name, code, description, university_id) values ('Analízis 6.', 'BME01', 'Modellezés',2);
insert into subject (name, code, description, university_id) values ('Tenisz', 'TF01', 'Kezdő tenisz',3);

insert into person_subjects (persons_id, subjects_id) values (1, 1);
insert into person_subjects (persons_id, subjects_id) values (1, 2);
insert into person_subjects (persons_id, subjects_id) values (1, 3);
insert into person_subjects (persons_id, subjects_id) values (2, 2);
insert into person_subjects (persons_id, subjects_id) values (2, 4);
insert into person_subjects (persons_id, subjects_id) values (3, 5);