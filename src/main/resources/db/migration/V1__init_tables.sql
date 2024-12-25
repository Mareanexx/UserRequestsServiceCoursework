-- db/migration/V1__init_tables.sql
CREATE TABLE "user_request" (
    "id_request" SERIAL PRIMARY KEY,
    "message_title" VARCHAR(255) NOT NULL,
    "message" TEXT NOT NULL,
    "created_at" TIMESTAMP NOT NULL DEFAULT NOW(),
    "status_date" TIMESTAMP NOT NULL DEFAULT NOW(),
    "id_user" INT NOT NULL,
    "status" VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    CONSTRAINT fk_user FOREIGN KEY ("id_user") REFERENCES "user"("id_user") ON DELETE RESTRICT
);

INSERT INTO "user_request" ("message_title", "message", "id_user", "status", "created_at", "status_date")
VALUES
    ('Автомобиль был не заправлен', 'Добрый день! Столкнулась с такой проблемой. Вчера в 12:03 бронировала автомобиль, а у него неверно показывался уровень топлива..', 1, 'OPEN', '2024-12-06 19:00:00', '2024-12-06 19:00:00'),
    ('Проблемы с доступом', 'Здравствуйте! Не удалось забронировать авто, хотя оно было свободно, помогите!', 1, 'RESOLVED', '2024-12-04 14:21:00', '2024-12-04 14:25:00');
