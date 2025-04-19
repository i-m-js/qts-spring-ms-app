insert into quote_users (
        username,
        email,
        password,
        is_active,
        user_source
    )
values ('admin', 'admin@qtsapp.com', '####', true, 0),
('user-1', 'user1@qtsapp.com', '####', true, 0),
    ('user-2', 'user2@qtsapp.com', '####', true, 0),
    ('user-3', 'user3@qtsapp.com', '####', true, 0);
-- insert into quote_authors (user_id, author_name)
-- values (1, NULL),
--     (2, NULL),
--     (NULL, 'Author-1');
-- Mock authors
-- insert into quote_authors (USER_ID, AUTHOR_NAME)
-- values (1, NULL),
--     (NULL, 'user-x'),
--     (NULL, 'Hyouka'),
--     (NULL, 'Winston Churchill'),
--     (NULL, 'Rene Descartes');
-- Mock quotes
insert into QUOTES (content, author, POSTED_BY_ID)
VALUES ('Ignorance is bliss.', NULL, 1),
    (
        'People do what they hate for money and use the money to do what they love.',
        NULL,
        1
    ),
    (
        'A masterpiece is born a masterpiece.',
        'Hyouka',
        1
    ),
    (
        'History will be kind to me, for I intend to write it.',
        'Winston Churchill',
        1
    ),
    ('I think, therefore I am.', 'Rene Descartes', 1);
-- insert into quotes (content, posted_by_id, author_id)
-- values ('Quote-1', 1, 1),
--     ('Quote-2', 2, 2),
--     ('Quote-3', 2, 2),
--     ('Quote-4', 1, 2),
--     ('Quote-5', 3, 1);