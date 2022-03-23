select  a.name, b.title from books b
inner join authorsbook ab on b.ID = ab.bookID
inner join authors a on ab.authorID = a.ID;

select a.name, count(b.ID) as numberOfBooks from books b
inner join authorsbook ab on b.ID = ab.bookID
inner join authors a on ab.authorID = a.ID
group by a.name;

select p.name, p.surname, c.start from people p
inner join cards c on p.ID = c.personID
where month(c.start) = 10;

select p.name, p.surname, c.validity from people p
inner join cards c on p.ID = c.personID
where year(c.validity) = 2022;

select p.name, p.surname, c.start from people p
left join cards c on p.ID=c.personID;
