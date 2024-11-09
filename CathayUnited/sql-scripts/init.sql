CREATE TABLE product
(
    product_id    INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id            VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    short_name    VARCHAR(100),
    data_grouping BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_id (id) -- 為 id 欄位添加唯一索引
);

CREATE TABLE price
(
    price_id   INT AUTO_INCREMENT PRIMARY KEY,
    id         VARCHAR(255)   NOT NULL,
    date       BIGINT         NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX      idx_product_id (id),
    INDEX      idx_product_id_date (id, date),
    CONSTRAINT fk_product
        FOREIGN KEY (id) REFERENCES product (id)
            ON DELETE CASCADE
);
