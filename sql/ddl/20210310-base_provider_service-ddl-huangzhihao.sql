
alter table base_provider_info_bak modify landmarks  varchar (1000);
alter table base_provider_info modify landmarks  varchar (1000);

alter table base_provider_info_bak modify portraiture  varchar (50);
alter table base_provider_info modify portraiture  varchar (50);

alter table base_provider_info_bak modify zipcode  varchar (50);
alter table base_provider_info modify zipcode  varchar (50);


alter table base_provider_service modify sellingpoint  varchar (1000);
alter table base_provider_service_bak modify sellingpoint  varchar (1000);

alter table base_provider_service modify foreignnote  varchar (1000);
alter table base_provider_service_bak modify foreignnote  varchar (1000);

alter table base_provider_service modify internalnote  varchar (1000);
alter table base_provider_service_bak modify internalnote  varchar (1000);


alter table base_doc_info modify remark  varchar (4000);










