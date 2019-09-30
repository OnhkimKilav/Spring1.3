CREATE TABLE STORAGE(
    ID NUMBER,
    CONSTRAINT STORAGE_PK PRIMARY KEY (ID),
    FORMATS_SUPPORTED NVARCHAR2(100) NOT NULL,
    STORAGE_COUNTRY NVARCHAR2(50),
    STORAGE_MAX_SIZE NUMBER CHECK (STORAGE_MAX_SIZE > 0)
);

CREATE SEQUENCE STORAGE_ID_SEQ
MINVALUE 1
INCREMENT BY 1;