DROP DATABASE IF EXISTS wms;
CREATE DATABASE  IF NOT EXISTS wms;
USE wms;
CREATE TABLE IF NOT EXISTS customer(
    c_id VARCHAR (10),
    c_name VARCHAR (45),
    tp VARCHAR (14),
    address VARCHAR (500),
    email VARCHAR (65),
    CONSTRAINT PRIMARY KEY (c_id)
    );


CREATE TABLE IF NOT EXISTS `order`(
    o_id VARCHAR(10),
    o_date datetime ,
    customer VARCHAR (10),
    total DOUBLE ,
    discount DOUBLE ,
    CONSTRAINT PRIMARY KEY (o_id),
    CONSTRAINT FOREIGN KEY (customer) REFERENCES customer(c_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

/*create product table */
CREATE TABLE IF NOT EXISTS product(
    p_id VARCHAR (10),
    p_name VARCHAR (55),
    brand VARCHAR (25),
    typeOfProduct VARCHAR (55),
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (p_id)
    );

/* CREATE TABLE  ORDER DETAILS */

CREATE TABLE IF NOT EXISTS order_details(
    o_id VARCHAR (10),
    pid VARCHAR (10),
    qty INT ,
    row_total DOUBLE ,
    CONSTRAINT PRIMARY KEY (o_id,pid),
    CONSTRAINT FOREIGN KEY (o_id) REFERENCES `order`(o_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (pid) REFERENCES product(p_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

/* create store table */
CREATE TABLE IF NOT EXISTS store(
    store_id VARCHAR (5),
    loaction VARCHAR (20),
    CONSTRAINT PRIMARY KEY (store_id)
    );

/*CREATE TABLE PRODUCT STOKE*/

CREATE TABLE IF NOT EXISTS product_stoke(
    p_id VARCHAR (10),
    qty_on_hand INT,
    store_id VARCHAR (5),
    CONSTRAINT PRIMARY KEY(p_id,store_id),
    CONSTRAINT FOREIGN KEY (p_id) REFERENCES product(p_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (store_id) REFERENCES store(store_id) ON DELETE CASCADE ON UPDATE CASCADE
    );

/*CREATE TABLE package order items */

CREATE TABLE IF NOT EXISTS package_order_items(
    pack_id VARCHAR (10),
    p_id VARCHAR (10),
    o_id VARCHAR (10),
    CONSTRAINT PRIMARY KEY (pack_id)
    );

/* CREATE TABLE SHIPPING DETAILS*/

CREATE TABLE IF NOT EXISTS shipping_details(
    shipping_id VARCHAR (15),
    pack_id VARCHAR (10),
    o_id VARCHAR (10),
    shipping_date DATE ,
    refferel_no VARCHAR (55),
    shipping_agency VARCHAR (35),
    CONSTRAINT PRIMARY KEY (shipping_id),
    CONSTRAINT FOREIGN KEY (pack_id) REFERENCES package_order_items(pack_id) ON DELETE CASCADE ON UPDATE CASCADE
    );
CREATE TABLE  IF NOT EXISTS shipping_agency(
    agency_id VARCHAR (55),
    agency_name VARCHAR (55),
    email VARCHAR (55),
    tp VARCHAR (25),
    country VARCHAR (25),
    CONSTRAINT PRIMARY KEY (agency_id)
);

/*CREATE TABLE RETURN_ITEMS*/

CREATE TABLE IF NOT EXISTS return_items(
    return_id VARCHAR (10),
    c_id VARCHAR (10),
    p_id VARCHAR (10),
    qty INT,
    return_date DATE ,
    CONSTRAINT PRIMARY KEY (return_id),
    CONSTRAINT FOREIGN KEY (c_id) REFERENCES customer(c_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN  KEY (p_id) REFERENCES product(p_id) ON DELETE CASCADE ON UPDATE CASCADE
    );


/*create table for save login details.....*/
CREATE TABLE IF NOT EXISTS loggin_details(
    first_name VARCHAR (55),
    last_name VARCHAR (55),
    user_name VARCHAR (10),
    password VARCHAR (12),
    email VARCHAR (35),
    mobile VARCHAR (25),
    accountType VARCHAR (10),
    CONSTRAINT PRIMARY KEY (user_name)
    );