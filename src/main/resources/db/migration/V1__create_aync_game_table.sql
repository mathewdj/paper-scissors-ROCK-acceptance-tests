CREATE TABLE async_game (
    id UUID PRIMARY KEY,
    game_id        varchar(36) not null,
    player_attack  varchar(36) not null,
    player_id      varchar(36) not null,
    created_at_utc timestamp not null
);
