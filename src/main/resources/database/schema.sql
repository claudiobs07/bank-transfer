CREATE TABLE bank_transfer (
      id                    SERIAL PRIMARY KEY,
      from_account          NUMERIC NOT NULL,
      to_account            NUMERIC NOT NULL,
      tax                   NUMERIC NOT NULL,
      transfer_date         TIMESTAMP NOT NULL,
      created_at            TIMESTAMP NOT NULL,
);