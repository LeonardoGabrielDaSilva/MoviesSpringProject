CREATE TABLE tbmovie (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    launch_year INTEGER NOT NULL,
    star INTEGER DEFAULT 0
);

CREATE TABLE tbrole (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE tbuser (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE user_role (
    user_id BIGINT,
    role_id INTEGER,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tbuser(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES tbrole(id) ON DELETE CASCADE
);

CREATE TABLE user_favorite_movie (
    user_id BIGINT,
    movie_id BIGINT,
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES tbuser(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES tbmovie(id) ON DELETE CASCADE
);

INSERT INTO TBUSER(username, password) VALUES ('teste1', '$2a$12$WYtIHUiV0PEYewXOftPwj.zhHgWeQ5fjS7ETAmJApWRwMJT2WJAWi');
INSERT INTO TBUSER(username, password) VALUES ('teste2', '$2a$12$WYtIHUiV0PEYewXOftPwj.zhHgWeQ5fjS7ETAmJApWRwMJT2WJAWi');
INSERT INTO tbrole(name) VALUES ('USER');
INSERT INTO tbrole(name) VALUES ('ADMIN');

INSERT INTO USER_ROLE(user_id, role_id) VALUES (1,1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (1,2);

