-- Criação da tabela
CREATE TABLE IF NOT EXISTS produtos (
                                        id CHAR(36) PRIMARY KEY DEFAULT (UUID()), -- UUID como ID
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    categoria VARCHAR(100),
    quantidade_estoque INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- Inserção de registros iniciais
INSERT INTO produtos (id, nome, descricao, preco, categoria, quantidade_estoque) VALUES
                                                                                     (UUID(), 'Produto 1', 'Descrição do Produto 1', 100.00, 'Categoria A', 10),
                                                                                     (UUID(), 'Produto 2', 'Descrição do Produto 2', 200.00, 'Categoria B', 20),
                                                                                     (UUID(), 'Produto 3', 'Descrição do Produto 3', 300.00, 'Categoria C', 30);