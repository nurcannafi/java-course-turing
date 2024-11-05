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

CREATE TABLE likes
  (
     id         SERIAL PRIMARY KEY,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     created_by INT NOT NULL REFERENCES users(id),
     updated_by INT REFERENCES users(id),
     user_id    INT UNIQUE REFERENCES users(id),
     post_id    INT UNIQUE REFERENCES posts(id),
     comment_id INT UNIQUE REFERENCES comments(id),
     CHECK (post_id IS NOT NULL AND comment_id IS NULL OR post_id IS NULL AND
     comment_id IS NOT NULL)
  );


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
              ON p.updated_by = u1.id;

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
       LEFT JOIN likes l
              ON p.id = l.post_id
GROUP  BY p.id;

SELECT c.id        comment_id,
       p.id        post_id,
       Count(l.id) like_count
FROM   comments c
       LEFT JOIN posts p
              ON c.post_id = p.id
       LEFT JOIN likes l
              ON c.id = l.comment_id
GROUP  BY c.id,
          p.id;

SELECT u.username,
       p.title   liked_post,
       c.content liked_comment
FROM   users u
       LEFT JOIN likes l
              ON u.id = l.user_id
       LEFT JOIN posts p
              ON l.post_id = p.id
       LEFT JOIN comments c
              ON l.comment_id = c.id;




