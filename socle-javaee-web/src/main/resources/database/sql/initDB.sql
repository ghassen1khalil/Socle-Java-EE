/*==============================================================*/
/* Table : persistent_logins (Spring Security)                                               */
/*==============================================================*/


CREATE TABLE IF NOT EXISTS persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);


INSERT INTO `T_ROLE` (`ROL_ID`, `ROL_DESIGNATION`, `CREATION_DATE`) VALUES
(1, 'admin', NOW());

/*==============================================================*/
/*login : admin / password : admin00                                              */
/*==============================================================*/
INSERT INTO `T_USER` (`USR_ID`, `USR_ADRESS`, `USR_BIRTHDATE`, `USR_EMAIL`, `USR_ENABLED`, `USR_LASTNAME`, `USR_FIRSTNAME`, `USR_LOGIN`,`USR_PASSWORD`, `USR_PHONE`, `CREATION_DATE`) VALUES
(1, 'sfax', '1991-05-07', 'bilel.jallouli@sifast.com', 1, 'admin', 'admin','admin', '2d68dde902f736c71f906e14c6c0052c8ab4c962', '52351651', NOW());

INSERT INTO `TJ_USER_ROLE` (`USR_ID`, `ROL_ID`) VALUES
(1, 1);

              
