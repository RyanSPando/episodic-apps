create table episodes (
    id bigint not null auto_increment primary key,
    show_id bigint not null,
    season_number int not null,
    episode_number int not null,
    FOREIGN KEY (show_id) REFERENCES shows(id) ON DELETE CASCADE
)