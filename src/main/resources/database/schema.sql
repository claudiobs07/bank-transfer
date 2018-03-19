CREATE TABLE bank_transfer (
      id                    SERIAL PRIMARY KEY,
      source_account        TEXT NOT NULL,
      destination_account   TEXT NOT NULL,
      amount                NUMERIC NOT NULL,
      tax                   NUMERIC NOT NULL,
      transfer_date         TIMESTAMP NOT NULL,
      created_at            TIMESTAMP NOT NULL,
);