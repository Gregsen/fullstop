CREATE TABLE IF NOT EXISTS fullstop_data.rule (
  id                       BIGSERIAL NOT NULL PRIMARY KEY,
  account_id               TEXT,
  region                   TEXT,
  application_id           TEXT,
  application_version      TEXT,
  image_name               TEXT,
  image_owner              TEXT,
  reason                   TEXT      NOT NULL,
  expiry_date              TIMESTAMP NOT NULL,
  violation_type_entity_id TEXT,
  created                  TIMESTAMP NOT NULL,
  created_by               TEXT      NOT NULL,
  last_modified            TIMESTAMP,
  last_modified_by         TEXT,
  version                  BIGINT     NOT NULL,
  FOREIGN KEY (violation_type_entity_id) REFERENCES fullstop_data.violation_type (id)
);

ALTER TABLE fullstop_data.violation
ADD COLUMN rule_entity_id BIGINT,
ADD FOREIGN KEY (rule_entity_id) REFERENCES fullstop_data.rule (id);
