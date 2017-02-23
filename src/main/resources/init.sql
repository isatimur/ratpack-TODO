DROP TABLE IF EXISTS todo;
CREATE TABLE todo (
  `id`        BIGINT AUTO_INCREMENT PRIMARY KEY,
  `title`     VARCHAR(256),
  `completed` BOOL   DEFAULT FALSE,
  `order`     INT    DEFAULT NULL
)