-- initialize the schema for the blacksmith application

CREATE TABLE IF NOT EXISTS class (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       CONSTRAINT class_id_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS race (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       speed INT(11) NOT NULL,
       CONSTRAINT race_id_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS background (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name varchar(256) NOT NULL,
       CONSTRAINT background_id_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS alignment (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name varchar(256) NOT NULL,
       CONSTRAINT alignment_id_pk PRIMARY KEY (id)
); 

CREATE TABLE IF NOT EXISTS player_character (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       race INT(11) NOT NULL,
       base_str INT(2) NOT NULL,
       base_dex INT(2) NOT NULL,
       base_con INT(2) NOT NULL,
       base_int INT(2) NOT NULL,
       base_wis INT(2) NOT NULL,
       base_cha INT(2) NOT NULL,
       background INT(11) NOT NULL,
       alignment INT(11) NOT NULL,
       experience INT(11) NOT NULL,
       hit_point_max INT(3) NOT NULL,
       CONSTRAINT player_characer_id_pk PRIMARY KEY (id),
       FOREIGN KEY (race)
       	  REFERENCES race(id),
       FOREIGN KEY (background)
       	  REFERENCES background(id),
       FOREIGN KEY (alignment)
       	  REFERENCES alignment(id)
);

CREATE TABLE IF NOT EXISTS character_class (
       player_character INT(11) NOT NULL,
       class INT(11) NOT NULL,
       class_level INT(2) NOT NULL,
       CONSTRAINT character_class_id_pk PRIMARY KEY (player_character, class),
       FOREIGN KEY (player_character)
       	  REFERENCES player_character(id)
	  ON DELETE CASCADE,
       FOREIGN KEY (class)
       	  REFERENCES class(id)
);

CREATE TABLE IF NOT EXISTS attribute (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       short_name VARCHAR(256) NOT NULL,
       CONSTRAINT attribute_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS skill (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       attribute INT(11) NOT NULL,
       CONSTRAINT skill_pk PRIMARY KEY (id),
       FOREIGN KEY (attribute)
       	  REFERENCES attribute(id)
);

-- many to many character -> skill prof
-- tells you which skills a character is proficient in
CREATE TABLE IF NOT EXISTS character_skill_proficiency (
       player_character INT(11) NOT NULL,
       skill INT(11) NOT NULL,
       CONSTRAINT character_skill_pk PRIMARY KEY (player_character, skill),
       FOREIGN KEY (player_character)
       	       REFERENCES player_character(id)
	       ON DELETE CASCADE,
       FOREIGN KEY (skill)
       	       REFERENCES skill(id)
	       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS save (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       attribute INT(11) NOT NULL,
       CONSTRAINT save_pk PRIMARY KEY (id),
       FOREIGN KEY (attribute)
       	  REFERENCES attribute(id)
);

-- many to many character -> save prof
-- tells you which saves a character is proficient in
CREATE TABLE IF NOT EXISTS character_save_proficiency (
       player_character INT(11) NOT NULL,
       save INT(11) NOT NULL,
       CONSTRAINT character_save_pk PRIMARY KEY (player_character, save),
       FOREIGN KEY (player_character)
       	       REFERENCES player_character(id)
	       ON DELETE CASCADE,
       FOREIGN KEY (save)
       	       REFERENCES save(id)
	       ON DELETE CASCADE
); 
