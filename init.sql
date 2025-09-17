
USE blog;

CREATE TABLE IF NOT EXISTS products (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100),
    description TEXT,
    image VARCHAR(255)
    );

INSERT INTO products (name, price, category, description, image) VALUES
                                                                     ('Laptop', 999.99, 'Electronics', 'High-performance laptop', 'laptop.jpg'),
                                                                     ('Smartphone', 499.99, 'Electronics', 'Latest model smartphone', 'phone.jpg'),
                                                                     ('Coffee Maker', 79.99, 'Home Appliances', 'Automatic coffee machine', 'coffee_maker.jpg');