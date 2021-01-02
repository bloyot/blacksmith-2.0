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
INSERT INTO attribute (id, name)
VALUES (1, 'strength');

INSERT INTO attribute (id, name)
VALUES (2, 'dexterity');

INSERT INTO attribute (id, name)
VALUES (3, 'constitution');

INSERT INTO attribute (id, name)
VALUES (4, 'intelligence');

INSERT INTO attribute (id, name)
VALUES (5, 'wisdom');

INSERT INTO attribute (id, name)
VALUES (6, 'charisma');

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
INSERT INTO race (id, name, speed)
VALUES(1, 'human', 30);

INSERT INTO race (id, name, speed)
VALUES(2, 'elf', 30);

INSERT INTO race (id, name, speed)
VALUES(3, 'halfing', 25);

INSERT INTO race (id, name, speed)
VALUES (4, 'dwarf', 25);

-- proficiency types --
INSERT INTO proficiency_type (id, name)
VALUES (1, 'save');

INSERT INTO proficiency_type (id, name)
VALUES (2, 'skill');

INSERT INTO proficiency_type (id, name)
VALUES (3, 'weapon');

INSERT INTO proficiency_type (id, name)
VALUES (4, 'armor');

INSERT INTO proficiency_type (id, name)
VALUES (5, 'tool');

INSERT INTO proficiency_type (id, name)
VALUES (6, 'vehicle');

INSERT INTO proficiency_type (id, name)
VALUES (7, 'other');

-- saves --
INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (1, 'strength', 1, 1);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (2, 'dexterity', 1, 2);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (3, 'constitution', 1, 3);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (4, 'intelligence', 1, 4);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (5, 'wisdom', 1, 5);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (6, 'charisma', 1, 6); 

-- skills TODO add the rest --
INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (7, 'athletics', 2, 1);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (8, 'acrobatics', 2, 2);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (9, 'history', 2, 4);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (10, 'medicine', 2, 5);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (11, 'intimidation', 2, 6); 

-- weapon and armor --
INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (12, 'light armor', 3, null);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (13, 'medium armor', 3, null);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (14, 'heavy armor', 3, null);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (15, 'shields', 3, null);

-- TODO add the rest? --
INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (16, 'simple weapons', 4, null);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (17, 'martial weapons', 4, null);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (18, 'longswords', 4, null);

-- tools TODO add the rest--
INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (19, 'herbalism kit', 5, null);

INSERT INTO proficiency (id, name, proficiency_type, attribute)
VALUES (20, 'thieve''s tools', 5, null);

-- languages --
INSERT INTO language (id, name)
VALUES (1, 'common');

INSERT INTO language (id, name)
VALUES (2, 'elvish');

INSERT INTO language (id, name)
VALUES (3, 'dwarvish');

INSERT INTO language (id, name)
VALUES (4, 'draconic');

INSERT INTO language (id, name)
VALUES (5, 'orcish');

