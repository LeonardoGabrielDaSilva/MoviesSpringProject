INSERT INTO TBUSER(username, password) VALUES ('teste1', '$2a$12$WYtIHUiV0PEYewXOftPwj.zhHgWeQ5fjS7ETAmJApWRwMJT2WJAWi');
INSERT INTO TBUSER(username, password) VALUES ('teste2', '$2a$12$WYtIHUiV0PEYewXOftPwj.zhHgWeQ5fjS7ETAmJApWRwMJT2WJAWi');
INSERT INTO tbrole(name) VALUES ('USER');
INSERT INTO tbrole(name) VALUES ('ADMIN');

INSERT INTO USER_ROLE(user_id, role_id) VALUES (1,1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES (1,2);

