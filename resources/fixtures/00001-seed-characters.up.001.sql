-- Rod --
INSERT INTO player_character
(id, name, race, base_str, base_dex, base_con, base_int, base_wis,
base_cha, background, alignment, experience, hit_point_max)
VALUES
(1, 'Rod', 2, 15, 14, 17, 9, 13, 10, 2, 5, 9981, 80);

INSERT INTO character_class (player_character, class, class_level) VALUES (1, 1, 5);
INSERT INTO character_class (player_character, class, class_level) VALUES (1, 2, 3);

INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 1);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 2);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 3);

INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 7);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 8);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 9);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 10);

INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 12);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 16);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (1, 20);

INSERT INTO character_language (player_character, language) VALUES (1, 1);
INSERT INTO character_language (player_character, language) VALUES (1, 2);
INSERT INTO character_language (player_character, language) VALUES (1, 4);

-- Tod --
INSERT INTO player_character
(id, name, race, base_str, base_dex, base_con, base_int, base_wis,
base_cha, background, alignment, experience, hit_point_max)
VALUES
(2, 'Tod', 1, 8, 9, 12, 18, 12, 14, 3, 1, 7901, 45);

INSERT INTO character_class (player_character, class, class_level) VALUES (2, 3, 7);

INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 1);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 2);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 3);

INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 7);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 8);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 9);

INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 12);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 15);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 16);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 17);
INSERT INTO character_proficiency (player_character, proficiency) VALUES (2, 19);

INSERT INTO character_language (player_character, language) VALUES (2, 1);
INSERT INTO character_language (player_character, language) VALUES (2, 3);
