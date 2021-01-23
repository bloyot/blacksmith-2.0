-- initialize the schema for the blacksmith application

CREATE TABLE IF NOT EXISTS class (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       CONSTRAINT class_id_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sub_class (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       class INT(11) NOT NULL,
       CONSTRAINT sub_class_id_pk PRIMARY KEY (id),
       FOREIGN KEY (class)
       	  REFERENCES class(id)
	  ON DELETE CASCADE,
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
       FOREIGN KEY (race) REFERENCES race(id),
       FOREIGN KEY (background) REFERENCES background(id),
       FOREIGN KEY (alignment) REFERENCES alignment(id)
);

CREATE TABLE IF NOT EXISTS character_class (
       player_character INT(11) NOT NULL,
       class INT(11) NOT NULL,
       sub_class INT(11),
       class_level INT(2) NOT NULL,
       CONSTRAINT character_class_id_pk PRIMARY KEY (player_character, class),
       FOREIGN KEY (player_character)
       	  REFERENCES player_character(id)
	  ON DELETE CASCADE,
       FOREIGN KEY (class) REFERENCES class(id),
       FOREIGN KEY (sub_class) REFERENCES sub_class(id)
);

CREATE TABLE IF NOT EXISTS attribute (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       CONSTRAINT attribute_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS proficiency_type (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       CONSTRAINT proficiency_type_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS proficiency (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       proficiency_type VARCHAR(256) NOT NULL,
       attribute INT(11) NULL,
       CONSTRAINT proficiency_pk PRIMARY KEY (id),
       FOREIGN KEY (proficiency_type) REFERENCES proficiency_type(id),
       FOREIGN KEY (attribute) REFERENCES attribute(id)
);

-- many to many character -> prof
-- tells you which things a character is proficient in
CREATE TABLE IF NOT EXISTS character_proficiency (
       player_character INT(11) NOT NULL,
       proficiency INT(11) NOT NULL,
       CONSTRAINT character_proficiency_pk PRIMARY KEY (player_character, proficiency),
       FOREIGN KEY (player_character)
       	       REFERENCES player_character(id)
	       ON DELETE CASCADE,
       FOREIGN KEY (proficiency)
       	       REFERENCES proficiency(id)
	       ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS language (
       id INT(11) NOT NULL AUTO_INCREMENT,
       name VARCHAR(256) NOT NULL,
       CONSTRAINT language_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS character_language (
       player_character INT(11) NOT NULL,
       language INT(11) NOT NULL,
       CONSTRAINT character_language_pk PRIMARY KEY (player_character, language),
       FOREIGN KEY (player_character)
               REFERENCES player_character(id)
               ON DELETE CASCADE,
       FOREIGN KEY (language)
               REFERENCES language(id)
               ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS class_feature (
       id INT(11),
       name VARCHAR(256) NOT NULL,
       description TEXT NOT NULL,
       CONSTRAINT class_feature_pk PRIMARY KEY (id),
);

-- map the class features for each character
CREATE TABLE IF NOT EXISTS character_class_feature (
       player_character INT(11) NOT NULL,
       class_feature INT(11) NOT NULL,
       level INT(11) NOT NULL,
       class INT(11),
       sub_class INT(11),
       CONSTRAINT character_class_feature_pk PRIMARY KEY (player_character, class_feature),
       FOREIGN KEY (player_character)
               REFERENCES player_character(id)
               ON DELETE CASCADE,
       FOREIGN KEY (class_feature)
               REFERENCES class_feature(id)
               ON DELETE CASCADE,
       FOREIGN KEY (class)
               REFERENCES class(id)
               ON DELETE CASCADE,
       FOREIGN KEY (sub_class)
               REFERENCES sub_class(id)
               ON DELETE CASCADE
);

