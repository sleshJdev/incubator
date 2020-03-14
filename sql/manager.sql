    create table orders
(
    id bigint,
    is_disabled bigint,
    is_available bigint,
    supported_order_methods bigint,
    billing_contact_name varchar(256),
    billing_contact_email varchar(256),
    billing_contact_phone varchar(256)
)