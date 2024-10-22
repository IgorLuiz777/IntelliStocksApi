CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO app_user (name, email, password)
VALUES
    ('Carlos Silva', 'carlos.silva@exemplo.com', '$2a$12$296cDnpbuLWP6exY4wa9/uSHaJHoLZkFnnUpJVFMKLkmemeDsHz0G'), --carlos123
    ('Ana Souza', 'ana.souza@exemplo.com', '$2a$12$q8u2Zii4BlJ0ajRxcFB/X.oJPcGO7zJIivbDJvHoevJk4naiz.RUa'), --ana2024!
    ('Pedro Oliveira', 'pedro.oliveira@exemplo.com', '$2a$12$/VyWNdxSCcemZedP8cdqSefS4q/k88POk2xabpb0I4FbjLLhyFSYO'), --pedroPass!456
    ('Fernanda Santos', 'fernanda.santos@exemplo.com', '$2a$12$z4nAoNBUpot5x5ZzS9sl2.CMKFJeD8Cl1NpBn6wA3u/ojwMqThPlG'), --fernanda789
    ('Lucas Pereira', 'lucas.pereira@exemplo.com', '$2a$12$F6Qx8RVcuD.b9i3iebVDGORyEy5rPit/UMZhDF0f0Ydw35YhALoZe'); --lucasPass
