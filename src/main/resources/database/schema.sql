CREATE TABLE bank_transfer (
      id                    SERIAL PRIMARY KEY,
      source_account        NUMERIC NOT NULL,
      destination_account   NUMERIC NOT NULL,
      tax                   NUMERIC NOT NULL,
      transfer_date         TIMESTAMP NOT NULL,
      created_at            TIMESTAMP NOT NULL,
);