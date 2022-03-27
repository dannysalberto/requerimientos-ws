--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: requerimientos; Type: SCHEMA; Schema: -; Owner: cobra
--

CREATE SCHEMA requerimientos;


ALTER SCHEMA requerimientos OWNER TO cobra;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: requerimiento; Type: TABLE; Schema: requerimientos; Owner: cobra
--

CREATE TABLE requerimientos.requerimiento (
    id integer NOT NULL,
    nombrerequerimiento character varying(200) NOT NULL,
    tipocaptura character varying(14) NOT NULL
);


ALTER TABLE requerimientos.requerimiento OWNER TO cobra;

--
-- Name: requerimiento_id_seq; Type: SEQUENCE; Schema: requerimientos; Owner: cobra
--

CREATE SEQUENCE requerimientos.requerimiento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE requerimientos.requerimiento_id_seq OWNER TO cobra;

--
-- Name: requerimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: requerimientos; Owner: cobra
--

ALTER SEQUENCE requerimientos.requerimiento_id_seq OWNED BY requerimientos.requerimiento.id;


--
-- Name: requerimientoobra; Type: TABLE; Schema: requerimientos; Owner: cobra
--

CREATE TABLE requerimientos.requerimientoobra (
    id integer NOT NULL,
    obraid integer,
    requerimientoid integer,
    valor character varying(255)
);


ALTER TABLE requerimientos.requerimientoobra OWNER TO cobra;

--
-- Name: requerimientoobra_id_seq; Type: SEQUENCE; Schema: requerimientos; Owner: cobra
--

CREATE SEQUENCE requerimientos.requerimientoobra_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE requerimientos.requerimientoobra_id_seq OWNER TO cobra;

--
-- Name: requerimientoobra_id_seq; Type: SEQUENCE OWNED BY; Schema: requerimientos; Owner: cobra
--

ALTER SEQUENCE requerimientos.requerimientoobra_id_seq OWNED BY requerimientos.requerimientoobra.id;


--
-- Name: requerimiento id; Type: DEFAULT; Schema: requerimientos; Owner: cobra
--

ALTER TABLE ONLY requerimientos.requerimiento ALTER COLUMN id SET DEFAULT nextval('requerimientos.requerimiento_id_seq'::regclass);


--
-- Name: requerimientoobra id; Type: DEFAULT; Schema: requerimientos; Owner: cobra
--

ALTER TABLE ONLY requerimientos.requerimientoobra ALTER COLUMN id SET DEFAULT nextval('requerimientos.requerimientoobra_id_seq'::regclass);


--
-- Data for Name: requerimiento; Type: TABLE DATA; Schema: requerimientos; Owner: cobra
--

COPY requerimientos.requerimiento (id, nombrerequerimiento, tipocaptura) FROM stdin;
\.


--
-- Data for Name: requerimientoobra; Type: TABLE DATA; Schema: requerimientos; Owner: cobra
--

COPY requerimientos.requerimientoobra (id, obraid, requerimientoid, valor) FROM stdin;
\.


--
-- Name: requerimiento_id_seq; Type: SEQUENCE SET; Schema: requerimientos; Owner: cobra
--

SELECT pg_catalog.setval('requerimientos.requerimiento_id_seq', 1, false);


--
-- Name: requerimientoobra_id_seq; Type: SEQUENCE SET; Schema: requerimientos; Owner: cobra
--

SELECT pg_catalog.setval('requerimientos.requerimientoobra_id_seq', 1, false);


--
-- Name: requerimiento requerimiento_pkey; Type: CONSTRAINT; Schema: requerimientos; Owner: cobra
--

ALTER TABLE ONLY requerimientos.requerimiento
    ADD CONSTRAINT requerimiento_pkey PRIMARY KEY (id);


--
-- Name: requerimientoobra requerimientoobra_pkey; Type: CONSTRAINT; Schema: requerimientos; Owner: cobra
--

ALTER TABLE ONLY requerimientos.requerimientoobra
    ADD CONSTRAINT requerimientoobra_pkey PRIMARY KEY (id);


--
-- Name: requerimientoobra fk7atr4d4bohaggp3x77cefnpqs; Type: FK CONSTRAINT; Schema: requerimientos; Owner: cobra
--

ALTER TABLE ONLY requerimientos.requerimientoobra
    ADD CONSTRAINT fk7atr4d4bohaggp3x77cefnpqs FOREIGN KEY (obraid) REFERENCES public.obra(intcodigoobra);


--
-- Name: requerimientoobra fkkw7n8i7gfscucuux3octfgog2; Type: FK CONSTRAINT; Schema: requerimientos; Owner: cobra
--

ALTER TABLE ONLY requerimientos.requerimientoobra
    ADD CONSTRAINT fkkw7n8i7gfscucuux3octfgog2 FOREIGN KEY (requerimientoid) REFERENCES requerimientos.requerimiento(id);


--
-- PostgreSQL database dump complete
--

