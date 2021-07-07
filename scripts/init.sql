create table user
(
    id bigint auto_increment,
    login varchar(50) not null,
    password varchar(256) not null,
    role enum('USER', 'ADMIN') not null,
    balance DECIMAL(10, 2) default 0 null,
    is_blocked boolean default false not null,

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
    status enum('PROCESSING', 'ACCEPTED', 'DECLINED') not null ,

    primary key (id),
    foreign key (user_id) references user(id)
);

create table room
(
    id bigint auto_increment,
    hotel_id bigint not null,
    class enum('BUDGET', 'URBAN', 'BEACH', 'SKI', 'THEMED') not null ,
    places int not null,
    booked_from date,
    booked_until date,

    primary key (id),
    foreign key (hotel_id) references hotel(id)
);

create table reservation
(
    id bigint auto_increment,
    order_id bigint not null,
    hotel_id bigint not null,
    room_id bigint not null,
    user_id bigint not null,
    price DECIMAL(10, 2) not null,
    is_paid boolean default false not null,

    primary key (id),
    foreign key (order_id) references order(id),
    foreign key (hotel_id) references hotel(id),
    foreign key (room_id) references room(id),
    foreign key (user_id) references user(id)
);