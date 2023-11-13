create table user (
    user_id integer not null auto_increment,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    phone varchar(255) not null,
    primary key (user_id)
) engine=InnoDB