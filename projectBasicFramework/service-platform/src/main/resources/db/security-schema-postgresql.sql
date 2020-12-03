/*
 Navicat Premium Data Transfer

 Source Server         : TSSA
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : SIDEDB

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 18/07/2019 08:59:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE public.oauth_access_token;
CREATE TABLE public.oauth_access_token (
  token_id character varying(256) COLLATE pg_catalog."default" NULL,
  token oid,
  authentication_id character varying(256) COLLATE pg_catalog."default" NOT NULL,
  user_name character varying(256) COLLATE pg_catalog."default" NULL,
  client_id character varying(256) COLLATE pg_catalog."default" NULL,
  authentication oid,
  refresh_token character varying(256) COLLATE pg_catalog."default" NULL,
  CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.oauth_access_token
    OWNER to postgres;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE public.oauth_approvals;
CREATE TABLE public.oauth_approvals (
  userId character varying(128) COLLATE pg_catalog."default" DEFAULT NULL,
  clientId character varying(128) COLLATE pg_catalog."default" DEFAULT NULL,
  scope character varying(128) COLLATE pg_catalog."default" DEFAULT NULL,
  status character varying(10) COLLATE pg_catalog."default" DEFAULT NULL,
  expiresAt timestamp NOT NULL DEFAULT (now()),
  lastModifiedAt timestamp NULL DEFAULT NULL
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.oauth_approvals
    OWNER to postgres;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE public.oauth_client_details;
CREATE TABLE public.oauth_client_details (
  client_id character varying(256) COLLATE pg_catalog."default" NOT NULL,
  resource_ids character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  client_secret character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  scope character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  authorized_grant_types character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  web_server_redirect_uri character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  authorities character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  access_token_validity bigint DEFAULT NULL,
  refresh_token_validity bigint DEFAULT NULL,
  additional_information character varying(4096) COLLATE pg_catalog."default" DEFAULT NULL,
  autoapprove character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.oauth_client_details
    OWNER to postgres;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE public.oauth_client_token;
CREATE TABLE public.oauth_client_token (
  token_id character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  token oid,
  authentication_id character varying(256) COLLATE pg_catalog."default" NOT NULL,
  user_name character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  client_id character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id)
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.oauth_client_token
    OWNER to postgres;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE public.oauth_code;
CREATE TABLE public.oauth_code (
  code character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  authentication oid
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.oauth_code
    OWNER to postgres;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE public.oauth_refresh_token;
CREATE TABLE public.oauth_refresh_token (
  token_id character varying(256) COLLATE pg_catalog."default" DEFAULT NULL,
  token oid,
  authentication oid
) WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.oauth_refresh_token
    OWNER to postgres;


SET FOREIGN_KEY_CHECKS = 1;
