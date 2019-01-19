INSERT INTO building
    (name, maintenance_cost, is_world_wonder)
VALUES
    ('Barracks',1, false),
    ('Granary',1, false),
    ('Library',1, false),
    ('Monument',1, false),
    ('Shrine',1, false),
    ('Walls',0,false),
    ('Pyramids',0, true),
    ('Stonehenge',0, true),
    ('Great Library',0, true);

INSERT INTO unit_category
    (name)
VALUES
    ('Melee'),
    ('Ranged'),
    ('Air'),
    ('Siege'),
    ('Naval'),
    ('Recon'),
    ('Support');

INSERT INTO unit (name, maintenance_cost, category_id)
SELECT val.name, val.maintCost, c.id
FROM  (
   VALUES
        ('Warrior', 0, 'Melee'),
        ('Archer', 1, 'Ranged'),
        ('Builder', 0, 'Support'),
        ('Battering Ram', 1, 'Siege'),
        ('Scout', 0, 'Recon'),
        ('Galley', 0, 'Naval'),
        ('Trader', 0, 'Support'),
        ('Spearman', 1, 'Melee'),
        ('Slinger', 0, 'Ranged'),
        ('Settler', 0, 'Support')
   ) val (name, maintCost, cat)
LEFT JOIN unit_category c on c.name = cat;

INSERT INTO civilization
    (name, leader, gold)
VALUES
    ('American','Teddy Roosevelt', 123 ),
    ('Egyptian','Cleopatra', 222),
    ('English','Victoria', 455),
    ('Indian','Gandhi',600);


INSERT INTO location (id, x, y)
VALUES
    (1, 4,4),
    (2, 4,11),
    (3, 12,7),
    (4, 16,1),
    (5, 27,2),
    (6, 29,8),
    (7, 35,4),
    (8, 32,15),
    (9, 36,20),
    (10, 10,16),
    (11, 7,25),
    (12, 18,20);

INSERT INTO city
    (name, is_capital, location_id)
VALUES
    ('Washington',true, 1),
    ('New York',false, 2),
    ('Philadelphia',false, 3),
    ('Boston',false, 4),
    ('Alexandria',true, 5),
    ('Thebes',false, 6),
    ('Memphis',false, 7),
    ('London',true, 10),
    ('Liverpool',false, 11),
    ('Manchester',false, 12),
    ('Delhi',true, 8),
    ('Mumbai',false, 9);

INSERT INTO civilization_cities (civilization_id , cities_id)
SELECT civ.id, c.id
FROM  (
   VALUES
        ('American', 'Washington'),
        ('American','New York'),
        ('American','Philadelphia'),
        ('American','Boston'),
        ('Egyptian','Alexandria'),
        ('Egyptian','Thebes'),
        ('Egyptian','Memphis'),
        ('English','London'),
        ('English','Liverpool'),
        ('English','Manchester'),
        ('Indian','Delhi'),
        ('Indian','Mumbai')
   ) val (civ_name, city_name)
LEFT JOIN civilization civ on civ.name = civ_name
LEFT JOIN city c on c.name = city_name;

INSERT INTO CIV_UNITS (civilization_id , unit_id, location_id, rank, health)
SELECT civ.id, u.id, l, h, r
FROM  (
   VALUES
        ('American', 'Warrior', 1, 100, 1),
        ('American','Warrior', 1, 100, 2),
        ('American','Scout', 3, 100, 2),
        ('American','Archer', 3, 100, 3),
        ('Egyptian','Galley', 5, 100, 1),
        ('Egyptian','Archer', 5, 100, 1),
        ('Egyptian','Archer', 7, 100, 1),
        ('English','Trader', 10, null, null),
        ('English','Trader', 12, null, null),
        ('English','Slinger', 12, 100, 2),
        ('English','Spearman', 12, 100, 3),
        ('English','Spearman', 12, 100, 1),
        ('Indian','Warrior', 9, 100, 1),
        ('Indian','Battering Ram', 9, 100, 2),
        ('Indian','Builder', 8, null, null),
        ('Indian','Settler', 8, null, null)
   ) val (civ_name, unit_name, l, h, r)
LEFT JOIN civilization civ on civ.name = civ_name
LEFT JOIN unit u on u.name = unit_name;

INSERT INTO city_buildings (city_id, buildings_id)
SELECT c.id, b.id
FROM  (
   VALUES
        ('Washington','Barracks'),
        ('Washington','Granary'),
        ('Washington','Monument'),
        ('Washington','Walls'),
        ('New York','Monument'),
        ('New York','Granary'),
        ('Philadelphia','Walls'),
        ('Philadelphia','Monument'),
        ('Philadelphia','Granary'),
        ('Boston','Stonehenge'),
        ('Boston','Granary'),
        ('Alexandria','Monument'),
        ('Alexandria','Walls'),
        ('Thebes','Library'),
        ('Thebes','Monument'),
        ('Thebes','Great Library'),
        ('London','Monument'),
        ('London','Barracks'),
        ('London','Granary'),
        ('London','Library'),
        ('London','Pyramids'),
        ('Liverpool','Monument'),
        ('Delhi','Walls'),
        ('Delhi','Monument'),
        ('Delhi','Shrine'),
        ('Mumbai','Granary'),
        ('Mumbai','Shrine')
   ) val (city_name, building_name)
LEFT JOIN city c on c.name = city_name
LEFT JOIN building b on b.name = building_name;




