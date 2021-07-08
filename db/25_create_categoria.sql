--
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: public; Owner: cobra
--

CREATE SEQUENCE public.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_seq OWNER TO cobra;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: cobra
--

CREATE TABLE public.categoria (
    id integer DEFAULT nextval('public.categoria_id_seq'::regclass) NOT NULL,
    codigo character varying(6) NOT NULL,
    nombre character varying(50) NOT NULL
);


ALTER TABLE public.categoria OWNER TO cobra;

insert into public.categoria (id,codigo,nombre) values (1,'01','General');