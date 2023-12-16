create table if not exists brand_item (
    id                    bigint auto_increment primary key,
    category_type         varchar(10)           default 'TOP'               not null,
    brand_id         bigint                   default 0                 not null,
    price            int                default 0                 not null,
    created_by       varchar(255) default ''                not null,
    updated_by       varchar(255) default ''                not null,
    created_datetime datetime              default CURRENT_TIMESTAMP not null,
    updated_datetime datetime              default CURRENT_TIMESTAMP not null,
    deleted_datetime datetime                                        null
);

CREATE INDEX idx_bi_category ON brand_item (category_type);
CREATE INDEX idx_bi_brand_id ON brand_item (brand_id);

create table if not exists brand (
    id               bigint auto_increment primary key,
    name             varchar(10)           default ''                not null,
    created_by       varchar(255)          default ''                not null,
    updated_by       varchar(255)          default ''                not null,
    created_datetime datetime              default CURRENT_TIMESTAMP not null,
    updated_datetime datetime              default CURRENT_TIMESTAMP not null,
    deleted_datetime datetime                                        null
);