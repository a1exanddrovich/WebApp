create table user
(
    id bigint auto_increment,
    login varchar(50) not null,
    password varchar(256) not null,
    role enum('USER', 'ADMIN') not null,
    balance DECIMAL default 0 null,

    primary key (id)
);

create table hotel
(
    id bigint auto_increment,
    name varchar(50) not null,
    description varchar(256) not null,
    image_id BIGINT,

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

    primary key (id),
    foreign key (user_id) references user(id)
);

create table room
(
    id bigint auto_increment,
    hotel_id bigint not null,
    class varchar(50) not null,
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
    price decimal not null,
    is_paid boolean not null,

    primary key (id),
    foreign key (order_id) references order(id),
    foreign key (hotel_id) references hotel(id),
    foreign key (room_id) references room(id),
    foreign key (user_id) references user(id)
);