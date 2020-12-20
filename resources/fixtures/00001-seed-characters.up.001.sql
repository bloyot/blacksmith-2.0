-- Rod --
INSERT INTO player_character
(id, name, race, base_str, base_dex, base_con, base_int, base_wis,
base_cha, background, alignment, experience, hit_point_max)
VALUES
(1, 'Rod', 2, 15, 14, 17, 9, 13, 10, 2, 5, 9981, 80);

INSERT INTO character_class (player_character, class, class_level) VALUES (1, 1, 5);
INSERT INTO character_class (player_character, class, class_level) VALUES (1, 2, 3);

INSERT INTO character_save_proficiency (player_character, save) VALUES (1, 1);
INSERT INTO character_save_proficiency (player_character, save) VALUES (1, 2);
INSERT INTO character_save_proficiency (player_character, save) VALUES (1, 3);

INSERT INTO character_skill_proficiency (player_character, skill) VALUES (1, 1);
INSERT INTO character_skill_proficiency (player_character, skill) VALUES (1, 2);
INSERT INTO character_skill_proficiency (player_character, skill) VALUES (1, 3);
INSERT INTO character_skill_proficiency (player_character, skill) VALUES (1, 4);

-- Tod --
INSERT INTO player_character
(id, name, race, base_str, base_dex, base_con, base_int, base_wis,
base_cha, background, alignment, experience, hit_point_max)
VALUES
(2, 'Tod', 1, 8, 9, 12, 18, 12, 14, 3, 1, 7901, 45);

INSERT INTO character_class (player_character, class, class_level) VALUES (2, 3, 7);

INSERT INTO character_save_proficiency (player_character, save) VALUES (2, 1);
INSERT INTO character_save_proficiency (player_character, save) VALUES (2, 2);
INSERT INTO character_save_proficiency (player_character, save) VALUES (2, 3);

INSERT INTO character_skill_proficiency (player_character, skill) VALUES (2, 1);
INSERT INTO character_skill_proficiency (player_character, skill) VALUES (2, 2);
INSERT INTO character_skill_proficiency (player_character, skill) VALUES (2, 3);

