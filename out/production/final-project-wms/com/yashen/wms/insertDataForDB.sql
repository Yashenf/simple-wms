    USE wms;

    INSERT INTO product VALUES ('P0001' , 'gtx 1050 Ti' , 'msi - gtx' ,'vga', '299.99');
    INSERT INTO product VALUES ('P0002' , 'gtx 1060 ' , 'msi - gtx' ,'vga', '159.99');
    INSERT INTO product VALUES ('p0003' , 'gtx 960' , 'msi - gtx' ,'vga', '199.99');
    INSERT INTO product VALUES ('P0004' , 'Rtx 3060' , 'msi - gtx' ,'vga', '249.99');
    INSERT INTO product VALUES ('P0005' , 'gtx 2050' , 'msi - gtx' ,'vga', '299.99');
    INSERT INTO product VALUES ('P0006' , 'gtx 2050 Ti' , 'msi - gtx' ,'vga', '399.99');
    INSERT INTO product VALUES ('P0007' , 'gtx 970' , 'msi - gtx' ,'vga', '99.99');
    INSERT INTO product VALUES ('P0008' , 'gtx 1020' , 'msi - gtx' ,'vga', '199.99');
    INSERT INTO product VALUES ('P0009' , 'gtx 1090' , 'msi - gtx' ,'vga', '249.99');
    INSERT INTO product VALUES ('P0010' , 'gtx 2020' , 'msi - gtx' ,'vga', '259.99');
    INSERT INTO product VALUES ('P0011' , 'gtx 3040' , 'msi - gtx' ,'vga', '299.99');


    INSERT INTO product VALUE ('P0012','rog 22','msi-rog','laptop','99.99');
    INSERT INTO product VALUE ('P0013','rog i5','msi-rog','laptop','129.99');
    INSERT INTO product VALUE ('P0014','rog ryzen 5','msi-rog','laptop','139.99');
    INSERT INTO product VALUE ('P0015','rog i3','msi-rog','laptop','159.99');
    INSERT INTO product VALUE ('P0016','rog i7','msi-rog','laptop','169.99');
    INSERT INTO product VALUE ('P0017','rog i9','msi-rog','laptop','179.99');
    INSERT INTO product VALUE ('P0018','rog 21','msi-rog','laptop','189.99');
    INSERT INTO product VALUE ('P0019','rog 20','msi-rog','laptop','199.99');
    INSERT INTO product VALUE ('P0020','rog 19','msi-rog','laptop','109.99');
    INSERT INTO product VALUE ('P0021','rog mini','msi-rog','laptop','179.99');
    INSERT INTO product VALUE ('P0022','rog potable','msi-rog','laptop','149.99');
    INSERT INTO product VALUE ('P0023','rog-gamePro','msi-rog','laptop','159.99');




    INSERT INTO customer VALUES ('C002','Amal Perera','0119863574','304/A , main road , center city , alabama , usa , 15446','amal@gmail.com');
    INSERT INTO customer VALUES ('C001','Saman Fernando','0712365984','304/A , main road , center city , alabama , usa , 15446','saman@gmail.com');
    INSERT INTO customer VALUES ('C003','Kamal Mendis','0119845987','304/A , main road , center city , alabama , usa , 15446','kamal@gmail.com');
    INSERT INTO customer VALUES ('C004','Savindu Fernando','0119883574','304/A , main road , center city , alabama , usa , 15446','savindu@gmail.com');
    INSERT INTO customer VALUES ('C005','Kusal Perera','0119863577','304/A , main road , center city , alabama , usa , 15446','kusal@gmail.com');
    INSERT INTO customer VALUES ('C006','Angelo Mathews','01198635121','304/A , main road , center city , alabama , usa , 15446','angi@gmail.com');



    INSERT INTO `order` VALUES ('Ord00001',now(),'c002',45000,20);


    INSERT INTO order_details VALUES ('Ord00001','P0020',100,(SELECT price FROM product WHERE p_id = 'p0020')*qty);




    INSERT  INTO store VALUES('st001','main-3rd-floor');
    INSERT  INTO store VALUES('st002','main-2rd-floor');


    INSERT INTO product_stoke VALUES('P0001',1550,'st001');
    INSERT INTO product_stoke VALUES('P0002',1550,'st002');
    INSERT INTO product_stoke VALUES('P0003',1550,'st001');
    INSERT INTO product_stoke VALUES('P0004',1550,'st001');
    INSERT INTO product_stoke VALUES('P0005',1550,'st001');
    INSERT INTO product_stoke VALUES('P0006',1550,'st002');
    INSERT INTO product_stoke VALUES('P0007',1550,'st001');
    INSERT INTO product_stoke VALUES('P0008',1550,'st002');
    INSERT INTO product_stoke VALUES('P0009',1550,'st001');
    INSERT INTO product_stoke VALUES('P0010',1550,'st001');
    INSERT INTO product_stoke VALUES('P0011',1550,'st002');
    INSERT INTO product_stoke VALUES('P0012',1550,'st001');
    INSERT INTO product_stoke VALUES('P0013',1550,'st001');
    INSERT INTO product_stoke VALUES('P0014',1550,'st002');
    INSERT INTO product_stoke VALUES('P0015',1550,'st002');
    INSERT INTO product_stoke VALUES('P0016',1550,'st002');
    INSERT INTO product_stoke VALUES('P0017',1550,'st001');
    INSERT INTO product_stoke VALUES('P0018',1550,'st002');
    INSERT INTO product_stoke VALUES('P0019',1550,'st001');
    INSERT INTO product_stoke VALUES('P0020',1550,'st002');
    INSERT INTO product_stoke VALUES('P0021',1550,'st002');
    INSERT INTO product_stoke VALUES('P0022',1550,'st001');
    INSERT INTO product_stoke VALUES('P0023',1550,'st002');
    INSERT INTO product_stoke VALUES('P0023',1550,'st001');


    SELECT o_id FROM `order` ORDER BY (o_id) DESC LIMIT 1;

/*
    212-222-1010
(203)753-5678
(203) 753-5678
201-853-6909
800-289-2583
215 5551212
515555121200000
201.853.6909
201 853 6909
201-853-6909
201 853 6909
101.853.6909
1018536909
666-666-6669
(208) 555-1234
666-666-666
666 666 6666
666.666.6666
555 555 5555
222-222-2222
123-123-132
236-548-9888

*/