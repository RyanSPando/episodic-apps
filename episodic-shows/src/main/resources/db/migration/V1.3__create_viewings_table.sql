create table viewings (
    id bigint not null auto_increment primary key,
    user_id bigint not null,
    show_id bigint not null,
    episode_id bigint not null,
    updated_at DATETIME not null,
    timecode int not null,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES shows(id) ON DELETE CASCADE,
    FOREIGN KEY (episode_id) REFERENCES episodes(id) ON DELETE CASCADE
)