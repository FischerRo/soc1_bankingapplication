# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  owner                     varchar(255),
  iban                      varchar(255),
  type_of                   varchar(8),
  constraint ck_account_type_of check (type_of in ('Checking','Savings','Loan')),
  constraint pk_account primary key (id))
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

create sequence transaction_seq;

alter table transaction add constraint fk_transaction_accountFrom_1 foreign key (account_from_id) references account (id) on delete restrict on update restrict;
create index ix_transaction_accountFrom_1 on transaction (account_from_id);
alter table transaction add constraint fk_transaction_accountTo_2 foreign key (account_to_id) references account (id) on delete restrict on update restrict;
create index ix_transaction_accountTo_2 on transaction (account_to_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists transaction;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists transaction_seq;

