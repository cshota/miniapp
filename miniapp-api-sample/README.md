# minefocus-base-miniapp-sample
### 起動前の設定

#### application-dev.yml 構成ファイルを編集する

1. データベース URL：{DATABASE_URL}
2. データベース アカウント：{DATABASE_USERNAME}
3. データベース パスワード：{DATABASE_PASSWORD}
4. ミニプログラム AccessKey：{MINIAPP_ACCESS_KEY}
5. ミニプログラム SecretKey：{MINIAPP_SECRET_KEY}

#### データベース (PostgreSQL) 構築テーブル SQL

```sql
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
```
