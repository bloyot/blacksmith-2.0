-- rollback the schema for the blacksmith application

DROP TABLE IF EXISTS character_save_proficiency;
DROP TABLE IF EXISTS save;
DROP TABLE IF EXISTS character_skill_proficiency;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS attribute;
DROP TABLE IF EXISTS character_class;
DROP TABLE IF EXISTS player_character;
DROP TABLE IF EXISTS alignment;
DROP TABLE IF EXISTS background;
DROP TABLE IF EXISTS race;
DROP TABLE IF EXISTS class;

