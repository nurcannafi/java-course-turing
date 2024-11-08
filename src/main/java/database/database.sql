CREATE TABLE users
  (
     id         SERIAL PRIMARY KEY,
     username   VARCHAR(30) NOT NULL UNIQUE,
     password   VARCHAR(30) NOT NULL CHECK(Length(password)>=8),
     email      VARCHAR(30) NOT NULL UNIQUE,
     status     VARCHAR(30) NOT NULL DEFAULT 'active',
     role       VARCHAR(10) NOT NULL DEFAULT 'user',
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by INT REFERENCES users(id),
     updated_by INT REFERENCES users(id)
  );

CREATE TABLE posts
  (
     id          SERIAL PRIMARY KEY,
     title       VARCHAR(255) NOT NULL,
     content     TEXT NOT NULL,
     views_count INT DEFAULT 0,
     created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by  INT NOT NULL REFERENCES users(id),
     updated_by  INT REFERENCES users(id)
  );

CREATE TABLE comments
  (
     id         SERIAL PRIMARY KEY,
     content    TEXT NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by INT NOT NULL REFERENCES users(id),
     updated_by INT REFERENCES users(id),
     user_id    INT REFERENCES users(id),
     post_id    INT REFERENCES posts(id)
  );

CREATE TABLE post_likes
  (
     id         SERIAL PRIMARY KEY,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by INT NOT NULL REFERENCES users(id),
     updated_by INT REFERENCES users(id),
     user_id    INT NOT NULL REFERENCES users(id),
     post_id    INT NOT NULL REFERENCES posts(id),
     UNIQUE(user_id, post_id)
  );

CREATE TABLE comment_likes
  (
     id         SERIAL PRIMARY KEY,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by INT NOT NULL REFERENCES users(id),
     updated_by INT REFERENCES users(id),
     user_id    INT NOT NULL REFERENCES users(id),
     comment_id INT NOT NULL REFERENCES comments(id),
     UNIQUE(user_id, comment_id)
  );

INSERT INTO users
            (username,
             password,
             email,
             status,
             role,
             created_by,
             updated_by)
VALUES      ('johndoe',
             'securePass123',
             'johndoe@example.com',
             'active',
             'user',
             1,
             1),
            ('janedoe',
             'strongPass456',
             'janedoe@example.com',
             'inactive',
             'admin',
             2,
             2),
            ('michael',
             'password789',
             'michael@example.com',
             'active',
             'user',
             1,
             3);

SELECT p.id        post_id,
       p.title     post_title,
       p.content   post_content,
       p.views_count,
       p.created_at,
       p.updated_at,
       u1.username created_by,
       u2.username updated_by
FROM   posts p
       LEFT JOIN users u1
              ON p.created_by = u1.id
       LEFT JOIN users u2
              ON p.updated_by = u2.id;

SELECT c.id        comment_id,
       c.created_at,
       c.updated_at,
       u1.username created_by_user,
       u2.username updated_by_user,
       p.title     post_title
FROM   comments c
       JOIN users u1
         ON c.created_by = u1.id
       JOIN users u2
         ON c.updated_by = u2.id
       JOIN posts p
         ON c.post_id = p.id;

SELECT p.id        post_id,
       p.title,
       Count(l.id) like_count
FROM   posts p
       LEFT JOIN post_likes l
              ON p.id = l.post_id
GROUP  BY p.id;

SELECT c.id        comment_id,
       p.id        post_id,
       Count(l.id) like_count
FROM   comments c
       LEFT JOIN posts p
              ON c.post_id = p.id
       LEFT JOIN comment_likes l
              ON c.id = l.comment_id
GROUP  BY c.id,
          p.id;

SELECT u.username,
       p.title   liked_post,
       c.content liked_comment
FROM   users u
       LEFT JOIN post_likes pl
              ON u.id = pl.user_id
       LEFT JOIN comment_likes cl
              ON u.id = cl.id
       LEFT JOIN posts p
              ON pl.post_id = p.id
       LEFT JOIN comments c
              ON cl.comment_id = c.user_id;