--DROP TABLE if exists cars cascade;
--DROP TABLE if exists status cascade;
--
--DROP SEQUENCE if exists status_id_seq;
--DROP SEQUENCE if exists cars_id_seq;
--
--CREATE SEQUENCE IF NOT EXISTS public.status_id_seq
--    INCREMENT 1
--    START 1
--    MINVALUE 1
--    MAXVALUE 2147483647
--    CACHE 1;
--
--ALTER SEQUENCE  public.status_id_seq
--    OWNER TO postgres;
--
--CREATE SEQUENCE IF NOT EXISTS public.cars_id_seq
--    INCREMENT 1
--    START 1
--    MINVALUE 1
--    MAXVALUE 2147483647
--    CACHE 1;
--
--ALTER SEQUENCE public.cars_id_seq
--    OWNER TO postgres;
--
--CREATE TABLE IF NOT EXISTS public.status
--(
--    id integer DEFAULT nextval('status_id_seq'::regclass),
--    name character varying NOT NULL,
--    CONSTRAINT status_pkey PRIMARY KEY (id)
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.status
--    OWNER to postgres;
--
--CREATE TABLE IF NOT EXISTS public.cars
--(
--    id integer DEFAULT nextval('cars_id_seq'::regclass),
--    model character varying NOT NULL,
--    status_id integer NOT NULL,
--    CONSTRAINT cars_pkey PRIMARY KEY (id),
--    CONSTRAINT cars_statusid_fkey FOREIGN KEY (status_id)
--        REFERENCES public.status (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
--)
--WITH (
--    OIDS = FALSE
--)
--TABLESPACE pg_default;
--
--ALTER TABLE public.cars
--    OWNER to postgres;


DROP TABLE if exists cars cascade;
DROP TABLE if exists status cascade;

create table status(
    id INTEGER auto_increment PRIMARY KEY,
    name VARCHAR NOT NULL,
    UNIQUE KEY name (name)
);

create table cars(
    id INTEGER auto_increment PRIMARY KEY,
    model VARCHAR NOT NULL,
    status_id INTEGER,
    foreign key (status_id) REFERENCES status(id)
);
