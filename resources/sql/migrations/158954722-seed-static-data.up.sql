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

-- sub_classes
-- TODO add the rest
INSERT INTO sub_class (id, name, class)
VALUES (1, 'champion', 1);

INSERT INTO sub_class (id, name, class)
VALUES (2, 'battle master', 1);

INSERT INTO sub_class (id, name, class)
VALUES (3, 'knowledge domain', 2);

INSERT INTO sub_class (id, name, class)
VALUES (4, 'life domain', 2);

INSERT INTO sub_class (id, name, class)
VALUES (5, 'school of abjuration', 3);

INSERT INTO sub_class (id, name, class)
VALUES (6, 'school of conjuration', 3);

-- class features --
-- TODO add the rest
INSERT INTO class_feature (id, name, description)
VALUES (1, 'ability score improvement - strength', 'You can increase one ability of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can''t increase an ability score above 20 using this feature.');

INSERT INTO class_feature (id, name, description)
VALUES (2, 'ability score improvement - dexterity', 'You can increase one ability of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can''t increase an ability score above 20 using this feature.');

INSERT INTO class_feature (id, name, description)
VALUES (3, 'ability score improvement - constitution', 'You can increase one ability of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can''t increase an ability score above 20 using this feature.');

INSERT INTO class_feature (id, name, description)
VALUES (4, 'ability score improvement - intelligence', 'You can increase one ability of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can''t increase an ability score above 20 using this feature.');

INSERT INTO class_feature (id, name, description)
VALUES (5, 'ability score improvement - wisdom', 'You can increase one ability of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can''t increase an ability score above 20 using this feature.');

INSERT INTO class_feature (id, name, description)
VALUES (6, 'ability score improvement - charisma', 'You can increase one ability of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can''t increase an ability score above 20 using this feature.');

INSERT INTO class_feature (id, name, description)
VALUES (7, 'fighting style - archery', 'You gain a +2 bonus to attack rolls you make with ranged weapons.');

INSERT INTO class_feature (id, name, description)
VALUES (8, 'fighting style - defense', 'While you are wearing armor, you gain a +1 bonus to AC.');

INSERT INTO class_feature (id, name, description)
VALUES (9, 'fighting style - two-weapon fighting', 'When you engage in two-weapon fighting, you can add your ability modifier to the damage of the second attack.');

INSERT INTO class_feature (id, name, description)
VALUES (10, 'second wind', 'You have a limited well of stamina that you can draw on to protect yourself from harm. On Your Turn, you can use a Bonus Action to regain Hit Points equal to 1d10 + your Fighter level. Once you use this feature, you must finish a short or Long Rest before you can use it again.');

INSERT INTO class_feature (id, name, description)
VALUES (11, 'action surge', 'Starting at 2nd Level, you can push yourself beyond your normal limits for a moment. On Your Turn, you can take one additional action on top of your regular action and a possible Bonus Action. Once you use this feature, you must finish a short or Long Rest before you can use it again. Starting at 17th level, you can use it twice before a rest, but only once on the same turn.');

INSERT INTO class_feature (id, name, description)
VALUES (12, 'improved critical', 'Beginning when you choose this archetype at 3rd Level, your weapon attacks score a critical hit on a roll of 19 or 20.');

INSERT INTO class_feature (id, name, description)
VALUES (13, 'extra attack', 'Beginning at 5th Level, you can Attack twice, instead of once, whenever you take the Attack action on Your Turn. The number of attacks increases to three when you reach 11th level in this class and to four when you reach 20th level in this class.
');

INSERT INTO class_feature (id, name, description)
VALUES (14, 'channel divinity: turn undead', 'As an action, you present your holy Symbol and speak a prayer censuring the Undead. Each Undead that can see or hear you within 30 feet of you must make a Wisdom saving throw. If the creature fails its saving throw, it is turned for 1 minute or until it takes any damage.

A turned creature must spend its turns trying to move as far away from you as it can, and it can''t willingly move to a space within 30 feet of you. It also can''t take Reactions. For its action, it can use only the Dash action or try to Escape from an Effect that prevents it from moving. If there''s nowhere to move, the creature can use the Dodge action.');

