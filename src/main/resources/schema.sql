CREATE TABLE animal
(
    registration_number VARCHAR(50) PRIMARY KEY,
    arrival_time TIMESTAMP NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE tray
(
    tray_id VARCHAR(50) PRIMARY KEY,
    part_type VARCHAR(100) NOT NULL,
    max_weight DECIMAL(10, 2) NOT NULL,
    current_weight DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE product_recipe
(
    recipe_id VARCHAR(50) PRIMARY KEY,
    recipe_name VARCHAR(100) NOT NULL
);

CREATE TABLE animal_part
(
    part_id VARCHAR(50) PRIMARY KEY,
    part_type VARCHAR(100) NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    animal_registration_number VARCHAR(50) NOT NULL,
    tray_id VARCHAR(50),

    CONSTRAINT fk_animal_part_animal
        FOREIGN KEY (animal_registration_number)
            REFERENCES animal (registration_number),

    CONSTRAINT fk_animal_part_tray
        FOREIGN KEY (tray_id)
            REFERENCES tray (tray_id)
);

CREATE TABLE product
(
    product_id VARCHAR(50) PRIMARY KEY,
    product_type VARCHAR(100) NOT NULL,
    packed_at TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    recipe_id VARCHAR(50) NOT NULL,

    CONSTRAINT fk_product_recipe
        FOREIGN KEY (recipe_id)
            REFERENCES product_recipe (recipe_id)
);

CREATE TABLE recipe_item
(
    recipe_id VARCHAR(50) NOT NULL,
    part_type VARCHAR(100) NOT NULL,
    required_quantity INTEGER NOT NULL,
    required_weight DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (recipe_id, part_type),

    CONSTRAINT fk_recipe_item_recipe
        FOREIGN KEY (recipe_id)
            REFERENCES product_recipe (recipe_id)
);

CREATE TABLE product_tray_usage
(
    product_id VARCHAR(50) NOT NULL,
    tray_id VARCHAR(50) NOT NULL,
    weight_used DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (product_id, tray_id),

    CONSTRAINT fk_product_tray_usage_product
        FOREIGN KEY (product_id)
            REFERENCES product (product_id),

    CONSTRAINT fk_product_tray_usage_tray
        FOREIGN KEY (tray_id)
            REFERENCES tray (tray_id)
);