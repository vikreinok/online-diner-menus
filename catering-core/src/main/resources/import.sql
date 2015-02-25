#import.sql file

insert into diner (id, created, name, description, picture ) values (1, '2014-09-16', 'Peamaja Daily', 'Suurim söökla peamajas', '-1');
insert into diner_menu(diner_menu, menu_id) values (1,1);
insert into menu (created, name, diner_id ) values ( '2014-09-16', 'Esamaspäeva menüü', 1);
