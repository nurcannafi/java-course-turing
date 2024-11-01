create table users(
	user_id serial primary key,
	username varchar(30) not null UNIQUE,
	email varchar(30) not null check(email like'%_@_%._%'),
	password varchar(30) not null check(length(password) >= 8),
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp
);

create table posts(
	post_id serial primary key,
	user_id serial references users(user_id),
	title varchar(30) not null,
	content text not null,
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp
);

create table comments(
	comment_id serial primary key,
	user_id serial references users(user_id),
	post_id serial references posts(post_id),
	content text not null,
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp
);

insert into users(username, email, password, created_at, updated_at)
values
    ('nurcan123', 'nafi@gmail.com', '12341234', '2024-10-31 16:03:57', '2024-10-31 16:04:03')
    ('user456', 'user456@gmail.com', '12345678', '2024-10-15 16:04:17', '2024-10-15 16:04:24'),
    ('jane_doe', 'jane.doe@gmail.com', '7834589302', '2024-10-15 16:04:40', '2024-10-15 16:04:54');

update users set password=97794227 where user_id=2;

INSERT INTO posts (user_id, title, content, created_at, updated_at)
VALUES
    (1, 'First Post', 'This is the content of the first post.', '2024-10-31 16:30:00', '2024-10-31 16:30:00'),
    (2, 'Second Post', 'Content for the second post goes here.', '2024-10-31 16:35:00', '2024-10-31 16:35:00'),
    (3, 'Third Post', 'Here is the third post content.', '2024-10-31 16:40:00', '2024-10-31 16:40:00');

INSERT INTO comments (user_id, post_id, content, created_at, updated_at)
VALUES
    (1, 1, 'This is a comment on the first post.', '2024-10-31 16:32:00', '2024-10-31 16:32:00'),
    (2, 1, 'Another comment on the first post.', '2024-10-31 16:37:00', '2024-10-31 16:37:00'),
    (3, 2, 'This is a comment on the second post.', '2024-10-31 16:42:00', '2024-10-31 16:42:00');

