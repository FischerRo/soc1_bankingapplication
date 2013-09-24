# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  owner_id                  bigint,
  iban                      varchar(255),
  type_of                   varchar(8),
  constraint ck_account_type_of check (type_of in ('Checking','Savings','Loan')),
  constraint pk_account primary key (id))
;

create table customer (
  id                        bigint not null,
  last_name                 varchar(255),
  first_name                varchar(255),
  sex                       varchar(6),
  city                      varchar(255),
  age                       integer,
  age_group                 varchar(255),
  constraint ck_customer_sex check (sex in ('male','female')),
  constraint pk_customer primary key (id))
;

create table transaction (
  id                        bigint not null,
  account_from_id           bigint,
  account_to_id             bigint,
  purpose                   varchar(255),
  date                      timestamp,
  value                     decimal(15,2),
  currency                  varchar(3),
  category                  varchar(255),
  status                    varchar(9),
  constraint ck_transaction_currency check (currency in ('USD','EUR','GBP')),
  constraint ck_transaction_status check (status in ('Processed','Pending','Error')),
  constraint pk_transaction primary key (id))
;

create sequence account_seq;

create sequence customer_seq;

create sequence transaction_seq;

alter table account add constraint fk_account_owner_1 foreign key (owner_id) references customer (id) on delete restrict on update restrict;
create index ix_account_owner_1 on account (owner_id);
alter table transaction add constraint fk_transaction_accountFrom_2 foreign key (account_from_id) references account (id) on delete restrict on update restrict;
create index ix_transaction_accountFrom_2 on transaction (account_from_id);
alter table transaction add constraint fk_transaction_accountTo_3 foreign key (account_to_id) references account (id) on delete restrict on update restrict;
create index ix_transaction_accountTo_3 on transaction (account_to_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists customer;

drop table if exists transaction;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists customer_seq;

drop sequence if exists transaction_seq;

