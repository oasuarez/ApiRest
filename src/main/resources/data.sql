insert into client (id,name,age,document,email,user,password) values (10021, 'Pedro Ramirez', 25, '1020657474','test@gmail.com','pramirez','abc1234');
insert into client (id,name,age,document,email,user,password) values (32122, 'Andres Perez', 25, '214242343423','test222@gmail.com','aperez','abc1234');
insert into product (id,NAME,DESCRIPTION,PRICE,BARCODE) values (2,'jarabe','medicamento',16800,'4655643646456353');
insert into product (id,NAME,DESCRIPTION,PRICE,BARCODE) values (1,'desodorante','aseo personal',12800,'53632683268328');
insert into product (id,NAME,DESCRIPTION,PRICE,BARCODE) values (3,'espuma afeitar','aseo personal',15000,'3465475765765');
insert into product (id,NAME,DESCRIPTION,PRICE,BARCODE) values (4,'suplemento vitaminico','alimentacion',20000,'46546547548797');
insert into product (id,NAME,DESCRIPTION,PRICE,BARCODE) values (5,'shampoo','aseo personal',13000,'43646547575757');
insert into store (id,name,schedule,address) values (1,'Principal','Mon-Fri','Calle 100');
insert into store (id,name,schedule,address) values (2,'Principal 2','Mon-Fri','Calle 127');
insert into store (id,name,schedule,address) values (3,'suba','Mon-Sun','Avenida boyaca 168');
insert into store_product (store_id,product_id) values (1,1);
insert into store_product (store_id,product_id) values (1,2);
insert into store_product (store_id,product_id) values (1,3);
insert into store_product (store_id,product_id) values (1,4);
insert into store_product (store_id,product_id) values (2,1);