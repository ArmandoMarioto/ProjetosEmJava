PGDMP     '    ;                u            sisdentaldb    9.2.2    9.2.2 J    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    24576    sisdentaldb    DATABASE     �   CREATE DATABASE sisdentaldb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE sisdentaldb;
             postgres    false                        2615    25223    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6            �            3079    25224    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    185            �            1259    25229    cidade    TABLE     �   CREATE TABLE cidade (
    cid_cod integer NOT NULL,
    est_cod smallint NOT NULL,
    cid_nome character varying(50) NOT NULL
);
    DROP TABLE public.cidade;
       public         postgres    false    6            �            1259    25232    con_mat    TABLE     x   CREATE TABLE con_mat (
    con_codigo integer NOT NULL,
    mat_codigo integer NOT NULL,
    cm_qtd integer NOT NULL
);
    DROP TABLE public.con_mat;
       public         postgres    false    6            �            1259    25235    con_pro    TABLE     x   CREATE TABLE con_pro (
    con_codigo integer NOT NULL,
    pro_codigo integer NOT NULL,
    cp_qtd integer NOT NULL
);
    DROP TABLE public.con_pro;
       public         postgres    false    6            �            1259    25238    consulta    TABLE     O  CREATE TABLE consulta (
    con_codigo integer NOT NULL,
    con_data date NOT NULL,
    con_horario character varying(6) NOT NULL,
    con_obs character varying(200),
    con_status integer NOT NULL,
    den_codigo integer NOT NULL,
    pac_codigo integer NOT NULL,
    CONSTRAINT ch_nivel CHECK ((con_status = ANY (ARRAY[1, 0])))
);
    DROP TABLE public.consulta;
       public         postgres    false    6            �            1259    25242    consulta_con_codigo_seq    SEQUENCE     y   CREATE SEQUENCE consulta_con_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.consulta_con_codigo_seq;
       public       postgres    false    171    6            �           0    0    consulta_con_codigo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE consulta_con_codigo_seq OWNED BY consulta.con_codigo;
            public       postgres    false    172            �            1259    25244    dentista    TABLE     �   CREATE TABLE dentista (
    den_codigo integer NOT NULL,
    den_nome character varying(40) NOT NULL,
    den_email character varying(40),
    den_cro character varying(15) NOT NULL,
    den_fone character varying(15) NOT NULL
);
    DROP TABLE public.dentista;
       public         postgres    false    6            �            1259    25247    dentista_den_codigo_seq    SEQUENCE     y   CREATE SEQUENCE dentista_den_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.dentista_den_codigo_seq;
       public       postgres    false    173    6            �           0    0    dentista_den_codigo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE dentista_den_codigo_seq OWNED BY dentista.den_codigo;
            public       postgres    false    174            �            1259    25249    estado    TABLE     �   CREATE TABLE estado (
    est_cod smallint NOT NULL,
    pais_cod smallint NOT NULL,
    est_sgl character(2) NOT NULL,
    est_nome character varying(50) NOT NULL
);
    DROP TABLE public.estado;
       public         postgres    false    6            �            1259    25252    material    TABLE     �   CREATE TABLE material (
    mat_codigo integer NOT NULL,
    mat_descricao character varying(200) NOT NULL,
    mat_preco numeric(10,2) NOT NULL
);
    DROP TABLE public.material;
       public         postgres    false    6            �            1259    25255    material_mat_codigo_seq    SEQUENCE     y   CREATE SEQUENCE material_mat_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.material_mat_codigo_seq;
       public       postgres    false    6    176            �           0    0    material_mat_codigo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE material_mat_codigo_seq OWNED BY material.mat_codigo;
            public       postgres    false    177            �            1259    25257    paciente    TABLE       CREATE TABLE paciente (
    pac_codigo integer NOT NULL,
    pac_cpf character varying(14) NOT NULL,
    pac_nome character varying(40) NOT NULL,
    pac_cep character varying(10),
    pac_bairro character varying(100),
    pac_tipo_log character varying(100),
    pac_logradouro character varying(100),
    pac_numero character varying(6),
    pac_fone character varying(15) NOT NULL,
    pac_email character varying(50),
    pac_historico character varying(200),
    pac_foto bytea,
    cid_cod integer NOT NULL
);
    DROP TABLE public.paciente;
       public         postgres    false    6            �            1259    25263    paciente_pac_codigo_seq    SEQUENCE     y   CREATE SEQUENCE paciente_pac_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.paciente_pac_codigo_seq;
       public       postgres    false    178    6            �           0    0    paciente_pac_codigo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE paciente_pac_codigo_seq OWNED BY paciente.pac_codigo;
            public       postgres    false    179            �            1259    25265    pais    TABLE     �   CREATE TABLE pais (
    pais_cod smallint NOT NULL,
    pais_sgl character(2) NOT NULL,
    pais_nome character varying(50) NOT NULL
);
    DROP TABLE public.pais;
       public         postgres    false    6            �            1259    25268    procedimentos    TABLE     �   CREATE TABLE procedimentos (
    pro_codigo integer NOT NULL,
    pro_descricao character varying(200) NOT NULL,
    pro_preco numeric(10,2) NOT NULL,
    pro_tempo integer NOT NULL
);
 !   DROP TABLE public.procedimentos;
       public         postgres    false    6            �            1259    25271    procedimentos_pro_codigo_seq    SEQUENCE     ~   CREATE SEQUENCE procedimentos_pro_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.procedimentos_pro_codigo_seq;
       public       postgres    false    6    181            �           0    0    procedimentos_pro_codigo_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE procedimentos_pro_codigo_seq OWNED BY procedimentos.pro_codigo;
            public       postgres    false    182            �            1259    25273    usuario    TABLE     �   CREATE TABLE usuario (
    usu_codigo integer NOT NULL,
    usu_nome character varying(40) NOT NULL,
    usu_nivel integer NOT NULL,
    usu_senha character varying(15) NOT NULL,
    CONSTRAINT ch_nivel CHECK ((usu_nivel = ANY (ARRAY[1, 2, 3])))
);
    DROP TABLE public.usuario;
       public         postgres    false    6            �            1259    25277    usuario_usu_codigo_seq    SEQUENCE     x   CREATE SEQUENCE usuario_usu_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.usuario_usu_codigo_seq;
       public       postgres    false    183    6            �           0    0    usuario_usu_codigo_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE usuario_usu_codigo_seq OWNED BY usuario.usu_codigo;
            public       postgres    false    184            �           2604    25279 
   con_codigo    DEFAULT     l   ALTER TABLE ONLY consulta ALTER COLUMN con_codigo SET DEFAULT nextval('consulta_con_codigo_seq'::regclass);
 B   ALTER TABLE public.consulta ALTER COLUMN con_codigo DROP DEFAULT;
       public       postgres    false    172    171            �           2604    25280 
   den_codigo    DEFAULT     l   ALTER TABLE ONLY dentista ALTER COLUMN den_codigo SET DEFAULT nextval('dentista_den_codigo_seq'::regclass);
 B   ALTER TABLE public.dentista ALTER COLUMN den_codigo DROP DEFAULT;
       public       postgres    false    174    173            �           2604    25281 
   mat_codigo    DEFAULT     l   ALTER TABLE ONLY material ALTER COLUMN mat_codigo SET DEFAULT nextval('material_mat_codigo_seq'::regclass);
 B   ALTER TABLE public.material ALTER COLUMN mat_codigo DROP DEFAULT;
       public       postgres    false    177    176            �           2604    25282 
   pac_codigo    DEFAULT     l   ALTER TABLE ONLY paciente ALTER COLUMN pac_codigo SET DEFAULT nextval('paciente_pac_codigo_seq'::regclass);
 B   ALTER TABLE public.paciente ALTER COLUMN pac_codigo DROP DEFAULT;
       public       postgres    false    179    178            �           2604    25283 
   pro_codigo    DEFAULT     v   ALTER TABLE ONLY procedimentos ALTER COLUMN pro_codigo SET DEFAULT nextval('procedimentos_pro_codigo_seq'::regclass);
 G   ALTER TABLE public.procedimentos ALTER COLUMN pro_codigo DROP DEFAULT;
       public       postgres    false    182    181            �           2604    25284 
   usu_codigo    DEFAULT     j   ALTER TABLE ONLY usuario ALTER COLUMN usu_codigo SET DEFAULT nextval('usuario_usu_codigo_seq'::regclass);
 A   ALTER TABLE public.usuario ALTER COLUMN usu_codigo DROP DEFAULT;
       public       postgres    false    184    183            �          0    25229    cidade 
   TABLE DATA               5   COPY cidade (cid_cod, est_cod, cid_nome) FROM stdin;
    public       postgres    false    168   iR       �          0    25232    con_mat 
   TABLE DATA               :   COPY con_mat (con_codigo, mat_codigo, cm_qtd) FROM stdin;
    public       postgres    false    169   Y       �          0    25235    con_pro 
   TABLE DATA               :   COPY con_pro (con_codigo, pro_codigo, cp_qtd) FROM stdin;
    public       postgres    false    170   v       �          0    25238    consulta 
   TABLE DATA               k   COPY consulta (con_codigo, con_data, con_horario, con_obs, con_status, den_codigo, pac_codigo) FROM stdin;
    public       postgres    false    171   �       �           0    0    consulta_con_codigo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('consulta_con_codigo_seq', 1, false);
            public       postgres    false    172            �          0    25244    dentista 
   TABLE DATA               O   COPY dentista (den_codigo, den_nome, den_email, den_cro, den_fone) FROM stdin;
    public       postgres    false    173   �       �           0    0    dentista_den_codigo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('dentista_den_codigo_seq', 1, false);
            public       postgres    false    174            �          0    25249    estado 
   TABLE DATA               ?   COPY estado (est_cod, pais_cod, est_sgl, est_nome) FROM stdin;
    public       postgres    false    175   �       �          0    25252    material 
   TABLE DATA               A   COPY material (mat_codigo, mat_descricao, mat_preco) FROM stdin;
    public       postgres    false    176   +      �           0    0    material_mat_codigo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('material_mat_codigo_seq', 1, false);
            public       postgres    false    177            �          0    25257    paciente 
   TABLE DATA               �   COPY paciente (pac_codigo, pac_cpf, pac_nome, pac_cep, pac_bairro, pac_tipo_log, pac_logradouro, pac_numero, pac_fone, pac_email, pac_historico, pac_foto, cid_cod) FROM stdin;
    public       postgres    false    178   H      �           0    0    paciente_pac_codigo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('paciente_pac_codigo_seq', 1, false);
            public       postgres    false    179            �          0    25265    pais 
   TABLE DATA               6   COPY pais (pais_cod, pais_sgl, pais_nome) FROM stdin;
    public       postgres    false    180   e      �          0    25268    procedimentos 
   TABLE DATA               Q   COPY procedimentos (pro_codigo, pro_descricao, pro_preco, pro_tempo) FROM stdin;
    public       postgres    false    181   �      �           0    0    procedimentos_pro_codigo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('procedimentos_pro_codigo_seq', 1, false);
            public       postgres    false    182            �          0    25273    usuario 
   TABLE DATA               F   COPY usuario (usu_codigo, usu_nome, usu_nivel, usu_senha) FROM stdin;
    public       postgres    false    183   �      �           0    0    usuario_usu_codigo_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('usuario_usu_codigo_seq', 1, false);
            public       postgres    false    184            �           2606    25286 	   PK_cidade 
   CONSTRAINT     N   ALTER TABLE ONLY cidade
    ADD CONSTRAINT "PK_cidade" PRIMARY KEY (cid_cod);
 <   ALTER TABLE ONLY public.cidade DROP CONSTRAINT "PK_cidade";
       public         postgres    false    168    168            �           2606    25288 	   PK_estado 
   CONSTRAINT     N   ALTER TABLE ONLY estado
    ADD CONSTRAINT "PK_estado" PRIMARY KEY (est_cod);
 <   ALTER TABLE ONLY public.estado DROP CONSTRAINT "PK_estado";
       public         postgres    false    175    175            �           2606    25290    PK_pais 
   CONSTRAINT     K   ALTER TABLE ONLY pais
    ADD CONSTRAINT "PK_pais" PRIMARY KEY (pais_cod);
 8   ALTER TABLE ONLY public.pais DROP CONSTRAINT "PK_pais";
       public         postgres    false    180    180            �           2606    25292 
   pk_cod_mat 
   CONSTRAINT     ]   ALTER TABLE ONLY con_mat
    ADD CONSTRAINT pk_cod_mat PRIMARY KEY (con_codigo, mat_codigo);
 <   ALTER TABLE ONLY public.con_mat DROP CONSTRAINT pk_cod_mat;
       public         postgres    false    169    169    169            �           2606    25294 
   pk_cod_pro 
   CONSTRAINT     ]   ALTER TABLE ONLY con_pro
    ADD CONSTRAINT pk_cod_pro PRIMARY KEY (con_codigo, pro_codigo);
 <   ALTER TABLE ONLY public.con_pro DROP CONSTRAINT pk_cod_pro;
       public         postgres    false    170    170    170            �           2606    25296    pk_consulta 
   CONSTRAINT     S   ALTER TABLE ONLY consulta
    ADD CONSTRAINT pk_consulta PRIMARY KEY (con_codigo);
 >   ALTER TABLE ONLY public.consulta DROP CONSTRAINT pk_consulta;
       public         postgres    false    171    171            �           2606    25298    pk_dentista 
   CONSTRAINT     S   ALTER TABLE ONLY dentista
    ADD CONSTRAINT pk_dentista PRIMARY KEY (den_codigo);
 >   ALTER TABLE ONLY public.dentista DROP CONSTRAINT pk_dentista;
       public         postgres    false    173    173            �           2606    25300    pk_material 
   CONSTRAINT     S   ALTER TABLE ONLY material
    ADD CONSTRAINT pk_material PRIMARY KEY (mat_codigo);
 >   ALTER TABLE ONLY public.material DROP CONSTRAINT pk_material;
       public         postgres    false    176    176            �           2606    25302    pk_paciente 
   CONSTRAINT     S   ALTER TABLE ONLY paciente
    ADD CONSTRAINT pk_paciente PRIMARY KEY (pac_codigo);
 >   ALTER TABLE ONLY public.paciente DROP CONSTRAINT pk_paciente;
       public         postgres    false    178    178            �           2606    25304    pk_procedimentos 
   CONSTRAINT     ]   ALTER TABLE ONLY procedimentos
    ADD CONSTRAINT pk_procedimentos PRIMARY KEY (pro_codigo);
 H   ALTER TABLE ONLY public.procedimentos DROP CONSTRAINT pk_procedimentos;
       public         postgres    false    181    181            �           2606    25306 
   pk_usuario 
   CONSTRAINT     Q   ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (usu_codigo);
 <   ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_usuario;
       public         postgres    false    183    183            �           2606    25307    fk_cidade_paciente    FK CONSTRAINT     r   ALTER TABLE ONLY paciente
    ADD CONSTRAINT fk_cidade_paciente FOREIGN KEY (cid_cod) REFERENCES cidade(cid_cod);
 E   ALTER TABLE ONLY public.paciente DROP CONSTRAINT fk_cidade_paciente;
       public       postgres    false    168    1976    178            �           2606    25312    fk_consulta_con_mat    FK CONSTRAINT     z   ALTER TABLE ONLY con_mat
    ADD CONSTRAINT fk_consulta_con_mat FOREIGN KEY (con_codigo) REFERENCES consulta(con_codigo);
 E   ALTER TABLE ONLY public.con_mat DROP CONSTRAINT fk_consulta_con_mat;
       public       postgres    false    171    169    1982            �           2606    25317    fk_consulta_con_pro    FK CONSTRAINT     z   ALTER TABLE ONLY con_pro
    ADD CONSTRAINT fk_consulta_con_pro FOREIGN KEY (con_codigo) REFERENCES consulta(con_codigo);
 E   ALTER TABLE ONLY public.con_pro DROP CONSTRAINT fk_consulta_con_pro;
       public       postgres    false    170    1982    171            �           2606    25322    fk_dentista_consulta    FK CONSTRAINT     |   ALTER TABLE ONLY consulta
    ADD CONSTRAINT fk_dentista_consulta FOREIGN KEY (den_codigo) REFERENCES dentista(den_codigo);
 G   ALTER TABLE ONLY public.consulta DROP CONSTRAINT fk_dentista_consulta;
       public       postgres    false    171    173    1984            �           2606    25327    fk_est_cid_cod    FK CONSTRAINT     ~   ALTER TABLE ONLY cidade
    ADD CONSTRAINT fk_est_cid_cod FOREIGN KEY (est_cod) REFERENCES estado(est_cod) ON DELETE CASCADE;
 ?   ALTER TABLE ONLY public.cidade DROP CONSTRAINT fk_est_cid_cod;
       public       postgres    false    168    1986    175            �           2606    25332    fk_materiais_con_mat    FK CONSTRAINT     {   ALTER TABLE ONLY con_mat
    ADD CONSTRAINT fk_materiais_con_mat FOREIGN KEY (mat_codigo) REFERENCES material(mat_codigo);
 F   ALTER TABLE ONLY public.con_mat DROP CONSTRAINT fk_materiais_con_mat;
       public       postgres    false    176    169    1988            �           2606    25337    fk_paciente_consulta    FK CONSTRAINT     |   ALTER TABLE ONLY consulta
    ADD CONSTRAINT fk_paciente_consulta FOREIGN KEY (pac_codigo) REFERENCES paciente(pac_codigo);
 G   ALTER TABLE ONLY public.consulta DROP CONSTRAINT fk_paciente_consulta;
       public       postgres    false    178    1990    171            �           2606    25342    fk_pais_est_cod    FK CONSTRAINT        ALTER TABLE ONLY estado
    ADD CONSTRAINT fk_pais_est_cod FOREIGN KEY (pais_cod) REFERENCES pais(pais_cod) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.estado DROP CONSTRAINT fk_pais_est_cod;
       public       postgres    false    1992    180    175            �           2606    25347    fk_procedimento_con_pro    FK CONSTRAINT     �   ALTER TABLE ONLY con_pro
    ADD CONSTRAINT fk_procedimento_con_pro FOREIGN KEY (pro_codigo) REFERENCES procedimentos(pro_codigo);
 I   ALTER TABLE ONLY public.con_pro DROP CONSTRAINT fk_procedimento_con_pro;
       public       postgres    false    181    170    1994            �      x�|��r�H�%����/�f����JM�bQ���Y}`���L��	��������}��z�VmV����-_��9'@&��d&�{ �@ �G��	�½�a5�����v�Ky��v'?ͮ[�LM�?�~�������������57���0>��П��'����to�}���x��to����/k��n��ѿt�	v�]zr�m���A�l�v}�q��?^:O�Ю�4��/͸i��pg%��r��몳g߶'���~0=̽�}'_�|���Y��᥃�W�P�q��_���v���vw����ݽt��C��7'W�����0Z|��n����v�y��$�n�������f�}���u34c�z����+k�G�G���/�������Ef��x!9����E�}�nPV��c3|����v���]��W�V�'pj���,��|i^�	{��m^�Nە�v߾L=�~ew��{���ʿ�������on �^�9_G4��iAis�����A43��4դygw�5MM��~�%���][�s�{��ƻ�L{���'�Ck�e0�U��~��y��+���gҜ���}����V@+���ʓ�����������eFK����s*�����W��Ǉ7���{�R��f��l�a�x�f���n�z�y�ߢqD'sOͺ{<y��Zk�/s8�v�~�u�������z�0�	�0���ٷ�\������^�˼�C��9y�?X�>��b5���3k@�gw��~�nw/�D���w�p�����n{g_��bh�CC�+`�Y����XEl��(`��h�5�"���}Y��sT����n3���SX-�����Y��uEM�}A�;���^eB�p�6���i�4v���e��СY��D���^7ɤ�n����9���^�B��^{�n��X	̾h�g��S��N�8Z��e�b%�Ƨ�OXc�v/+�m��^Q��ݠ���Y��̕�������Nޮ���J�����/��,�_�,����?���;?��ԻU��Y��Ϡ�$n�6�,����Hk�<�D��7��ϧ?�鼬^o����k���=_�)�v�Q��)F�X��u�M����e�m͝V_v��|3>�s|YT��HZ���2��i�=�]MM������ۮ���q	-eG��Y? `ܤ1�.�[]�'�Ukul��ow~��{��oX�8o;	,���l�.�ق����{o�6�I�X�����ٶ@��:,��/�y��
�{���_�T�D�Y��WlOi�ڽ�{Vpm}� ���ܾ�����G���X(\``_��e�)��Wr���\�?��C�;��S��<�.����ЬN_DZk�?+�j���n͚��p���f'��5P�y8{=4��L�{�۩ه'4��������Ï��6ͽuQ� 55 Y[�5?����3DM�c����!i��tߘ[k�j��a�W5�3C�p4E����Bzl�X1�鋟���cbj"�`@|��Y����\5��]�F_��D�0:4�`���PoMκ@<V[ıo��Y?>C�?L�1���zC��;f�?ಡm���
��f/�����w��mϟe��~�Ն�Y�L-�p���y�}K�,(YY�����f<�UM�Z�n��}�۝����-��l���P�Sg�x�U���rςP���R��Zs_5�� o.ϩؚy��"�4>����;�f	�ا�_����ɯz��H��m��lC�tøm�~3���>�+(`�[�ŉ�b2�{76\����Wq�eA�m�m�x�L;E��V�6k��ހ���ki6�rkɓ�%L;C���0��bZ
)�}��Zª�_o�Z�(�����jSAa!*�,��踬$^p��)[Xe��J(�O�!��5�4�`B{Ŝ�Y��hPU�"{�Q�U٤5[����uxBA��V����8ZlѢ�ߢ�����`4�x�|�o,�rlW�~�kn�#��2����NB����7�4j����]s3t�5"�#�>�&�fwuK��7�h�'��5��`����6(S���}×[����3�GW��mꨆ�Qg6*I���a\�9��Ѿ@v�ް3�Í��zRIx��p҄��/�!ߎ
;�eF<o�c��c�oR	���ް��� �oPy�0���9:+��Dڵy�m�a��r�O�����u?�-.��[��pGC��!�7��ثw�vn��6�r��X����a���V3�N���Fd�-��z�]�3�i�}Ϫ�4���{8�u\�KZٻ7���0_��z"�+I{�}$�>��6b��˂��G뽷1v�����a�4�j��<�ˤ�Ҁ
H��7?{���֪�K�7��K�Ek��㪥��_���:(��,	2�I��v��cV�y��bS�b��=��6�a��<��\����
hy�99kw�N��=~P�	�V@���5J�άl�it�NCzZ��G���L�m������4�Ӭ��i��[�B߀8� .�����-�C�� �vrڊO�C�0l4�w�הn0�Ƈ���a�/�^�N�ƷRxJ�8�Eʿm$�5�ED��M�)~��{3�ꂲ��]���m��|jg�ݘ�[�� Y�A�.� o��̚�PE��}�Q�~*}�~����>�z)��
>}������f�mYF�caDVjI[��0���K_�����O���d�v�E�U��Sj�q��W��c����J��	
_�A~�@���-5�B̴Jv�7c�V��]�*�3&��ܗ�e�~n��,G�S��ZWMKR�RM#������ب�״���Uk�ݨ��ijw�h��R��jׯP?5뷘�'�ϰ��2M��"�nC�[�煚&�2��l�I	hV�9)��f���^������`�O��)��U��:��1�ɹ�V3Xi"����D���b���X��99g�ߖؙ:9�o�0O��^�{�^OJ�{5t�؍��8h����#���R(i�H��l����]4뚐��������l<h:���������k{��?�0���a���fm#Q���?��.�ٮ�L���X3jb�e����3����7���}���3��ձ_~5r����f���������=&�Sb����i�d�if�BJ�|k^�Zt��M�|�ਾ���i�ǵ�����&F�0�n�C(o�����(�o�m39��������-��XJl|�?���������ӟqv�;�����['�6�2)�d���9�gS��_-~����ȑ#Oo���ƕ
)��+EV)�2>��)�"��)�Ѥ��R���%%>����Rb���#�W�j4�M	��9����<�Ҽ�"�Zi.����)a�t���"��5�xz�'[�R��47����/O��`(%b:vs3��%�޷+�I<�@h<���D?أ�N�-*���>�}E��Na(#ؙ�H:%Ι���N���g��̪�D7JCÛ�A�`3%�at��>%��ز%����GH��7x 1��Ƣ��)Ҡ`|������&�Y��1��a���yԫO����~��Q����;774,�i�=��vn�p����c6��V��:�i��n[gQ
M�V�[sŧRQͯf��P�����`�,�]�����g�DV,�Y"��e����YmgĤ��~iâ��G0�u���J��yL�o�B�9�[��A���s$sq��s�`��w^��Z�n��2��Ykho���:NX2�̙�?��8#�Ө?��c�Ei"��&�ٸ��^�$��x�k`,��܂Ϯ����ކ��	�)Z��ԯ)r*��7E�����b�ie��6��W�CM���DF�1�GaԢ�[� �Ɉ��?��A�����wN �S$�Aλ��ۭf��[0�ч�j �`h��!#�\��x>#����8b�U)>�Hr��*|~�Z��O.���3��E�W�E�k/Ꝉq��3F�3�Y&#�����FVB�L�nw�j<��K�*��9�Ԭ
��\V��F�@N>��#V�d�ne�I�u?����Y��ke�v�
�N��3�I����4MG��xFD���*�    �B��������ˌ�r���3��"e���Y3'��z?ꮄ���ψ/��1�6��q{�N�735���?{����{<��ܦ����G���y���nǵQ��a$� ]�a2!#�o	!�}g����Fk��2��{���ʈE���(ˠ4# Q���D�a3j��$�^#���DՕ��0��Q�ZK�!TF��".
d+����#'2B��C7n?۽Z����*���53���/`�NX����;��L�yF��J���l���`q�?�)�O�̈a�S�ĸ fu3��e3�썌P�9�>�[�3��%��0s�������g�8�f�<���v�5{�-B�e�F;���l�����ĵ�p�.;'��bo��ii�hp��͉p������.���r"�%����FN�
����6�i��(w�W�-���q�;ȉqZd���!��	s���0W��.	H91n1e�z�c��`�P��k��f)R~��k��0�l=�
9��籵�j!�<v֢��1���-D�9�����Du��9��h�Q2G�f������.Z@|�C�����0('R^u7�8�ac��[�/���r���Z?�	�W:�Q�+�x�d��X��"`8*�wN$��o9�G��_,�n�����}��N���>�[�*z���Ă"�����-�A9<�~�V��c�W͍u�k�7�tQ�����2'��ń�ӿ���&׎M�''��N�W� �A�e�Tvݢ�γ<j�㣉��x����4��g�9�M��f����8Z̓���d*}��?���IN&���G��wh���m�|�O�0,�a%��X��3M~��p�S(9��Ť�r�Tդ�T|�\�	ڇ��s�����輦|�����y�]���õ�M|�^�}�^�P��o�/�ҋ�.�ǧ?�$��Ck����њuH/"�+r��췟ar"�Ɨ`�}ZY����791^긎��SJVN��F�q��:쓷���{���j��:PZ����^��"�8o��{���y�蜩����'����k)�mb}HLL�C���};�@��5WU7���v���Q�s���2��/�>_1޴���V�t�U��.������[t}�k�9�"��#�r��ǰD��M2s �t�9�cwC�&��܌���$�>Z?Q�?jTY�?rD�B���ݟ��V0B7EC������Y]���\����Z��A4�ԘƠ� �j��&;�V9&��9�A`/�1�?��m;��Q��bX������ݮ����wc�	�A�o�����|Q`��34��v��ρ�	�Avm�RTC�5�SV�؍L�5�.��i/�=���	��A2�>$
�bS`�_aa\`���Ps`Uxx?v�q_���(�1O��h�5M��w�*u�����EꃌN�HSI\.Rڧ,M�qJ�00GE�4�b-x��"�-�WP��ms�	�"�)�"��������2��Zd)5{W��&��޴��"�)�ck�`�����vc
�^"�q��U��Z��DX�)�*��[<1wﭕ�&��S�hú�"O��Zɳ(u셊<��?q�B�V�GE^Rޭ��c�l�cQǜ�eR�L*d�}�Ȱ,h�z��}ip˂�|�$SQdA�2T4�Z6߂���ni]QҔ�o�-a�0�u{?Z�ON1+�l-��~Q�h	���`�ڢ�eoF�����E ��S���{�le.�"��rd���Eh�1*J������YOqQ��w\fYT���W.
2��{v�VL|76��+�L"����Y|�UK6a�VT������E��1iQ��SC��YJEUS�}�׉������"C�:����UC1;��VV��S����Y�	Ʀ�����ߖ}u!Ũ!hQ����J)�(jZe>cݹLI���4i�``L�Y&�I*��ie���7�69�2gˤ�<�a�$�L�]��T��I=i:M�K��d7�X�0��b�5�K�R���t0���3D�X�j��Q:X�,��^��zNe�D���a���i�$l̩���a��u��v�sc�:��.(lȈ)ϒ0�	���N��U4#{gTY�|o\I�h8�c��h��Oؘf�J���6T4�fq�Z&j\t��C�t��@|U%����w"(�AM{�����(�ӟ_��&�4�RG.�W�ܠ�6j��$���3M5W�"��N8��𑕄��V|�����3ĥ��s��� �JB��=*�8b���z�� r	��"��������<p��eI������ ~p���>J|�c�<j*�e��������U&cI��L�B"�e�'q��ii%��TaIH��CIH�,�z���r���-R�+�P	�!� پ�y�Ya8Ɂ��$�@l�O���2���|�D��]j�%��22�-	0��`>Xۓ��`�JIl��vb����Q���
�JB͢@0nzج�m��J���p�CkN��y^ZCʩ�""�r�b�!:�<��~s�B�J$�)��&��~�O}��>�q}�����~YR���gn��(�|�n���g�Ad���-�����mTQ�kf-q�F"_���>rI�$}���"oظ$�'����W�lP��C�5q�2i*�R�f��~��J�r��Ac�Q��|�ɫ�ӯ�E��T�_��v����J%��QV�Yv�����gN�!��íJ�<�H1ެ�L�]}�0���L��6�U[sDU���I�n����f���~�`:�jkf#�
*�R��	6��W�#�}�æmq9�S��䩃#�[v���1E��A\)��\�R��VXe�\M���5����W�Z+/�wR|�ZXy\Ľ0�6p�u����������Y�!�U�ϩF�v��Qyx���g���<\|����.���EW��%\I~Y��	�0c]��0��*�CbA���Sm��R�y:������M.$O��Rp�Ja�)C�*�A�7�k�2�ca
&�6a_��*�i�h޷�Ԭ2X66p	��F�#r涤f^�ǚ��*˩Cv����Fۯ���kUVJ?MvTl�萗��V|��\=�r��u.l��E�`����Se��j��L�x[�0�ߥٖ�Z-o0�U�������{�a�~d��a�bV���[��yY0��I�� �*\�0ވ08L����"��a�1��U�M�����p�`u�E,\�3$��2�Mӗ���eE/l�gqOU�Qs�s�N���k�*��>�U	/�G�a5V������5�H���NX��Іᨊ2��k�̙�,�擙�y�,'#Ϊ��Ӵ�aj�y�[�廾C^�5�k��A�Z}D�&�N��+C�z��~p�l*����Q��F�[-�M�����]R�GU���uɪ���	T�boYӝ��B���
ƚͫ�[Z�m��P.Vu���O_u�~���:���	�UMO�6�D�Zӏ{|���TC����D����s�H��u�u�$�YW���:�Ao\'i����y4�6s�T'�zЮ�:������	�~@"�����U�#.�)L�浃��@
�Ӌ�,&lr�xP�c�uQ��d�ya�ˤ�p���#���I@UPem]u���r�c�S�j�-YO��~������M�����k�������$.�>���������'3�j_P�悤��OU����}EE�E~��:�X����dR��S��������;v��Y��ey�ʁa�ǖ�+�7�CbsJ�Ͼ�Ҹ����-ԩ<yh�+�xuJЛ��3��>j&��h�k׷1�ANH��h�$���" ��G��nf��+�B�:���M�ݜ]Zg��ƍa��Mʩ�I禡3�*V�RguT,wu�H�������o��d�����h�����fs��L�i~��sjM7�կ��aU�����~��52`���7H��EB�tu{�=��g������)�~�P�T0ZY����VV�Ҟ���`��I��Q�V�pިPuT�r�u�L����ߺtR�N���\����.�    P�P�.s���ܚKg�-��범��������=�\���w�Rk��"Ջ� ��`Ƴ&�J�%ڞb�6`O�K��{��yEA�ˈ,���zz� �L�(17�&���fv��R_a1S�7~N��}|�I$��nHNZ�pe��u\�5��	��ڹk�&*b_1�.���޾/��L���sb�i`�.%z�![�䔲�m&�³f�u&�<Þ�?ՒL�����TL�bR��0/D��O~�_�Zce0�
�E&���s1����DOq�
�q�tB�#���(���,��t0;�Vg��z���D��&�⋾SKp	�.&_���X�;y�n�
xP�C�u2�G�5˭�Ŀy��yP&ݍ��n/����6�-+��x�0̵@L\������q�r\B8|�<j�c2�z��+��{����&2<��%��&��L�GU��MWD]ܢ`�2�Nc���ab��g{2"� :.!4r VS��zǽ�	q��)#̔p�'�ѼN��~`��t#��%�a�n���+����T�	[�Eu,"�b�xU�Kl�`�$.^r|߉��ti�q�b2��l�EAl�l��I� F�T�PM�H.�eް�����[�k���\���AGb~�D���Y�j
����=�"e�@V/@��fv� yi�'b���nw�~@��g$�/̈́�W�-AH���T�*��p!�4>h�6_��-����i�պ]|���ì���&�.e�T�V��sQ5����ai=�jW:E�I��0�h�h&�����k6i� ���j�<"�<�xT��"|?� ��2�,��Yq��������UC%���u�Yf�:*�a�3XN����NS�V�
���G� a9/Rz�®H�W<.���!���]1�8*F��&�%�1q�~x�}�/����̺�>+qE�UC�Ӳb0{34��v�pw�;�NsG���������5����U���t��$%���u��\O�ڝ\٫#�:F* �a>�sU�,4�^]��� �(�>�1<��`D����A3��1:���`������1.�Ԭ�O�8� ���a\�[V0c�O݊{:�jh��1D���߇ws�k�sP&(Ê�=:����p����_d�&�єnU.76s��,&�vϼ�g�)C�<�>����L'+ub�%�\8�t`ԁ��=ǷL](�dȳR�ƶ����4h����(��k���S9��"�ّ��w�=Jˠؐ@�
�O@��B!(��WIJ�(��Im@�3���,�j��{�r�C�X�n0A����~���˨�/��^N`�^V��_�"��$O�B�ɹ���}.���K}�w�Q�$f��4<(Z�,����@�ò8mU>�����C�~�y�ǻf�F~"gR5I�A5��Fr��<��IDX�@+7+W#8���3�DM�S�S]��	>,��L�?�ȣ���($NSY�5_Ѝ�: �b8���g\i��Z��n�(��)`�aIL;0�H1�#-X��F�+�@DNZj)/�����2�
{��n��|(+eq����r��Z�{GT���k.�\�:��P��]m�r���sZ����
!6��o���}�Ns ��+�|�;�Wr`�Қ����G,�طڮ� +zVP:��V�i��AMw��<��,�v*��������*����@;B�!򧥡XipLe�߅�,��PN)�������*�u� �#9����������0���A4B�%on�]�=9�o��������2���9؆/9��@�@>tX����{{�uRQ���|�"kE˃�D(gr����:Q�A�}��L��q��2���b��)yx8� �)�W]_G�ꃂ�U���Z�&���Ȋ�o�.fۜe�-r�, d�
R#�FwԒ�ldeg;�!�g��C�ȧ�o�@u�BF����'�^)�n�8�zݸ]ޠ@�"|v�џ��/��C����
c�v��ir^����+�	T�aS��
`�U�[�x[u�i��+����6Ĝ��p^!�"z�#�i��k���ܸC�yE,Jͳng���+T������������+R�6U͇x�*ܟ�f�0�d��y)A^ �W�����F���8���p��ߚW ���B�!��y2a؃8f)��4Ez����]+��$R�ͽ��!͗V��n��[�����*���.薫��;(ιl�(��ڦC��脩��;P�ì��N��!,"�Wd�!���*5�y�7��c���WT���Hd��<��hg 9�o�yŏW��rȜ�3~^QrA ���v�.DT{f�;��&F��.̂Zi��+���~�o�����T톆)����z�4ڀ�6po{E5���{��(�y��y�8�}��nEr3����n�J�"�E�d?����y���V'�p��;����:V2�*҉�*����=��[̸�}T���)����u6���n��gmW�r��tVj���{�;�W�s�\k��
^1�QN���E�W���T߱���aw��u��`���YTq�/Cq�A��a&���%R�U������%���h1[0'���%qs"BD�ݼ§��I���W߿���t���G��er�"���Q�=��{^IP��I��&�2���O�lQ���eh�(͏K���(�|�1B-T�
���ͳ�&M��R�ҳ�
�bsr@��R�_�ݟ`�BK�\iWM��Z�6rƔ}^�ss[�I�#�\��Z��S���kO�A��Qw.|���F��*@�3x������R�j��0�۰"6����ԅz�L��Tۇ�F�a�'����e��ť�ۮI �Y:l\
ݮqB�jLQ�)�-$:zm0���%�	��_���H�*L���q��vN�T���r�0UTv=�y&�K��L	���U
�B��D��h,�REb���J�~Ԡ�-��H�(-2�LC�TA���M
�	gr ��(�L�.�r�|�5� ЌA�v9�V���f_�N'���^�@�����тV3�TT*3��-�fue�B�F��A��`d��U��*0�&6_���f���h�w�P��"5�GQ��l�����V��Y����ӯ�6���(�haV����%��XZϥa�갨�X����5ɹ��-�����"M7�T��AZ�Ũ�q>������S+j�ˁ,��P�\v^/�0��yׯ�~�eTȡ~���Ǩ�~f���{���,e��5u�����6���X� v͆%�-M�$6�JEsuB�{dSAG�U�T�?�;V[I���py��>K���M�[��P��t��^)���y�ʫ��,�f�b.�W�f;	\�U.�����)�B���e�!�oUI���� i<f��~gۨ��al%{ɻU���ff�s���n�4�|��K�`8SQgQ)�A�/H�>u�u)�~�}M�1�4X/�D��7��@yFq���3*�V�M�W���,�|5�B:0�M%:�h�o6�(ey�:������/�Iy�X�pKe9)���\j˂���z�2�$�,�զ�� v���ɾ=�rp_7���ą��w����&NP�5�I�K?]>�3g�bR�������*���^ll�*�{P�AǍh"|p�e����k��"��4��E!5cfMv�kCI�귘0aed.���-�S޶�ܝ��w�O�N|9���d��S	|5qN���`x[�0+'"�����qY*g���64d} |���Glv�[�7S]� �����&�(��3w7~�C�Ʒei�C�;<�
�oP�؛yr:S��'?E�����w�2E!��6-����5�}�O�S.S��|����].��G/��`�p��%��L!�a��[(/���3{(�����"�	7Ң��)^u�0+us�2f��|00����@
�A(��#�2�*�@����ס`�I�_ �˙d٤�\��~�ǊW��G`˄�
R�3S��2�)q�\�~3)��f�),��4������v�I�W�3�+���c`�s�3��r�~X��%    ���r�AW�x�n����
q
U�(u�ޡ��V�L�/�?�:�����a/��œ���?��O`[�S/|x%:�2��٢���,������G��+T��G��.�5d�w��s���Ut�UÕ�?��Rl��@]玎Ws ���x�U.ywpo�}�}��R��W�p7�Φ������	�m�N�%��.����0j��#��t �uM17�\8g�t�:�H|�*.��X<:�n���˛�m?�%��!�ή�td
ۓ�=��;�����N��J���B���1	�:����ǃ��}���t'�B�u��A��	VG�Е��7��!d';P�I�esP�Qܷ�!�>�<3�V�c�5'AwG�@��R�i�1��P�Ұ0 �f.sO�rg*<\q��}����8ک���>%V<8���m|�`�s�;A�~��N�Rx��w��N�+��YN���х���g��/�A�4w�s�v�#�	C0�9$$L�]ІCa@p4S�)֮"���.�W_&��������Д���@�w(m���@}��4Vmo�^Ё�e������y��s8����w.�5�[�T��[VtJ/��u�CHҜpc���-�0�ݩ���w����
UF��o�S�T��P,fJ�k7il {O�?j;�y>��]�lN����IT�<�6=����݁�
	Q��1����e=�s�l�y!��v�'�A�"=�l���,ر|sy-���+��;��I�@Y��Q�o�V��N
�Lj��tҒ�Y�P8�~���@KG�x�x�V\y8xv�'v�Ah�|u(!���I��z���L�b?���ʻ�Nn-��Q���^	��[j�������L"`'WA���w]�d�TA{���QTIP�����M
uO��
.Ȁ�.<�ϯ��ʂ�ʃ���F�ウN]�l	�ॣx�i J:*�K�vĲ!(���^��ݾ_��"X��޷�X���Ɲ��:�u���!�*�ΥX��B��'ˁ����-(��b���>�bz�v�]R;��������|-��:�k����>��T@w�2��#��B[�rk��]TG:һ��t88�1���]E�8@z'y:�Á��:a9��A���1d�u+��dv'&5jd�!;��ի&$�F�]5<���Kev�A��=R�p^��F�x零x��e�A%���&�Y���w$�t��EW�}?���<�>z�Β
Ӂ2/�ˍ�����m������v>OāS��=��pY��f-Z=hp�g��u ףn���J�XJޒ��|*��CZRש��<<J1����a�&t:7���z�]&�0�I�0.g��e���B�>m1��$���
�)�B��h�Y&ե`�`rt[xPt�|m�Z��oPv��_�W��	�_����
�� ']���<��&Dyph|�G�zT���<�z�"/'9Ly0�y �pE^�:~E:e���|��F���qE��*2[���fՔ�V��%��-�Ť��~��\(�Y���,�'K{��epc^o)��G8g�ep��㻆����e��'iq7�+�|�iz�(��	әE��)[q֬(��\����J��!����7ۧ�hu�f�\�)�����כ��?U�8�)�|R݆��IsׯyM5ѭ*x0ܠ��&�?�a�u0�����R��hssc�+j5���E��>������Q�W�3��8�/�vE]�-�����}�I��٭�{���D��$���������/�=5����w�I��J[�VNJnW&�9���YU�N�2��t'�UƖg�&����g��$�<��7��7Ε��W1+l[���喙J����i���՗�_I��J��NֺW���W*�a]z��S�a�X����Y� �(?�+�|��?�$���O$]�ڿ���ҿ�ל�)�Du�L�01P
�Q0�BK�QRM3������V�Ua��Ux�a�\*8.��r�₃�v>@�T�p���I��L��%�w.��1�e��p�X���v���u�63�G�K`��4��g-[����@w���%K��܁fٚ�X	��ٕY�{�r=��:���q�%#�2������e=ܓؕ!��tl7�����X?�޻2D�u�0�*C�@�4-C��=�
N=��!�0q6s�!���o1��"��+CL�0o�gK��u�-;�]��p����\�H³��Ԙx�6�~NJ�je��GTk�����9�L��_J�Gxv�pU�x�4#�C(��yԂ+C�Gl�k��YϷ��a\�s�e�C��1�=s	�2�%V*n@W��dD����2�%������	7R�3�u�
K^�ko�k�b�稣T|bZ֊����Ra	�񶃘�(�R��\�Ў6):�2pwE'�����9�t*���YK;��
VD��X�T�"R>��,�s[�E�J�-*~>�)�,	]���r7�;�R�L�*�+k7��+�ᩕ���bh@�C@U մ:^*z��;dIC�J�.Њ]�:y�[��i���40qB�He҃��rw*,W�)�?xH�▩ \{�������'\uΛ���9��
z�f��G7m?����޵��>�ÜJ!̢�Uh�]�HFM�m�i�J���.o�R0�fx_u�p��n��=O��"xJ�#�iPbr�?̂�)_I�:]�U�j�ծRLi~^+W�Rh� ��k^�͢��O�b�7��b�\)�y�?o���`���_U��7���v����T��[f�W�m�qn&��Jq*%�P�X�oSwW)�	�ˬ�\[��+�;�A�� "��+ʕB��t��`μC�Z)Z�F�Rx�ؖ6�q�%aG�A�\���l����%�G�|����_�����_���`Z`��G�r�p��i�((�K[��T
H�"Ԅ�
H&��W�| W)y�-����*�'5�Rq���N�B�w��Y�J�ȻfӮCZ)y�n��V)�S�q\) 	�R���� zW)	Yא3��C��撧�v�"^�~L�q����N)�x7��K]�Xc��)� �:!�3�>Tn�����BYzv*���
r6�v+P�6����R�`�ocx��۞���3�R�UL�Q�Jo��>kfkU�pH.2(y�>m��Rtp�%�����@�ݹJ���1ܯ�MV�+���]E�y��q��σAI�x E6��W
NuB���2:la�"�a��R�5p�E �_e���5�lY.�qV�����v���mHů��~�5e!=�.}����lb�	�q��=���C&������n#�pW	�O�TI��J�nr�[p�c{1�R	�O�G�T�j���Q�ւn��ѝZ�-9Ҧ�e����^-�桼�x�|R<���B�@_�kʠ��&k�4O���^-�G��@{W��RL��������Iv�.����lpd��]6�\��]�T��#4Ť�7�Mt�t�vэ��烺�E?��U+
��'��r��FI�\���O.������h���f�j����G&F�><FzW�`r���}�x���:֎�BZ���t,�"��c�Zx��� fQ�n�K�&!���ga����Yd:��8�����uP����F�y�yX�&���� l-�<��*`�����\bѹe�Nj�_v-�\.K�Bʠ���Zh�!k�k��Y�s+\-�49�H-��v��~��悙�Zp)���d�!�I��P 7��6>[������P]q��r�l�,�rW��ѳ1p��B�3�Ǳc�ZXz6NIԵ��\����g���0­���.����hgW-۱���ZPwiO��j!��ӪK-X�6`�Z����mJa��47Q\ϛ��FAkHs�����*�d7_C\��,8�3���ja�y�MV�|�������F�ZH���v����A:2��r��k����	\��f�9��Ȟ�[.����q��k!,4S�Wg�s��E�DH{�]�����Y}i������'փ����S{aA�
sK|����&��Gkҗ�E��R    {?�x����PNk]u�N�Ә�����x���k>I�Ii��I�lvC��J{cp�$i��^�r�'IpaXw:�'I�9A��I�P�����ţ	}�w�qU�'Ipg?s���%Qi/M����V���p"�ąw��Y��S��t�~����΢)�>q��}?䊨z��O\���Ļˏpj6uP�\ȫ	�9��k��C;��9�'Bߋvǎ
�M�b�C�D�k�[�>�"?���>��� �V�~uV�*��!�'Bߋg�D�k҆���_�5���q{>�i��pn����i4�,�q(Ţ�O��RqQ�'Bd��z/m5k��'i=k�����,YrO�O27�"�)�~�>���,x7��tp���Tm��>_聚�����b��{�(�����@[d|"���b��'�����G��`��E%������}�Tғ�w%�6�}�+iri���'����}�A�!U��}���DH��G-���'�^$$ֶ��*$�^��?n��DpL%��@�.Td�$��D�N?���2�����+�I���ʠ�O��7�դ�H��\D���%��Z7�������%q@���&.���A0T�"[*b��O�?!fbZG��#: !�O�#�7��D����
�����}"Ц�m�W+̦F$>h����x�%6�C���i��O�MȤ|"7�V39>�CC��\x��xHu�f�v_bԨ"ח�U2^��T�O�4*�FN��YT��>�����R���>��Yy�"�'u5���S��Iw��%I����U�]��/玽K���lL0X��]��8�]�K��3�d\���j,8���B� ���f������	��mʻ�Nx-�-�?Q��q��;�
b���Z�Q{'��Z���1��%�N��y��;��#�7�լ�GC<�h�;�D�R�k���TE�O�O�������ƀT�=�ح���@��kF��ky��M�ԓwB�E��r��;�T���0��N@?�NK�	���F�KP����v?׮��� �@
2,{�Q!�r�9�]Z-TB=��Q�˒I��me.*8X�.����e��q��]n���/�5]��ԅe+��x'��Wf'*Tȍc���;a��v�8�W_x~�w�n)9qޯ���;���9�y`��>�������΃�.n���X*���چ���\Z��bR�w���!�qy'_0b/8�.1�w��9R�M��ٰ��4;*���P�wB��a�� �WX�3�k�g�IB���v=>�ғ���ۯ��(N�~ծ����_��#o!T�G`"�N�n��u���,P7ծ	�4{'@'Hh�rj�@��Q)-)U7����dy'l���N�RNu8��Q��3�9[и����	ڧ��	ԡ��#TYT��u�_�Qô�"*�l�U�B��f�]UME��,ԓ#��96M=9BWO�,j��,���>l:Z>[���*t{��1�s��i��-CـB���a<�=�����Vb�̛��%/��Ь�t��B�������ȵ�}&�����zގ����y�r׾���_u=�=���+.8�w��6���f�\r\�&���%z���7�;2g.�^��������]:���S���Ƶ�J�[T�¼w�j� 7Ry���슫޻�Y1����zW�0���_ԋ��͸�{^�u.������Yp�}��<V�ώ�,0�L>����Bmz�ы��Z�lQY�u������^$D(�J���?�}��2����;(#G>�O�Q�;.�}��a5	����h���>͏
�a�O��2u�()Kv�:ES�R��ާ�����:�Q'{�%߿�ݠz��������߿"��x��߿�B�c�e�/��NF��悎I��7^T���u�NO���)��°���aO�xL�ir��)6;��k�Q��W���/�K^��^���?�¶�3ph������Z�b��y����i�,s����Xɕ�P^?/G&J���}1W��i�!����}��� ��n��+/�{M@|_d߽d�!��=w{_�uv@#xXoEyt�6��,���]���0����Y�}�84�C_��ra��E���*���ۭ�M_f�/��/�祗ZE��<����<��76�2S��򸮖���\V����-y�W�
#1I<.���=/;7@�^���,T�����POUv\<��|�?/i��W��B�a��y陒����x{]�����Ý����Y��������Z�짯�q�AGX�ϊw'!3��z�"��󅎇��]_=Ẇ������W`��.��U���%��w/���M�L���$�7iҧ��7�-?M���f�I�I��sQ��d���/V��)O��s^4M�ڽ��j��k���"���O��2#���B�����>���������`g�i���_�q�C�Y9y�.��R�*�a�!�j���K�> 1��4�ޡ���f<�i�>���p�x�0W'���Ϝj�6AA}X���vjd��4��H��x.!w�Sc�	�a�sY�!�f;�Q������aעOCd
v'��OCT�匎O.gųm_>!�O���������§!bn#�OC�ܭD��� ٔ��3�����X�1��a1�N�[���7��}��~=M`F7_77�Z�l������@@�S���ȼ��b����QB����-U�i
���'*V��	��Ppg�yM!����u�l�S��V�[ت�˰����k-�
6��7P�&�+�_qgE�ר �Wq�}�7��L&�H؟�X"U��/�,f%I`����9�mŇ�<>S�s>ULx=���@&�O���B��7�B�}�Q�+��H�/���
�>��Q�>l�w��77#�um����$u�� �O�b:�j�Wf�Ul؊��%��#�Idm\�HYp���T��e�
��1Y�ѱ�E�#�7�E~���5���S�H
�*^"��)P2e{����4�/������Y��K}u����|�H�<x/y���CqΧ��1�d�"�u��O�@��K�@�hR
e>al��ܸ��*��ԃ8�m�
a��ݮ�ݺq�#��Q&��v��T�a&zU}i[2�p�B�O?��*H��Q�w�y~f8�~�5������jԭ��3����S��);�i�-|�mG^���IFr����U2���.F\d��p�;{R�vf/~lخ��	I�!Y:!n���d'yX!- Oq47������^s=d��xf�CǕ rpf����|�Yd|�$��^�9����Y�����Y8�j��&�DOBN^0N��L��1t ����Wp�ɾ�-x?��d�<*��������6�^r!90�2�(�Z_�,dU{�mf��y.3A]KM�c�U$��^�]��4���j��ܚ��V�Em�i,�!�mw�<R9�����zRlB?t3q�'�fv�i��T��_5�����`���G����Nd�;���޽�t�4�i�͑_�z�a���u4?�[��K�3��c�$ө�篓�<i3Z����=d���SC�N�o���19�<šv�6�_�AW�CzL(�)OP$Cf����-N��2�q{�;����aAN�ϭ;�FN�#Rxcf/.���N���bꏍf�ȑ�1ɐNv�,0⁍ܓSk���
4ѩ� nޒ�I�	m /�d�4E�
K�d���+�.d�"wJ>r�'�e�&���o�L�+���MJp��{���j�z0�c�G̠�j����d�4�n:�ӑ3{�S8yn%��i�uLN����4�C�*�3��܎Kzy	NB��J$O'1�O���p�FA�1���P�J7� Ӂ]cEg8��ˏ=�TEw.�;Ѥ�13���t� :��xOV�,c��b��2$&f��d%$��f�`hh6�j�{��F_��m8}�gt�Br�.���wY3��q�;2a:��*(��gB��+`�ֹ �p+�F.�_�R|�K?�.�Qa:��~���#�+(8ZE�~�O�ۣN3W��25�<)4d������f�CT5Odg�_�O�6 _�    DE�O��mQ|��
�Ѡ�O�3)���}�����w�E"��ˇ���,+�˴�t#�����WS�<7�n�	�>ws�͉M�T�܇fHe}���p��>��<��*�Bb�\�'��\A���~B��*�.���+E ��\
��u�UUP��r�+d��A�@�+`�<p^1W��q��=��`��W�8�SwG��|R̐+Y1�bJ���3qp�'ug�K�m��ܙ��i�@��I���w��+RvB� vE*�]92DVDs�����ȩ+zd��gbfh��ГdUP��u�id�ܲ�r7I:�)�y�u�"���졏}J.Kw�1v�d��m��;Ǥx�SQ�"�L7(�
��{2u�/&f�y}e��2��H�	�N����7�t)�GH�E�[�0�>���3�'�b61�#����^��RWL�x�<��@<�;�F=O��|��7�(XY)�ı��,�S?��������8��L��i�'�gJlQgzrvBf��n�"�C�|j)�<�ٝ<�ﭦAI��R���8aO"σ���ѓ�s*�=*'����R|m�YU�T�ѓ�3�r3�'�gN>�5��d��'ʷ�C�ʨ��V��T����u�a@���9����{��=�~B�����˾_~���I�ɂQ;�=�?���"�n� C�O�|`�;��r���D�9	ſ�͓�31���B�\�[��1Y>!�<�|FQ�dQZ��<�w���h`^1���������x�l4�@v�|�g1��#��Ost�t":�ϓ��T '�q=y>�oT�,�;=Y>sWލ���$���pw.AK��5�=p:�'�g���J�!�g6���!�'5_嘧����x������4�tc��f�3�:sl���3��g/z�t�I]C2uF��[QW��c���5��CFNS`}[�Hș�͝������������ؒ����H+�Iƙ���b��V7�U0t���T�ԍh~XY/�a�ܫB������s��x�zqb�/��6��{�
���8�}!T��8f5B拆�Q!`>h�B�&4�F��8*#�j�B�|�1@!l��8�=� ��������%�x�4(B�X��<�ȃ�{�&;�LV�w��+��ܜ��D����_���c�qx�g�q=2�*���c��j��ch	_����|!�]`]>�������I�P�Rv c���۰/E����\ֹ6�>���`Y[�V(hwض��B�*%7��p����m�޸�j����;�e��}��Є���r+
�h�V;M�"N!���O��wq��~^v�䇾r^���G2�i��7����W�!�=�B�Ɍ�5D��y�B�i�ṵǡ�酝��7�b�Ο7��	6�o�d*��X���#�BX9��AeT(i�ل��4L��ϘU7Ƒ,.��p��N�B����ke�����.p�R+U?E���ث�S�J��})��g��fU8�֗B��!���������	���·�4p)���IlU�K钃B�e���ҹ�P����,R<�߅z/������h�ߞ�W
��310p�H�ԕ�gg�����c�J���%��K�q�P�T���_��U�w���$���0.}�������;��Dqq\�BRz�җ�)<\��z~͇�fD�W��Y!W����2M�s���e:W��i�p�L纊�$��d�5�2��j9G�x�nϠ�L��^ٟ`�8��,��)ݳ�*��Ra��/b�_*��8��u�XD�8�(�Hye E�+�P���R!����|��Y�KE%���WyP���RA������'2���|7tU����v`bO�p�lu����{�JE!�����R�(�z���gʴ�c���"�eu+	+ِi!���T������@y��ɈB
,��9�O�M�o��<96����!�}k�:���E��Mz�bZ���][=$e�bV�@K|N���~'ŋ�~<�6���ׄ${�R�m�/�@�C6���4�O������O�HAa��yPh����RVc1�p)�ñ����4
�P��n��*	���H�Iy\�Ǔ8*�VL$癠��������#���m���[�ɇݾ�xSپ���B�y�x��#Cfqx�'Cf�"P@0w����,�cq��d��#�dG\��2����δ�H/�I�Y%�ɜy�f��}���,�RS��<K\QMWD3ӈ��,�C)��Hb��pI>�X0�e�U�@��\Uѣ�����EX�o�"��r�+܂����r�LfM���5)5�{�ɘ᧵���uV��H�Y�,M)�LS
�kiJdwG�b�>��ɥ�(�gv�w%�fq4�4�XڟĤjF%"�v��k�iƂ��6�\�QwрtѓJs�Y�.K�|��9Gw3`����x���܌�x2j�\�hs.�y��DǾ���b}��fpК'�&53�4��?`X@:M*8Z'�fA�w����r��;�b}��ܽ�eNfM���8i�̗�^I@Vri��K(�B
&FZvOMS�ۇE�K�C�F��:��i�f��;��ɘY�o_4�&Y��P�o �6��j���BÅhpwv|`�Hc=��q�*)4��O�C���A=��'_��L>b�0� yjN�EDZ�ò�1Mz��="�Q�i֢�!�f��%��hS
���ɴYL3��qʞ,�˂i��\��%� ��C?uqM��bqv��f�I� ��)*���_v܀V)��1��C�ƂiQ�SU��$ �kO��n�"�/�}ۇ/D�e�B��Fߔkɏho��+o���D�93�No�R ��`�"L�L;���VX�B�}��d�����e(&y�8l�
I9ڲ����kB!JP�w���He^�AY������<;4�W�\����/g������CQD�������N��R��n���"�;����`�8ΝV�S��i_�8��nT)8	|���I�v���Pw?6lj
F���lm�_�j5�T)��k��^�=մ����٤�VxqzC�j��7� X_+�|���V�Ň�VAZU�4����	��EY�-=���0�����^�3�"�C�T_+f8�&�k���^��b�X4�Z�����Z�)N�S��A��G�؀$��5��1�"����͘�b2x~eJ�kE,ĠQ+,����Y+&qy�����("0W�j���k��X8!�W��o-�'�&���I����p�|
֊L���F�r�kA��f=	��Yj1Q��m�s����U4X�O���9/Z��~�i�V p6�-@�Vp6�X_+89����7��8֨�ʌ���7���3���MU#h?��P��|�C����B�x�ͫ���D9,���UR#�LI��^������enh-ܾh��v��h��Z };b�]��n��먚&�k��>/�|j�s���$g-t6e�-��uka��F_ЯǘY�b� t�l]䓒�CuQD��@�.ʩ(����_��:�Z��$*f�Ju��&83��o����=���e�}����`�~�h�2X�AP]{���&_���qy���H�T�����/l��Zh�<8@�Q�P�5'\ŎW�t�i�Ѹ����4��K!�I���jf.���=H��m\���c�X�
���̓���	pU���Jc�^�[j��ʙP��tH��
�RP�O��WS�>K!��t"7���\��k�
�o�.'m`��v��+6�R]Ϯ���I��1S'M���k&��I]'g�
#�jz���o��DP����~KE�\��4�_2c�i"h�^>��b�}H��)z�8�Dp�h�aC��EP����@�Ϊ;�9���]�{Rc���]:��4q��}8���Si�ʥ�������=su�bI���i�-�P���sQpV�~iMYi"X�&���������G�����v��i�IK5i"�W!b�4�Cd��hL�<�:u%M�"��gyTKOA<X�4d\    Nͦ� ?����z�!sQ�7.&����r������ _	�?l�G2����^�u��	�f��&B|�&r�4��,�D9M���h
_�V�&
lW�(��S{QT��h���lױ@n�+bH�(0 V��TAQ��}�B��P�q	A{)n�4Q��#՗�J�(h���Ǉ4ˏL���V�4�G�P�3�n�ڜ�&
 �l�q����]��ȵ,ѱ�+�y���c��ס���#1u���A���VD8[��QAv�N���tOr�k��6���LiX��ݱ���]%��5��w���-^v���k��r�����n�{p���{��^s�v��4��,7Ey�\���������(��$��/U\�:��}�K�8���.�7��e?[�^do�*1��Mߠ(e����U"�w��E1"���D0��J�"k_���g|�F�d��
ю�����0�*��=��]%�1[L��U��Ap���h�rC]���"ѐ-�]�Q�&_�5Y쾻J��}wU
l��O�ѷ��[��w������jP��9������zE�c���h�pl�[��МXS�MEqE����sO�� "�ج���{W`�}� 3�߿`�6q��}���1�j��%6��n�)�r�Ϥ�Ѯt��*Q����El�~?�f	��Eo���^��-m��Z�Ǭ��b���yJS��S[)��j���9`�����~����[]���;-��u�"�S�����L��4��u�|b��:��,DRX�w��)u��V�(w��|ŏw�l��:w�: ���~wO�W7��1���MIP5WV,,�Dn!��j�����x� ~o�/�[����k���q&Y}W���n�X��]*w�����W-2vi�G j�!�b) m+�Zl���w+����\�Θ������j�0�v_	��qV~�>���L׫���2�W�u������sI�@�e�
�|]N�'g�yu%}��a������X�R�ZT�W�ba��"Y�ށ��u�JJ�Н�ű������u��Y�
[���p���G��K�۩��R���jq����N��Z�㸿���V�#R��"N'g�]-������/�wϸ��|-�vG�~���3A3�M}�O
�	V*��V�O�QJ�	ʾvv.9����
��\�+��8j��T@'@1f����3��|��k��QEvEA�NGj(��6ʬ`�*﯑��ޱ��Q�7���>��(��͇OL�x��O��5^y��j	�v�P���+�ocrt�����(�*�=�-H�t�$p��^m�洣�k�C%r�}He�ھM���R`V����57z�~z��
�*�������Cz|Ce�8�ec�Z���[04!5��N�M|'8R� ��9�v<.g䑖j�]�w�A��j�4O6K��+�Xѹ�֚{;9���1ߺ�n%]��t�f�d̟i�����Qܕ�	b���(���(�
��d��R���J��������ޟ�&k72qTaE9�ƚ�Qw�ȑ��2��)���N��RE�y�PG�Cֻ�Y[h���V��W�����}OG}�.mG�}IY�QiKQ;�RD���#����!�=C��v9�$� 1LӖ?�z�0 q� '�@y�)[�>�7ES������}��"G��;b�r�&�ϐ���H;9�O���=G��.mm9��vϊ�"�v��\Q�U����!�n�+C�����l[�Q����@;m>��0�j��g�AJOvK���^��(�|x�d������f�����yCqwG=O�+V-��"�?n>Q��Q����s~^]Mَׄ��_�<�S��7TU8�9����B��c��jZ��r�K��G��򳋤_���}���6ȱ&u ����I���ɳΪᮑ[�9�=%�
���t��)����5�\���9U�[ޘ���3(|��V�hs���/6�7r����yT,S�5�\��',7jS����,� 4r�Z~�v�k�//0ަ�J�@��a^���96��I��[�xJN����W^��,���@UW��R��)w�F�1��F���u�?�1	\ w�~}=~�+"G�N.D�����y�y
���S%�Fn�No�3�K,�6�Fn�do��`Dv��"���\�<`q��9��r9fE!ٔ��9���zBА���|��q���ꌓ\�#��^W�'0+��đ�Um��K"��n[�^��:�%N�F���3̖+,i�s��ₓk]j�&�d��h�SyDt������7w��S�[�$'������^�F���aS�p"w��q'�t�/VN���[ñ��?�/�F]"��^h�l�Z:'7}��㉓���F,�;y�"��9�gI���+�N޺|��ך���8ylHĘ�qrۗ;4�� �Na��}rܗ{��|br��B��߆���2�8'�̢G���V�/7��7��/+=n9���������y2�����j�H#$I*�G��l�����
�tG���h�wTH�1���hH��~)�਑��	��W8�(��Ոld:ri��_.�04
�_�ۿ����B:
(������{�
�k�J�
j����X��:�)�9��Rn|��c�<���ڸ7K!žSX�N-{`F��8��*��4D�r�W�)�ا��������9�[4ɥb$J�n}�!��~8:��F�m;�q���3�i,dC/��q����8����Nʹ���':�0yٱ]�څv���6�/8�0
�i��?��e_wj�B-j�İ4��Z���c�E�j#CZI�������;*3�c�~���FƤ��4:3N��&?����ln�9��t��O~�M����=�H�n��]���lf(��:d+v/�U�J�v�0Z�-�\�s��G,8���J����f�x���30�g�g�T@9�2e5��cZ��&���.,����큓������Q�J<��WF-���	�S%!��<�		`�cF'��!��T/jE��/(-	?I��~�ex9ꞝ��. @m�c<(���َ1���5%{�w�i2LU�^�X妦d��"RR�n��U�K�*�����;��n���Z�����y��G��^"�_�E��#��Lg۝�#�7X���ԅ��d���f}�"Y��H5%'�C�hAuT��St�B)=�#p[�z��6l��/��Ǔ�	�D�VG���f�'�2�7η�BY�qə'u(K���8�Q� � �.��|���e�pl�qs5q��*�=�2��8oȞaz������Lq��y�V�;6�,~53��d!F.3Pv���x��،3�@C��VF)B�3��#_h3>�O��s�]�`�������W�a����1�΋(�Z��'ذ���t��A/��u�s^)M.���E����c>����|>x/�"J�ܱ��Ē�>��a���V����:�?�JE
���F���um/�4/��r,n�-.�x��?<�q�G)���;4ҋ;����h�΋0��W�2��W�����z��2�9/�d;�����aD� ��+���Al��S<�*�����l/�R����>�-M�@t��w�I�(���o�@���K9Es����%��8/����\TU�I���Wɸ�YnS�š�<�/>�����V|Ă���R�j^R*��]q�~n��D�N.Xh�;/�U4r��Ű��~�{�c�|"�X�/i��*5K����QF\�  ;�q����i�}�X}Y?����Am�/L"P���K���bN\Sėa��[^�(��>�S����+�<-�ѷָ���^�H��gg ��[a!�Ƚ� �t1���ȑ�LWg��8L��E���=���`!�@5g��w,��ɻ�y�ٌ�I��V�O�$=�eA�������l-0�����kNփ��y�;����L}�8P>׵�;6{�����#�m�	�_�=/�1�Y|Ȑ��vRk���P�m���R��`��p�X� ~�$hQI1�E4��&�-��W�c:���6[��[:�����ޕ��|A���|KD����J?!    ��T)Ŝ�
�K��O�33��yj�� F��!3��H�?�B�Ԗ7�O���ma\fKr�uO�`q��߱Ϳ��
Y�=��?Ųf�W>wл����9)D)-Q'��{rM1-��|����e��K9Ti�{����)��ز%i"�E��|����n�'"B�a�]s�Y�>��ÂX!N��~;"��|�O̭)�炸������չ��Mg��~�,�2m��4��&J�a;-=�b~��,�����	]�����K3�bc�K����V37W��4$	�_g����l����F��Ùk����p����J� �.�R$��hJw��8�z��IB����	���o����d�xq?�kt�v�_�<�"�:��R	����;zs����0_�uBL5Ԓ�_Gc/#�Y�}����$�8��v�sŔz�C�J�(:pa�~}5�Ɛ�׻�ୣl(�;��W�-�r�����vC��w�ߞ�Y�R�+��r�D-��߫��t�M��dZm��(0�~�u �X���N���E��}KJ���*�߁-���ك�!�+$��5�w��`�� ��zC�L��h`S��� )+:p/Z3Z��p���bcE�SL�[�J�ەe�q�x΍�q��e�.):�)OJ+�(:pۚ	��C�R� �D����"���(��z9G����^�Ov�iG�PC�HM��i�^1��2��'�� :��
?�N��o���Ok�j��-.*�fر{�T��O�b�x������� qe�?cx���x�[wD��ÃR����R���z�c���A��
��s
���h�90���¨�T�֗D��a�G{��!�M*������L��m�o��x��ђ���EH�%����L��q�Q�ti�YD-NS�tXp��s&�r*N�\��uR�@G��$c�X���}3O�n���U��(m
 ���N��e���cn|ML�0Ʊb��7 6J6�Q�t�'���ꦃx~��y�_}�, q�^�R˂P]�8
��G����>�Х�9 ��?b��)��9h��/���WK�p#�@��n{��7Q^�FXu��t�+7�\<"TB������PmN��ŋo���SX���&F������j0�Oa��!���(Q�=��d��NQ�lO�k>��͐,��dD����6�����f'�mP�¬��-�iI<�S��]6���B!y���]l�C���b*�u�B���Z7T�ސ-.�+�T��]q��Lz���B��H2L��A5�+��=[�by(K� �J�k\�=��^����W���6�k��}W��B�0B	�Ȳڦ��m��f�2|�Z3�^j4��^��S[�*~�<��T�Oļ4S�=��}� [�z���l/��
��J5�h`�0�[��Q��c�ܧ� �t����DfaȣF�ͼEqT ��T��P�<���b/ǯ?|X��v��mT\Bqc��-�A5g�x���YT��|Y��C����C��%����㟱T��oqx��r�R�SC���0�\܋��b��L�S�%Yi! p���B�ڦ�)+j;ț¢́��y�RՆ�j�:��,��!a
�Y9:���:��2���Pi��>��%N�e�!�R!F:C�+#�	��7����I��Ai�t8�T -J���usl	�'�0!'j ��J_�}i/ J��0bX�h��P�Ac4�AY���	;����9��/���۱T׶�熄̹�4Bg�J)����􌥃Th~^�@;4BQBdJ!���
����XhD��C���t 
3ާ��P��|B��V��T�f�Z��&6~5�́�%���8�!߀�h����$J�ob*Lϋ�Ӯ���
w�	u���I'w/�da5@��J,�ur��Q n#�Է]'O/y칫����W���[z���)Wk����:UJ^�b��tr'�~�i�ޔ.���7Û�M@;��9�3={����5�n'@��3��H�yeJ��N ����ӂt"�ߞ��&06����D
:��1��+�(y�8�w������8:т�Mɉ�������u-�n��U'��d]6�ub�PLv���rRp>ޏXT�D!���xk�Vb'R�s�S�Yq\7T'�L@���C�[�����z˪�.t��r����;��LvC����[��Wq>�}�M|�E5���zǑ��f�<LYn�Eщ|̪s�X/�!��K2ڋ�0H�>�I�s;�ʷ���	��f\_Y˱	��h��\]�6ښ�������EC.����1��L�⭇��b�u��(88��?ai^��EQ�U�**�� މ^<��R�60�3lٯwHY���E��R���O�����J>�c��q͟����Ћ��������k��di崔��A�4b�uFɳ�Ɍ��f)����~�*3�@7#��W9}i�&%p��(M4��Q�a� X[����0	:�I���)lR�Ʃ��ɧ�89���z�n��n�ܑ"�Ք��m��7�ѠR*@�l)ey��H��H
����O�7KT��2�%Qw},�����j�>�T���6)0��^�m��|��]g���I��P"ei�]�`'%�Ңx,���E�������8�RY�N�R;�HK�_)I�aa��r�J�4$���Ζ�W,�H��k���f��@��")���KdU[�茽v�oT[���0�Ia(D��oz8��@�tiV��ޠ�?1�����  uRCr�-�.�)���6���㗑%bTJi)cp-�2���#���R)�+���F���N��M�w�#����ݯ��tA�kL�Qn��<ۅ]i�׹�ı��/wW�k� 4~zG��}e���9���[��!Cj ��=�2ūs-zk��b��f�~��Q�ma<��%ܴg H:#���a����D�H3���A�J�zov�vP'�kGn(C�T�zâ5+���C�T��k����]���+���ֈ{�������:� �R�?��J��o�w�T���J�l�\�C�4�6G�h)��5/����*4K	����J	`󨨒�^�8A��Nް\�r��@�t����0ߩ��&1rS`����A�Tej�S���Q;>�8J@M��}U(���0�g�~��m�AT����M�:�(&0�q�AD ����4fr�yx��fWt�8AZ�K�샘�G����Dc��n7@�=Fu���>���A��B�Q�E9�Dd�9�s�N��� 2�r�0�(D�RQ���NW�tD
^��šx=t��a}w�?Ex9n���t��r��AD���g{�����J�d��xVw��4�. ���Z���PL=�6�.$LU*���L�fn��v-��G���:�Cq$J�|wD'�d����Z��[&:�=BG?	>� B���y�a�X�~��a��p�Ek���ī��j���'�
�� ���ë�6ijb�?�"?0d]xu�c*d��x©>%�&�-�΋����jQl��@D�c���Gi�
"N�9� G#��A<����Ԇ���v�C�Q����I�!��x��{��DL ����=_33��\� 	�c.���� z`�9H!��M[ݮK8犈`���?x�������|Nbحө�A$�֔�i���9�� ��z��A����Ň~��f0��o9��f� ���]��g��2I�fY����� ��f�l�n=x�>*"<��-G��7�����_"n�7��"^`��:5%݆A� Y�NG�r�� �@�/���Z�D$��5������$��čp�c7������P�꬯�	���~)�:_��e.9 s��VԈ�H�ٕ�|%>����x��(eƕݬ����ھ/ 6]��X�[m��E8������8�l��D��k��,_����o�~^��{\sW�W��g�����*[�����+�3N�|%�v\��Mr�IxbR�l�+���JΟ!*W�M���bb �x�߰�+    �Z��������ԭ����M�Q�W���V�r���)��+9��ffE�F�K�S���a#�H|%�����B�i|%�/��5���T���z���SQ&r���J>�Y+�����ŉ�~Ž��78a_��S3k��WaI�Wr��Nƍ_ɷ�cJE�z@^�'8��J^�
x�J>ݎS|ՙr����-_L�p�T���Fy�%9꼁��r���6���1��_���1�-Xxb&/L�*X;�,X�;�����
팿[k6�`��:ލ��" �Aa_�i��9��+���F0�·|%�ͽ��/����ьC:��+�p��3�z�r�l���+9���:$�6j�ȉ�LGڐ��|yB�����9*�o8�ə�cJw�r��q,c��9�J��|� (�=+��s���?\2Õ���	�Մo�<�����9!�J>��m��W��B�iؕ�&��[t�>������G�rи(����穰�䲿+����Ӧ9z��7@n�,��W��dQ �敡c��s���W}n�{xWb�y�wwe�r�2�R�c_��B�k�*�����9�jp3���j�3x	�xi�J>�����J>�b5ޙ����ՓLLn�z>S�{_ɻK\�S-�
���Z�����L��r�
�C��bN��k9�&]�҉���/�0�Z]YktR��_��߲����l��!1k�b�6�r��͝V��_��LZq���E��k9��:\�~b���mu�����>����E(�#S$l]a+�v-�Ȇ���<�r|�zq�Y��ע���6[U\� B�;kq
S���E�kׄT���}-b �R��Z�@ � �3�72|-� �H���.v�a���bx��XC^���CD�:����k�H��MHKҾ?��Vjl_� @j��z1����+w|9D�#?����{�,o-պ�������oy����EV)_�0��Sk_�D�}{"�qfUa�3x���9��ע��O�Z�@�ڴ.}-r H$�?`nD��������r�>�n��zu�;C��i2�O
3�¬V.�� ��aŞܟA�(C����o�HoH����~�V��&��i/��٢My_��	����n]�2���[�!�K�2;y,|m;_�=�g�u�-��c#Y��6��۹#��-�����Ȃ�}Y_wUF�ԫ�~"������,P�|�]�u��{�:_\�}.�!�\�#�fp�"�������w�/�}?Ce*l�~��OϪE���S;�O�^��7ѡ#��ע)K�l�.�)@�Wd���S#6�7q�3n���d!k@m%e���ݩ�xM��;FJVR������b&[�S��|=�OmT����<1E75���C�w�]di��;�Cjz�C��]���^r��a_]�%V3�OмO��!��|Э����al`��9���sغݱ�ܩ�S�����O�싦�}�vA��TmaG>o�T�CT)J�T����P����u8���Թ3�@Z�BR9��6h((���F
���Ft+6�m|#��a-��R��_nX�k���<e5��i��|#����$�K�ӊk�	;�Gx�Oܯ�gJ1߈iY�r�}Q���|�X;�$���G�X�RLM95nļp��=�`�F��C��qB��ZI��7��3��F��ru�7E<k�<��h����S7���\5��29��\��6���UJ����cZ��LqM���Z�	�r��F�*Ϗ�İ.w��=�$zu	]�X��r/!<���U����"j�(Eo������}0�b� E��|4���z��d����9��oĴΘo߈e%���`�S�oī.����S#FE�w��"�]����XbmD��m� �!j¤$��M� ��&�F,)���PD)����[/f��>�����5�; ��
!e�oD���oD��HQ�7"<t�j���a��4;����8M1�iDa>�s��A��~u��KTP4�㸺���و�D�N�hK)_NGv��JD�g�O�"*�2G���b&����߮��5:ۈ�,���I��(�%M �$S��IӷٚRD����%��t�b"���~�aC��� �,�k��><�]eݕ�zZB��}�}N��!�J4)�s4��r��m4&;�r@���j��w���՜��C�U��o�DS��+�T+�Q� �g���q��,M�{�l_:�`��×b�^|���-?`lK�������0�!�hh�:����+���D=]cy΢<��\��8��+)�`#�L�/�i!]�s��빎5�2�b�(�?��z����25�w>b�@��&~f�0тn+M{���(���E�w�Pj���WB�0	��;���;n�yH�&�N�{��
#���׺lm���4�1.ll��JC�e.�ǚ!W P�ѓ�2�0���^��E�s�f�[�t2�����
��h������1��}锬��vl��r��;�&[$9�V�&��x�yc�C6]��"J;���s><���[9�̐��y)��gD�rϗͱ�Ƀ��������ba���TP�����Ћv��?:��z(���&�Els�J`#N2���aXA�ȀQ�Q�\�((�G�E ���[�p>���^�7G�Q`=��j�e�~������ȏ,�و)��W�ڞH ����n����j���H��Xǘ��=��,�!�j�����L���k���rw� ��)!
�?G�������z?�� ����~+��f�������y%|�jt~n�����kjӧ�}hբ�HϘ�<�]�=�tW	����g�~�y:�C�����z�s놶����?�Ps%t���eW�Cmd:5�qȘ7A���z��٩⑓���j�t�
��X��83�K��K� `H�.�/$���'����f5>�T� ����C���.mG]�eP�4:��ҽ�Z��
�l���`%`J�Z��
+��Szȯ�@�pE@-�*on�6�#���lc���j�����%���])'���*��G|��Y�$?�z�V�e2�p��Ck��)��[v�&$Vc�qĈ/��U%0b	"�,�WQ�T�5�S����r�3�ػ�`�ly�|L��	J��=dS	n��d���>�P*��R/�C$U؈5r m��/���F�_��|�V_Qi5g�9��PD%�c#(�Cƀ����U@tQͨr� L!�nըM�m�fϱ��bqC�i��B��Q�I�C���uh�
�|:*�
�S�!h��'\�O� 4S~�f@MC�<P���_���$�ּ\�WP��@����"��g�'<�PaC��;ݸ F�Pruz�ϑ��؊WP��P?e!�F� ~�@��,L��!�
��60�r4P/��{H��0A~r>̥{i=�9�	�xh�Y�����JTP#�f�y�[*�w������ �@�E(���t��6��^k�N��X4�?��J6鞭!��c��fH�a� 4}Ɂ�ދD�u0T�h�;�K/R��1��x@,]�$��� ��K!�f��^^�,>d~r�gH�ķR��,�
������q�şmo�������n��In�a`(�r��.�t/�~vo>�˵�r�$9vQ?{5���ǇRg�#K�d|�����S?#��r���ȫC@_�\8��sr�L`d+�^��N?�z���t	�p3�8��o�+�=�^�Y�e�_�^�-ғM�!�L/Z|Vr�ow�D|Rr�o�p�0n��噵5��n<���@�d8b���Ş����
��/����S��b�Ҟ��.d<}�D	��g�~�������?nq� ��.Na�܏�V��E4��.	��J̿V#�ab�A�8Rp|�(���u��y���'���� ��Z����1���9�.P��A���}��=��r���Eo��
�<m��>���S�P��Yؤ ���n���l�w���hS��=���]f?�A��zq*�`��<���yڟ��v��� O�'K�A^V�Y���X9Y{m�9Xb#����Ê������߬W    ��A>��㊙�ws���j.��rUD�<�F� �K�5���qpTGȡr�뮄��� O��"x� oz��O{�A�4O��<�E��l�V�<�V����?�c�M2�>�wJ�Þa՟>�s^�E0 ��e�*�9/x0Mߒ\��~,V#��a=���A~�A<z��9�f�Lp����Ď�A��¢���>3T�� ?*'7�5��B?靖�V���Br�� ?ʄ�-�;-V�R�;J��kqE
��͟�~^Cy�y��\#�"���u�����-�,�I�8ۘd�,*��O��!��|���j���bO3�+�0��o�)[r�#G� ǜr��� ����I㤜�{,;�+�{V �jD|��œ{F��)�x����l,��r���Փ��^f��3lY�}��.�-����K�b����in0佟�Q�']}�tBW�U¥!����{��=����R����),ѳ��^��,�?)%���EJt`!cEyH��E;�S�	�nFk��7�lՂڼ(DN�Sv�3�Vai�����6ް����d ]@���/!~0T��m�g�AJ�V�qs�r���nn����9 ��r��y����'�չ�wη�}�F~ �~������u���@���F�V��C���r+��!Q�v���؆ 6��:�D>f�ߊy@?@Z`��4�(;�q%?^��K�y󭘆��`݊j���v�#>�V$#����V4C�%���XFZz����������te�CZ��i;�t�bJ�[����(ǯ8�V�+�d��(G,#����Wk��0x��7T��[q����N��V�#b�.��3�Cb�f�d+�������[��_m�j�׊i�=N��T+��w�{@�u�o�������ُ7G�? � �c&(�,���B�bZ����!Y��55��/M��I\N.�*7��A�"���$u8ق���Et`�uQE2p�wė�VV,&kԐ�*H��uh�q��h�x{�k��2�"�!�v��uZ5i�0ng�KE� Q��A�j��V�w�e���7Ɯl�S`�{��S^2��|�N-��0P��rk��� >��OiI��;~���p����E�@���$a�
u�:zW��r�$ju��b:�b�����ݩ��r��(z+ޯy�>Xy����iԫ��������|9z�z����Z��P�b~����X=��>��[[#�F$���TJC����崫�
�$d,a� ��:��O�ޠ�>����J�q���S��W����������k��`#ps�Ā�R�x�}sXaE�Z��b���A��yp趼ak&�/x�=����Z��d����!˴X��h����sJ@
^s�GH�T�$01v�ʏ(�z��1�7�ZO{�Vu^_�����L�eV0��w��_�AR��㸃��c���%�yķ�*6.���SD�7r�sj���aN��)��'�x�:�89�cXl� �}�p�;��a�^�l��Fo�S���F�G���2<��\���B�G"k'�y�������c�h6�{��z�I;�Ty��1ڔx��������[�pfFq��x�TP\������c@<Nl�f\T��dZ?����A��� i�$����;3�Ȗ���<	��_�����|r,Z�A'��v��HFɰ�.�T�2l��_v��`����<XUM��R�]a����H �-Z�٨��ݞY�|�V���c�ߵ֢8��K�6	�M�g����/�,���Z��}�Z3��T��JY��-�V�o]kM�n k��Cu]����LZK$i���q���}�Yk~�T���I�V{^:���rLKW]g��"_�_̠������R�_<���n����~\�����޼���]�7�� jz���Nn��f}�6����J�`�����0 ��n{��l�<���MU]�f����2��T����ۃL��;�`�ٞ^'/�bw�˿'l��n��YM��X:����"̸xy����H�-w��/&(O�s��%T�{�c�0�Y�^����w�ĩ��^+���厕�g<z9嗌�f�U9����?����A/��r�)P�^~�%�T��%v|�<{y�'�T�	�
(�֯Ⳕ���傓v / A��W:���#3�(%���Jɽ}/w����<1
?����^n��Nx�6�)>,����O���\dG-{y��\a���~�Շv(0��r�ICe�½���������z3�&�K�ˇ[BR��Y(!�k6w(�����V�jE��H�µ�^.S�ux �q��� 9�F�^^��1�7��ˉ���/ҙ� w C?vY�����v�I��U/��ƾ�^��M�)���F��QGBn�[V]��M��5ה,���ړ��� �'k�����xm[߽�z|MK��r�@�0;��թ���֙V�^`�u!��1�T��c�u�ƶ^��v�Qs�*�'7/��\}�fd�}�3�Q'3rd�#�m�6U}�	Vߦ�﹧ѷs�5��۹��6�6U��\U����������_OȪ�e�u ��6��ʺ����b��]�2\z/?}��_//}&a@��Ac���G����\rD��o/���^��p���ٚ�,����-#t.�ر0��$*��Q���I�����L��bww��t��&�a�Q�9���⁗^�Xq"5������?f9���g�8./�q/�|v��	�^�����(6V�>�>�7���͛������QRe}0���r�g�U����{�A��'�2���6 ;�vQ�PrV��1�� ����'��Aޖ��\�)�)�^q����x	>���2k�*�h����2��s��UN��q�S+�M2�~.���6!+��Z��P����o�S�5��Tume�0�u��ꎚs��:#|`CcV�7VW�~c�����'�	WBXCFu�X� 7�e�6�za�6\r�o9br�8)>�r��2��u�K|�#냼��8 �r��>��&gx28��~ܢ��j��A����DV��sW��AO�.�!���RLI�h�M��|}���/�\���3����𝳏��" ��|�`�?��I��e{��Rh ��ػ_�_-^_��w���< d�,<r�|�(8����>��	�ƺA�0/D��D8����J.m�r��\<��b��~M��t�,VjJ�9��)�,��y?���J����w�}$zR�~念�j�A��2�{��c��r{���)�AN�|u70�坯 �@�An���|�9&�����6�\�E�* ��'QCgu�_�<"uV���]�.*���������h�4�V{��A��(�DU�?�+� Gx�3a�A^Pz����Y{49�A.�{�'�D?�/2�װyEʴ{�K���@�veJ�~����p��AN2�����ݎ\���V�y�i�va�Ӵh���V9�sl����t�� �͈�*)u��7O��c�1T���t��H�����?ּ��oS1���ٖ"�BU��Ʃ�vF0�{��Z��.�HI�>T��m?Ŷ\�.�6,?Ƣ�9H^��\�jG��Yֹp��k�m�}��dS�����?�և�&��%{n�J�������4Tr�&�5r��0��FA��$~�*)C���hkL��V�؄x]`nư���P�)$y7�*�g��-e
C%GMNl5�J��/q�=�:�ۛ��2�,Tr�?O��rڿ\�}�J��l�h��/��?b/�r6�
�2-T�ٖn����s�7T�ؿ�ӗ*��_�?�8����r���m�rxXmn,cD���l>T��Eh\��q&*yj�Zc)$T�� &G=��Y��x���Y��懡��V��
/���EE�>*����b�R���6l���*�m��u���^�~�
� '��!$(֘�TB�<N�_��w�|Q����k���m�-g�|�m� q�P��|�a~a[�@%��	�Pɝ_ �c�U�Pɡ+� jghvr��_����9ʳ9ȯ����� �  ��1(��[�o��_��`�P��[���s�8/�� ��
�\��(o*9��Q�����7�}@��/ p��n�������%?_F7�J���O��P���K+wA�� oO@����C����}���Ä���T;���T��BI��@T��![S=�*a�ô�4u��/T�U�����J�}*
�\{�c�K���	˗_�6�㣨��y���r�p�l��8"4�Q���*� �G�7�]�����jy��WD�!s�PW�����֕��/���T�r�����9C�壑Ye�Nj9�Ehh���b�h-?]�{*�<�rׅ�vle�'�WHE�YT����~������GD� �j�����j���bS�:�n�7r���\�u���A�Qy�X�L,6*�u	�|42@�oP�*b7�#S-�LU9K�=s�Z^�����~��<�pgpf⡖WH��P�/'1��#+�
�u.�n��c.
��|t6�zY�:��S�g����jy�l�_��l���F���\#d-��~����ୱ�k=Uo�-z�[3SN�P{��v��j���=�G�y�T �������rꈐU�z+�s�'?]�9���\9���Z��
�(6*ުS�%j9m�����6r܄s�y���i�����C�C-��A�_-�m
~J���8Z�e��ck���aecUk�G�ُ7����u��y��g<K�%bT��0k#�m�A���6*�aevN]�+��W�\0<��T�	uW�^`ɪ������� �]�3�s`g���s�Į����sv��1��K�=�I�_wE��da~���O���zm�^tDm�^�S���Y1T�k�NH��!V|Ek���>�_���O��b�Q�na���A��]A�����P�>`[�Y�0%:u�}�
�Q�k�=;�sG�����e�P}����P���!๧�c歹� bQsWq�i9��������>�8"j���_S�w~3Eq�H�P��,��3��#�p�Ј�,��/v*��K��K�ϓ�EhĊN���_$moĒ�����>4�/�����xMN/(��CS�O��W�T�a�k���}V*M�{�����ν�N[̲-��Թ��W[$�M�{�"��Ƿ��S���|
P�!E ��K(o����BMY@��8!��;8��F3�����0�鏚��<34�}0{m����Oe?���@�2�g����t#�f����)]EhDψmY�P�}4�eT��`�Ј�Q��&���Y���E�gv�X-CY�`�+��Z1��!{����G����lߡ��0}[�if#�E���zC�OS�F$@zwD���sIQ��8/`�D�.M�?4�X�+���F��\ӈZ]�E�rPy�����֚~����ni���20���*���{��U�/Q�`�Ow�`>��
V�I;ӡ��\��AhĦ.׻8�Ɛ{w���������WVG�֪vhD�px������9�8�&���5�Q�{%{	����~}�f�Ј2���~g�Q0���8yG�=�-+���X�hD��?2
(4�>�3�׈��z���D$'��1��@pE��Ј��z�y��kgB�[�����ye#���s����e�+���%�	�8�GT�+|�hI�祱F��N��F,�N�,3��F,di,�,.b�_6&�	�F��#��{�o|��%��C#rq*ħ��F�bVܳ�G������9u^C#.��Xۈ?�7�)�c� Z;�0)�;%��ѩެ��ƀ6��ȇ�&�*�LnD��V�3s�*^;�������x-]���k-�8�7(��Z�+�3O����O���X���?��j�{�b�➳)j�8�,����=D����1���t�E<�ߵ
��q��|�^�3�as^s��"���w�?�[S��E������9z�|�ۖk�k�с3�)06P�U�
x��J
ݵ
8٫36!���ˈ&*�	QrV�w$��t�ٔ��;�1�Rv�v�j�Ƽ[ݮ�|�M�2��m8�b]��ػ+=�f�2�!-'����;Xԩ�׻�X��f:v"P��=�q�Ak���k�q5^!@�]���u~e���Ny-��	�1�	T�;��1���߻ �f{Zn��}�<m���=� z��;(c�r?Þ�g_h��|�AK�K��e�Z.���Ae$_����p��H����j,Գkm�z��&φ]�w�{�:�ڵ�ޏ�56��r�s߁�vf��eI}�6-#�O#�e���#g�T�k����@m�v�q���Ͽ��f���t��][��?xB����㋪��"xmq���⶚��@1���\�Bx�����C�*P/Zɳ����0��൑��i�l���
Q��5� �i��͚Q���"��g<�3/�Q�{�L�yՎ
x�VI�]�x$���w�2�E�s���u8m�`^T��)`]yHN�3	o
�u�_��^ݡO�d�P��%����x3�Ⱦ�ЖZ�d��;��w��^��c����̖P�lR�3���Ո��W��,ll�O���_œ��������JW�0
���	ͫ���ͫV����؅���l�R��䊠+�я�6�ac�� ѓ�c�,TJ�u�4Ӻ��A(>����pmw��T�#�����Nɼ���Ix)��-2J�u)o0F�� kXX�z��^��>�dg�;�+SW $9�QT�{V,���F��B.M'�o��|��5mj���νP7��W�I�j6��j_�s��p��Ă�����/~�m_&�=���rZ�_��j�2|ݳ׏�9`S�/��p4צ�oVX��Ć6l�4��Z�t�u��E���Nk{��|ݳ7;DE��A���z�ƽ��E��{(�9Έ"�rv�6.���1K$�%��#5�����&aI��@�f "�Mx��4�ѱ���K�&m�Q�o6��J'�4���`� 6�����bٳ]p�����2%.�/��蒣�/�� �c(��)�l}���5=Dφ�'�g����+�/��`��c��<�B%�:*�u�c;Ȕ��R�C�]g���F���>c:b��gXZ��j_�,m/1�b�b���4&����Xe�2��ѴUa����r�?j����~(s�wOU��B1~���Zd����+jֶ*�I����u9��}վ�ױr�Eg.�	%��g*"����^��M����._���z���w��:ڍ���G�b/��]o���`R��ۻ��6�__O�C���wȤ���Ew�g��G��=I1I3-/���n˫��ye̋�p�0�W�g�1V���A'��.!Z�V-�]a7%����v��zr�prER6*/MZ��,��l�mѯO.h�2k_\���ti�K|�d�hqQ�/��}N(�H����Wt����1�=8�i��x~ �KsKX}
"S������ĩ.W�v\�"S�ĖӃU���vA�꒷��!�l���prau�;�����?��/����;      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   N  x�]�=n�0�g�>AQ����8F�:��]�DH8R �Kot���b%=��)�{T�����'���?�������3]�+>�jE}���'�e%�<�3����Q��V�%,|?$?�l�.Q������2�悥0D|c��@�t�Qݳlh)Q8M��g�iR�{���������p���1��7{�*�� =*�ץ(�n;B%��� Aנ]
tލ{�(����q��j��y��V�Os�7��nx�k������	r.i�L��'�As	a;�f-���e�8��j�|�L��1�D�5X����[%�v۸�>��y�����      �      x������ � �      �      x������ � �      �      x�3�t
�t*J,������� %��      �      x������ � �      �      x�3�LL����4��\1z\\\ ?�B     