-- :name get-all :? :*
select 
       pc.id,
       pc.name,
       pc.base_str,
       pc.base_dex,
       pc.base_con,
       pc.base_int,
       pc.base_wis,
       pc.base_cha,
       pc.experience,
       pc.hit_point_max,
       r.name as race,
       r.speed as speed,
       a.name as alignment,
       b.name as background
from player_character pc 
       inner join race r on pc.race = r.id 
       inner join alignment a on pc.alignment = a.id 
       inner join background b on pc.background = b.id
limit :limit

-- :name get-count :? :1
select count(*) from player_character

-- :name get-one :? :1
select
       pc.id,
       pc.name,
       pc.base_str,
       pc.base_dex,
       pc.base_con,
       pc.base_int,
       pc.base_wis,
       pc.base_cha,
       pc.experience,
       pc.hit_point_max,
       r.name as race,
       r.speed as speed,
       a.name as alignment,
       b.name as background
from player_character pc 
       inner join race r on pc.race = r.id 
       inner join alignment a on pc.alignment = a.id 
       inner join background b on pc.background = b.id 
where pc.id = :id
limit 1

-- :name get-classes :? :*
select
        c.name as class,
        cc.class_level as level
from character_class cc
     inner join class c on cc.class = c.id
where cc.player_character = :id

-- :name get-proficiencies :? :*
select 
        p.name,
        a.name as attribute,
        pt.name as type 
from character_proficiency cp 
        inner join proficiency p on cp.proficiency = p.id 
        left outer join attribute a on p.attribute = a.id 
        inner join proficiency_type pt on p.proficiency_type = pt.id 
where cp.player_character = :id

-- :name get-languages :? :*
select 
        l.name 
from character_language cl 
        inner join language l on cl.language = l.id 
where cl.player_character = :id

-- :name get-class-features :? :*
select
        cf.name,
        cf.description,
        ccf.level,
        c.name as class,
        sc.name as subclass
from character_class_feature ccf
     inner join class_feature cf on ccf.class_feature = cf.id
     left outer join class c on ccf.class = c.id
     left outer join sub_class sc on ccf.sub_class = sc.id
where ccf.player_character = :id
