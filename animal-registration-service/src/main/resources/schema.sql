CREATE TABLE animal
(
    registration_number VARCHAR(50) PRIMARY KEY,
    arrival_date DATE NOT NULL,
    weight DECIMAL(10, 2) NOT NULL,
    origin VARCHAR(100) NOT NULL,

    CONSTRAINT chk_animal_weight
        CHECK (weight > 0)
);