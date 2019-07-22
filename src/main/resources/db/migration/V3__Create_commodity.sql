create table commodity
(
	id int auto_increment,
	title varchar(50),
	descripition TEXT,
	price int,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator int,
	status varchar(10),
	comment_count int default 0,
	view_count int default 0,
	tag varchar(256),
	constraint commodity_pk
		primary key (id)
);

