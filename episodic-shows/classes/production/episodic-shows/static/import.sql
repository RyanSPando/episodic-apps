insert into shows
values (NULL, 'Penny Dreadful');

SELECT @last := LAST_INSERT_ID();

insert into episodes(show_id, season_number, episode_number)
values(@last, 1, 1);

insert into episodes(show_id, season_number, episode_number)
values(@last, 1, 2);

insert into episodes(show_id, season_number, episode_number)
values(@last, 1, 3);

insert into shows
values (NULL, 'Black List');

SELECT @last := LAST_INSERT_ID();

insert into episodes(show_id, season_number, episode_number)
values(@last, 1, 1);

insert into episodes(show_id, season_number, episode_number)
values(@last, 1, 2);

insert into episodes(show_id, season_number, episode_number)
values(@last, 1, 3);