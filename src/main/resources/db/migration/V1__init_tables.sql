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

INSERT INTO "user_request" ("message_title", "message", "id_user", "status")
VALUES
    ('Initial Request', 'This is a sample user request.', 1, 'OPEN'),
    ('Closed Request', 'This is a sample RESOLVED user request.', 1, 'RESOLVED');