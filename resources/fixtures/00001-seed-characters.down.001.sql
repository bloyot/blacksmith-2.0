DELETE FROM character_skill_proficiency where player_character in (1, 2);
DELETE FROM character_save_proficiency where player_character in (1, 2);
DELETE FROM character_class where player_character in (1, 2);
DELETE FROM player_character where id in (1, 2);

