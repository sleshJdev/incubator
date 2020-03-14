create table user
(
    uuid bigint default "identity"(5988815, 2, '1,1'::text),
    user_email varchar(512),
    user_name varchar(512),
)