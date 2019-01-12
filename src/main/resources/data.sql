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

INSERT INTO city
    (name, is_capital)
VALUES
    ('Washington',true),
    ('New York',false),
    ('Philadelphia',false),
    ('Boston',false),
    ('Alexandria',true),
    ('Thebes',false),
    ('Memphis',false),
    ('London',true),
    ('Liverpool',false),
    ('Manchester',false),
    ('Delhi',true),
    ('Mumbai',false);

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

INSERT INTO civilization_units (civilization_id , units_id)
SELECT civ.id, u.id
FROM  (
   VALUES
        ('American', 'Warrior'),
        ('American','Warrior'),
        ('American','Scout'),
        ('American','Archer'),
        ('Egyptian','Galley'),
        ('Egyptian','Archer'),
        ('Egyptian','Archer'),
        ('English','Trader'),
        ('English','Trader'),
        ('English','Slinger'),
        ('English','Spearman'),
        ('English','Spearman'),
        ('Indian','Warrior'),
        ('Indian','Battering Ram'),
        ('Indian','Builder'),
        ('Indian','Settler')
   ) val (civ_name, unit_name)
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




