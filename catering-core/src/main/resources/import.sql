#import.sql file

insert into diner (id, created, name, description, picture ) values (1, '2014-09-16', 'Peamaja Daily', 'Suurim söökla peamajas', '-1');
insert into menu (id, created, name, diner_id ) values (1, '2014-09-16', 'Esamaspäeva menüü', 1);
insert into diner_menu(diner_id, menu_id) values (1,1);
insert into menu_item(id, created, name, price, menu_id) values (1 ,'2014-09-16', 'Päevapraad', 2.50, 1);
insert into menu_item(id, created, name, price, menu_id) values (2 ,'2014-09-16', 'Seljanka', 1.50, 1);
insert into menu_item(id, created, name, price, menu_id) values (3 ,'2014-09-16', 'Kanakoib tomatikastmes', 3.50, 1);
insert into menu_menu_item(menu_id, menuItems_id) values (1, 1);
insert into menu_menu_item(menu_id, menuItems_id) values (1, 2);
insert into menu_menu_item(menu_id, menuItems_id) values (1, 3);