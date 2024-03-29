-- Drop da base de dados se existir
DROP DATABASE IF EXISTS flixcheck_db;

-- Criar o banco de dados se não existir
CREATE DATABASE IF NOT EXISTS flixcheck_db;

-- Usar o banco de dados flixcheck_db
USE flixcheck_db;
-- ALTER TABLE filme MODIFY COLUMN sinopse VARCHAR(1000); -- ou qualquer tamanho adequado

CREATE TABLE IF NOT EXISTS filme (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    autor VARCHAR(255),
    genero VARCHAR(255),
    data DATE,
    sinopse VARCHAR(1000),
    assistido BOOLEAN
);

-- Criar a tabela 'analises' se não existir
CREATE TABLE IF NOT EXISTS analises (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_filme INT,
    descricao VARCHAR(255),
    nota INT,
    FOREIGN KEY (id_filme)
        REFERENCES filme (id)
);

-- Apagar todos os dados das tabelas


-- Inserir dados na tabela 'filme'
INSERT INTO filme (nome, autor, genero, data, sinopse, assistido) VALUES
('Matrix', 'Lana Wachowski, Lilly Wachowski', 'Ficção Científica', '1999-03-31', 'Em um futuro distópico, Neo, um hacker, descobre a verdade sobre a realidade.', false),
('Titanic', 'James Cameron', 'Romance', '1997-11-18', 'Um romance épico entre Jack e Rose a bordo do Titanic, que enfrenta um trágico destino.', false),
('Inception', 'Christopher Nolan', 'Ação', '2010-07-08', 'Dominic Cobb, um ladrão de informações, entra nos sonhos para implantar ideias.', false),
('Forrest Gump', 'Robert Zemeckis', 'Drama', '1994-06-23', 'Forrest Gump vive uma vida extraordinária, influenciando eventos históricos sem perceber.', false),
('The Dark Knight', 'Christopher Nolan', 'Ação', '2008-07-14', 'Batman enfrenta o Coringa, um criminoso caótico, em uma batalha que testa seus limites morais.', false),
('Pulp Fiction', 'Quentin Tarantino', 'Crime', '1994-05-21', 'Uma trama envolvente que entrelaça várias histórias de criminosos em Los Angeles.', false);

-- Inserir dados na tabela 'analises'
INSERT INTO analises (id_filme, descricao, nota) VALUES
-- Matrix
(1, 'Um clássico da ficção científica.', 10),
(1, 'Efeitos visuais revolucionários.', 7),
(1, 'Narrativa intrigante.', 9),
-- Titanic
(2, 'Uma história de amor inesquecível.', 3),
(2, 'Efeitos visuais deslumbrantes.', 9),
(2, 'Final emocionante.', 9),
-- Inception
(3, 'Mind-blowing!', 5),
(3, 'Direção brilhante de Nolan.', 6),
(3, 'Conceito único.', 8),
-- Forrest Gump
(4, 'Tom Hanks brilha no papel principal.', 0),
(4, 'História cativante.', 2),
(4, 'Uma jornada emocional.', 7),
-- The Dark Knight
(5, 'Melhor filme de super-herói.', 1),
(5, 'Atuação icônica de Heath Ledger.', 0),
(5, 'Roteiro excepcional.', 4),
-- Pulp Fiction
(6, 'Obra-prima de Tarantino.', 5),
(6, 'Diálogos memoráveis.', 7),
(6, 'Estilo único.', 6);

SELECT * FROM filme;
SELECT * FROM analises;
