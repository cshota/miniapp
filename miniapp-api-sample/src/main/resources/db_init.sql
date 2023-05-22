CREATE TABLE public.user_info (
                                  user_id int8 NOT NULL,
                                  customer_info text NULL,
                                  create_at timestamp NULL,
                                  update_at timestamp NULL,
                                  access_token text NOT NULL,
                                  env_id int8 NULL,
                                  CONSTRAINT user_info_pk PRIMARY KEY (user_id)
);

CREATE TABLE public.message (
                                id int8 NOT NULL,
                                user_id int8 NOT NULL,
                                title text NULL,
                                message text NULL,
                                create_at timestamp NULL,
                                clickpagepath text NULL,
                                env_id int8 NULL,
                                CONSTRAINT message_pk PRIMARY KEY (id)
);