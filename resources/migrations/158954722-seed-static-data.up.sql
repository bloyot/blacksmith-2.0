-- alignments --
INSERT INTO alignment (id, name)
VALUES (1, 'lawful good');

INSERT INTO alignment (id, name)
VALUES (2, 'chaotic good');

INSERT INTO alignment (id, name)
VALUES (3, 'neutral good');

INSERT INTO alignment (id, name)
VALUES (4, 'lawful neutral');

INSERT INTO alignment (id, name)
VALUES (5, 'true neutral');

INSERT INTO alignment (id, name)
VALUES (6, 'chaotic neutral');

INSERT INTO alignment (id, name)
VALUES (7, 'lawful evil');

INSERT INTO alignment (id, name)
VALUES (8, 'neutral evil');

INSERT INTO alignment (id, name)
VALUES (9, 'chaotic evil'); 

-- attributes --
INSERT INTO attribute (id, name, short_name)
VALUES (1, 'strength', 'str');

INSERT INTO attribute (id, name, short_name)
VALUES (2, 'dexterity', 'dex');

INSERT INTO attribute (id, name, short_name)
VALUES (3, 'constitution', 'con');

INSERT INTO attribute (id, name, short_name)
VALUES (4, 'intelligence', 'int');

INSERT INTO attribute (id, name, short_name)
VALUES (5, 'wisdom', 'wis');

INSERT INTO attribute (id, name, short_name)
VALUES (6, 'charisma', 'cha');

-- backgrounds --
INSERT INTO background (id, name)
VALUES (1, 'sage');

INSERT INTO background (id, name)
VALUES (2, 'soldier');

INSERT INTO background (id, name)
VALUES (3, 'folk hero');

INSERT INTO background (id, name)
VALUES (4, 'sailor');

INSERT INTO background (id, name)
VALUES (5, 'urchin'); 

-- classes --
INSERT INTO class (id, name)
VALUES (1, 'fighter');

INSERT INTO class (id, name)
VALUES (2, 'cleric');

INSERT INTO class (id, name)
VALUES (3, 'wizard');

INSERT INTO class (id, name)
VALUES (4, 'rogue');

-- races --
INSERT INTO race (id, name)
VALUES(1, 'human');

INSERT INTO race (id, name)
VALUES(2, 'elf');

INSERT INTO race (id, name)
VALUES(3, 'halfing');

INSERT INTO race (id, name)
VALUES (4, 'dwarf');

-- saves --
INSERT INTO save (id, name, attribute)
VALUES (1, 'strength', 1);

INSERT INTO save (id, name, attribute)
VALUES (2, 'dexterity', 2);

INSERT INTO save (id, name, attribute)
VALUES (3, 'constitution', 3);

INSERT INTO save (id, name, attribute)
VALUES (4, 'intelligence', 4);

INSERT INTO save (id, name, attribute)
VALUES (5, 'wisdom', 5);

INSERT INTO save (id, name, attribute)
VALUES (6, 'charisma', 6); 

-- skills --
INSERT INTO skill (id, name, attribute)
VALUES (1, 'athletics', 1);

INSERT INTO skill (id, name, attribute)
VALUES (2, 'acrobatics', 2);

INSERT INTO skill (id, name, attribute)
VALUES (3, 'history', 4);

INSERT INTO skill (id, name, attribute)
VALUES (4, 'medicine', 5);

INSERT INTO skill (id, name, attribute)
VALUES (5, 'intimidation', 6); 
