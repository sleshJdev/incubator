create table order_main
(
    order_id varchar(256),
    store_id varchar(256),
    city_manager_id bigint,
    restaurant_id bigint,
    driver_id bigint,
    user_id bigint,

    final_status varchar(256),

    total_menu_cost double precision,

    total_profit double precision,
    net_profit double precision,
    costs double precision,

    total_restaurant_time double precision,

    restaurant_name varchar(256),
    restaurant_country varchar(256),
)