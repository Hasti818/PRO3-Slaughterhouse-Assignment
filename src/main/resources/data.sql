INSERT INTO animal
(registration_number, arrival_time, weight, status)
VALUES
    ('A-1001', '2026-09-01 08:00:00', 450.00, 'PROCESSED'),
    ('A-1002', '2026-09-01 08:30:00', 500.00, 'PROCESSED'),
    ('A-1003', '2026-09-01 09:00:00', 475.00, 'PROCESSED');


INSERT INTO tray
(tray_id, part_type, max_weight, current_weight, status)
VALUES
    ('T-01', 'LEG', 100.00, 39.50, 'CLOSED'),
    ('T-02', 'RIB', 100.00, 29.50, 'CLOSED'),
    ('T-03', 'SHOULDER', 100.00, 16.00, 'CLOSED');


INSERT INTO product_recipe
(recipe_id, recipe_name)
VALUES
    ('R-01', 'Mixed Meat Package'),
    ('R-02', 'Shoulder Package');


INSERT INTO animal_part
(part_id, part_type, weight, animal_registration_number, tray_id)
VALUES
    ('PART-01', 'LEG', 18.50, 'A-1001', 'T-01'),
    ('PART-02', 'LEG', 21.00, 'A-1002', 'T-01'),
    ('PART-03', 'RIB', 12.50, 'A-1001', 'T-02'),
    ('PART-04', 'RIB', 17.00, 'A-1003', 'T-02'),
    ('PART-05', 'SHOULDER', 16.00, 'A-1002', 'T-03');


INSERT INTO product
(product_id, product_type, packed_at, status, recipe_id)
VALUES
    ('P-1001', 'MIXED_MEAT_PACKAGE',
     '2026-09-01 14:00:00', 'PACKED', 'R-01'),

    ('P-1002', 'SHOULDER_PACKAGE',
     '2026-09-01 14:30:00', 'PACKED', 'R-02');


INSERT INTO recipe_item
(recipe_id, part_type, required_quantity, required_weight)
VALUES
    ('R-01', 'LEG', 1, 10.00),
    ('R-01', 'RIB', 1, 10.00),
    ('R-02', 'SHOULDER', 1, 10.00);


INSERT INTO product_tray_usage
(product_id, tray_id, weight_used)
VALUES
    ('P-1001', 'T-01', 15.00),
    ('P-1001', 'T-02', 15.00),
    ('P-1002', 'T-03', 10.00);