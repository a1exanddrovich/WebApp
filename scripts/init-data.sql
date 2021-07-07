create
database booking_database;

USE booking_database;

SET time_zone = "+0:00";

create table user
(
    id bigint auto_increment,
    login varchar(50) not null,
    password varchar(256) not null,
    role enum('USER', 'ADMIN') not null,
    balance DECIMAL(10, 2) default 0 null,
    is_blocked boolean not null,

    primary key (id)
);

create table hotel
(
    id bigint auto_increment,
    name varchar(50) not null,
    description varchar(256) not null,
    image_path varchar(256) not null ,
    balance DECIMAL(10, 2) default 0 null,

    primary key (id)
);

create table order
(
    id bigint auto_increment,
    user_id bigint not null,
    hotel_name varchar(50) not null,
    places int not null,
    class varchar(50) not null,
    arrival_date date not null,
    departure_date date not null,
    status enum('PROCESSING', 'ACCEPTED', 'DECLINED'),

    primary key (id)
);

create table room
(
    id bigint auto_increment,
    hotel_id bigint not null,
    class enum('BUDGET', 'URBAN', 'BEACH', 'SKI', 'THEMED'),
    places int not null,
    booked_from date,
    booked_until date,

    primary key (id)
);

create table reservation
(
    id bigint auto_increment,
    order_id bigint not null,
    hotel_id bigint not null,
    room_id bigint not null,
    user_id bigint not null,
    price DECIMAL(10, 2) not null,
    is_paid boolean not null,

    primary key (id)
);


alter table order add foreign key (user_id) references user (id);

alter table room add foreign key (hotel_id) references hotel (id);

alter table reservation
    add foreign key (order_id) references order (id),
    add foreign key (hotel_id) references hotel (id),
    add foreign key (room_id) references room (id),
    add foreign key (user_id) references user (id);


insert into user(login, password, role, balance)
values ( 'admin', md5('admin'), 'admin', 0.00);
insert into user(login, password, role, balance)
values ( 'serg228', md5('stupid'), 'user', 0.00);
insert into user(login, password, role, balance)
values ( 'dumb', md5('dumber'), 'user', 0.00);

insert into hotel(name, description, image_path, balance)
values ('Rehana', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Rixos', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Albato', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('New York', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Albatros', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Garmony', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Makadi Bay', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Pick', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('NearSea', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('SomeName', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Regaty', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Puha', 'Some description here', 'image_path', 0);
insert into hotel(name, description, image_path, balance)
values ('Regu', 'Some description here', 'image_path', 0);

insert into room(hotel_id, class, places, booked_from, booked_until)
values (1, 'BUDGET', 2, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (2, 'SKI', 3, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (3, 'URBAN', 2, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (4, 'BUDGET', 1, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (5, 'THEMED', 4, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (6, 'BUDGET', 2, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (7, 'SKI', 5, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (8, 'BEACH', 3, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (9, 'BUDGET', 2, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (10, 'URBAN', 2, null, null);
insert into room(hotel_id, class, places, booked_from, booked_until)
values (11, 'BUDGET', 4, null, null);

insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Rehana', 1, 'BUDGET', 2021-06-01, 2021-06-04, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Rixos', 1, 'BUDGET', 2021-07-02, 2021-07-10, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Albato', 1, 'BUDGET', 2021-06-10, 2021-06-11, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Pick', 1, 'BUDGET', 2021-07-01, 2021-07-08, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'NearSea', 1, 'BUDGET', 2021-06-06, 2021-06-12, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Albatros', 1, 'BUDGET', 2021-06-10, 2021-06-14, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'New York', 1, 'BUDGET', 2021-06-21, 2021-06-24, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Rixos', 1, 'BUDGET', 2021-06-10, 2021-06-25, 'PROCESSING');
insert into order(user_id, hotel_name, places, class, arrival_date, departure_date, status)
values (1, 'Garmony', 1, 'BUDGET', 2021-07-10, 2021-08-04, 'PROCESSING');

insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (1, 2, 3, 1, 780, 0);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (2, 3, 5, 1, 980, 1);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (13, 5, 4, 1, 700, 1);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (14, 21, 7, 1, 670, 0);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (7, 1, 11, 3, 350, 0);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (5, 8, 3, 6, 150, 1);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (7, 9, 3, 8, 1800, 1);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (8, 20, 3, 1, 900, 1);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (9, 22, 6, 1, 1080, 1);
insert into reservation(order_id, hotel_id, room_id, user_id, price, is_paid)
values (2, 19, 4, 1, 690, 0);