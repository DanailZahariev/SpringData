CREATE PROCEDURE total_books_by_author(IN first_name varchar(50), IN last_name varchar(40)
,OUT book_count int)
BEGIN
    SET book_count = (SELECT COUNT(b.id)
                      FROM authors AS a
                               JOIN books AS b ON a.id = b.author_id
                      WHERE a.first_name = first_name
                        AND a.last_name = last_name
                      GROUP BY a.id);
END;