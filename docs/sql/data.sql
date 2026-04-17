INSERT INTO category (id, name) VALUES
    ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Technology'),
    ('b2c3d4e5-f6a7-8901-bcde-f12345678901', 'Science'),
    ('c3d4e5f6-a7b8-9012-cdef-123456789012', 'Travel'),
    ('d4e5f6a7-b8c9-0123-def0-234567890123', 'Food');

INSERT INTO post (id, title, content, created_date, category_id) VALUES
    (
        'e5f6a7b8-c9d0-1234-ef01-345678901234',
        'Introduction to Spring Boot',
        'Spring Boot makes it easy to create stand-alone, production-grade Spring applications that you can just run.',
        NOW(),
        'a1b2c3d4-e5f6-7890-abcd-ef1234567890'
    ),
    (
        'f6a7b8c9-d0e1-2345-f012-456789012345',
        'Getting Started with Angular',
        'Angular is a platform and framework for building single-page client applications using HTML and TypeScript.',
        NOW(),
        'a1b2c3d4-e5f6-7890-abcd-ef1234567890'
    ),
    (
        'a7b8c9d0-e1f2-3456-0123-567890123456',
        'Exploring the French Riviera',
        'The French Riviera is a beautiful destination along the Mediterranean coast, perfect for a summer vacation.',
        NOW(),
        'c3d4e5f6-a7b8-9012-cdef-123456789012'
    ),
    (
        'b8c9d0e1-f2a3-4567-1234-678901234567',
        'The Wonders of Space',
        'Space exploration has led to many incredible discoveries about our universe and the nature of existence.',
        NOW(),
        'b2c3d4e5-f6a7-8901-bcde-f12345678901'
    ),
    (
        'c9d0e1f2-a3b4-5678-2345-789012345678',
        'Best Pasta Recipes',
        'Italian cuisine is famous worldwide for its pasta dishes. Here are some of the best recipes to try at home.',
        NOW(),
        'd4e5f6a7-b8c9-0123-def0-234567890123'
    );
