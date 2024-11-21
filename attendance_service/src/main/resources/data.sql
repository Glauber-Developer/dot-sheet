-- CREATE TABLE attendances (
--     id SERIAL PRIMARY KEY,
--     userId INT NOT NULL,
--     status VARCHAR(255) NOT NULL,
--     date DATE NOT NULL,
--     FOREIGN KEY (userId) REFERENCES users(id)
-- ); 


-- INSERT INTO ATTENDANCE (userId, status, date) VALUES ('1', 'Present', '2024-11-01');
-- INSERT INTO ATTENDANCE (userId, status, date) VALUES ('2', 'Absent', '2024-11-01');