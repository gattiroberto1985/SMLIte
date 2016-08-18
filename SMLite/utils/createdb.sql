/**
 * Author:  roberto.gatti
 * Created: Aug 16, 2016
 */

/*
 * PRECREATION OPERATIONS:
 * PS C:\Users\roberto.gatti> psql.exe -U postgres template1
 * template1=# create user smlite_admin with password 'Smlt20!6';
 * CREATE ROLE
 * template1=# create database smlite with owner smlite_admin;
 * CREATE DATABASE
 * template1=# grant all privileges on database smlite to smlite_admin;
 * GRANT
 */

CREATE TABLE public.categories
(
   id serial NOT NULL, 
   title character varying NOT NULL, 
   description character varying NOT NULL DEFAULT 'No description specified', 
   color character varying NOT NULL, 
   tmst_ins timestamp without time zone NOT NULL DEFAULT current_timestamp, 
   tmst_upd timestamp without time zone NOT NULL DEFAULT current_timestamp, 
   PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public.categories
  OWNER TO smlite_admin;

CREATE INDEX idx_categories_id ON public.categories (id ASC NULLS LAST);


