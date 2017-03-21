CREATE TABLE UserConnection(
  userId VARCHAR2(255 byte) not null,
  providerId VARCHAR2(255 byte) not null,
  providerUserId VARCHAR2(255 byte) not null,
  rank NUMBER(10,0) not null,
  displayName VARCHAR2(255 byte) null,
  profileUrl VARCHAR2(512 byte) null,
  imageUrl VARCHAR2(512 byte) null,
  accessToken VARCHAR2(512 byte) not null,
  secret VARCHAR2(512 byte) null,
  refreshToken VARCHAR2(512 byte) null,
  expireTime NUMBER(25, 0) null,
  CONSTRAINT UserConnection_PK PRIMARY KEY (userId, providerId, providerUserId),
  CONSTRAINT UserConnection_UNIQUE UNIQUE (userId, providerId, providerUserId)
);